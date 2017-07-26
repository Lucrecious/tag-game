package taggame.player;

import com.badlogic.gdx.graphics.Color;
import com.nilunder.bdx.GameObject;
import taggame.GameState;
import taggame.items.Effect;

import java.util.HashMap;
import java.util.LinkedList;

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
        CONTROLS[0].put("item", ".");

        CONTROLS[1] = new HashMap<String, String>();
        CONTROLS[1].put("up", "w");
        CONTROLS[1].put("down", "s");
        CONTROLS[1].put("left", "a");
        CONTROLS[1].put("right", "d");
        CONTROLS[1].put("item", "f");
    }

    public int player;

    public float maxSpeed = 10;
    public float currentSpeed = 10;

    protected LinkedList<Effect> effects = new LinkedList<Effect>();

    public boolean hasOrb = false;
    public GameObject orb;

    @Override
    public void init() {
        player = GameState.NUM_PLAYERS++;
        collisionGroup(GameState.CGInnerWall);
        setColor();
        components.add(new Movement(this, CONTROLS[player]));
        components.add(new Tag(this));
        components.add(new Itemer(this));
    }

    @Override
    public void main() {
        cycleEffects();
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

    private void cycleEffects() {
        for (int i = 0; i < effects.size(); i++) {
            Effect effect = effects.pollFirst();
            if (effect.finished()) {
                effect.destroy();
            } else {
                if (!effect.applied) {
                    effect.applied = true;
                    effect.apply();
                }

                effects.addLast(effect);
            }
        }
    }
}
