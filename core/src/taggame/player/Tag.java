package taggame.player;

import com.nilunder.bdx.Bdx;
import com.nilunder.bdx.Component;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.State;
import com.nilunder.bdx.utils.Color;

import javax.vecmath.Vector3f;

import static taggame.GameState.PLAYERS;

public class Tag extends Component {
    private final Player player;
    public Tag(final Player g) {
        super(g);
        player = g;
        state(empty);
    }

    private State empty = new State() {
        @Override
        public void main() {
            if (player.touching("Orb")) {
                player.hasOrb = true;
                state(holder);
            }

            for (Player otherPlayer : PLAYERS) {
                if (otherPlayer.player == player.player) {
                    continue;
                }

                for (GameObject g : player.touchingObjects) {
                    if (g == otherPlayer && otherPlayer.hasOrb) {
                        otherPlayer.hasOrb = false;
                        // otherPlayer.stun();
                        player.hasOrb = true;
                        state(holder);
                    }
                }
            }
        }
    };

    private State holder = new State() {
        private Vector3f localOrb = new Vector3f(0, 0, 2);
        @Override
        public void main() {
            player.orb.position(player.position().plus(localOrb));

            if (!player.hasOrb) {
                state(stun);
            }
        }
    };

    private State stun = new State() {
        private float time;
        private float seconds = 1f;

        @Override
        public void enter() {
            time = Bdx.time;
            player.materials.color(new Color(Player.Hurt));
        }

        @Override
        public void main() {
            player.velocity(0, 0, 0);
            if (Bdx.time - time > seconds) {
                state(empty);
            }
        }

        @Override
        public void exit() {
            player.setColor();
        }
    };
}
