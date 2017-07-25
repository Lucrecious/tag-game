package taggame.player;

import com.badlogic.gdx.graphics.Color;
import com.nilunder.bdx.GameObject;

public class Player extends GameObject {
    public static final Color Orange = Color.ORANGE;
    public static final Color Blue = Color.SKY;

    public void init() {
        setColor();
        components.add(new Movement(this));
    }

    private void setColor() {
        materials.color(new com.nilunder.bdx.utils.Color(Blue));
    }
}
