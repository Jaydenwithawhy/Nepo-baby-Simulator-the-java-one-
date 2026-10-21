package com.jayorehla.nepobaby.game;

import com.jayorehla.nepobaby.game.Control.Cam;
import com.jayorehla.nepobaby.game.Control.KeyBoard;
import com.jayorehla.nepobaby.game.Control.MickeyMouse;
import com.jayorehla.nepobaby.game.Models.Employers;
import com.jayorehla.nepobaby.game.Models.TheOffice;
import com.jayorehla.nepobaby.game.Models.Touchables;
import com.jayorehla.nepobaby.game.Mouskatools.VoiceSyth;
import com.jayorehla.nepobaby.game.Ui.HonkShoo;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.InputListener;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.light.LightProbe;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.app.state.AppState;
import com.jme3.system.Timer;

/**
 * The JMonkeyEngine game entry, you should only do initializations for your game here, game logic is handled by
 * Custom states {@link com.jme3.app.state.BaseAppState}, Custom controls {@link com.jme3.scene.control.AbstractControl}
 * and your custom entities implementations of the previous.
 *
 */
public class NepoBaby extends SimpleApplication {


    Cam camera;

    MickeyMouse mickeyMouse;

    TheOffice theOffice;

    Employers employers;

    Timer timer;

    VoiceSyth voiceSyth;

    Touchables touchables;

    KeyBoard keyBoard;

    HonkShoo honkShoo;

    boolean yapping = false;

    public NepoBaby() {
    }

    public NepoBaby(AppState... initialStates) {
        super(initialStates);
    }

    @Override
    public void simpleInitApp() {
//        Box b = new Box(1, 1, 1);
//        Geometry geom = new Geometry("Box", b);

        setDisplayFps(false);
        setDisplayStatView(false);

        timer =  stateManager.getApplication().getTimer();

        flyCam.setMoveSpeed(4);

//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", ColorRGBA.Blue);
//        geom.setMaterial(mat); im sorry defult box... your time has come

//        rootNode.attachChild(geom);

        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);

        cam.setLocation(new Vector3f(2.1749218f,2.3135061f,-1.8647175f)); // found this by flying around

        // I lowkirkgenually have no clue what a quaterion is i just fricked around to find out
        cam.setRotation(new Quaternion(0.03264319f, -0.5638806f, 0.022313379f, 0.82490915f));

        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-1,-1,-1).normalizeLocal());
        rootNode.addLight(sun);

        camera = new Cam(cam,rootNode);
        mickeyMouse = new MickeyMouse(inputManager);
        theOffice = new TheOffice(rootNode,assetManager);
        employers = new Employers(rootNode,assetManager,stateManager);
        touchables = new Touchables(rootNode,assetManager);

        honkShoo = new HonkShoo(assetManager,guiNode,settings);

        keyBoard = new KeyBoard(inputManager);
        keyBoard.init();

        voiceSyth = new VoiceSyth();
        stateManager.attach(voiceSyth);

        voiceSyth.SetVoice("kevin");
    }

    public void simpleUpdate(float tpf) {

        if(!yapping){
//            voiceSyth.SayText("hey gooner king, practice your 'stroke' wont ya?");
            yapping = true;
        }
        camera.MouseClicked = mickeyMouse.leftClick;
        camera.Eclicked = keyBoard.lastKeyChara == KeyInput.KEY_E;
        honkShoo.closeEyes = keyBoard.lastKeyChara == KeyInput.KEY_E;
        camera.update(tpf);
        honkShoo.Update(tpf);

        employers.Update(tpf);

        System.out.println(cam.getRotation());
    }

}
