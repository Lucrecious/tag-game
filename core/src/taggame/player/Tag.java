package taggame.player;

import com.nilunder.bdx.Component;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.State;

import javax.vecmath.Vector3f;

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
        }
    };

    private State holder = new State() {
        private Vector3f localOrb = new Vector3f(0, 0, 2);
        @Override
        public void main() {
            player.orb.position(player.position().plus(localOrb));
        }
    };
}
