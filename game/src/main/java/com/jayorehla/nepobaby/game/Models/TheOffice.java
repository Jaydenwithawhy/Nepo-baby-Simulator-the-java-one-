package com.jayorehla.nepobaby.game.Models;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class TheOffice {
    // no, not the show silly head
    Spatial theOffice;
    AssetManager ass; // ehehehehehehehehehewhe

    public TheOffice(Node root, AssetManager ass){
        this.ass = ass;

        theOffice = ass.loadModel("Models/nepobaby.glb");

        root.attachChild(theOffice);
    }
}
