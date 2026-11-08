package com.jayorehla.nepobaby.game.Ui;
import com.atr.jme.font.TrueTypeFont;
import com.atr.jme.font.asset.TrueTypeKey;
import com.atr.jme.font.asset.TrueTypeKeyBMP;
import com.atr.jme.font.asset.TrueTypeLoader;
import com.atr.jme.font.shape.TrueTypeNode;
import com.atr.jme.font.util.Style;
import com.jayorehla.nepobaby.game.Mouskatools.Mainer;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class SleepTimer {

    TrueTypeKey newTimeBaq;

    AssetManager ass;

    TrueTypeNode node;

    TrueTypeFont ttfFont;


    Node guinode;

    String lastRenderedText;


    double amountSlept;

    Text text;

    AudioNode honkshoo;

    public SleepTimer(AssetManager ass, Node guiroot, Text text ){
        this.ass = ass;
        this.guinode = guiroot;
        this.text = text;

//        ass.registerLoader(TrueTypeLoader.class,"ttf");
//        newTimeBaq = new TrueTypeKeyBMP("Interface/Times_New_Baq.ttf", Style.Plain, 25);
//        ttfFont = (TrueTypeFont) ass.loadAsset(newTimeBaq);

        node = text.ttfFont.getText("", 0, ColorRGBA.Red);
        node.setLocalTranslation(500f, 1000f, 1f);
        node.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);
        node.setCullHint(Spatial.CullHint.Always);
        guiroot.attachChild(node);

        honkshoo = new AudioNode(ass,"Sounds/snore(1).wav");
        honkshoo.setVolume(1);
        honkshoo.setPositional(false);
        guiroot.attachChild(honkshoo);
        honkshoo.setLooping(true);
    }

    public void PrintOut(boolean sleeping, int counter, float tph){
        if(sleeping && counter == 0) {
            Mainer.SleepTimer += tph;
            honkshoo.play();
        }else{
            honkshoo.stop();
        }
        String targetText = String.valueOf((int) Mainer.SleepTimer);


            if (!targetText.equals(lastRenderedText)) {
                if (node != null) {
                    guinode.detachChild(node);
                }

                node = text.ttfFont.getText(targetText, 0, ColorRGBA.Black);
                node.setLocalTranslation(950f, 950f, 1f);
                node.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);
                node.setLocalScale((float) ((float)(-0.0888889*(targetText.length()))+1.08889));
                // this forces it to be centred


                node.setCullHint(Spatial.CullHint.Inherit);

                guinode.attachChild(node);
                lastRenderedText = targetText;
            }

    }
}
