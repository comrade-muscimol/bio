package com.muscimol.bio.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.muscimol.bio.generate.Map;

public class MapScreen implements Screen {
    boolean test = true;
    private long lastUpdated;

    private int cell_size = 32;

    private Game game;

    private TextureAtlas atlas;
    private Skin skin;

    private Stage stage;


    public MapScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ExtendViewport(480, 640));

        atlas = new TextureAtlas(Gdx.files.internal("cells/map.atlas"));
        skin = new Skin(Gdx.files.internal("cells/map.json"), atlas);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {

        if(test){
            try {   stage.getRoot().findActor("map").clearListeners();              }catch (Exception e){e.printStackTrace();}
            try {   stage.getRoot().removeActor(stage.getRoot().findActor("map"));  }catch (Exception e){e.printStackTrace();}


            try {   stage.getRoot().addActor(generate_map());                              }catch (Exception e){e.printStackTrace();}

            lastUpdated = Map.getInstance().getLastUpdateTime();
            test = false;
        }

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        atlas.dispose();
        stage.dispose();
    }

    private Group generate_map(){
        Group out = new Group();
        out.setName("map");
        out.setBounds(0, 0, stage.getHeight(), stage.getWidth());

        Table table = new Table();
        table.defaults().width(cell_size).height(cell_size);


        for(int i = 0; i<Map.getInstance().getMap().size(); i++){
            for(int o = 0; o<Map.getInstance().getMap().get(i).size();o++){

                table.add(
                        new Image(atlas.findRegion(Map.getInstance().get(i, o).getImageName()))
                );

            }
            table.row();
        }

        ScrollPane pane = new ScrollPane(table);
        pane.setScrollingDisabled(true, true);
        pane.setBounds(0 ,0 ,out.getHeight() , out.getWidth());
        out.addActor(pane);

        return out;
    }
}
