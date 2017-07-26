package taggame;

import com.nilunder.bdx.GameObject;
import taggame.player.Player;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class GameState extends GameObject {
    public static int NUM_PLAYERS = 0;
    public static Player[] PLAYERS = new Player[2];
    public static Vector2f[] POSITIONS = { new Vector2f(-2, -2), new Vector2f(2, 2) };

    public void init() {
        GameObject orb = scene.add("Orb");
        orb.position(setPosition(new Vector2f(0, 0), 0.5f));

        PLAYERS[0] = (Player)scene.add("Player");
        PLAYERS[0].orb = orb;
        PLAYERS[0].position(setPosition(POSITIONS[PLAYERS[0].player], 1));

        PLAYERS[1] = (Player)scene.add("Player");
        PLAYERS[1].orb = orb;
        PLAYERS[1].position(setPosition(POSITIONS[PLAYERS[1].player], 1));
    }

    private Vector3f setPosition(Vector2f gridPosition, float z) {
        return new Vector3f(gridPosition.x, gridPosition.y, z);
    }
}
