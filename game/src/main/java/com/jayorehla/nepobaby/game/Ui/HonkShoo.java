package com.jayorehla.nepobaby.game.Ui;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;

public class HonkShoo {

    Picture eyelids;

    AssetManager ass;

    public boolean closeEyes;
    public boolean OpenEyes;

    private int awakeState;
    private int sleepState;

    private boolean opening = false;

    double timer;

    public HonkShoo(AssetManager ass, Node guiNode, AppSettings settings){
        this.ass = ass;
       eyelids = new Picture("Eyeballs");
       eyelids.setImage(ass, "Textures/4.png", true);
       eyelids.setWidth(settings.getWidth());
       eyelids.setHeight(settings.getHeight());
       eyelids.setPosition(0, 0);

       eyelids.setCullHint(Spatial.CullHint.Always);
       guiNode.attachChild(eyelids);

    }

    public void Update(float tph){
         timer += tph;
        if (OpenEyes) opening = true;

        if(closeEyes){
            SleepState();
        }

        if(opening){
            AwakeState();
        }
    }

    private void SleepState(){
        awakeState = 0;
         switch (sleepState){
             case 0:
                 timer = 0;
                 sleepState = 1;
                 break;
             case 1:
                 eyelids.setCullHint(Spatial.CullHint.Inherit);
                 eyelids.setImage(ass, "Textures/4.png", true);
                 if(timer > ((double) 2 /12)) sleepState = 2;
                 break;
             case 2:
                 timer = 0;
                 sleepState = 3;
                 break;
             case 3:
                 eyelids.setImage(ass,"Textures/3.png", true);
                 if(timer > ((double) 2 /12)) sleepState = 4;
                 break;
             case 4:
                 timer = 0;
                 sleepState = 5;
                 break;
             case 5:
                 eyelids.setImage(ass,"Textures/2.png", true);
                 if(timer > ((double) 2 /12)) sleepState = 6;
                 break;
             case 6:
                 timer = 0;
                 sleepState = 7;
                 break;
             case 7:
                 eyelids.setImage(ass,"Textures/1.png", true);
                 break;
         }
    }



    private void AwakeState(){
        sleepState = 0;
        switch (awakeState){
            case 0:
                timer = 0;
                awakeState = 1;
                break;
            case 1:
                eyelids.setImage(ass, "Textures/1.png", true);
                if(timer > ((double) 2 /12)) awakeState = 2;
                break;
            case 2:
                timer = 0;
                awakeState = 3;
                break;
            case 3:
                eyelids.setImage(ass,"Textures/2.png", true);
                if(timer > ((double) 2 /12)) awakeState = 4;
                break;
            case 4:
                timer = 0;
                awakeState = 5;
                break;
            case 5:
                eyelids.setImage(ass,"Textures/3.png", true);
                if(timer > ((double) 2 /12)) awakeState = 6;
                break;
            case 6:
                timer = 0;
                awakeState = 7;
                break;
            case 7:
                eyelids.setImage(ass,"Textures/4.png", true);
                if(timer > ((double) 2 /12)) awakeState = 8;
                break;
            case 8:
                timer = 0;
                awakeState = 9;
                break;
            case 9:
                eyelids.setCullHint(Spatial.CullHint.Always);
                opening = false;
        }
    }


}
