package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParallaxBackground {
	 //the layers of this background
	private ParallaxLayer[] layers;
	protected float x;
	protected float y;
	
	//create a background
	public ParallaxBackground(ParallaxLayer[] pLayers) {
		layers = pLayers;
		y = 0;
		x = 0;
	}

	//render the parallax background
	public void draw(SpriteBatch sb) {
		//batch.setProjectionMatrix(camera.projection);
		for (int i=1; i<layers.length; i++) {
			layers[i].draw(sb, this.x, this.y);
		}
	}

	// move the parallax background on the x-axis and/or y-axis
	public void moveXY(float xSpeed, float ySpeed, float delta) {
		for (int i=0; i<layers.length; i++) {
			layers[i].moveXY(xSpeed * delta, ySpeed * delta);
		}
	}
	
	public void setXPos(float x) {
		this.x = x;
	}
	
	public void setYPos(float y) {
		this.y = y;
	}
	
	public void resetPos() {
		this.x = 0;
		this.y = 0;
	}
}
