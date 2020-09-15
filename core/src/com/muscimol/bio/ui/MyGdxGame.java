package com.muscimol.bio.ui;

import com.badlogic.gdx.Game;
import com.muscimol.bio.Lifetime;
import com.muscimol.bio.generate.Map;

public class MyGdxGame extends Game {


	@Override
	public void create () {

		Map.getInstance();

		if(Lifetime.current==null){
			Lifetime.current = new Lifetime();

			Lifetime.current.start();
		}
		this.setScreen(new MapScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
