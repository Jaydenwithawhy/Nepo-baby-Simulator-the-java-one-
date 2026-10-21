package com.jayorehla.nepobaby.game.Models;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

public class Touchables {

    Box computer;
    Geometry computerG;
    Box trashCan;
    Geometry trashCanG;
    Box cabinet;
    Geometry cabinetG;

    public Touchables(Node root, AssetManager ass){

        Material mat = new Material(ass, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", new ColorRGBA(1f, 1f, 1f, 1f));
        mat.getAdditionalRenderState().setColorWrite(false);

        cabinet = new Box(0.9f,2,0.9f);
        cabinetG = new Geometry("Cabinet", cabinet);
        cabinetG.setMaterial(mat);

        cabinetG.setLocalTranslation(2.5135036f,1,2.052273f);

        root.attachChild(cabinetG);

        computer = new Box(1,1,1);
        computerG = new Geometry("Computer", computer);

        computerG.setLocalTranslation(0,0,0);

        trashCan = new Box(1,1,1);
        trashCanG = new Geometry("Trash Can", trashCan);

        trashCanG.setLocalTranslation(0,0,0);

    }
}
