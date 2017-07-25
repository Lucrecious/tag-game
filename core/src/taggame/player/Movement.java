package taggame.player;

import com.nilunder.bdx.Component;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.State;

public class Movement extends Component {
    public Movement(GameObject g) {
        super(g);

        State core = new State() {
            @Override
            public void main() {
            }
        };

        state(core);
    }
}
