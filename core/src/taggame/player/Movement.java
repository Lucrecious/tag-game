package taggame.player;

import com.nilunder.bdx.Bdx;
import com.nilunder.bdx.Component;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.State;

import javax.vecmath.Vector3f;

import java.util.HashMap;

import static taggame.player.Player.CONTROLS;

public class Movement extends Component {
    private final String up;
    private final String down;
    private final String left;
    private final String right;

    public Movement(final GameObject g, final HashMap<String, String> controls) {
        super(g);

        up = controls.get("up");
        down = controls.get("down");
        left = controls.get("left");
        right = controls.get("right");

        State core = new State() {
            private float speed = 10;

            @Override
            public void main() {
                Vector3f direction = vector();
                g.velocity(direction.mul(speed));
                if (direction.length() > 0) {
                    g.alignAxisToVec("Y", direction, 0.5f);
                }
            }
        };

        state(core);
    }

    public Vector3f vector() {
        int up = Bdx.keyboard.keyDown(this.up) ? 1 : 0;
        int down = Bdx.keyboard.keyDown(this.down) ? 1 : 0;
        int left = Bdx.keyboard.keyDown(this.left) ? 1 : 0;
        int right = Bdx.keyboard.keyDown(this.right) ? 1 : 0;

        Vector3f direction = new Vector3f(right - left, up - down, 0);
        if (direction.length() > 0) {
            direction.normalize();
        }

        return direction;
    }
}
