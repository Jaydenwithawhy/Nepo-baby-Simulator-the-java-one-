package com.jayorehla.nepobaby.game.Control;

import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.InputListener;
import com.jme3.input.controls.MouseButtonTrigger;

import com.jme3.input.controls.ActionListener;

public class MickeyMouse {

    InputManager inputManager;
    public boolean leftClick;

    public MickeyMouse(InputManager inputManager){
        this.inputManager = inputManager;

        inputManager.addMapping("Left Click", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(LeftClick,"Left Click");
    }

    private ActionListener LeftClick = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Left Click")) {
                leftClick = keyPressed;
            }
        }
    };
}
