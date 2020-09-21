package com.muscimol.bio.ui;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import com.badlogic.gdx.graphics.Color;
import com.muscimol.bio.generate.Map;


public class LoadingScreen implements Screen {

    public static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.:;,{}\"´`'<>";

    

    

    private Game game;

    private TextureAtlas atlas;
    private Skin skin;

    private Stage stage;

    private BitmapFont raleway_medium_32_EEDFDF;


    LoadingScreen (Game game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ExtendViewport(800, 600));

        atlas = new TextureAtlas(Gdx.files.internal("cells/map.atlas"));
        skin = new Skin(Gdx.files.internal("cells/map.json"), atlas);

        raleway_medium_32_EEDFDF = createFont(32, new Color(238 / 255f, 223 / 255f, 223 / 255f, 1), "font/Raleway-Medium.ttf");

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        if(true) {

            try {
                stage.getRoot().findActor("bars").clearListeners();
                stage.getRoot().removeActor(stage.getRoot().findActor("bars"));

            }catch (Exception e){

                e.printStackTrace();
            }

            try {       stage.getRoot().addActor(generateBars());    }catch (Exception e){e.printStackTrace();}

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

    }


        

    
    private Group generateBars(){

        int x = 0;
        int height = 64;
        int padding = 8;


        Group out = new Group();
        out.setName("bars");


        Group reducent_bar;
        Group producent_bar;
        Group consument_1_bar;
        Group consument_2_bar;
        Group consument_3_bar;
        Group consument_4_bar;

        reducent_bar = generateBar(Type.REDUCENT, 40, 40, 500);
        producent_bar = generateBar(Type.PRODUCENT, 35, 35, 500);
        consument_1_bar = generateBar(Type.CONSUMENT_1, 1, 1, 500);
        consument_2_bar = generateBar(Type.CONSUMENT_2, 100, 100, 500);
        consument_3_bar = generateBar(Type.CONSUMENT_3, 0, 0, 500);
        consument_4_bar = generateBar(Type.CONSUMENT_4, 15, 15, 500);

        reducent_bar.setPosition(x, 0);
        producent_bar.setPosition(x, (height*1)+(padding*1));
        consument_1_bar.setPosition(x, (height*2)+(padding*2));
        consument_2_bar.setPosition(x, (height*3)+(padding*3));
        consument_3_bar.setPosition(x, (height*4)+(padding*4));
        consument_4_bar.setPosition(x, (height*5)+(padding*5));

        out.addActor(reducent_bar);
        out.addActor(producent_bar);
        out.addActor(consument_1_bar);
        out.addActor(consument_2_bar);
        out.addActor(consument_3_bar);
        out.addActor(consument_4_bar);


        out.setPosition(
                ((stage.getWidth()/2)- (out.getWidth()/2)),
                ((stage.getHeight()/2)- (out.getHeight()/2))
        );

        return out;
    }
    private Group generateBar(Type type, double percent, int done, int all){
        Group out = new Group();

        String empty_id;
        String filled_id;

        empty_id = "empty_bar_segment";

        int filled_segments = (int) (percent/5);

        switch (type){
            case REDUCENT:
                filled_id = "reducent_bar_segment";
                out.setName("reducent_bar");
                break;
            case PRODUCENT:
                filled_id = "producent_bar_segment";
                out.setName("producent_bar");
                break;
            case CONSUMENT_1:
                filled_id = "consument_1_bar_segment";
                out.setName("consument_1_bar");
                break;
            case CONSUMENT_2:
                filled_id = "consument_2_bar_segment";
                out.setName("consument_2_bar");
                break;
            case CONSUMENT_3:
                filled_id = "consument_3_bar_segment";
                out.setName("consument_3_bar");
                break;
            case CONSUMENT_4:
                filled_id = "consument_4_bar_segment";
                out.setName("consument_4_bar");
                break;
            default:
                filled_id = "empty_bar_segment";
                out.setName("error_bar");
        }


        int y = 0;
        int height = 64;
        int width = 32;
        int padding = 8;

        for(int i = 0; i<20; i++){
            Image segment;

            if(filled_segments>i){
                 segment = new Image(atlas.findRegion(filled_id));
            }else{
                segment = new Image(atlas.findRegion(empty_id));
            }

            int x = i*(width+padding);
            segment.setBounds(x, y, width, height);
        }

        Label.LabelStyle done_labelStyle = new Label.LabelStyle();
        done_labelStyle.font = raleway_medium_32_EEDFDF;
        Label done_label = new Label(
                done+"/"+all,
                done_labelStyle
        );
        done_label.setAlignment(Align.left);
        done_label.setWrap(false);
        done_label.setBounds((20*(width+padding)), y, width, height);
        out.addActor(done_label);


        return out;

    }


    private BitmapFont createFont(int size, Color color, String font_internal_path) {
        FileHandle fontFile = Gdx.files.internal(font_internal_path);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        parameter.characters = FONT_CHARACTERS;

        BitmapFont font = generator.generateFont(parameter);

        generator.dispose();
        return font;
    }



    enum Type{
        REDUCENT,
        PRODUCENT,
        CONSUMENT_1,
        CONSUMENT_2,
        CONSUMENT_3,
        CONSUMENT_4,
    }
}
