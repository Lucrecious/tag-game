package taggame;

import com.nilunder.bdx.Bdx;
import com.nilunder.bdx.Component;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.State;
import com.nilunder.bdx.utils.Random;
import taggame.items.Boost;
import taggame.items.Ghost;
import taggame.items.Item;
import taggame.player.Player;
import taggame.player.TimerCube;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.Stack;

public class GameState extends GameObject {
    public static int NUM_PLAYERS = 0;
    public static final Player[] PLAYERS = new Player[2];
    public static final Vector2f[] POSITIONS = { new Vector2f(-2, -2), new Vector2f(2, 2) };

    public static final int GRID_SIZE = 20;
    public static final int OFFSET = -10;
    public static final float TILE_SIZE = 1f;

    public static short CGOuterWall;
    public static short CMOuterWall;
    public static short CGInnerWall;
    public static short CMInnerWall;
    public static short CGGhost;
    public static short CMGhost;

    public static final int[][] map =
            {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };

    public void init() {
        components.add(new Timer(this));

        CGOuterWall = scene.objects.get("CGOuterWall").collisionGroup();
        CMOuterWall = scene.objects.get("CGOuterWall").collisionMask();
        CGInnerWall = scene.objects.get("CGInnerWall").collisionGroup();
        CMInnerWall = scene.objects.get("CGInnerWall").collisionMask();
        CGGhost = scene.objects.get("CGGhost").collisionGroup();
        CMGhost = scene.objects.get("CGGhost").collisionMask();

        initializeBoard();

        GameObject orb = scene.add("Orb");
        orb.position(setPosition(new Vector2f(0, 0), 0.5f));

        PLAYERS[0] = (Player)scene.add("Player");
        PLAYERS[0].orb = orb;
        PLAYERS[0].position(setPosition(POSITIONS[PLAYERS[0].player], 1));

        PLAYERS[1] = (Player)scene.add("Player");
        PLAYERS[1].orb = orb;
        PLAYERS[1].position(setPosition(POSITIONS[PLAYERS[1].player], 1));

        components.add(new Spawner(this, freeSpaces()));
    }

    private ArrayList<Vector2f> freeSpaces() {
        ArrayList<Vector2f> freeSpaces = new ArrayList<Vector2f>();

        for (int i = 1; i < GRID_SIZE - 1; i++) {
            for (int j = 1; j < GRID_SIZE - 1; j++) {
                freeSpaces.add(new Vector2f(j*TILE_SIZE + OFFSET, i*TILE_SIZE + OFFSET));
            }
        }

        return freeSpaces;
    }

    private void initializeBoard() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                GameObject wall = null;

                if (j == 0 || i == 0 || i == GRID_SIZE - 1 || j == GRID_SIZE - 1) {
                    wall = scene.add("Wall");
                    wall.collisionGroup(CGOuterWall);
                } else if (map[j-1][i-1] == 1) {
                    wall = scene.add("Wall");
                    wall.collisionGroup(CGInnerWall);
                }

                if (wall != null) {
                    wall.position(setPosition(new Vector2f(i * TILE_SIZE + OFFSET, j * TILE_SIZE + OFFSET), 0.4f));
                }
            }
        }
    }

    private Vector3f setPosition(Vector2f gridPosition, float z) {
        return new Vector3f(gridPosition.x, gridPosition.y, z);
    }

    public class Spawner extends Component {
        private final ArrayList<Vector2f> freeSpaces;

        public Spawner(GameObject g, ArrayList<Vector2f> freeSpaces) {
            super(g);
            this.freeSpaces = freeSpaces;
            state(core);
        }

        private State core = new State() {
            private float time;
            private float spawnSeconds;

            @Override
            public void enter() {
                time = Bdx.time;
                spawnSeconds = Random.random(5, 10);
            }

            @Override
            public void main() {
                if (Bdx.time - time > spawnSeconds) {
                    Item item = (Item)scene.add("Item");
                    item.init(Ghost.class);
                    item.position(setPosition(Random.choice(freeSpaces), 0.5f));
                    state(core);
                }
            }
        };
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
