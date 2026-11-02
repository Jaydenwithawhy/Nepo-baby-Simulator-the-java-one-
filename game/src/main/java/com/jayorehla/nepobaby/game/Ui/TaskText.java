package com.jayorehla.nepobaby.game.Ui;

import com.atr.jme.font.TrueTypeFont;
import com.atr.jme.font.asset.TrueTypeKey;
import com.atr.jme.font.shape.TrueTypeNode;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class TaskText {

    TrueTypeKey newTimeBaq;

    AssetManager ass;

    TrueTypeNode node;

    TrueTypeNode timerNode;

    TrueTypeFont ttfFont;

    Node guinode;

    String lastRenderedText;

    String lastRenderedTimer;

    Text text;

     public float taskCountdown = 100;

    public TaskText(AssetManager ass, Node guinode, Text text){
        this.ass = ass;
        this.guinode = guinode;
        this.text = text;

        node = text.ttfFontBigga.getText("", 0, ColorRGBA.Blue);
        node.setLocalTranslation(500f, 500f, 1f);
        node.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);
        node.setCullHint(Spatial.CullHint.Always);
        guinode.attachChild(node);

        timerNode = text.ttfFontBigga.getText("", 0, ColorRGBA.Blue);
        timerNode.setLocalTranslation(500f, 900f, 1f);
        timerNode.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);
        timerNode.setCullHint(Spatial.CullHint.Always);
        guinode.attachChild(timerNode);
    }

    public void LTaskText(){}

    public void RTaskText(boolean started, float tph, double taskCountdown){

        String targetTextOne = "Collect The Money!!!";
        String targetTextTwo = String.valueOf((int) taskCountdown);


        if (!targetTextOne.equals(lastRenderedText)) {
            if (node != null) {
                guinode.detachChild(node);
            }

            node = text.ttfFontBigga.getText(targetTextOne, 0, ColorRGBA.Blue);
            node.setLocalTranslation(500f, 500f, 1f);
            node.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);


            node.setCullHint(Spatial.CullHint.Inherit);

            guinode.attachChild(node);
            lastRenderedText = targetTextOne;
        }


        if (!targetTextTwo.equals(lastRenderedTimer)) {
            if (timerNode != null) {
                guinode.detachChild(timerNode);
            }

            timerNode = text.ttfFontBigga.getText(targetTextTwo, 0, ColorRGBA.Blue);
            timerNode.setLocalTranslation(500f, 900f, 1f);
            timerNode.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);


            timerNode.setCullHint(Spatial.CullHint.Inherit);

            guinode.attachChild(timerNode);
            lastRenderedText = targetTextTwo;
        }
    }


}
