package com.jayorehla.nepobaby.game.Control;

import com.jme3.collision.CollisionResults;
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

    public boolean MouseClicked; // I dont want to add mouse logic here because that kinda disorganized


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
    }

    public void update(){
        results.clear();
        ray.setDirection(cam.getDirection());
        root.collideWith(ray, results);
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
}
