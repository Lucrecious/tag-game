package taggame.items;

import com.nilunder.bdx.utils.Color;
import taggame.GameState;
import taggame.player.Player;

public class Ghost extends Effect {
    public Ghost(Player player, float creationTime) {
        super(player, creationTime);
    }

    @Override
    public void apply() {
        player.collisionMask(GameState.CMGhost);
        Color color = player.materials.get(0).color();
        color.a = 0.5f;
        player.materials.color(color);
    }

    @Override
    public void destroy() {
        Color color = player.materials.get(0).color();
        color.a = 1;
        player.materials.color(color);
        player.collisionMask(GameState.CMInnerWall);
    }

    @Override
    protected float duration() {
        return 5;
    }
}
