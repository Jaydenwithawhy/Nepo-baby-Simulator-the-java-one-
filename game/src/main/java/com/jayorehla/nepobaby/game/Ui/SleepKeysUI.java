package com.jayorehla.nepobaby.game.Ui;

import com.atr.jme.font.TrueTypeFont;
import com.atr.jme.font.asset.TrueTypeKey;
import com.atr.jme.font.asset.TrueTypeKeyBMP;
import com.atr.jme.font.asset.TrueTypeLoader;
import com.atr.jme.font.shape.TrueTypeNode;
import com.atr.jme.font.util.Style;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SleepKeysUI {

    TrueTypeKey newTimeBaq;

    public List<String> sleepKeys;

    AssetManager ass;

    TrueTypeNode node;

    TrueTypeFont ttfFont;

    public Map<Integer,String> Keys;

    Node guinode;

    String lastRenderedText;

    public SleepKeysUI(AssetManager ass,Node guiroot ){
        this.ass = ass;
        this.guinode = guiroot;

        ass.registerLoader(TrueTypeLoader.class,"ttf");
        newTimeBaq = new TrueTypeKeyBMP("Interface/Times_New_Baq.ttf", Style.Plain, 24);
        ttfFont = (TrueTypeFont) ass.loadAsset(newTimeBaq);

        sleepKeys = new ArrayList<>();

        node = ttfFont.getText("", 0, ColorRGBA.White);
        node.setLocalTranslation(500f, 500f, 1f);
        node.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);
        node.setCullHint(Spatial.CullHint.Always);
        guiroot.attachChild(node);

        Keys = new HashMap<>();

        Keys.put(KeyInput.KEY_A, "A");
        Keys.put(KeyInput.KEY_B, "B");
        Keys.put(KeyInput.KEY_C, "C");
        Keys.put(KeyInput.KEY_D, "D");
        Keys.put(KeyInput.KEY_E, "E");
        Keys.put(KeyInput.KEY_F, "F");
        Keys.put(KeyInput.KEY_G, "G");
        Keys.put(KeyInput.KEY_H, "H");
        Keys.put(KeyInput.KEY_I, "I");
        Keys.put(KeyInput.KEY_J, "J");
        Keys.put(KeyInput.KEY_K, "K");
        Keys.put(KeyInput.KEY_L, "L");
        Keys.put(KeyInput.KEY_M, "M");
        Keys.put(KeyInput.KEY_N, "N");
        Keys.put(KeyInput.KEY_O, "O");
        Keys.put(KeyInput.KEY_P, "P");
        Keys.put(KeyInput.KEY_Q, "Q");
        Keys.put(KeyInput.KEY_R, "R");
        Keys.put(KeyInput.KEY_S, "S");
        Keys.put(KeyInput.KEY_T, "T");
        Keys.put(KeyInput.KEY_U, "U");
        Keys.put(KeyInput.KEY_V, "V");
        Keys.put(KeyInput.KEY_W, "W");
        Keys.put(KeyInput.KEY_X, "X");
        Keys.put(KeyInput.KEY_Y, "Y");
        Keys.put(KeyInput.KEY_Z, "Z");
        Keys.put(KeyInput.KEY_SEMICOLON, ";");
        Keys.put(KeyInput.KEY_APOSTROPHE, "'");
        Keys.put(KeyInput.KEY_COMMA, ",");
        Keys.put(KeyInput.KEY_PERIOD, ".");
        Keys.put(KeyInput.KEY_SLASH, "/");
        Keys.put(KeyInput.KEY_LBRACKET, "[");
        Keys.put(KeyInput.KEY_RBRACKET, "]");
        Keys.put(KeyInput.KEY_1, "1");
        Keys.put(KeyInput.KEY_2, "2");
        Keys.put(KeyInput.KEY_3, "3");
        Keys.put(KeyInput.KEY_4, "4");
        Keys.put(KeyInput.KEY_5, "5");
        Keys.put(KeyInput.KEY_6, "6");
        Keys.put(KeyInput.KEY_7, "7");
        Keys.put(KeyInput.KEY_8, "8");
        Keys.put(KeyInput.KEY_9, "9");

        // ... I hate my life chouses
    }
    
    public void UpdateList(List<Integer> raw){
        sleepKeys.clear();
        for (int i = 0; i < raw.size(); i++) {
            sleepKeys.add(Keys.getOrDefault(raw.get(i),"fahh we might be cooked gang"));
        }
        
    }

    public void PrintOut(boolean sleeping){
        if(sleeping){
            String targetText = sleepKeys.toString().replace("[", "").replace("]", "");

            // Only recreate geometry if the button sequence actually changed!
            if (!targetText.equals(lastRenderedText)) {
                if (node != null) {
                    guinode.detachChild(node); // Drop old "gya" or old list
                }

                // Generate completely pristine layout vectors
                node = ttfFont.getText(targetText, 0, ColorRGBA.White);
                node.setLocalTranslation(500f, 500f, 1f);
                node.setQueueBucket(com.jme3.renderer.queue.RenderQueue.Bucket.Gui);

                // CRITICAL FIX: Make sure it's set to Inherit so it draws immediately!
                node.setCullHint(Spatial.CullHint.Inherit);

                guinode.attachChild(node);
                lastRenderedText = targetText;
            } else {
                // Ensure it stays visible if text didn't change this frame
                node.setCullHint(Spatial.CullHint.Inherit);
            }
        }else {
            node.setCullHint(Spatial.CullHint.Always);
        }

    }
}
