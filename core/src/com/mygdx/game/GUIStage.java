package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.*;

public class GUIStage {

    public Group grpIngameUI;

    private Stage stage;
    private boolean active;

    public GUIStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void show() {
        active = true;
        grpIngameUI.setVisible(true);
    }

    public void hide() {
        active = false;
        grpIngameUI.setVisible(false);
    }

    public void init() {
        grpIngameUI = new Group();

        // Init Scene2D UI elemnts here
        // and add to grpIngameUI

        stage.addActor(grpIngameUI);
    }

    public void draw(float delta){
        if (active) {
            if (stage != null) {
                stage.act(delta);
                stage.draw();
            }
        }
    }
}
