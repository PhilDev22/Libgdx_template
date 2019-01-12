package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Animation bestehend aus mehreren Frames (TextureRegions)
 * mit einer bestimmten Zeit zwischen den Frames
 * @author Philip
 *
 */
public class Animation {
	
	private final TextureRegion[] frames;
	private float frameDuration;
	//private float animationDuration;
	private int frameCount;
	private float currentFrameTime;
	private int currentFrameNr;
	private boolean loop;
	private boolean finished;
	
	protected float[] boundsAnim; //x-offset, y-offset, anim-width, anim-height
	protected float[] sizeObjBackup; //width, height
	protected boolean hasDifferentSize = false;
	
	/** Constructor, storing the frame duration and frames.
	 * 
	 * @param frameDuration the time between frames in seconds.
	 * @param frames the {@link TextureRegion}s representing the frames. */
	public Animation (float frameDuration, boolean loop, TextureRegion... frames) {
		this.loop = loop;
		this.frameDuration = frameDuration;
		//this.animationDuration = frames.length * frameDuration;
		this.frameCount = frames.length;
		this.frames = frames;
		//this.playMode = NORMAL;
	}
	
	public Animation (float frameDuration, boolean loop, float[] boundsAnim,  float[] sizeObj, TextureRegion... frames) {
		this(frameDuration, loop, frames);
		this.hasDifferentSize = true;
		this.sizeObjBackup = sizeObj;
		this.boundsAnim = boundsAnim;
	}

	/**
	 * Berechnet das aktuelle Animationsbild und gibt es zur�ck
	 * @param delta
	 * @return TextureRegion
	 */
	public TextureRegion calculateFrame(float delta) {
		if (!finished) {
			currentFrameTime += delta;
			if (currentFrameTime >= frameDuration) {
				currentFrameTime = 0;
				if (currentFrameNr < frameCount-1) {
					currentFrameNr++;
				} else {
					if (loop)
						currentFrameNr = 0;
					else 
						finished = true;
						
				}
			}
		}
		return frames[currentFrameNr];
	}
	
	/**
	 * Stellt das Aktuelle Bild der Animation ein
	 * @param frameNr
	 */
	public void setCurrentFrame(int frameNr) {
		if (frameNr >= this.frameCount)
			this.currentFrameNr = 0;
		else
			this.currentFrameNr = frameNr;
		
		this.currentFrameTime = 0;
	}
	
	/**
	 * Gibt an ob Animation bis zum letzten Frame durchgelaufen ist.
	 * Funktioniert nur, wenn loop-Modus nicht aktiviert ist
	 * @return
	 */
	public boolean isAnimationFinished() {
		return finished;
	}
	
	/**
	 * Gibt ein beliebiges Bild der Animation zur�ck
	 * @param frameNr
	 * @return
	 */
	public TextureRegion getFrame(int frameNr) {
		if (frameNr < this.frames.length)
			return this.frames[frameNr];
		else
			return this.frames[0];
	}

	public void restart() {
		currentFrameNr = 0;
		finished = false;
	}
}
