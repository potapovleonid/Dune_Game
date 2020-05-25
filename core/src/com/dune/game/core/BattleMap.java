package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.graalvm.compiler.loop.MathUtil;

import java.util.Random;

public class BattleMap {
    private TextureRegion grassTexture;
    private TextureRegion flowerTexture;
    private boolean data[][];

    //    public BattleMap() {
//        this.grassTexture = Assets.getInstance().getAtlas().findRegion("grass");
//    }
    public BattleMap() {
        this.grassTexture = Assets.getInstance().getAtlas().findRegion("grass");
        this.flowerTexture = Assets.getInstance().getAtlas().findRegion("flower");
        data = new boolean[16][9];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (Math.random() > 0.5) {
                    data[i][j] = true;
                } else data[i][j] = false;
                System.out.println(data[i][j]);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                batch.draw(grassTexture, i * 80, j * 80);
                if (data[i][j]) {
                    batch.draw(flowerTexture, i * 80 + 40, j * 80 + 40);
                }
            }
        }
    }

    public void update(GameObject gameObject) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (Math.abs((i * 80 + 40) - gameObject.position.x) < 32 && Math.abs((j * 80 + 40) - gameObject.position.y) < 32){
                    data[i][j] = false;
                }
            }
        }
    }
}
