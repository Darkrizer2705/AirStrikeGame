package airstrike;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {

    private static class ClipData {
        AudioFormat format;
        byte[] data;
        ClipData(AudioFormat f, byte[] d) { format = f; data = d; }
    }

    private static final Map<String, ClipData> cache = new HashMap<>();
    private static volatile boolean muted = false;

    static {
        // preload known sounds (fails silently if missing)
        load("kill.wav");
        load("letGo.wav");
        load("pickDrop.wav");
        load("gameOver.wav");
        load("shoot.wav");
    }

    private static void load(String resourceName) {
        try (InputStream is = AudioManager.class.getResourceAsStream("/airstrike/audio/" + resourceName)) {
            if (is == null) return;
            try (AudioInputStream ais = AudioSystem.getAudioInputStream(is)) {
                AudioFormat format = ais.getFormat();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[4096];
                int r;
                while ((r = ais.read(buf)) != -1) baos.write(buf, 0, r);
                cache.put(resourceName, new ClipData(format, baos.toByteArray()));
            }
        } catch (Exception e) {
            // ignore load errors
            System.err.println("[Audio] failed to load " + resourceName + ": " + e.getMessage());
        }
    }

    public static void play(String resourceName) {
        if (muted) return;
        ClipData cd = cache.get(resourceName);
        if (cd == null) return;

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(cd.data);
            AudioInputStream ais = new AudioInputStream(bais, cd.format, cd.data.length / cd.format.getFrameSize());
            DataLine.Info info = new DataLine.Info(Clip.class, cd.format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            clip.start();
        } catch (Exception e) {
            System.err.println("[Audio] play error " + resourceName + ": " + e.getMessage());
        }
    }

    public static void setMuted(boolean m) { muted = m; }

    public static boolean isMuted() { return muted; }

    public static void toggleMute() { muted = !muted; }
}
