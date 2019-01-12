package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader.BitmapFontParameter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Resources extends AssetManager {

    //paths to assets (Core/Assets/)
    private String fntBangers = "fonts/Bangers_bitmap.fnt";
    public String imgSpaceship = "spaceship_tileset.png";
    //font instance for global usage
    public BitmapFont font1;

    public void loadAssets() {
        //set BitmapFont Filter
        BitmapFontParameter fontParam = new BitmapFontParameter();
        fontParam.magFilter = Texture.TextureFilter.Linear;
        fontParam.minFilter = Texture.TextureFilter.Linear;
        //load font
        load(fntBangers, BitmapFont.class, fontParam);
        //load images
        load(imgSpaceship, Texture.class);

        System.out.println("FINISHED LOADING ASSETS!");
    }

    //initializations that have to be done after loadAssets() is finished
    public void initLoadedAssets() {
        //create BitmapFont
        font1 = get(fntBangers, BitmapFont.class);
        font1.setColor(Color.WHITE);
    }
}
