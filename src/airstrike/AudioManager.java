package airstrike;

import javax.sound.sampled.*;
import java.io.InputStream;

public class AudioManager {

    private static boolean muted = false;
    private static Clip backgroundClip = null;

    public static synchronized void toggleMute() {
        setMuted(!muted);
    }

    public static synchronized void setMuted(boolean m) {
        muted = m;
        // Update background clip volume if present
        if (backgroundClip != null) {
            try {
                if (backgroundClip.isOpen()) {
                    applyMuteToLine(backgroundClip, muted);
                }
            } catch (Exception e) {
                // ignore
            }
        }

        // Try to apply mute to available mixers (best-effort)
        try {
            Mixer.Info[] infos = AudioSystem.getMixerInfo();
            for (Mixer.Info info : infos) {
                try {
                    Mixer mixer = AudioSystem.getMixer(info);
                    Line.Info[] lines = mixer.getTargetLineInfo();
                    for (Line.Info li : lines) {
                        try {
                            Line line = mixer.getLine(li);
                            boolean opened = line.isOpen();
                            if (!opened) {
                                try { line.open(); } catch (Exception ex) { continue; }
                            }

                            applyMuteToLine(line, muted);

                            if (!opened) line.close();
                        } catch (Exception ex) {
                            // ignore
                        }
                    }
                } catch (Exception ex) {
                    // ignore
                }
            }
        } catch (Exception ex) {
            // ignore
        }
    }

    private static void applyMuteToLine(Line line, boolean m) {
        try {
            if (line.isControlSupported(BooleanControl.Type.MUTE)) {
                BooleanControl bc = (BooleanControl) line.getControl(BooleanControl.Type.MUTE);
                bc.setValue(m);
                return;
            }
        } catch (Exception e) {}

        try {
            if (line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl fc = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
                if (m) fc.setValue(fc.getMinimum());
                else fc.setValue(0.0f);
                return;
            }
        } catch (Exception e) {}
    }

    public static boolean isMuted() { return muted; }

    public static void playSound(String resourcePath) {
        if (muted) return;
        try (InputStream is = AudioManager.class.getResourceAsStream(resourcePath)) {
            if (is == null) return;
            try (AudioInputStream ais = AudioSystem.getAudioInputStream(is)) {
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
            }
        } catch (Exception e) {
            // ignore playback errors
        }
    }

    public static void playLoop(String resourcePath) {
        stopLoop();
        if (muted) return;
        try (InputStream is = AudioManager.class.getResourceAsStream(resourcePath)) {
            if (is == null) return;
            AudioInputStream ais = AudioSystem.getAudioInputStream(is);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(ais);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            applyMuteToLine(backgroundClip, muted);
        } catch (Exception e) {
            backgroundClip = null;
        }
    }

    public static void stopLoop() {
        try {
            if (backgroundClip != null) {
                backgroundClip.stop();
                backgroundClip.close();
                backgroundClip = null;
            }
        } catch (Exception e) {
            // ignore
        }
    }
}
