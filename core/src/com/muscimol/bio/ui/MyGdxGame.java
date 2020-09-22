package com.muscimol.bio.ui;

import com.badlogic.gdx.Game;
import com.muscimol.bio.Lifetime;
import com.muscimol.bio.generate.Map;

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
