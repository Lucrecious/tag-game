package taggame;

import com.nilunder.bdx.Component;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.State;
import taggame.player.Player;
import taggame.player.TimerCube;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import java.util.Stack;

public class GameState extends GameObject {
    public static int NUM_PLAYERS = 0;
    public static Player[] PLAYERS = new Player[2];
    public static Vector2f[] POSITIONS = { new Vector2f(-2, -2), new Vector2f(2, 2) };

    public void init() {
        components.add(new Timer(this));

        initializeBoard();

        GameObject orb = scene.add("Orb");
        orb.position(setPosition(new Vector2f(0, 0), 0.5f));

        PLAYERS[0] = (Player)scene.add("Player");
        PLAYERS[0].orb = orb;
        PLAYERS[0].position(setPosition(POSITIONS[PLAYERS[0].player], 1));

        PLAYERS[1] = (Player)scene.add("Player");
        PLAYERS[1].orb = orb;
        PLAYERS[1].position(setPosition(POSITIONS[PLAYERS[1].player], 1));
    }

    private void initializeBoard() {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                if (j == 0 || i == 0 || i == 39 || j == 39) {
                    GameObject wall = scene.add("Wall");
                    wall.position(setPosition(new Vector2f(i*.5f-10, j*.5f-10), 1));
                }
            }
        }
    }

    private Vector3f setPosition(Vector2f gridPosition, float z) {
        return new Vector3f(gridPosition.x, gridPosition.y, z);
    }

    public class Timer extends Component {
        private final GameState gameState;
        public Timer(final GameState g) {
            super(g);

            gameState = g;

            state(timer);
        }

        private State timer = new State() {
            int seconds = 60;
            int cols = 10;

            private Vector3f startPosition;

            private Stack<TimerCube> cubes = new Stack<TimerCube>();

            @Override
            public void enter() {
                startPosition = gameState.position();
                initializeTimerCubes();
            }

            @Override
            public void main() {
                if (cubes.size() == 0) {
                    return;
                }

                if (!cubes.peek().start) {
                    cubes.peek().start = true;
                    return;
                }

                if (cubes.peek().finished) {
                    cubes.pop().end();
                    return;
                }
            }

            private void initializeTimerCubes() {
                for (int i = 0; i < seconds/cols; i++) {
                    for (int j = 0; j < cols; j++) {
                        TimerCube cube = (TimerCube)scene.add("TimerCube");
                        cube.position(startPosition.plus(new Vector3f(i*1.1f, -j*1.1f, 0)));
                        cubes.add(cube);
                    }
                }
            }
        };
    }

}
