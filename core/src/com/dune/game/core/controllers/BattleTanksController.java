package com.dune.game.core.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dune.game.core.ObjectPool;
import com.dune.game.core.units.BattleTank;
import com.dune.game.core.units.Owner;

public class BattleTanksController extends ObjectPool<BattleTank> {
    private GameController gc;

    @Override
    protected BattleTank newObject() {
        return new BattleTank(gc);
    }

    public BattleTanksController(GameController gc) {
        this.gc = gc;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).render(batch);
        }
    }

    public void setup(float x, float y, Owner ownerType) {
        BattleTank t = activateObject();
        t.setup(ownerType, x, y);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}
