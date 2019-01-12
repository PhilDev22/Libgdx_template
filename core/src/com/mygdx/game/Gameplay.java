package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gameplay {

    AnimatedSprite ship = new AnimatedSprite();

    public Gameplay() {
        //initialization
        ship.addAnimation(GdxGame.resources.get(
                GdxGame.resources.imgSpaceship, Texture.class),
                4, 8, 4, 4, 0.05f, "DEFAULT", true);
        ship.setAnimation("DEFAULT");
        ship.setSize(256, 128);
        ship.setPosition(550, 300);
    }

    public void start() {

    }

    public void resume(){

    }

    public void pause() {

    }

    public void update(float delta){
        ship.animate(delta);
    }

    public void draw(SpriteBatch sb) {
        ship.draw(sb);
    }

    public void touchDown(float screenX, float screenY) {

    }

    public void touchUp(float screenX, float screenY) {

    }
}
