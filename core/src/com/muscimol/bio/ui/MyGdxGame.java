package com.muscimol.bio.ui;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {


	@Override
	public void create () {

		this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
