package com.jayorehla.nepobaby.game;

import com.jayorehla.nepobaby.game.Control.Cam;
import com.jayorehla.nepobaby.game.Control.MickeyMouse;
import com.jayorehla.nepobaby.game.Models.TheOffice;
import com.jme3.app.SimpleApplication;
import com.jme3.input.controls.InputListener;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.app.state.AppState;

/**
 * The JMonkeyEngine game entry, you should only do initializations for your game here, game logic is handled by
 * Custom states {@link com.jme3.app.state.BaseAppState}, Custom controls {@link com.jme3.scene.control.AbstractControl}
 * and your custom entities implementations of the previous.
 *
 */
public class NepoBaby extends SimpleApplication {


    Cam camera;

    MickeyMouse mickeyMouse;

    InputListener inputListener;

    TheOffice theOffice;

    public NepoBaby() {
    }

    public NepoBaby(AppState... initialStates) {
        super(initialStates);
    }

    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        flyCam.setMoveSpeed(0f);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);

        camera = new Cam(cam,rootNode);
        mickeyMouse = new MickeyMouse(inputManager);
        theOffice = new TheOffice(rootNode,assetManager);
    }

    public void simpleUpdate(float tpf) {
        camera.MouseClicked = mickeyMouse.leftClick;
        camera.update();

        System.out.println(camera.ObjectClicked().name());
    }

}
