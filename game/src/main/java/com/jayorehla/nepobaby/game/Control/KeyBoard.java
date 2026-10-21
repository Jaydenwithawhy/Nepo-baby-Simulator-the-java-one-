package com.jayorehla.nepobaby.game.Control;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.RawInputListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.event.*;

public class KeyBoard implements RawInputListener {


    InputManager inputManager;
    public int lastKeyChara = -67;
    public boolean keypressed= false;
    boolean keyheld;

    public KeyBoard(InputManager inputManager){
        this.inputManager = inputManager;
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
        if (evt.isPressed()) {
            int code = evt.getKeyCode();

            if (code != KeyInput.KEY_UNKNOWN && code != 0) {
                lastKeyChara = code;
                keypressed = true;
            }
        } else if (evt.isReleased()) {
            keypressed = false;
        }

    }

    @Override
    public void onTouchEvent(TouchEvent evt) {

    }
}
