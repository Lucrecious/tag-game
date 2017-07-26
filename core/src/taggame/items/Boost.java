package taggame.items;

import taggame.player.Player;

public class Boost extends Effect {
    public Boost(final Player player, final float creationTime) {
        super(player, creationTime);
    }

    @Override
    public void apply() {
        player.currentSpeed = 2*player.maxSpeed;
    }

    @Override
    public void destroy() {
        player.currentSpeed = player.maxSpeed;
    }

    @Override
    protected float duration() {
        return 1;
    }
}
