package com.jayorehla.nepobaby.game.Scenes;

import com.jayorehla.nepobaby.game.Control.*;
import com.jayorehla.nepobaby.game.Models.Employers;
import com.jayorehla.nepobaby.game.Models.TheOffice;
import com.jayorehla.nepobaby.game.Models.Touchables;
import com.jayorehla.nepobaby.game.Mouskatools.VoiceSyth;
import com.jayorehla.nepobaby.game.Ui.*;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.system.Timer;

import java.util.ArrayList;

// in therory eerything in there comes here

public class MainOffice extends BaseAppState {

    public Node localMainroot;

    public Node localguiroot;

    Cam camera;

    MickeyMouse mickeyMouse;

    TheOffice theOffice;

    Employers employers;

    Timer timer;

    SleepTimer sleepTimer;

    VoiceSyth voiceSyth;

    Touchables touchables;

    KeyBoard keyBoard;

    KeyboarE keyboarE;

    HonkShoo honkShoo;

    SleepKeysUI sleepKeysUI;

    SleepList sleepList;

    Satifaction satifaction;
    Text text;

    SimpleApplication sa;

    @Override
    protected void initialize(Application app) {
        localMainroot = new Node("Im the main node ");
        localguiroot = new Node(" Im so gui");

        sa = (SimpleApplication) app;
        InputManager inputManager = sa.getInputManager();
        AssetManager ass = sa.getAssetManager();
        AppStateManager stateManager = sa.getStateManager();


        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        localMainroot.addLight(al);

        sa.getFlyByCamera().setMoveSpeed(0);
        sa.getFlyByCamera().setZoomSpeed(0); // no more zoming jermaise...

        sa.getCamera().setLocation(new Vector3f(2.1749218f,2.3135061f,-1.8647175f)); // found this by flying around

        // I lowkirkgenually have no clue what a quaterion is i just fricked around to find out
        sa.getCamera().setRotation(new Quaternion(0.03264319f, -0.5638806f, 0.022313379f, 0.82490915f));


        DirectionalLight sun = new DirectionalLight();
        sun.setColor(ColorRGBA.White);
        sun.setDirection(new Vector3f(-1,-1,-1).normalizeLocal());
        localMainroot.addLight(sun);

        camera = new Cam(sa.getCamera(),localMainroot);
        mickeyMouse = new MickeyMouse(inputManager);
        theOffice = new TheOffice(localMainroot,ass);
        employers = new Employers(localMainroot,ass,stateManager);
        touchables = new Touchables(localMainroot,ass);

        text = new Text(ass,localguiroot);
        sleepKeysUI = new SleepKeysUI(ass,localguiroot,text);
        honkShoo = new HonkShoo(ass,localguiroot,sa.getContext().getSettings());

        sleepTimer = new SleepTimer(ass,localguiroot,text);

        keyBoard = new KeyBoard(inputManager);
        keyBoard.init();

        keyboarE = new KeyboarE(inputManager);
        keyboarE.init();

        voiceSyth = new VoiceSyth();
        stateManager.attach(voiceSyth);

        sleepList = new SleepList();

        satifaction = new Satifaction(ass,localguiroot,text);

    }

    @Override
    public void update(float tpf) {
        camera.MouseClicked = mickeyMouse.leftClick;
        camera.Eclicked = keyboarE.lastKeyKris.contains(18);
        honkShoo.closeEyes = camera.sleeping || keyboarE.lastKeyKris.contains(18);
        honkShoo.OpenEyes  = sleepList.SleepDone(camera.sleeping) || !camera.sleeping;
        camera.update(tpf,sleepList.SleepDone(camera.sleeping));
        honkShoo.Update(tpf);

        sleepKeysUI.UpdateList(new ArrayList<>(sleepList.keysToType));

//        sleepList.letkeygo = keyBoard.lastKeyFrisk;

        sleepList.theRoaringSet = keyBoard.lastKeyKris;

        sleepList.debug();

        sa.getFlyByCamera().setRotationSpeed(camera.headRot);

        employers.Update(tpf,camera.sleeping);
        theOffice.update(employers.YourComputerIsDucked);

        sleepKeysUI.PrintOut(camera.sleeping);
        sleepTimer.PrintOut(camera.sleeping,sleepList.counter,tpf);
        satifaction.PrintOut(employers.dockSatifation,employers.goodboy,employers.goodboypoints);

        if(camera.sleeping) {
            sleepList.CreateList(1, tpf);
            sleepList.Countup(keyBoard.lastKeyChara);
        }else{
            sleepList.ClearList();
            camera.NeakHurts(sa.getFlyByCamera());
//            sleepList.InitalE(keyboarE.lastKeyChara);
        }

//        System.out.println(keyBoard.lastKeyKris);
        System.out.println(camera.sleeping);
    }

    @Override
    protected void cleanup(Application app) {

    }

    @Override
    protected void onEnable() {
        sa.getRootNode().attachChild(localMainroot);
        sa.getGuiNode().attachChild(localguiroot);
    }

    @Override
    protected void onDisable() {
        localMainroot.removeFromParent();
        localguiroot.removeFromParent();
    }
}
