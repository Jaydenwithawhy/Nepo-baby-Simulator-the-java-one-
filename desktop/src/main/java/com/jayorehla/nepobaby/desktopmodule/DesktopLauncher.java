package com.jayorehla.nepobaby.desktopmodule;

import com.jayorehla.nepobaby.game.NepoBaby;
import com.jme3.system.AppSettings;

/**
 * Used to launch a jme application in desktop environment
 *
 */
public class DesktopLauncher {
    public static void main(String[] args) {
        final NepoBaby game = new NepoBaby();

        final AppSettings appSettings = new AppSettings(true);

        appSettings.setFullscreen(true); // thes an isuse where this chages the aspect ratio of the computer. a big no no
        // TODO: set the window size to be bigger

        game.setSettings(appSettings);
        game.setShowSettings(false); //Settings dialog not supported on mac
        game.start();
    }
}
