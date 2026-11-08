package com.jayorehla.nepobaby.game.Ui;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;

public class CTaskUI {

    Picture Cat;
    Picture SpeachBubble;

    AssetManager ass;
    Node guiNode;

    double timmer;
    public enum CatState{
        headpats,
        tailpats,
        notouchy,
        theCatsAreGoingToOverThrowTheGovermentYouNeedToStopThemPlease,
    }

    public CTaskUI(AssetManager ass, Node guiNode){
        this.guiNode = guiNode;
        Cat = new Picture("Meow mew");
        Cat.setImage(ass,"Textures/pnging my g.png",true);
        Cat.setWidth(400f);
        Cat.setHeight(200f);
        Cat.setLocalTranslation(500,500,1);
        guiNode.attachChild(Cat);
    }

    public void update(float tph){

    }
}
