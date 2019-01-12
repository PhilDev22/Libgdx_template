package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

/**
 * Erstellt ein Sprite mit Animationen. Es k�nnen Animationen unter einem bestimmten Namen hinzugef�gt werden.
 * Bei mehreren Animationen sollte es die Animation "DEFAULT" geben, auf die zur�ckgesprungen werden kann.
 * 
 * @author Philip
 *
 */
public class AnimatedSprite extends Sprite{

	Map<String, Animation> animations;
	Animation activeAnimation;
	String activeAnimationName;
	TextureRegion currentFrame;

	protected boolean activeAnimFinished;
	protected boolean pause = false;
	
	private String nextAnimation = ""; 
	
	public AnimatedSprite() {
		super();
		animations = new HashMap<String, Animation>();
	}
	
	public void addAnimation(Animation anim, String name) {
		
		animations.put(name, anim);
	}
	
	public void addAnimation( Texture texture, int FRAME_COLS, int FRAME_ROWS, float frameSpeed, String name) {
		int frames = FRAME_COLS * FRAME_ROWS;
		this.addAnimation(texture, FRAME_COLS, FRAME_ROWS, 0, frames, frameSpeed, name, true);
	}
	
	
	public void addAnimation( Texture texture, int FRAME_COLS, int FRAME_ROWS, int startFrame, int frameCount, float frameSpeed, String name, boolean loop) {

		TextureRegion[] frames = new TextureRegion[frameCount];
		
		//this.setSize((texture.getWidth() / FRAME_COLS), (texture.getHeight() / FRAME_ROWS));
				
		TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / FRAME_COLS, texture.getHeight() / FRAME_ROWS);   

        int index = 0;
        int counter = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                	
                	if (counter >= startFrame) {
	                	if (index < frameCount) 
	                		frames[index++] = tmp[i][j];
                	}
                	counter++;
                }
        }
        Animation a = new Animation(frameSpeed, loop, frames); 
        
        animations.put(name, a);
	}
	
	public void setAnimation(String name) {
		if (animations.containsKey(name)) {
			activeAnimation = animations.get(name);
			activeAnimationName = name;
			currentFrame = activeAnimation.getFrame(0);
			
		} else {
			activeAnimationName = name;
			activeAnimFinished = true;
			activeAnimation = null;
			pause = true;
		}
	}
	
	/**
	 * Bestimmt die Animation, die nach einer beendeten Animation (nicht im loop modus), aktiviert werden soll
	 * @param next Name der Animation (z.B. "DEFAULT"; Keine Animation: "")
	 */
	public void setNextAnimation(String next) {
		this.nextAnimation = next;
	}

	public Animation getActiveAnimation() { return activeAnimation; }

	public void pauseAnimation() {
		pause = true;
	}
	
	public void continueAnimation() {
		pause = false;
	}
	
	public void animate(float delta) {
		if (activeAnimation != null) {
			if (!pause) {
				//stateTime += Gdx.graphics.getDeltaTime();
				currentFrame = activeAnimation.calculateFrame(delta);

				activeAnimFinished = activeAnimation.isAnimationFinished();
				if (activeAnimFinished){

					//Wurde eine Animation beendet, wird die n�chste eingeschaltet
					if (this.nextAnimation != "")
						this.setAnimation(nextAnimation);
					
					//this.setAnimation("");	//Animationsprozess pausieren			
				} 
			}
			this.setRegion(currentFrame);
		}
	}
	
	public void draw(SpriteBatch sb) {
		super.draw(sb);
	}
}
