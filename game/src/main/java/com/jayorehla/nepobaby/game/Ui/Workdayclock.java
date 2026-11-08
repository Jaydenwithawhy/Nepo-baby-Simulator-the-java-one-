package com.jayorehla.nepobaby.game.Ui;

import com.atr.jme.font.TrueTypeFont;
import com.atr.jme.font.shape.TrueTypeNode;
import com.jayorehla.nepobaby.game.Mouskatools.Mainer;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.ui.Picture;

public class Workdayclock {

    // for the game we are going to try to make a 12 hour clock  but no promises beccase
    // we are working 9 to 5 (elite ball)

    AssetManager ass;


    TrueTypeNode node;

    TrueTypeNode goalnode;

    TrueTypeFont ttfFont;

    Node guinode;

    String lastRenderedText;

    Text text;

    String filteredTime;

    boolean lastcheck = true;

    Picture board;

    public Workdayclock(AssetManager ass, Text text, Node guinode){
        this.ass = ass;
        this.text = text;
        this.guinode = guinode;

        node = text.ttfFont.getText("",0, ColorRGBA.Blue);
        node.setLocalTranslation(0f, 1000, 1f);
        node.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);
        node.setCullHint(Spatial.CullHint.Inherit);
        guinode.attachChild(node);

        goalnode = text.ttfFont.getText("Sleep for 2.5 hours",0,ColorRGBA.Red);
        goalnode.setLocalTranslation(0,900,1f);
        goalnode.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);
        goalnode.setCullHint(Spatial.CullHint.Inherit);
        guinode.attachChild(goalnode);

        board = new Picture("the board of justice");
        board.setImage(ass, "Textures/ui nepo.png", true);
        board.setWidth(676/2);
        board.setHeight(586/2);
        board.setPosition(700,800);
        board.setCullHint(Spatial.CullHint.Inherit);
        guinode.attachChild(board);
    }

    public void PrintTime(boolean done){

        String AmPm;

        double hour = Math.floor(Mainer.workDayClock / 60);
        if(hour > 12){
            AmPm = "PM";
        }else{
            AmPm = "AM";
        }

        double wrappedHour = ((hour - 1) % 12) + 1;

        double minutePercent =  (Mainer.workDayClock / 60) - hour;
        double minute = minutePercent * 60;
        String finalminute = String.format("%02d",(int)minute);
//        System.out.println(minutePercent+"%");
//        System.out.println((int)wrappedHour+":"+finalminute+" "+AmPm);

        filteredTime = (int)wrappedHour+":"+finalminute+" "+AmPm;

        if (!filteredTime.equals(lastRenderedText)) {
            if (node != null) {
                guinode.detachChild(node);
            }

            node = text.ttfFontBigga.getText(filteredTime, 0, ColorRGBA.Blue);
            node.setLocalTranslation(780f, 950, 1f);
            node.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);
            node.setCullHint(Spatial.CullHint.Inherit);
            guinode.attachChild(node);
            node.setLocalScale(0.7f);
            lastRenderedText = finalminute;
        }

        if(done != lastcheck){
            if (goalnode != null){
                guinode.detachChild(goalnode);
                System.out.println("Hey its me gonnery");
                if(done) goalnode = text.ttfFont.getText("Sleep for 2.5 hours",0, ColorRGBA.Green);
                else goalnode = text.ttfFont.getText("Sleep for 2.5 hours", 0, ColorRGBA.Red);
                goalnode.setLocalTranslation(0,900f,1f);
                goalnode.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);
                goalnode.setCullHint(Spatial.CullHint.Inherit);
                guinode.attachChild(goalnode);
                lastcheck = done;
            }
        }

    }
}
