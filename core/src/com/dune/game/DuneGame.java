package com.dune.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class DuneGame extends ApplicationAdapter {
    private static class Tank {
        private Vector2 position;
        private Texture texture;
        private float angle;
        private float speed;

        public Tank(float x, float y) {
            this.position = new Vector2(x, y);
            this.texture = new Texture("tank.png");
            this.speed = 200.0f;
        }

        public void update(float dt) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                angle += 180.0f * dt;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                angle -= 180.0f * dt;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                float distance_x = speed * MathUtils.cosDeg(angle) * dt;
                float distance_y = speed * MathUtils.sinDeg(angle) * dt;
                if (position.x + distance_x >= 0 && position.x + distance_x <= Gdx.graphics.getWidth()) position.x += distance_x;
                if (position.y + distance_y >= 0 && position.y + distance_y <= Gdx.graphics.getHeight()) position.y += distance_y;

            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                float distance_x = speed * MathUtils.cosDeg(angle) * dt;
                float distance_y = speed * MathUtils.sinDeg(angle) * dt;
                if (position.x - distance_x >= 0 && position.x - distance_x <= Gdx.graphics.getWidth()) position.x -= distance_x;
                if (position.y - distance_y >= 0 && position.y - distance_y <= Gdx.graphics.getHeight()) position.y -= distance_y;
            }
        }

        public void render(SpriteBatch batch) {
            batch.draw(texture, position.x - 40, position.y - 40, 40, 40, 80, 80, 1, 1, angle, 0, 0, 80, 80, false, false);
        }

        public void dispose() {
            texture.dispose();
        }
    }

    private static class Buff {
        private Vector2 position;
        private Texture texture;

        public Buff() {
            float x = (float) (Math.random() * 1260);
            float y = (float) (Math.random() * 700);
            this.position = new Vector2(x,y);
            this.texture = new Texture("buff.png");
        }

        public void update(Buff buff, Tank tank) {
            if (tank.position.x + 40 >= buff.position.x - 20 && tank.position.x - 40 <= buff.position.x + 20
                    && tank.position.y + 40 >= buff.position.y - 20 && tank.position.y - 40 <= buff.position.y + 20){
                buff.position.x = (float) Math.random() * 1240 + 20;
                buff.position.y = (float) Math.random() * 680 + 20;
                tank.speed += 15;
            }
        }

        public void render(SpriteBatch batch) {
            batch.draw(texture, position.x - 20, position.y - 20);
        }

        public void dispose(){
            texture.dispose();
        }
    }

    private SpriteBatch batch;
    private Tank tank;
    private Buff buff;

    @Override
    public void create() {
        batch = new SpriteBatch();
        tank = new Tank(200, 200);
        buff = new Buff();
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(0, 0.4f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        tank.render(batch);
        buff.render(batch);
        batch.end();
    }

    public void update(float dt) {
        tank.update(dt);
        buff.update(buff, tank);
    }

    @Override
    public void dispose() {
        batch.dispose();
        tank.dispose();
        buff.dispose();
    }

    // Домашнее задание:
    // - Задать координаты точки, и нарисовать в ней круг (любой круг, радиусом пикселей 50)
    // - Если "танк" подъедет вплотную к кругу, то круг должен переместиться в случайную точку

    // - * Нельзя давать танку заезжать за экран
}