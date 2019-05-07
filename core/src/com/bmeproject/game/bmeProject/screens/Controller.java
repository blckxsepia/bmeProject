package com.bmeproject.game.bmeProject.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bmeproject.game.BMEProject;

public class Controller
{
	// ===================================
	// ATTRIBUTES
	// ===================================

	protected Stage stage;

	// ===================================
	// METHODS
	// ===================================

	protected void init(SpriteBatch spriteBatch)
	{
		stage = new Stage(new ScreenViewport(), spriteBatch);
		stage.setDebugAll(BMEProject.DEBUG);
		Gdx.input.setInputProcessor(stage);
	}

	public void update(float delta)
	{
		stage.act(delta);
	}

	void dispose()
	{
		stage.dispose();
	}
}
