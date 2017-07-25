package taggame;

import com.nilunder.bdx.GameObject;
import taggame.player.Player;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class GameState extends GameObject {
    public static int PLAYERS = 0;
    public static Vector2f[] POSITIONS = { new Vector2f(-2, -2), new Vector2f(2, 2) };

    public void init() {
        GameObject orb = scene.add("Orb");
        orb.position(setPosition(new Vector2f(0, 0), 0.5f));

        Player player1 = (Player)scene.add("Player");
        player1.orb = orb;
        player1.position(setPosition(POSITIONS[player1.player], 1));

        Player player2 = (Player)scene.add("Player");
        player2.orb = orb;
        player2.position(setPosition(POSITIONS[player2.player], 1));
    }

    private Vector3f setPosition(Vector2f gridPosition, float z) {
        return new Vector3f(gridPosition.x, gridPosition.y, z);
    }
}
