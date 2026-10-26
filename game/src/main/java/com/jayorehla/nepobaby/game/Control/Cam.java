package com.jayorehla.nepobaby.game.Control;

import com.jayorehla.nepobaby.game.utls.PIDFController;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;

public class Cam {

    Node head;

    Node root;

    Camera cam;
    public Ray ray;

    CollisionResults results;

    int sleepstate = 0;
    int awakestate = 0;

    public boolean MouseClicked; // I dont want to add mouse logic here because that kinda disorganized

    public boolean Eclicked;

    public boolean eeppy = false;

    public boolean sleeping = false;

    public float headRot = 1;


    PIDFController headtranPIDF;
    PIDFController headRollPIDF;


    String lookedAtThing;

    public enum ThingsToLookAt{
        computer,
        cabeniet,
        employee,
        AdrienneChristianHernandez,
        nothing
        }


    public Cam(Camera cam, Node root){
        head = new Node("Augustus A Avonshire");
        this.cam = cam;
        ray = new Ray(cam.getLocation(),cam.getDirection());
        results = new CollisionResults();
        this.root = root;
        headRollPIDF = new PIDFController(0.1,0,0);
        headtranPIDF = new PIDFController(0.1,0,0);
    }

    public void update(float tph, boolean donesleep){
        results.clear();
        ray.setDirection(cam.getDirection());
        root.collideWith(ray, results);

        if(Eclicked){
            eeppy = true;
        }

        if(eeppy){
            sleepAction();
        }

        if(donesleep){
            AwakeAction();
        }
    }

    public String debug(){
        if (results.size() > 0 ){
            return results.getClosestCollision().getGeometry().getName();
        }
        return "nothing in list?!";
    }

    public ThingsToLookAt ObjectClicked(){
        if(MouseClicked){
            if (results.size() > 0) {
                lookedAtThing = results.getClosestCollision().getGeometry().getName();

                switch (lookedAtThing){ // this way we can make sure we are only logging things we care about
                    case "Box":
                        return ThingsToLookAt.AdrienneChristianHernandez;

                }
            }else return ThingsToLookAt.nothing;;
        }
        return ThingsToLookAt.nothing;
    }

    private void sleepAction(){
        awakestate = 0;
        switch (sleepstate){
            case 0:
                double movex = headtranPIDF.getOutput(cam.getLocation().x,0.1695178);
                double movey = headtranPIDF.getOutput(cam.getLocation().y,2.511712);
                double movez = headtranPIDF.getOutput(cam.getLocation().z,-1.1441668);

                // seroiusly dude... what the hell is a quaderion
//                double lookw = headRollPIDF.getOutput(cam.getRotation().getW(),-0.49166816);
//                double lookx = headRollPIDF.getOutput(cam.getRotation().getX(),-0.43740118);
//                double looky = headRollPIDF.getOutput(cam.getRotation().getY(),0.562561);
//                double lookz = headRollPIDF.getOutput(cam.getRotation().getZ(),-0.5004676);

                Quaternion targetRot = new Quaternion(-0.43740118f, 0.562561f, -0.5004676f, -0.49166816f);

                Quaternion gotToRot = new Quaternion();
                gotToRot.slerp(cam.getRotation(),targetRot,0.1f);
                cam.setRotation(gotToRot);


                cam.setLocation(new Vector3f((float) (cam.getLocation().x + movex), (float) (cam.getLocation().y + movey), (float) (cam.getLocation().z + movez)));

                //cam.setRotation(new Quaternion((float) (cam.getRotation().getX() + lookx), (float) (cam.getRotation().getY() + looky), (float) (cam.getRotation().getZ() + lookz), (float) (cam.getRotation().getW() + lookw)));

                if (Math.abs(cam.getLocation().x - 0.1695178) < 0.5 && Math.abs(cam.getLocation().y - 2.511712) < 0.5 && Math.abs(cam.getLocation().z - (-1.1441668)) < 0.5){
                    sleepstate = 1;
                }
                break;
            case 1:
                headRot = 0;
                sleeping = true;
                eeppy = false;

        }
    }


    private void AwakeAction(){
        sleepstate = 0;
        switch (awakestate){
            case 0:
                double movex = headtranPIDF.getOutput(cam.getLocation().x,2.1749218f);
                double movey = headtranPIDF.getOutput(cam.getLocation().y,2.3135061f);
                double movez = headtranPIDF.getOutput(cam.getLocation().z,-1.8647175f);

                // seroiusly dude... what the hell is a quaderion
//                double lookw = headRollPIDF.getOutput(cam.getRotation().getW(),-0.49166816);
//                double lookx = headRollPIDF.getOutput(cam.getRotation().getX(),-0.43740118);
//                double looky = headRollPIDF.getOutput(cam.getRotation().getY(),0.562561);
//                double lookz = headRollPIDF.getOutput(cam.getRotation().getZ(),-0.5004676);

                Quaternion targetRot = new Quaternion(0.03264319f, -0.5638806f, 0.022313379f, 0.82490915f);

                Quaternion gotToRot = new Quaternion();
                gotToRot.slerp(cam.getRotation(),targetRot,0.1f);
                cam.setRotation(gotToRot);


                cam.setLocation(new Vector3f((float) (cam.getLocation().x + movex), (float) (cam.getLocation().y + movey), (float) (cam.getLocation().z + movez)));

                //cam.setRotation(new Quaternion((float) (cam.getRotation().getX() + lookx), (float) (cam.getRotation().getY() + looky), (float) (cam.getRotation().getZ() + lookz), (float) (cam.getRotation().getW() + lookw)));

                if (Math.abs(cam.getLocation().x - 2.1749218f) < 0.5 && Math.abs(cam.getLocation().y - 2.3135061f) < 0.5 && Math.abs(cam.getLocation().z - (-1.8647175f)) < 0.5){
                    awakestate = 1;
                }
                break;
            case 1:
                headRot = 1;
                sleeping = false;

        }
    }
}
