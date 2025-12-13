package airstrike;

import java.io.File;

public class ResourceTest {
    public static void main(String[] args) {
        String[] resources = new String[] {
            "/airstrike/images/player.png",
            "/airstrike/images/enemy.png",
            "/airstrike/images/missile.png",
            "/airstrike/images/Drop.png",
            "/airstrike/images/background.png"
        };

        System.out.println("-- Classpath resource checks --");
        for (String r : resources) {
            java.io.InputStream is = ResourceTest.class.getResourceAsStream(r);
            System.out.println(r + " -> " + (is != null ? "FOUND" : "MISSING"));
            if (is != null) try { is.close(); } catch (Exception ignored) {}
        }

        System.out.println("\n-- Filesystem checks (relative to project root) --");
        String[] paths = new String[] {
            "src/airstrike/images/player.png",
            "src/airstrike/images/enemy.png",
            "src/airstrike/images/missile.png",
            "src/airstrike/images/Drop.png",
            "src/airstrike/images/background.png",
            "assets/images/player.png",
            "assets/images/enemy.png"
        };

        for (String p : paths) {
            File f = new File(p);
            System.out.println(p + " -> " + (f.exists() ? "exists" : "missing"));
        }
    }
}
