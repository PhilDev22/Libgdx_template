package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpawnObject extends AnimatedSprite {

    private boolean spawned;
    private float alpha;
    private boolean isFadeIn;
    //movement direction
    private boolean moveVertical;
    private boolean moveHorizontal;
    //bouncing
    private float yValue;
    private float bouncingSpeed = 1.0f;
    private float xSpeed;
    private float bouncingHeightValue = 2.0f;

    public SpawnObject() {}

    public void setSpawned(boolean spawned) {
        this.spawned = spawned;
    }

    public boolean isSpawned() {
        return spawned;
    }

    public void setMoveVertical(boolean move, float bouncingSpeed, float bouncingHeight) {
        if (move) {
            this.bouncingSpeed = bouncingSpeed;
            this.bouncingHeightValue = bouncingHeight;
        }
        moveVertical = move;
    }

    public void setMoveHorizontal(boolean move, float xSpeed) {
        if (move)
            this.xSpeed = xSpeed;
        moveHorizontal = move;
    }

    public void setMovement(boolean moveX, boolean moveY) {
        moveHorizontal = moveX;
        moveVertical = moveY;
    }

    public void setFadeIn() {
        isFadeIn = true;
        alpha = 0.0f;
    }

    public void fadeIn(float delta) {
        alpha += delta * 2.0f;
        if (alpha >= 1.0f){
            alpha = 1.0f;
            isFadeIn = false;
        }
        setAlpha(alpha);
    }

    public void kill(SpawnPool pool) {
        pool.returnToPool(this);
        setPosition(GdxGame.screenWidth, 0);
        yValue = 0.0f;
    }

    private void move(float delta) {
        //move up and down
        float yTrans = 0.0f;
        if (moveVertical) {
            yValue += bouncingSpeed * delta;
            yTrans = (float) Math.sin(yValue) / bouncingHeightValue;
        }
        //move left
        float xTrans = 0.0f;
        if (moveHorizontal)
            xTrans = xSpeed * delta;
        //TODO: if moveVertical?
        translate(xTrans, yTrans);
    }

    public void update(float delta) {
        if (spawned) {
            move(delta);
            animate(delta);
        }
    }

    @Override
    public void draw(SpriteBatch sb) {
        if (spawned)
            super.draw(sb);
    }
}
