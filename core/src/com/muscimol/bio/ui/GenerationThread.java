package com.muscimol.bio.ui;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.muscimol.bio.gen.Map;

public class GenerationThread extends Thread {
    private Game game;
    private Map map;

    GenerationThread(Game game, Map map){
        this.game = game;
        this.map = map;
    }
    @Override
    public void run() {

        map.generate_default(game);
        new SetScreen(game).start();

    }

    class SetScreen extends Thread{
        Game game;

        SetScreen(Game game){
            this.game = game;
        }
        @Override
        public void run() {
            Gdx.app.postRunnable(new Runnable(){
                @Override
                public void run() {
                    game.setScreen(new MapScreen(game, map));
                }
            });
        }
    }
}
