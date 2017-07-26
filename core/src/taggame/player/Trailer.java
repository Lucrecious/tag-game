package taggame.player;

import com.nilunder.bdx.Bdx;
import com.nilunder.bdx.Component;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.State;
import com.nilunder.bdx.utils.Color;

public class Trailer extends Component {
    private final GameObject gobj;
    private final String trail;
    private final Color color;

    public Trailer(final GameObject g, final String trail, final Color color) {
        super(g);
        this.gobj = g;
        this.trail = trail;
        this.color = color;

        state(new State(){
            private float time;
            private float duration = 0.01f;

            @Override
            public void enter() {
                time = Bdx.time;
                GameObject t = gobj.scene.add(trail);
                t.position(gobj.position());
                t.orientation(gobj.orientation());
                t.materials.color(color);
            }

            @Override
            public void main() {
                if (Bdx.time - time > duration) {
                    state(this);
                }
            }
        });
    }
}
