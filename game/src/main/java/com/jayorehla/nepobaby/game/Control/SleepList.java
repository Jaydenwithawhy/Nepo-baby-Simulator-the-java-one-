package com.jayorehla.nepobaby.game.Control;

import com.jme3.input.KeyInput;

import java.util.*;

public class SleepList {

    private List<Integer> sleepingkeys;
    public List<Integer> keysToType;

    double timer = 0;

    Random randomguy;

    public int currentStep = 0;

    private boolean clear = false;

    private boolean counting = true;

    public int letkeygo;

    public Set<Integer> theRoaringSet;

    boolean WDGaster = true;

    boolean initalE = false;

    public SleepList(){
        sleepingkeys = new ArrayList<>();
        keysToType = new ArrayList<>();
        randomguy = new Random();
        theRoaringSet = new HashSet<>();

        sleepingkeys.add(KeyInput.KEY_A);
        sleepingkeys.add(KeyInput.KEY_B);
        sleepingkeys.add(KeyInput.KEY_C);
        sleepingkeys.add(KeyInput.KEY_D);
        sleepingkeys.add(KeyInput.KEY_E);
        sleepingkeys.add(KeyInput.KEY_F);
        sleepingkeys.add(KeyInput.KEY_G);
        sleepingkeys.add(KeyInput.KEY_H);
        sleepingkeys.add(KeyInput.KEY_I);
        sleepingkeys.add(KeyInput.KEY_J);
        sleepingkeys.add(KeyInput.KEY_K);
        sleepingkeys.add(KeyInput.KEY_L);
        sleepingkeys.add(KeyInput.KEY_M);
        sleepingkeys.add(KeyInput.KEY_N);
        sleepingkeys.add(KeyInput.KEY_O);
        sleepingkeys.add(KeyInput.KEY_P);
        sleepingkeys.add(KeyInput.KEY_Q);
        sleepingkeys.add(KeyInput.KEY_R);
        sleepingkeys.add(KeyInput.KEY_S);
        sleepingkeys.add(KeyInput.KEY_T);
        sleepingkeys.add(KeyInput.KEY_U);
        sleepingkeys.add(KeyInput.KEY_V);
        sleepingkeys.add(KeyInput.KEY_W);
        sleepingkeys.add(KeyInput.KEY_X);
        sleepingkeys.add(KeyInput.KEY_Y);
        sleepingkeys.add(KeyInput.KEY_Z);
        sleepingkeys.add(KeyInput.KEY_SEMICOLON);
        sleepingkeys.add(KeyInput.KEY_APOSTROPHE);
        sleepingkeys.add(KeyInput.KEY_PERIOD);
        sleepingkeys.add(KeyInput.KEY_SLASH);
        sleepingkeys.add(KeyInput.KEY_1);
        sleepingkeys.add(KeyInput.KEY_2);
        sleepingkeys.add(KeyInput.KEY_3);
        sleepingkeys.add(KeyInput.KEY_4);
        sleepingkeys.add(KeyInput.KEY_5);
        sleepingkeys.add(KeyInput.KEY_6);
        sleepingkeys.add(KeyInput.KEY_7);
        sleepingkeys.add(KeyInput.KEY_8);
        sleepingkeys.add(KeyInput.KEY_9);
        // Im not doing that ever again. i was in hell² looking at hell
    }

    public void ClearList(){
        if(!clear) {
            keysToType.clear();
            keysToType.add(sleepingkeys.get(randomguy.nextInt(0,sleepingkeys.size())));
            currentStep = 0;
            counting = true;
            clear = true;
            initalE = false;
        }
    }

    public void CreateList(int speed, float tph){
        clear = false;
        timer += tph;

        if(timer >= speed && counting){
            keysToType.add(sleepingkeys.get(randomguy.nextInt(0,sleepingkeys.size())));
            timer =  0;
        }
    }

    public void Countup(int currentKey){
        // hate. hate! HATE!!! Let me tell you how much i hated you since you began to live

        System.out.println(initalE);
        if(theRoaringSet.contains(18) && WDGaster && !initalE){
            initalE = true;
            WDGaster = false;
        }

        if (!keysToType.isEmpty() && theRoaringSet.isEmpty() && WDGaster && initalE) {
            counting = false;
            System.out.println("Im tung tung sahere");
            if(currentKey == keysToType.get(currentStep)) keysToType.removeFirst();
            else keysToType.add(sleepingkeys.get(randomguy.nextInt(0,sleepingkeys.size())));
            WDGaster = false;
        }

        if( !theRoaringSet.isEmpty() && !WDGaster){
           WDGaster = true; // yes i shorten the varible name jermaise. its not fun to type...
        }

    }

    public boolean SleepDone(boolean sleeping){
        return keysToType.isEmpty() && sleeping;
    }
}
