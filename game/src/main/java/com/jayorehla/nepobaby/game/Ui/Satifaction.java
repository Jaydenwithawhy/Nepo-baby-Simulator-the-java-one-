package com.jayorehla.nepobaby.game.Ui;

import com.atr.jme.font.TrueTypeFont;
import com.atr.jme.font.asset.TrueTypeKey;
import com.atr.jme.font.asset.TrueTypeKeyBMP;
import com.atr.jme.font.asset.TrueTypeLoader;
import com.atr.jme.font.shape.TrueTypeNode;
import com.atr.jme.font.util.Style;
import com.jayorehla.nepobaby.game.Main;
import com.jayorehla.nepobaby.game.Mouskatools.Mainer;
import com.jme3.asset.AssetManager;
import com.jme3.input.KeyInput;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.ui.Picture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Satifaction {
        TrueTypeKey newTimeBaq;

        AssetManager ass;

        TrueTypeNode node;

        TrueTypeFont ttfFont;

        Node guinode;

        String lastRenderedText;

        Picture theSign;



        Text text;

        public Satifaction(AssetManager ass,Node guiroot, Text text ){
            this.ass = ass;
            this.guinode = guiroot;
            this.text = text;

//            ass.registerLoader(TrueTypeLoader.class,"ttf");
//            newTimeBaq = new TrueTypeKeyBMP("Interface/Times_New_Baq.ttf", Style.Plain, 25);
//            ttfFont = (TrueTypeFont) ass.loadAsset(newTimeBaq);

            node = text.ttfFontBigga.getText("", 0, ColorRGBA.Red);
            node.setLocalTranslation(500f, 950f, 1f);
            node.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);
            node.setCullHint(Spatial.CullHint.Inherit);
            guiroot.attachChild(node);

            theSign = new Picture("The satifation sign");
            theSign.setImage(ass, "Textures/ui more nepo.png", true);
            theSign.setWidth(444/2);
            theSign.setHeight(587/2);
            theSign.setPosition(400,800);
            theSign.setCullHint(Spatial.CullHint.Inherit);
            guiroot.attachChild(theSign);
        }

    public void PrintOut(int docker, boolean goodboy, boolean goobooypoints){
        String targetText = String.valueOf((int) Mainer.Satifaction);

            Mainer.Satifaction += docker;


            if(Mainer.pointsGotenFromTask){
               Mainer.Satifaction += Mainer.taskSatifactionOmpf;
                Mainer.pointsGotenFromTask = false;
            }



        if (!targetText.equals(lastRenderedText)) {
            if (node != null) {
                guinode.detachChild(node);
            }
            node = text.ttfFontBigga.getText(targetText, 0, ColorRGBA.Red);
            node.setLocalTranslation(500f, 950f, 1f);
            node.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);
            node.setLocalScale((float) ((float)(-0.0888889*(targetText.length()))+1.08889));

            // oh linear regression, my linear regression


            node.setCullHint(Spatial.CullHint.Inherit);

            guinode.attachChild(node);
            lastRenderedText = targetText;
        }

    }
}


