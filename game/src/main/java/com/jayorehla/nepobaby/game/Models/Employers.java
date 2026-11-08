package com.jayorehla.nepobaby.game.Models;

import com.jayorehla.nepobaby.game.Main;
import com.jayorehla.nepobaby.game.Mouskatools.Task;
import com.jayorehla.nepobaby.game.Mouskatools.VoiceSyth;
import com.jayorehla.nepobaby.game.Mouskatools.PIDFController;
import com.jayorehla.nepobaby.game.Mouskatools.Mainer;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.BillboardControl;
import com.jme3.scene.shape.Quad;
import com.jme3.audio.AudioNode;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Employers {

    boolean employeeTime;

    List<String> employeeList;
    Quad employee;
    public Geometry dash; // OH MY GOD GD REFERECNE
    Node root;
    Material mat;


    Texture LTexture;
    Texture RTexture;
    Texture CTexture1;
    Texture CTexture2;
    Texture CTexture3;
    Texture ATexture;
    Texture JTexture;
    Texture WTexture;

    AudioNode audioNode;

    List<String> voiceOptions;

    List<String> varOptions;

    String voiceLine;
    boolean chousenline = false;

    public boolean YourComputerIsDucked = false;

    PIDFController xPidf;
    PIDFController zPidf;
    public double timer;

    public double goonertimer;

    public int stroketime;

    private boolean setStroke;
    public boolean goodboy;
    public boolean goodboypoints;

    private boolean spoke = false;
    private boolean iHaveNoMouthButImustScream = false;
    Random random; // he can do anything!

    String currentEmployee = "No One";

    int step = 0;

   public  double xpower, zpower;

    public float targetx, targetz;

    VoiceSyth Lvoice;
    VoiceSyth Rvoice;
    VoiceSyth Cvoice;
    VoiceSyth Avoice;
    VoiceSyth Jvoice;
    VoiceSyth Wvoice; // A was taken, sorry Archey boy

    public Employers(Node root, AssetManager ass, AppStateManager stateManager){
        this.root = root;
        xPidf = new PIDFController(0.5,0,0);
        zPidf = new PIDFController(0.5,0,0); //TODO: tune these values
        employee = new Quad(1.5f,3);

        mat = new Material(ass, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        mat.getAdditionalRenderState().setBlendMode(com.jme3.material.RenderState.BlendMode.Alpha);

        dash = new Geometry("employees",employee);
        dash.setMaterial(mat);

        LTexture = ass.loadTexture(new TextureKey("Textures/the L is real 2108.png", true));
        RTexture = ass.loadTexture(new TextureKey("Textures/Roberto Robyn Roby Robinder Robbie Robertson III the robber.png", true));
        ATexture = ass.loadTexture(new TextureKey("Textures/Amora Tennisman.png",true));

        BillboardControl billboard = new BillboardControl();
        billboard.setAlignment(BillboardControl.Alignment.Screen);
        dash.addControl(billboard);

        dash.setLocalTranslation(-7.5f,0,-0.7f);

        root.attachChild(dash);

        random = new Random();

        audioNode = new AudioNode(ass,"Sounds/Footsteps.wav");
        audioNode.setVolume(2);
        audioNode.setPositional(false);
        root.attachChild(audioNode);
        audioNode.setLooping(true);

        //these are normal names you give normal people
        employeeList = new ArrayList<>();
        employeeList.add("Lothario Casanova the E-mail guy");
        employeeList.add("Roberto Robyn Roby Robinder Robbie Robertson III the robber");
        employeeList.add("The Cats");
        employeeList.add("Amora the Secretary");
        employeeList.add("Janitorial Jimmy");
        employeeList.add("Archey 'Arch' Winicks the Computer Technician");

        //employee voicelines
        voiceOptions = new ArrayList<>();

        // Lothario Casanova lines
        voiceOptions.add("My favorite Employer! So the chicks down at {VAR} were expecting a response from me like an hour ago so now they are angry. On the bright side I shifted the blame to you so you better write to them quick! ");
        voiceOptions.add("Heya Mista Top dog! A fine dandelion like yourself out to send a message down to {VAR}, they would love to hear from you");
        voiceOptions.add("Okay so the press knows about your dads money laundering scheme. Not to worry email them a nice email, say your sorry, send some roses and they'll blow it over! That's a Lothario guarantee!");

        // Roberto Robyn Roby Robinder Robbie Robertson III lines
        voiceOptions.add("Guess whoze heair fore zour cool zings?! Roberto Robyn Roby Robinder Robbie Robertson Za Third! Zats who!");
        voiceOptions.add("I'm am Roberto Robyn Roby Robinder Robbie Robertson Za third and I am heair to rob you!");
        voiceOptions.add("Your Zings are now propairty of Mothair Quebec! Un sale anglophone comme toi en redoute le nom, Roberto Robyn Roby Robinder Robbie Robertson le Troisième!");

        // The cats "lines"
        voiceOptions.add("Meow mew meowmeow!");
        voiceOptions.add("Meeerrrrooouugh");

        // Amora the Secretary
        voiceOptions.add("Sir, {VAR} needs you to sign these documents");
        voiceOptions.add("Sir, In order to close your fathers money laundering scheme, {VAR} need to sgin here");

        // Janitorial Jimmy
        voiceOptions.add("Oi laddie! Whata frugg'n mess we got 'ere! I ant clean'n after yah laddie! Hop to it!!");
        voiceOptions.add("My laddie! Your pops wasnt as much of a slob as your 'ere! Do yah self a favor an make dis place sparkl' and shine!");
        voiceOptions.add("I ant paid two shillies an hour you clen up afta your sorry behind! Get up and clean yah Numptie!");

        // Archey 'Arch' Winicks
        voiceOptions.add("Ermm I can only fix your computer if you install Linux on it because I only know how to use Linux not Miroslop. Or fix it your self. Try turning it on and off");
        voiceOptions.add("Heh, Typical. Any computer would rm -rf / itself if it ran Miroslop. Powercycle the computer and it would be fine. Turning it on and off in Windows user words.");
        voiceOptions.add("HMMMMMMMMmm... uhhh... Have you tried turning it on and off? Works every time! Never have it failed me!");

        Lvoice = new VoiceSyth();
        Rvoice = new VoiceSyth();
        Cvoice = new VoiceSyth();
        Avoice = new VoiceSyth();
        Jvoice = new VoiceSyth();
        Wvoice = new VoiceSyth();

        stateManager.attach(Lvoice);
        stateManager.attach(Rvoice);
        stateManager.attach(Cvoice);
        stateManager.attach(Avoice);
        stateManager.attach(Jvoice);
        stateManager.attach(Wvoice);

        varOptions = new ArrayList<>();
        varOptions.add("Security");
        varOptions.add("Sales");
        varOptions.add("Marketing");
        varOptions.add("Manufacturing");
        varOptions.add("Competitor spies");
        varOptions.add("The C.R.A.");
        varOptions.add("The Bradreian Quesly Avonshire Fan club");
        varOptions.add("The Bradreian Quesly Avonshire Hate club");


        // overtime, i may reconsider the voices they are goofy but loveable (for know)

        // yes everyone is SAM
        Lvoice.SetVoice( 62, 72, 128, 170,false);
        Rvoice.SetVoice( 203, 124, 210, 141,false);
        Cvoice.SetVoice( 26, 72, 128, 128,false);
        Avoice.SetVoice( 42, 127, 125, 162,false);
        Jvoice.SetVoice( 74, 176, 182, 129,false);
        Wvoice.SetVoice( 88, 66, 139, 225,false);


    }

    public void Update(float tpf, boolean sleeping){
        timer += tpf;
        goonertimer += tpf;
        YourComputerIsDucked = currentEmployee.equals("Archey 'Arch' Winicks the Computer Technician")
                && step < 4;
        System.out.println("your puta"+YourComputerIsDucked);
        if (timer >= 5 && currentEmployee == "No One"){
            step = 0;
            employeeTime =false;
            int randEmployee = random.nextInt(0,100);
           if (randEmployee <= 30){
               currentEmployee = "No One";
               timer = 0;
           }else if(randEmployee > 30 && randEmployee < 40 ){
               currentEmployee = employeeList.get(0);
           }
           else if(randEmployee > 40 && randEmployee < 50 ){
               currentEmployee = employeeList.get(1);
           }
           else if(randEmployee > 50 && randEmployee < 60){
               currentEmployee = employeeList.get(2);
           }
           else if(randEmployee > 60 && randEmployee < 70 ){
               currentEmployee = employeeList.get(3);
           }
           else if(randEmployee > 80 && randEmployee < 90 ){
               currentEmployee = employeeList.get(4);
           }
           else if(randEmployee > 90 && randEmployee < 100 ){
               currentEmployee = employeeList.get(5);
           }
            timer = 0;
        }

        if(currentEmployee != "No One" ){
            //man I wish I could set the thing to be the list rather than copying over the names...
            switch (currentEmployee){
                case "Lothario Casanova the E-mail guy":
                    mat.setColor("Color", ColorRGBA.White);
                    // Must be White to be the defult colours or else it dies the imgaes to being the colour
                    // the images right now are a little streached becase the aspect ratio is wrong
                    // that will get fixed when all the art is done :)
                    mat.setTexture("ColorMap", LTexture);
                    break; // TODO: Make it swap to the corect texures
                case "Roberto Robyn Roby Robinder Robbie Robertson III the robber":
                    mat.setColor("Color", ColorRGBA.White);
                    mat.setTexture("ColorMap",RTexture);
                    break;
                case "The Cats":
                    mat.clearParam("ColorMap");
                    mat.setColor("Color", ColorRGBA.Brown);
                    break;
                case "Amora the Secretary":
                    mat.setColor("Color", ColorRGBA.White);
                    mat.setTexture("ColorMap", ATexture);
                    break;
                case "Janitorial Jimmy":
                    mat.clearParam("ColorMap");
                    mat.setColor("Color", ColorRGBA.Yellow);
                    break;
                case "Archey 'Arch' Winicks the Computer Technician":
                    mat.clearParam("ColorMap");
                    mat.setColor("Color", ColorRGBA.DarkGray);
                    break;
            }

            switch (step){
                case 0:
                    setStroke = false;
                    goodboy = true;
                    audioNode.play();
                    targetx = -2.5f;
                    targetz = 4f;
                    if(Math.abs(dash.getWorldTransform().getTranslation().x - targetx) <= 1) xpower = 0;
                    else xpower = xPidf.getOutput(dash.getWorldTransform().getTranslation().x, targetx);

                    if(Math.abs(dash.getWorldTransform().getTranslation().z - targetz) <= 1) zpower = 0;
                    else zpower = zPidf.getOutput(dash.getWorldTransform().getTranslation().z, targetz);

                    dash.move(new Vector3f((float) xpower * tpf,0, (float) zpower * tpf));


                    if(Math.abs(dash.getWorldTransform().getTranslation().x - targetx) <= 1 && Math.abs(dash.getWorldTransform().getTranslation().z - targetz) <= 1 ){
                        step = 1;
                    }
                    break;
                case 1:
                    targetx = -2.19f;
                    targetz = -0.5f;
                    if(Math.abs(dash.getWorldTransform().getTranslation().x - targetx) <= 1) xpower = 0;
                    else xpower = xPidf.getOutput(dash.getWorldTransform().getTranslation().x, targetx);

                    if(Math.abs(dash.getWorldTransform().getTranslation().z - targetz) <= 1) zpower = 0;
                    else zpower = zPidf.getOutput(dash.getWorldTransform().getTranslation().z, targetz);

                    dash.move(new Vector3f((float) xpower * tpf,0, (float) zpower * tpf));

                    if(Math.abs(dash.getWorldTransform().getTranslation().x - targetx) <= 1 && Math.abs(dash.getWorldTransform().getTranslation().z - targetz) <= 1 ){
                        employeeTime  = true;
                        timer = 0;
                        spoke = false;
                        iHaveNoMouthButImustScream = false;
                        goodboypoints = true;
                        Mainer.doneTask = false;
                        chousenline = false;

                        audioNode.stop();

                        step = 2;
                    }
                    break;
                case 2:
                    if(!setStroke){
                        stroketime = random.nextInt(1,6);
                        setStroke = true;
                    }
                    switch (currentEmployee){
                        case "Lothario Casanova the E-mail guy":
                            Mainer.Task = Task.Email.toString();
                             if(!iHaveNoMouthButImustScream){
                                 voiceLine = voiceOptions.get(random.nextInt(0,3));
                                 Lvoice.SayText(voiceLine.replace("{VAR}", varOptions.get(random.nextInt(0, varOptions.size()))));
                                 iHaveNoMouthButImustScream = true;
                             }
                             if (!Lvoice.ispeaking){
                                 goonertimer = 0;
                                 spoke = true;
                             }
                            break;
                        case "Roberto Robyn Roby Robinder Robbie Robertson III the robber":
                            Mainer.Task = Task.ForkMoneyOver.toString();
                            if(!iHaveNoMouthButImustScream){
                                Rvoice.SayText(voiceOptions.get(random.nextInt(3,6)));
                                iHaveNoMouthButImustScream = true;
                            }
                            if (!Rvoice.ispeaking){
                                goonertimer = 0;
                                spoke = true;
                            }
                            break;
                        case "The Cats":
                            Mainer.Task = Task.PetTheCat.toString();
                            if(!iHaveNoMouthButImustScream) {
                                Cvoice.SayText(voiceOptions.get(random.nextInt(6, 8)));
                                iHaveNoMouthButImustScream = true;
                            }
                            if (!Cvoice.ispeaking){
                                goonertimer = 0;
                                spoke = true;
                            }
                            break;
                        case "Amora the Secretary":
                            Mainer.Task = Task.SpreadDemSheets.toString();
                            if(!iHaveNoMouthButImustScream) {
                                voiceLine = voiceOptions.get(random.nextInt(8,10));
                                Avoice.SayText(voiceLine.replace("{VAR}", varOptions.get(random.nextInt(0, varOptions.size()))));
                                iHaveNoMouthButImustScream = true;
                            }
                            if (!Avoice.ispeaking){
                                goonertimer = 0;
                                spoke = true;
                            }
                            break;
                        case "Janitorial Jimmy":
                            Mainer.Task = Task.CleanUpMess.toString();
                            if(!iHaveNoMouthButImustScream) {
                                Jvoice.SayText(voiceOptions.get(random.nextInt(10, 13)));
                                iHaveNoMouthButImustScream = true;
                            }
                            if (!Jvoice.ispeaking){
                                goonertimer = 0;
                                spoke = true;
                            }

                            break;
                        case "Archey 'Arch' Winicks the Computer Technician":
                            Mainer.Task = Task.RebootComputa.toString();
                            if(!iHaveNoMouthButImustScream) {
                                Wvoice.SayText(voiceOptions.get(random.nextInt(13, 16)));
                                iHaveNoMouthButImustScream = true;
                            }
                            if (!Wvoice.ispeaking){
                                goonertimer = 0;
                                spoke = true;
                            }
                            break;
                    }
                    if(sleeping){
                        Mainer.SatifationOmpf = -1;
                        goodboy = false;
                    }else{
                        Mainer.SatifationOmpf = 0;
                    }
                    if(timer > stroketime && spoke && Mainer.doneTask){
                        System.out.println(spoke);
                        step = 3;
                    }
                    break;
                case 3:
                    if(goodboy && goodboypoints){
                        Mainer.SatifationOmpf = 20;
                        goodboypoints = false;
                    }
                    step = 4;
                    break;
                case 4:
                    setStroke = false;
                    Mainer.SatifationOmpf = 0;

                    if(!Mainer.pointsGotenFromTask) Mainer.taskSatifactionOmpf = 0;
                    targetx = -2.5f;
                    targetz = 4;
                    if(Math.abs(dash.getWorldTransform().getTranslation().x - targetx) <= 1) xpower = 0;
                    else xpower = xPidf.getOutput(dash.getWorldTransform().getTranslation().x, targetx);

                    if(Math.abs(dash.getWorldTransform().getTranslation().z - targetz) <= 1) zpower = 0;
                    else zpower = zPidf.getOutput(dash.getWorldTransform().getTranslation().z, targetz);

                    dash.move(new Vector3f((float) xpower * tpf,0, (float) zpower * tpf));

                    if(Math.abs(dash.getWorldTransform().getTranslation().x - targetx) <= 1 && Math.abs(dash.getWorldTransform().getTranslation().z - targetz) <= 1 ){
                        step = 5;
                    }
                    break;
                case 5:
                    targetx = -7.5f;
                    targetz = -0.7f;
                    if(Math.abs(dash.getWorldTransform().getTranslation().x - targetx) <= 1) xpower = 0;
                    else xpower = xPidf.getOutput(dash.getWorldTransform().getTranslation().x, targetx);

                    if(Math.abs(dash.getWorldTransform().getTranslation().z - targetz) <= 1) zpower = 0;
                    else zpower = zPidf.getOutput(dash.getWorldTransform().getTranslation().z, targetz);

                    dash.move(new Vector3f((float) xpower * tpf,0, (float) zpower * tpf));

                    if(Math.abs(dash.getWorldTransform().getTranslation().x - targetx) <= 1 && Math.abs(dash.getWorldTransform().getTranslation().z - targetz) <= 1 ){
                        employeeTime  = false;
                        currentEmployee = "No One";
                    }
                    break;
            }

        }
    }
}
