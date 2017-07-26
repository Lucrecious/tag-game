package taggame.items;

import com.nilunder.bdx.Bdx;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.utils.Color;
import taggame.player.Player;

public class Item extends GameObject {
    private Class<?> effect;

    public void init(Class<?> effect) {
        this.effect = effect;
        setColor();
    }

    public Effect effect(Player player) {
        try {
            return (Effect)effect.getConstructor(Player.class, float.class).newInstance(player, Bdx.time);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private void setColor() {
        if (effect.isAssignableFrom(Boost.class)) {
            materials.color(new Color(Color.YELLOW));
        }
    }
}
