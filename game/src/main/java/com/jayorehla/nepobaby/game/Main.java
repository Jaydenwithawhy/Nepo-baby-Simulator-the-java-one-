package com.jayorehla.nepobaby.game;

import com.jayorehla.nepobaby.game.Control.MickeyMouse;
import com.jayorehla.nepobaby.game.Scenes.CTask;
import com.jayorehla.nepobaby.game.Scenes.EndContion;
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
        Mainer.workDayClock += tpf;
        if(Mainer.workDayClock <= 480 + 540) {
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
        }else{
          if(Mainer.TaskTime){
              if (baseAppStateNamedDesire == null && stateManager.hasState(baseAppStateNamedDesire)) {
                  stateManager.detach(baseAppStateNamedDesire);
                  stateManager.attach(new EndContion());
              }
          }else{
              if(stateManager.hasState(mainOffice)) {
                  stateManager.detach(mainOffice);
                  stateManager.attach(new EndContion());
              }
          }
        }
    }

    private BaseAppState createTask(String name) {
        switch (name) {
            case "Email":
                return new RTask();
            case "ForkMoneyOver":
                return new RTask();
            case "PetTheCat":
                return new CTask();
            case "CleanUpMess":
                return new RTask();
            case "SpreadDemSheets":
                return new RTask();
            case "RebootComputa":
                return new RTask();   // TODO: a different class per task
        }
        return null;
    }
}
