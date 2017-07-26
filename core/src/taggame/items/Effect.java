package taggame.items;

import com.nilunder.bdx.Bdx;
import taggame.player.Player;

public abstract class Effect {
    protected final Player player;

    private final float creationTime;

    public Effect(final Player player, final float creationTime) {
        this.player = player;
        this.creationTime = creationTime;
    }

    public boolean finished() {
        return Bdx.time - creationTime > duration();
    }

    public abstract void apply();
    public abstract void destroy();
    protected abstract float duration();
}
