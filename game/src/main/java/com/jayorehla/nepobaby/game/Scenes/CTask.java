package com.jayorehla.nepobaby.game.Scenes;

import com.jayorehla.nepobaby.game.Mouskatools.Mainer;
import com.jayorehla.nepobaby.game.Mouskatools.VoiceSyth;
import com.jayorehla.nepobaby.game.Ui.TaskText;
import com.jayorehla.nepobaby.game.Ui.Text;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

public class CTask extends BaseAppState {

    SimpleApplication sa;
    Node localMainRoot;
    Node localGuiRoot;
    TaskText taskText;
    Text text;
    VoiceSyth catVoice;

    double counter = 10;
    @Override
    protected void initialize(Application app) {
        sa = (SimpleApplication) app;
        localMainRoot = new Node("Mew Meowmew Mew");
        localGuiRoot = new Node("Woof..."); // hes trying
        text = new Text(sa.getAssetManager(), localGuiRoot);
        taskText = new TaskText(sa.getAssetManager(), localGuiRoot,text);

        sa.getViewPort().setBackgroundColor(ColorRGBA.White);
        sa.getGuiNode().attachChild(localGuiRoot);
        sa.getFlyByCamera().setEnabled(false);

        catVoice = new VoiceSyth();
        catVoice.SetVoice( 26, 72, 128, 128,false);
        sa.getStateManager().attach(catVoice); // a baseappstate in a baseappstate in a baseappstate!
        //thats like a baseappstae cubed. bro is John BaseAppState
    }

    @Override
    public void update(float tph){
        counter -= tph;
        if(Mainer.TaskTime){

        }
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
        localGuiRoot.removeFromParent();
        localMainRoot.removeFromParent();
        sa.getStateManager().detach(catVoice);
    }
}
