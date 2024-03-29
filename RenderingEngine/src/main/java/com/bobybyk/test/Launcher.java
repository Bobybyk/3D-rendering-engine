package com.bobybyk.test;

import com.bobybyk.core.EngineManager;
import com.bobybyk.core.WindowManager;
import com.bobybyk.core.utils.Consts;
import org.lwjgl.Version;

public class Launcher {
    private static WindowManager window;
    private static TestGame game;
    public static void main(String[] args) {
        System.out.println(Version.getVersion());
        window = new WindowManager(Consts.TITLE, 1600, 900, false);
        game = new TestGame();
        EngineManager engine = new EngineManager();
        try {
            engine.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WindowManager getWindow() {
        return window;
    }

    public static TestGame getGame() {
        return game;
    }
}
