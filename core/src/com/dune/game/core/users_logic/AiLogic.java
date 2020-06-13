package com.dune.game.core.users_logic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dune.game.core.BattleMap;
import com.dune.game.core.GameController;
import com.dune.game.core.units.AbstractUnit;
import com.dune.game.core.units.BattleTank;
import com.dune.game.core.units.Harvester;
import com.dune.game.core.units.types.Owner;
import com.dune.game.core.units.types.UnitType;

import java.util.ArrayList;
import java.util.List;

public class AiLogic extends BaseLogic {
    private float timer;

    private List<BattleTank> tmpAiBattleTanks;
    private List<Harvester> tmpAiHarvesters;
    private List<Harvester> tmpPlayerHarvesters;
    private List<BattleTank> tmpPlayerBattleTanks;

    public AiLogic(GameController gc) {
        this.gc = gc;
        this.money = 1000;
        this.unitsCount = 10;
        this.unitsMaxCount = 100;
        this.ownerType = Owner.AI;
        this.tmpAiBattleTanks = new ArrayList<>();
        this.tmpAiHarvesters = new ArrayList<>();
        this.tmpPlayerHarvesters = new ArrayList<>();
        this.tmpPlayerBattleTanks = new ArrayList<>();
        this.timer = 10.0f;
    }

    public void update(float dt) {
        timer += dt;
        if (timer > 2.0f) {
            timer = 0.0f;
            gc.getUnitsController().collectTanks(tmpAiBattleTanks, gc.getUnitsController().getAiUnits(), UnitType.BATTLE_TANK);
            gc.getUnitsController().collectTanks(tmpAiHarvesters, gc.getUnitsController().getAiUnits(), UnitType.HARVESTER);
            gc.getUnitsController().collectTanks(tmpPlayerHarvesters, gc.getUnitsController().getPlayerUnits(), UnitType.HARVESTER);
            gc.getUnitsController().collectTanks(tmpPlayerBattleTanks, gc.getUnitsController().getPlayerUnits(), UnitType.BATTLE_TANK);
            for (int i = 0; i < tmpAiBattleTanks.size(); i++) {
                BattleTank aiBattleTank = tmpAiBattleTanks.get(i);
                aiBattleTank.commandAttack(findNearestTarget(aiBattleTank, tmpPlayerBattleTanks));
            }

            for (int i = 0; i < tmpAiHarvesters.size(); i++) {
                findResource(tmpAiHarvesters.get(i));
            }
        }
    }

    public <T extends AbstractUnit> T findNearestTarget(AbstractUnit currentTank, List<T> possibleTargetList) {
        T target = null;
        float minDist = 1000000.0f;
        for (int i = 0; i < possibleTargetList.size(); i++) {
            T possibleTarget = possibleTargetList.get(i);
            float currentDst = currentTank.getPosition().dst(possibleTarget.getPosition());
            if (currentDst < minDist) {
                target = possibleTarget;
                minDist = currentDst;
            }
        }
        return target;
    }

    public void findResource(Harvester harvester) {
        if (harvester.getContainer() == harvester.getContainerCapacity() - 1){
            harvester.commandMoveTo(new Vector2(860, 460));
            return;
        }

        if (gc.getMap().getResourceCount(harvester.getPosition()) > 0) {
            return;
        }

        Vector2 minDst = new Vector2(MathUtils.random(BattleMap.MAP_WIDTH_PX), MathUtils.random(BattleMap.MAP_HEIGHT_PX));
        for (int i = 1; i < BattleMap.getColumnsCount() - 1; i++) {
            for (int j = 1; j < BattleMap.getRowsCount() - 1; j++) {
                if (gc.getMap().getResourceCount(i, j) > 0) {
                    if (Math.abs(harvester.getPosition().dst(i * BattleMap.getCellSize(), j * BattleMap.getCellSize()))
                            < Math.abs(harvester.getPosition().dst(minDst)))
                        minDst.set(i * BattleMap.getCellSize() + BattleMap.getCellSize() / 2, j * BattleMap.getCellSize() + BattleMap.getCellSize() / 2);
                }
            }
        }
        harvester.commandMoveTo(minDst);
    }
}
