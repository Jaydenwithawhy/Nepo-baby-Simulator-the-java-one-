package com.jayorehla.nepobaby.game;

import com.jayorehla.nepobaby.game.Scenes.MainOffice;
import com.jme3.app.SimpleApplication;

public class Main extends SimpleApplication {


    @Override
    public void simpleInitApp() {
        setDisplayFps(false);
        setDisplayStatView(false);
        stateManager.attach(new MainOffice()); // dark, darker yet darker
    }
}
