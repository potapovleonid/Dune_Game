package com.dune.game.core;

import com.badlogic.gdx.math.Vector2;
import com.dune.game.core.controllers.GameController;
import com.dune.game.core.units.AbstractUnit;
import com.dune.game.core.units.UnitType;

public class AiLogic {
    private GameController gc;

    public AiLogic(GameController gc) {
        this.gc = gc;
    }

    private void moveRandomPosition(AbstractUnit abstractUnit) {
        float posY = (float) Math.random() * (720 - 50);
        float posX = (float) Math.random() * (1280 - 50);
        Vector2 move = new Vector2(posX,
                posY);
        abstractUnit.commandMoveTo(move);
    }

    public void update(float dt) {
        for (int i = 0; i < gc.getUnitsController().getAiUnits().size(); i++) {
            for (int j = 0; j < gc.getUnitsController().getPlayerUnits().size(); j++) {
                AbstractUnit unit1 = gc.getUnitsController().getAiUnits().get(i);
                AbstractUnit unit2 = gc.getUnitsController().getPlayerUnits().get(j);

                if (unit1.getTarget() == null) {
                    if (Math.abs(unit1.getPosition().dst(unit2.getPosition())) <= 150
                            && unit1.getUnitType() == UnitType.BATTLE_TANK) {
                        unit1.commandAttack(unit2);
                    } else if (unit1.getUnitType() == UnitType.BATTLE_TANK){
                        moveRandomPosition(unit1);
                    }

                    if (unit1.getUnitType() == UnitType.HARVESTER) {
                        if (Math.abs(unit1.getPosition().dst(unit1.getDestination())) <= 20) {
                            moveRandomPosition(unit1);
                        }
                        if (unit1.gc.getMap().getResourceCount(unit1.getPosition()) > 0) {
                            unit1.commandMoveTo(unit1.getPosition());
                        }
                    }
                }
            }
        }
    }

}
