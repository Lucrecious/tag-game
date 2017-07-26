package taggame.player;

import com.badlogic.gdx.graphics.Color;
import com.nilunder.bdx.GameObject;
import taggame.GameState;

import java.util.HashMap;

public class Player extends GameObject {
    public static final Color Orange = Color.ORANGE;
    public static final Color Blue = Color.SKY;
    public static final Color Hurt = Color.RED;

    public static final HashMap<String, String>[] CONTROLS;
    static {
        //noinspection unchecked
        CONTROLS = new HashMap[2];

        CONTROLS[0] = new HashMap<String, String>();
        CONTROLS[0].put("up", "up");
        CONTROLS[0].put("down", "down");
        CONTROLS[0].put("left", "left");
        CONTROLS[0].put("right", "right");

        CONTROLS[1] = new HashMap<String, String>();
        CONTROLS[1].put("up", "w");
        CONTROLS[1].put("down", "s");
        CONTROLS[1].put("left", "a");
        CONTROLS[1].put("right", "d");
    }

    public int player;

    public boolean hasOrb = false;
    public GameObject orb;

    public void init() {
        player = GameState.NUM_PLAYERS++;
        setColor();
        components.add(new Movement(this, CONTROLS[player]));
        components.add(new Tag(this));
    }

    public void setColor() {
        Color color;
        if (player == 0) {
            color = Orange;
        } else {
            color = Blue;
        }

        materials.color(new com.nilunder.bdx.utils.Color(color));
    }
}
