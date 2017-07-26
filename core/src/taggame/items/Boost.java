package taggame.items;

import taggame.player.Player;
import taggame.player.Trailer;

public class Boost extends Effect {
    public Boost(final Player player, final float creationTime) {
        super(player, creationTime);
    }

    Trailer trailer;

    @Override
    public void apply() {
        player.currentSpeed = 2*player.maxSpeed;
        player.components.add(trailer = new Trailer(player, "Trail", player.materials.get(0).color()));
    }

    @Override
    public void destroy() {
        player.currentSpeed = player.maxSpeed;
        player.components.remove(trailer);
    }

    @Override
    protected float duration() {
        return 1;
    }
}
