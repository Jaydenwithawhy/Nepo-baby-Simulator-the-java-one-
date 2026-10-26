package com.jayorehla.nepobaby.game.Control;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.event.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyboarE implements RawInputListener {


    InputManager inputManager;
    public int lastKeyChara = -67;
    public int lastKeyFrisk = -67;
    public Set<Integer> lastKeyKris;
    public boolean keypressed= false;
    boolean keyheld;

    public KeyboarE(InputManager inputManager){
        this.inputManager = inputManager;
        lastKeyKris = new HashSet<>();
    }


    public void init(){
        inputManager.addRawInputListener(this);
    }


    @Override
    public void beginInput() {

    }

    @Override
    public void endInput() {

    }

    @Override
    public void onJoyAxisEvent(JoyAxisEvent evt) {

    }

    @Override
    public void onJoyButtonEvent(JoyButtonEvent evt) {

    }

    @Override
    public void onMouseMotionEvent(MouseMotionEvent evt) {

    }

    @Override
    public void onMouseButtonEvent(MouseButtonEvent evt) {

    }

    @Override
    public void onKeyEvent(KeyInputEvent evt) {
        int code = evt.getKeyCode();
        lastKeyFrisk = code;
        if (code == KeyInput.KEY_UNKNOWN || code != KeyInput.KEY_E) return;// stray code-0 events

        if (evt.isPressed()) {
            lastKeyChara = code;
            keypressed = true;
            lastKeyKris.add(code);
        } else if (evt.isReleased() && code == lastKeyChara) {
            keypressed = false;
            lastKeyKris.clear();
        }

    }

    @Override
    public void onTouchEvent(TouchEvent evt) {

    }
}
