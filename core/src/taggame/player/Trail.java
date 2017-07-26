package taggame.player;

import com.nilunder.bdx.Bdx;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.utils.Color;

public class Trail extends GameObject {
    @Override
    public void main() {
        Color color = materials.get(0).color();
        color.a -= Bdx.TICK_TIME;
        color.a = color.a > 0 ? color.a : 0;
        materials.color(color);

        if (color.a < 0.01f) {
            end();
        }
    }
}
