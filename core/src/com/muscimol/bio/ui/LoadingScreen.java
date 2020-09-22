package com.muscimol.bio.ui;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import com.badlogic.gdx.graphics.Color;
import com.muscimol.bio.generate.GeneratorDefaultSettings;
import com.muscimol.bio.generate.Map;

import java.util.Date;


public class LoadingScreen implements Screen {

    public static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.:;,{}\"´`'<>";

    
    private Map map;

    private long last_updated;

    private Game game;

    private TextureAtlas atlas;
    private Skin skin;

    private Stage stage;

    private BitmapFont raleway_medium_32_EEDFDF;


    LoadingScreen (Game game){
        this.game = game;

        this.map = new Map(GeneratorDefaultSettings.size);

        new GenerationThread(game, map).start();

    }

    @Override
    public void show() {
        stage = new Stage(new ExtendViewport(800, 600));

        atlas = new TextureAtlas(Gdx.files.internal("loading/loading.atlas"));
        skin = new Skin(Gdx.files.internal("loading/loading.json"), atlas);

        raleway_medium_32_EEDFDF = createFont(32, new Color(238 / 255f, 223 / 255f, 223 / 255f, 1), "font/Raleway-Medium.ttf");
        last_updated = new Date().getTime()-1000;

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {


        if(new Date().getTime()-last_updated>1000) {
            Gdx.gl.glClearColor(0,1,1,1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            try {
                stage.getRoot().findActor("bars").clearListeners();
                stage.getRoot().removeActor(stage.getRoot().findActor("bars"));

            } catch (Exception e) {

                e.printStackTrace();
            }

            try {
                Group bars = generateBars();
                stage.getRoot().addActor(bars);

            } catch (Exception e) {
                e.printStackTrace();
            }


            last_updated = new Date().getTime();
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
        stage.dispose();

        atlas.dispose();
        skin.dispose();

        raleway_medium_32_EEDFDF.dispose();

    }


        

    
    private Group generateBars(){
        float x = 0f;
        float height = (float)64/2;
        float padding = (float)8/2;


        Group out = new Group();
        out.setName("bars");

        Group reducent_bar;
        Group producent_bar;
        Group consument_1_bar;
        Group consument_2_bar;
        Group consument_3_bar;
        Group consument_4_bar;

        reducent_bar = generateBar(Type.REDUCENT, ((double)map.reducent_done/(double)map.reducent_all)*100, map.reducent_done, map.reducent_all);
        producent_bar = generateBar(Type.PRODUCENT, ((double)map.producent_done/(double)map.producent_all)*100, map.producent_done, map.producent_all);
        consument_1_bar = generateBar(Type.CONSUMENT_1, ((double)map.consument_1_done/(double)map.consument_1_all)*100, map.consument_1_done, map.consument_1_all);
        consument_2_bar = generateBar(Type.CONSUMENT_2, ((double)map.consument_2_done/(double)map.consument_2_all)*100, map.consument_2_done, map.consument_2_all);
        consument_3_bar = generateBar(Type.CONSUMENT_3, ((double)map.consument_3_done/(double)map.consument_3_all)*100, map.consument_3_done, map.consument_3_all);
        consument_4_bar = generateBar(Type.CONSUMENT_4, ((double)map.consument_4_done/(double)map.consument_4_all)*100, map.consument_4_done, map.consument_4_all);

        consument_4_bar.setPosition(x, 0);
        consument_3_bar.setPosition(x, (height*1)+(padding*1));
        consument_2_bar.setPosition(x, (height*2)+(padding*2));
        consument_1_bar.setPosition(x, (height*3)+(padding*3));
        producent_bar.setPosition(x, (height*4)+(padding*4));
        reducent_bar.setPosition(x, (height*5)+(padding*5));

        out.addActor(reducent_bar);
        out.addActor(producent_bar);
        out.addActor(consument_1_bar);
        out.addActor(consument_2_bar);
        out.addActor(consument_3_bar);
        out.addActor(consument_4_bar);


        System.out.println("position");
        System.out.println("stage:x-y "+stage.getWidth()+" "+stage.getHeight());
        System.out.println("out:x-y "+out.getHeight()+" "+out.getWidth());

        out.setWidth(reducent_bar.getWidth());
        out.setHeight((height*5)+(padding*5));

        out.setPosition(
                ((stage.getWidth()/(float)2) - (out.getWidth()/(float)2)),
                ((stage.getHeight()/(float)2) - (out.getHeight()/(float)2))
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

        float y = 0;
        float height = (float)64/2;
        float width = (float)32/2;
        float padding = (float)8/2;

        for(int i = 0; i<20; i++){
            Image segment;

            if(filled_segments>i){
                 segment = new Image(atlas.findRegion(filled_id));
            }else{
                segment = new Image(atlas.findRegion(empty_id));
            }

            float x = i*(width+padding);
            segment.setBounds(x, y, width, height);
            out.addActor(segment);
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

        out.setHeight(height);
        out.setWidth((20*(width+padding))+done_label.getWidth());
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
