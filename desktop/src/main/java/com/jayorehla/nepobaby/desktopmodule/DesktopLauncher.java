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

        appSettings.setFullscreen(false); // thes an isuse where this chages the aspect ratio of the computer. a big no no
        appSettings.setGraphicsDebug(false);
        appSettings.setHeight(1080);
        appSettings.setWidth(1080); //square
        // okay it kinda works but it need to be exact or esle the game screws wiht you acpect ratio
        // for now ill leave it like this

        game.setSettings(appSettings);
        game.setShowSettings(false); //Settings dialog not supported on mac
        game.start();
    }
}
