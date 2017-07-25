package taggame;

import com.nilunder.bdx.GameObject;
import taggame.player.Player;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class GameState extends GameObject {
    public static int PLAYERS = 0;
    public static Vector2f[] POSITIONS = { new Vector2f(2, 2), new Vector2f(4, 4) };
    public void init() {
        Player player1 = (Player)scene.add("Player");
        player1.position(setPosition(POSITIONS[player1.player]));

        Player player2 = (Player)scene.add("Player");
        player2.position(setPosition(POSITIONS[player2.player]));
    }

    private Vector3f setPosition(Vector2f gridPosition) {
        return new Vector3f(gridPosition.x, gridPosition.y, 1);
    }
}
