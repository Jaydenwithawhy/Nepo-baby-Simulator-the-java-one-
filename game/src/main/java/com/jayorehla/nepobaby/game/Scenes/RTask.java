package com.jayorehla.nepobaby.game.Scenes;

import com.jayorehla.nepobaby.game.Main;
import com.jayorehla.nepobaby.game.Mouskatools.Mainer;
import com.jayorehla.nepobaby.game.Ui.RTaskUI;
import com.jayorehla.nepobaby.game.Ui.TaskText;
import com.jayorehla.nepobaby.game.Ui.Text;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;

public class RTask extends BaseAppState {

    SimpleApplication sa;
    Text text;

    TaskText taskText;

    RTaskUI rTaskUI;

    double counter = 10;

    Node localguiroot;

    @Override
    protected void initialize(Application app) {
        localguiroot = new Node("Im the Rtask root");
        sa  = (SimpleApplication) app;
        sa.getViewPort().setBackgroundColor(com.jme3.math.ColorRGBA.White);
        text = new Text(sa.getAssetManager(),localguiroot);
        rTaskUI = new RTaskUI(localguiroot,sa.getAssetManager(),sa.getInputManager());

        taskText = new TaskText(sa.getAssetManager(), localguiroot, text);


        sa.getGuiNode().attachChild(localguiroot);

        sa.getFlyByCamera().setEnabled(false);
    }

    @Override
    public void update(float tph){
        taskText.RTaskText(Mainer.TaskTime,tph);
        if(Mainer.TaskTime){
            rTaskUI.update(tph);
        }

        counter -= tph;
        if(counter <= 0){
            Mainer.TaskTime = false;
            Mainer.doneTask = true;
        }
    }

    @Override
    protected void cleanup(Application app) {

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {
//        localMainroot.removeFromParent();
        localguiroot.removeFromParent();
    }
}
