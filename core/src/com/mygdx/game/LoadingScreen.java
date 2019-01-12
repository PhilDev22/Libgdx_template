package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

//Shows as long as the AssetManager is loading the resources.
//Loads GameplayScreen afterwards
public class LoadingScreen implements Screen {

	//reference to the main Game class
	private GdxGame game;
	//stage for the image
	private Stage stage;
	//"Loading" image
	private Image image;
	private String backgroundPath = "loading_logo.png";
	
	// constructor
	public LoadingScreen(GdxGame game){
		this.game = game;
		init();
	}

	//create stage and image
	private void init() {
		//create StretchViewport to handle resizing of game window
		StretchViewport viewport = new StretchViewport(
				GdxGame.screenWidth, GdxGame.screenHeight);
		//instantiate Stage with viewport
		stage = new Stage(viewport);

		//set a loading image centered
    	image = new Image(new Texture(Gdx.files.internal( backgroundPath )));
    	image.setPosition(
			(GdxGame.screenWidth / 2) - (image.getWidth() / 2),
			(GdxGame.screenHeight / 2) - (image.getHeight() / 2));//(0, -1328);
		//add image to stage
    	stage.addActor(image);
	}
	
	@Override
	public void render(float delta) {
		//wait for resources to load
		if (GdxGame.resources.update()) {
			//initialize assets and screens when AssetManager finished
			GdxGame.resources.initLoadedAssets();
			//load GameplayScreen
			game.gameplayScreen.init();
			//show GameplayScreen
			game.setScreen( game.gameplayScreen );
		}
		//Set background color
		Gdx.gl.glClearColor(0.f, 0.f, 0.f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//render the Stage including the loading image
        stage.act(delta);
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		//stretch viewport when window is being resized
		stage.getViewport().update(width, height, false);
	}

	@Override
	public void show() {
		stage.addAction(Actions.sequence( Actions.fadeOut(0.0f),Actions.fadeIn(0.5f))); 			 	
	}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		stage.dispose();		
	}
}
