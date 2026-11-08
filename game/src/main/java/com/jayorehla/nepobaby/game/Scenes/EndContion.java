package com.jayorehla.nepobaby.game.Scenes;

import com.atr.jme.font.shape.TrueTypeNode;
import com.jayorehla.nepobaby.game.Mouskatools.Mainer;
import com.jayorehla.nepobaby.game.Ui.Text;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.audio.AudioNode;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class EndContion extends BaseAppState {

    // the reason why this is not geting their own class is becase it is so insifigigant

    Text text;

    TrueTypeNode stateNode;
    TrueTypeNode tryagianNode;

    SimpleApplication sa;

    Node guiNode;
    boolean checkonce = false;

    @Override
    protected void initialize(Application app) {
        sa = (SimpleApplication) app;
        guiNode = new Node("Im the node that disides if your gamer owo");
        text = new Text(sa.getAssetManager(), guiNode);
        stateNode = text.ttfFontBigga.getText("",0,ColorRGBA.White);
        stateNode.setLocalTranslation(500,500,1f);
        stateNode.setQueueBucket(RenderQueue.Bucket.Gui); // aperently this works to lol
        stateNode.setCullHint(Spatial.CullHint.Inherit);
        guiNode.attachChild(stateNode);
        sa.getViewPort().setBackgroundColor(ColorRGBA.Black);

        tryagianNode = text.ttfFont.getText("",0,ColorRGBA.White);
        tryagianNode.setLocalTranslation(500,400,1f);
        tryagianNode.setQueueBucket(RenderQueue.Bucket.Gui);
        tryagianNode.setCullHint(Spatial.CullHint.Inherit);
        guiNode.attachChild(tryagianNode);

        sa.getGuiNode().attachChild(guiNode);

    }

    @Override
    public void update(float tph){

    }

    @Override
    protected void cleanup(Application app) {

    }

    @Override
    protected void onEnable() {
        if(Mainer.SleepTimer > 144 && Mainer.Satifaction >= 0 && !checkonce){
            if(stateNode != null) guiNode.detachChild(stateNode);
            if(tryagianNode != null) guiNode.detachChild(tryagianNode);

            System.out.println("you won dummy");

            stateNode = text.ttfFontBigga.getText("You Win!1!!",0,ColorRGBA.White);
            stateNode.setLocalTranslation(500,500,1f);
            stateNode.setQueueBucket(RenderQueue.Bucket.Gui);
            stateNode.setCullHint(Spatial.CullHint.Inherit);
            guiNode.attachChild(stateNode);

            tryagianNode = text.ttfFont.getText("Restart to pay agian!",0,ColorRGBA.White);
            tryagianNode.setLocalTranslation(500,500,1f);
            tryagianNode.setQueueBucket(RenderQueue.Bucket.Gui);
            tryagianNode.setCullHint(Spatial.CullHint.Inherit);
            guiNode.attachChild(tryagianNode);
            checkonce = true;
        }else if (Mainer.SleepTimer < 144 || Mainer.Satifaction <= 0 && !checkonce){
            if(stateNode != null) guiNode.detachChild(stateNode);
            if(tryagianNode != null) guiNode.detachChild(tryagianNode);

            System.out.println("Du hast das große Imperium, das dieses Unternehmen darstellt, im Stich gelassen! Durch dein Versagen sind wir gegenüber unseren Feinden nun verwundbar! Ein solches Fehlverhalten werden wir nicht dulden.");

            stateNode = text.ttfFontBigga.getText("You lost...",0,ColorRGBA.White);
            stateNode.setLocalTranslation(500,500,1f);
            stateNode.setQueueBucket(RenderQueue.Bucket.Gui);
            stateNode.setCullHint(Spatial.CullHint.Inherit);
            guiNode.attachChild(stateNode);

            tryagianNode = text.ttfFont.getText("Restart to pay agian!",0,ColorRGBA.White);
            tryagianNode.setLocalTranslation(500,500,1f);
            tryagianNode.setQueueBucket(RenderQueue.Bucket.Gui);
            tryagianNode.setCullHint(Spatial.CullHint.Inherit);
            guiNode.attachChild(tryagianNode);
            checkonce = true;
        }

        sa.getGuiNode().attachChild(guiNode);
    }

    @Override
    protected void onDisable() {

    }
}
