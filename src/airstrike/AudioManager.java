package airstrike;

// Stub AudioManager - no audio functionality (AWT/Swing only)
public class AudioManager {

    private static volatile boolean muted = false;

    public static void play(String resourceName) {
        // Audio disabled - using only AWT/Swing
    }

    public static void setMuted(boolean m) { muted = m; }

    public static boolean isMuted() { return muted; }

    public static void toggleMute() { muted = !muted; }
}
