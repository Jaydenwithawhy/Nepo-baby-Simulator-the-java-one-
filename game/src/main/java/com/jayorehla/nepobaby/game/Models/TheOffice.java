package com.jayorehla.nepobaby.game.Models;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.*;
import com.jme3.texture.Texture;
import com.jme3.util.BufferUtils;

public class TheOffice {
    // no, not the show silly head
    Spatial theOffice;
    AssetManager ass; // ehehehehehehehehehewhe

    public TheOffice(Node root, AssetManager ass){
        this.ass = ass;

        theOffice = ass.loadModel("Models/nepobaby.glb");

        // this fancy pants thingermoboble allows us to render the gemontery inside the building because that's how i modeled it

        theOffice.depthFirstTraversal(
                new SceneGraphVisitor() {
                    @Override
                    public void visit(Spatial spaty) {
                        if(spaty instanceof Geometry){
                            Geometry geo = (Geometry) spaty;
                            Material oldMat = geo.getMaterial(); // im old gah ha ha

                            // we are a classic lighting material becase the new stuff goes over my head
                            Material newMat = new Material(ass, "Common/MatDefs/Light/Lighting.j3md");


                            if (oldMat.getParam("BaseColorMap") != null) {
                                newMat.setTexture("DiffuseMap", (Texture) oldMat.getParam("BaseColorMap").getValue());
                            } else if (oldMat.getParam("BaseColor") != null) {
                                newMat.setColor("Diffuse", (ColorRGBA) oldMat.getParam("BaseColor").getValue());
                                newMat.setBoolean("UseMaterialColors", true);
                            }

                            geo.setMaterial(newMat);
                            geo.getMaterial().getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
                        }
                    }
                }
        );

        root.attachChild(theOffice);
    }
}
