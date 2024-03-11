package com.bobybyk.test;

import com.bobybyk.core.WindowManager;
import org.lwjgl.Version;

public class Launcher {

    public static void main(String[] args) {
        System.out.println(Version.getVersion());
        WindowManager window = new WindowManager("ENGINE", 1600, 900, false);
        try {
            window.init();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        while(!window.windowShouldClose()) {
            window.update();
        }

        window.cleanup();
    }
}
