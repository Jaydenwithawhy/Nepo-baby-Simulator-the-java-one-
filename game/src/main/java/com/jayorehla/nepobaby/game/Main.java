package com.jayorehla.nepobaby.game;

import com.jayorehla.nepobaby.game.Control.MickeyMouse;
import com.jayorehla.nepobaby.game.Scenes.MainOffice;
import com.jayorehla.nepobaby.game.Mouskatools.Mainer;
import com.jayorehla.nepobaby.game.Scenes.RTask;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;

public class Main extends SimpleApplication {

    private MainOffice mainOffice;
    private BaseAppState baseAppStateNamedDesire;

    MickeyMouse mickeyMouse;

    @Override
    public void simpleInitApp() {
        setDisplayFps(false);
        setDisplayStatView(false);
        mickeyMouse = new MickeyMouse(inputManager);

        mainOffice = new MainOffice();
        stateManager.attach(mainOffice);
    }

    @Override
    public void simpleUpdate(float tpf) {
        if (Mainer.TaskTime) {
            if (baseAppStateNamedDesire == null) {
                BaseAppState task = createTask(Mainer.Task);
                if (task == null) return;
                mainOffice.setEnabled(false);
                baseAppStateNamedDesire = task;
                stateManager.attach(baseAppStateNamedDesire);
            }
        } else if (baseAppStateNamedDesire != null) {
            stateManager.detach(baseAppStateNamedDesire);
            baseAppStateNamedDesire = null;
            mainOffice.setEnabled(true);
        }
    }

    private BaseAppState createTask(String name) {
        switch (name) {
            case "Email":
            case "ForkMoneyOver":
            case "PetTheCat":
            case "CleanUpMess":
            case "SpreadDemSheets":
            case "RebootComputa":
                return new RTask();   // TODO: a different class per task
        }
        return null;
    }
}
