package taggame.player;

import com.nilunder.bdx.Bdx;
import com.nilunder.bdx.Component;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.State;
import taggame.items.Effect;
import taggame.items.Item;

public class Itemer extends Component {
    private final Player player;

    public Itemer(final Player g) {
        super(g);
        player = g;
        state(noItem);
    }

    private Item current = null;

    private State noItem = new State() {
        @Override
        public void main() {
            for (GameObject g : player.touchingObjects) {
                if (g instanceof Item) {
                    current = (Item)g;
                    g.end();
                    state(withItem);
                }
            }
        }
    };

    private State withItem = new State() {
        @Override
        public void main() {
            if (Bdx.keyboard.keyDown("e")) {
                Effect effect = current.effect(player);
                player.effects.addLast(effect);
                player.effects.getLast().apply();
                current = null;
                state(noItem);
            }
        }
    };

}
