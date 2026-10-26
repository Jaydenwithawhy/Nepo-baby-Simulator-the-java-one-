package com.jayorehla.nepobaby.game.Ui;

import com.atr.jme.font.TrueTypeFont;
import com.atr.jme.font.asset.TrueTypeKey;
import com.atr.jme.font.asset.TrueTypeKeyBMP;
import com.atr.jme.font.asset.TrueTypeLoader;
import com.atr.jme.font.shape.TrueTypeNode;
import com.atr.jme.font.util.Style;
import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class Text {

    TrueTypeKey newTimeBaq;

    AssetManager ass;

    TrueTypeNode node;

    public TrueTypeFont ttfFont;

    Node guinode;

    public Text(AssetManager ass, Node guiroot ){
        this.ass = ass;
        this.guinode = guiroot;

        ass.registerLoader(TrueTypeLoader.class,"ttf");
        newTimeBaq = new TrueTypeKeyBMP("Interface/Times_New_Baq.ttf", Style.Plain, 25);
        ttfFont = (TrueTypeFont) ass.loadAsset(newTimeBaq);
        ttfFont.getText("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 .,;:'/[]->", 0, ColorRGBA.White);

    }
}
