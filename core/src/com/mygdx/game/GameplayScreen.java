package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameplayScreen implements Screen {

	//reference to the main Game class
	public GdxGame game;
	//SpriteBatch for rendering ingame world
	private SpriteBatch spriteBatch;
	//class for initialization, update and rendering of gameplay
	private Gameplay gamePlay;
	//stage for rendering GUI
	private GUIStage guiStage;
	//handling input (keyboard, controller, mouse)
	private GameInput gameInput;
	//putting GUIStage-input and GameInput together
	private InputMultiplexer ingameUI;

	public GameplayScreen(GdxGame game){
		//keeping a reference to the main game class
		this.game = game;
	}
	
	public void init() {
		//instantiate Spritebatch to render ingame world
		spriteBatch = new SpriteBatch();
		//initialize UI
		StretchViewport viewport = new StretchViewport(GdxGame.screenWidth, GdxGame.screenHeight);
		//create stage for gui elements with given viewport
		Stage uiStage = new Stage(viewport);
		guiStage = new GUIStage(uiStage);
		guiStage.init();

		initGame(); //Todo: call initGame() from a Menu
	}

	private void initGame() {
		//load new gameplay instance
		gamePlay  = new Gameplay();
		//handler for non-stage inputs (keyboard, mouse, controller)
		gameInput = new GameInput(this, gamePlay);
		//multiplexer for handling both input classes (guiStage and gameInput) as one input processor
		ingameUI = new InputMultiplexer();
		ingameUI.addProcessor(guiStage.getStage()); //for handling dialogs which use a stage (Actors)
		ingameUI.addProcessor(gameInput); //for handling input without stage
		//activate GUI
		guiStage.show();
		Gdx.input.setInputProcessor(ingameUI);
		//start gameplay
		this.gamePlay.start();
	}

	private void resumeGameplay() {
		Gdx.input.setInputProcessor(ingameUI);
		gamePlay.resume();
	}

	@Override
	//Rendering und Update
	public void render(float delta) {
		//update gamePlay
		if (gamePlay != null) {
			gamePlay.update(delta);
		}

		//render gamePlay
		Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
			if (gamePlay != null)
				gamePlay.draw(spriteBatch);
		spriteBatch.end();

		//render GUI
		if (guiStage != null)
			guiStage.draw(delta);
	}

	@Override
	public void resize(int width, int height) {
		//stretch/crop SpriteBatch on a bigger/smaller screen resolution
		spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, GdxGame.screenWidth, GdxGame.screenHeight);
		//get the ratio to stretch/crop on a different resolution
		GdxGame.initGraphicRatio();
	}

	@Override
	public void show() {
		//resume game
		if (gamePlay != null)
			gamePlay.resume();
		//show ingame UI
		guiStage.show();
	}

	@Override
	public void hide() {
		if (gamePlay != null)
			gamePlay.pause();
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}
}
