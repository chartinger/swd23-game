package at.campus02.swd.game.strategy.input;

import at.campus02.swd.game.gameobjects.Entity;

public class MeleeAttackCommand implements AttackCommand {

    private final Entity attacker;

    public MeleeAttackCommand(Entity attacker) {
        this.attacker = attacker;
    }

    @Override
    public void attack() {
        attacker.attack();
    }
}
