package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A Layer for a ParallaxBackground. Should have two or
 * more parts which should be as width as the screen.
 * When the layer is scrolling to the left the parts are rendered directly
 * behind each other. The parts are moving in an endless loop.
 */
public class ParallaxLayer {
	// the Textures of this layer
	private ArrayList<TextureRegion> parts;
	
	private int activeIndex = 0;

	//relative moving speed (in percent) when the whole background is moved
	//0.5f is half as fast as the speed
	private float ratioX;
	private float ratioY;
	//current position
	private float positionX;
	private float positionY;

	// pRatioX	the X moving speed relative to the Background in percent
	// pRatioY	the Y moving speed relative to the Background in percent
	public ParallaxLayer(float pRatioX, float pRatioY) {
		parts = new ArrayList<TextureRegion>(); 
		ratioX = pRatioX;
		ratioY = pRatioY;
		positionX = 0;
		positionY = 0;
	}	
	
	//add a new texture to the layer
	//Width should be at least App.screenWidth
	public void addPart(TextureRegion region) {
		parts.add(region);
	}

	public void setPosition( float x, float y) {
		this.positionX = x;
		this.positionY = y;
	}
	
	public void moveY(float y) {
		this.positionY += y;
	}
	
	// move this layer - called by the ParallaxBackground
	protected void moveXY(float xSpeed, float ySpeed) {
		positionX += xSpeed * ratioX;
		positionY += ySpeed * ratioY;
	}

	//draw two parts at once, attaching the second part to the end of the first
	protected void draw(SpriteBatch sb, float parentX, float parentY) {
		//draw the part of the activeIndex
		sb.draw(parts.get(activeIndex),
				parentX + positionX, 
				parentY + positionY);
		
		//if right border of the part becomes visible draw the next part attached to the one before
		if ((positionX + (parts.get(activeIndex).getRegionWidth() )) < GdxGame.screenWidth) {
			//if arrived at the end of the array, draw first part
			if (activeIndex == parts.size()-1)
				sb.draw(parts.get( 0 ),
						parentX + positionX + (parts.get(activeIndex).getRegionWidth()),
						parentY + positionY);
			else
				sb.draw(parts.get(activeIndex +1 ), 
						parentX + positionX + (parts.get(activeIndex).getRegionWidth()),
						parentY + positionY);
		}
		
		//if the texture of the active index scrolls out of visible area, increase index
		if (positionX <= (-parts.get(activeIndex ).getRegionWidth() )) {
			//reset position to 0
			positionX = 0;
			//increment index
			activeIndex++;
			if (activeIndex >= parts.size()) 
				activeIndex = 0;
		}
	}

}
