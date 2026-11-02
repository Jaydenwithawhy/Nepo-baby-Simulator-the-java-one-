package com.jayorehla.nepobaby.game.Ui;

import com.jayorehla.nepobaby.game.Mouskatools.Mainer;
import com.jayorehla.nepobaby.game.Mouskatools.PIDFController;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.input.InputManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.ui.Picture;

import java.util.Random;

public class RTaskUI {

    Node guiNode;

    Node root;

    AssetManager ass;

    InputManager inputManager;

    Money money1;
    Money money2;
    Money money3;
    Money money4;
    Money money5;
    Money money6;
    Money money7;
    Money money8;
    Money money9;
    Money money10;
    Money money11;
    Money money12;
    Money money13;
    Money money14;
    Money money15;
    Money money16;
    Money money17;
    Money money18;
    Money money19;
    Money money20;
    // money money money
    public RTaskUI(Node root, Node guiNode, AssetManager ass, InputManager inputManager){
        this.guiNode = guiNode;
        this.inputManager = inputManager;
        this.ass = ass;
        this.root = root;
        money1 = new Money(guiNode);
        money2 = new Money(guiNode);
        money3 = new Money(guiNode);
        money4 = new Money(guiNode);
        money5 = new Money(guiNode);
        money6 = new Money(guiNode);
        money7 = new Money(guiNode);
        money8 = new Money(guiNode);
        money9 = new Money(guiNode);
        money10 = new Money(guiNode);
        money11 = new Money(guiNode);
        money12 = new Money(guiNode);
        money13 = new Money(guiNode);
        money14 = new Money(guiNode);
        money15 = new Money(guiNode);
        money16 = new Money(guiNode);
        money17 = new Money(guiNode);
        money18 = new Money(guiNode);
        money19 = new Money(guiNode);
        money20 = new Money(guiNode);
    }

    public void update(float tph){
        money1.update(tph);
        money2.update(tph);
        money3.update(tph);
        money4.update(tph);
        money5.update(tph);
        money6.update(tph);
        money7.update(tph);
        money8.update(tph);
        money9.update(tph);
        money10.update(tph);
        money11.update(tph);
        money12.update(tph);
        money13.update(tph);
        money14.update(tph);
        money15.update(tph);
        money16.update(tph);
        money17.update(tph);
        money18.update(tph);
        money19.update(tph);
        money20.update(tph);

    }

    private class Money {
        Picture moneyDotPNG;
        PIDFController xpidf;
        PIDFController ypidf;
        Random random;
        AudioNode moneydies;
        int smartLevel;
        int id;
        Node guiNode;
        boolean compleate;

        double targetX;
        double targetY;

        double xpower;
        double ypower;

        double timer;
        boolean givenSaifaction = false;

        private Money(Node guiNode){
            this.guiNode = guiNode;
            random = new Random();
            smartLevel = random.nextInt(1,4);
            id = random.nextInt();

            moneydies = new AudioNode(ass,"Sounds/lego-yoda-death-sound-effect.wav");
            moneydies.setVolume(2);
            moneydies.setPositional(false);
            root.attachChild(moneydies);
            moneydies.setLooping(false);

            moneyDotPNG = new Picture("MoneyOWO" + id); // gives each money a unique name. I forgot why I needed it so it's not used
            moneyDotPNG.setImage(ass, "Textures/mony.png", true);
            moneyDotPNG.setWidth(200);
            moneyDotPNG.setHeight(200);
            moneyDotPNG.setPosition(0,0);
            moneyDotPNG.setCullHint(Spatial.CullHint.Inherit);

            guiNode.attachChild(moneyDotPNG);

            if(smartLevel == 1){
                xpidf = new PIDFController(0.2,0,0);
                ypidf = new PIDFController(0.2,0,0);
            }else if(smartLevel == 2){
                xpidf = new PIDFController(0.4,0,0);
                ypidf = new PIDFController(0.4,0,0);
            }else if(smartLevel == 3){
                xpidf = new PIDFController(0.9,0,0);
                ypidf = new PIDFController(0.9,0,0);
            }

        }

        private void update(float tph){
            System.out.println(smartLevel);
            if(smartLevel == 1){
                if(compleate){
                    targetX = random.nextDouble(0,1000);
                    targetY = random.nextDouble(0,1000);
                    compleate = false;
                }else{
                    if(Math.abs(moneyDotPNG.getLocalTransform().getTranslation().x - targetX) > 0.01){
                        xpower = xpidf.getOutput(moneyDotPNG.getLocalTransform().getTranslation().x, targetX);
                    }else xpower = 0;

                    if (Math.abs(moneyDotPNG.getLocalTransform().getTranslation().y - targetY) > 0.01){
                        ypower = ypidf.getOutput(moneyDotPNG.getLocalTransform().getTranslation().y, targetY);
                    }else ypower = 0;

                    moneyDotPNG.move((float) xpower * tph, (float) ypower * tph, 0);

                    if(Math.abs(moneyDotPNG.getLocalTransform().getTranslation().x - targetX) < 0.01 && Math.abs(moneyDotPNG.getLocalTransform().getTranslation().y - targetY) < 0.01){
                        compleate = true;
                    }
                }
            } else if (smartLevel == 2){
                timer += tph;
                if(compleate){
                    targetX = random.nextDouble(-1000,1000);
                    targetY = random.nextDouble(-1000,1000);
                    compleate = false;
                }else{
                    if(Math.abs(moneyDotPNG.getLocalTransform().getTranslation().x - targetX) > 0.01){
                        xpower = xpidf.getOutput(moneyDotPNG.getLocalTransform().getTranslation().x, targetX);
                    }else xpower = 0;

                    if (Math.abs(moneyDotPNG.getLocalTransform().getTranslation().y - targetY) > 0.01){
                        ypower = ypidf.getOutput(moneyDotPNG.getLocalTransform().getTranslation().y, targetY);
                    }else ypower = 0;

                    moneyDotPNG.move((float) xpower * tph, (float) ypower * tph, 0);

                    if(Math.abs(moneyDotPNG.getLocalTransform().getTranslation().x - targetX) < 0.01 && Math.abs(moneyDotPNG.getLocalTransform().getTranslation().y - targetY) < 0.01 || timer > 2.5){
                        compleate = true;
                        timer = 0;
                    }
                }
            } else if (smartLevel == 3){
                timer += tph;
                if(compleate){
                    targetX = random.nextDouble(-1000,1000);
                    targetY = random.nextDouble(-1000,1000);
                    compleate = false;
                }else{
                    if(Math.abs(moneyDotPNG.getLocalTransform().getTranslation().x - targetX) > 1){
                        xpower = xpidf.getOutput(moneyDotPNG.getLocalTransform().getTranslation().x, targetX);
                    }else xpower = 0;

                    if (Math.abs(moneyDotPNG.getLocalTransform().getTranslation().y - targetY) > 0.01){
                        ypower = ypidf.getOutput(moneyDotPNG.getLocalTransform().getTranslation().y, targetY);
                    }else ypower = 0;

                    moneyDotPNG.move((float) xpower * tph, (float) ypower * tph, 0);

                    if(Math.abs(moneyDotPNG.getLocalTransform().getTranslation().x - targetX) < 1 && Math.abs(moneyDotPNG.getLocalTransform().getTranslation().y - targetY) < 1 || timer > 0.5){
                        compleate = true;
                        timer = 0;
                    }
                }
            }

            if ((inputManager.getCursorPosition().x < (moneyDotPNG.getLocalTransform().getTranslation().x + moneyDotPNG.getWidth()) && inputManager.getCursorPosition().x > (moneyDotPNG.getLocalTransform().getTranslation().x - moneyDotPNG.getWidth())) && (inputManager.getCursorPosition().y < (moneyDotPNG.getLocalTransform().getTranslation().y + moneyDotPNG.getHeight()) && inputManager.getCursorPosition().y > (moneyDotPNG.getLocalTransform().getTranslation().y - moneyDotPNG.getHeight()))){
                if(Mainer.mouseDown && moneyDotPNG.getCullHint() != Spatial.CullHint.Always){ // the  if statement was too long so i cut it in half
                    if(!givenSaifaction){
                        Mainer.taskSatifactionOmpf += 5;
                        givenSaifaction = true;
                    }
                    moneydies.play();
                    Mainer.pointsGotenFromTask = true;
                    moneyDotPNG.setCullHint(Spatial.CullHint.Always);
                }
            }
        }
    }
}
