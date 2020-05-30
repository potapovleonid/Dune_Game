package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
<<<<<<< HEAD
=======
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> master
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class BattleMap {
    private class Cell {
        private int cellX, cellY;
        private int resource;
        private float resourceRegenerationRate;
        private float resourceRegenerationTime;

        public Cell(int cellX, int cellY) {
            this.cellX = cellX;
            this.cellY = cellY;
            if(MathUtils.random() < 0.1f) {
                resource = MathUtils.random(1, 3);
            }
            resourceRegenerationRate = MathUtils.random(5.0f) - 4.5f;
            if (resourceRegenerationRate < 0.0f) {
                resourceRegenerationRate = 0.0f;
            } else {
                resourceRegenerationRate *= 20.0f;
                resourceRegenerationRate += 10.0f;
            }
        }

        private void update(float dt) {
            if (resourceRegenerationRate > 0.01f) {
                resourceRegenerationTime += dt;
                if (resourceRegenerationTime > resourceRegenerationRate) {
                    resourceRegenerationTime = 0.0f;
                    resource++;
                    if (resource > 5) {
                        resource = 5;
                    }
                }
            }
        }

        private void render(SpriteBatch batch) {
            if (resource > 0) {
                float scale = 0.5f + resource * 0.2f;
                batch.draw(resourceTexture, cellX * 80, cellY * 80, 40, 40, 80, 80, scale, scale, 0.0f);
            } else {
                if (resourceRegenerationRate > 0.01f) {
                    batch.draw(resourceTexture, cellX * 80, cellY * 80, 40, 40, 80, 80, 0.1f, 0.1f, 0.0f);
                }
            }
        }
    }

    public static final int COLUMNS_COUNT = 16;
    public static final int ROWS_COUNT = 9;
    public static final int CELL_SIZE = 80;

    private TextureRegion grassTexture;
    private TextureRegion resourceTexture;
    private Cell[][] cells;

    public BattleMap() {
        this.grassTexture = Assets.getInstance().getAtlas().findRegion("grass");
        this.resourceTexture = Assets.getInstance().getAtlas().findRegion("resource");
        this.cells = new Cell[COLUMNS_COUNT][ROWS_COUNT];
        for (int i = 0; i < COLUMNS_COUNT; i++) {
            for (int j = 0; j < ROWS_COUNT; j++) {
                cells[i][j] = new Cell(i, j);
<<<<<<< HEAD
=======
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
>>>>>>> master
            }
        }
    }

<<<<<<< HEAD
=======
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> master
    public int getResourceCount(Tank harvester) {
        return cells[harvester.getCellX()][harvester.getCellY()].resource;
    }

    public int harvestResource(Tank harvester, int power) {
        int value = 0;
        if(cells[harvester.getCellX()][harvester.getCellY()].resource >= power) {
            value = power;
            cells[harvester.getCellX()][harvester.getCellY()].resource -= power;
        } else {
            value = cells[harvester.getCellX()][harvester.getCellY()].resource;
            cells[harvester.getCellX()][harvester.getCellY()].resource = 0;
        }
        return value;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < COLUMNS_COUNT; i++) {
            for (int j = 0; j < ROWS_COUNT; j++) {
                batch.draw(grassTexture, i * 80, j * 80);
                cells[i][j].render(batch);
<<<<<<< HEAD
=======
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    public void render(SpriteBatch batch) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                batch.draw(grassTexture, i * 80, j * 80);
                if (data[i][j]) {
                    batch.draw(flowerTexture, i * 80 + 40, j * 80 + 40);
                }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
>>>>>>> master
            }
        }
    }

<<<<<<< HEAD
=======
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> master
    public void update(float dt) {
        for (int i = 0; i < COLUMNS_COUNT; i++) {
            for (int j = 0; j < ROWS_COUNT; j++) {
                cells[i][j].update(dt);
<<<<<<< HEAD
=======
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    public void update(GameObject gameObject) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (Math.abs((i * 80 + 40) - gameObject.position.x) < 32 && Math.abs((j * 80 + 40) - gameObject.position.y) < 32){
                    data[i][j] = false;
                }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
>>>>>>> master
            }
        }
    }
}
