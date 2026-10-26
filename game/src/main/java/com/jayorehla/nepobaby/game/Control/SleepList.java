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

    private boolean numbers = false;

    public int letkeygo;

    public Set<Integer> theRoaringSet;

    boolean WDGaster = true;

    boolean initalE = false;

    public int counter;
    boolean sawKey;

    int desirednumber;

    public SleepList(){
        sleepingkeys = new ArrayList<>();
        keysToType = new ArrayList<>();
        randomguy = new Random();
        theRoaringSet = new HashSet<>();

        sleepingkeys.add(KeyInput.KEY_A);
        sleepingkeys.add(KeyInput.KEY_B);
        sleepingkeys.add(KeyInput.KEY_C);
        sleepingkeys.add(KeyInput.KEY_D);
//        sleepingkeys.add(KeyInput.KEY_E);
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
            counting = true;
            initalE = false;
            desirednumber = Integer.MAX_VALUE;
            counter = 0;
            numbers = false;
            WDGaster = true;

            clear = true;

        }
    }

    public void CreateList(int speed, float tph){
        System.out.println("I am the roaring fickin king the roaring fiking king ");
        clear = false;
        timer += tph;

        if(timer >= speed && counting){
            System.out.println("And I hate tv time");
            keysToType.add(sleepingkeys.get(randomguy.nextInt(0,sleepingkeys.size())));
            timer =  0;
        }
    }

//    public void InitalE(int E){
//        System.out.println(E);
//        if(E == 18 && theRoaringSet.isEmpty() && WDGaster && !initalE){
//            initalE = true;
//            WDGaster = false;
//        }
//    }

    public void debug(){
        System.out.println("initalE=" + initalE + " set=" + theRoaringSet
                + " WD=" + WDGaster + " list=" + keysToType
                + " counter=" + counter + "/" + desirednumber);
    }

    public void Countup(int currentKey){
        // hate. hate! HATE!!! Let me tell you how much i hated you since you began to live

        if (!theRoaringSet.isEmpty()) {
            sawKey = true;
            return;
        }
        if (!sawKey || keysToType.isEmpty()) return;
        sawKey = false;

        counting = false;
        if (!numbers) {
            counter = 0;
            desirednumber = keysToType.size();
            numbers = true;
        }
        if (currentKey == keysToType.get(0)) {
            keysToType.remove(0);
            counter++;
        } else {
            keysToType.add(sleepingkeys.get(randomguy.nextInt(sleepingkeys.size())));
            desirednumber++;
        }if (!theRoaringSet.isEmpty()) {
            sawKey = true;
            return;
        }
        if (!sawKey || keysToType.isEmpty()) return;
        sawKey = false;

        counting = false;
        if (!numbers) {
            counter = 0;
            desirednumber = keysToType.size();
            numbers = true;
        }
        // if you see this void, make it feel shame. it disgust me how much tweaking it needs it make me tweak
        if (currentKey == keysToType.get(0)) {
            keysToType.remove(0);
            counter++;
        } else {
            keysToType.add(sleepingkeys.get(randomguy.nextInt(sleepingkeys.size())));
            desirednumber++;
        }

        if( !theRoaringSet.isEmpty() && !WDGaster){
           WDGaster = true; // yes i shorten the varible name jermaise. its not fun to type...
        }

    }



    public boolean SleepDone(boolean sleeping){
        return counter >= desirednumber;
    }
}
