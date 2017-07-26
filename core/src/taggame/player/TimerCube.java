package taggame.player;

import com.badlogic.gdx.graphics.Color;
import com.nilunder.bdx.Bdx;
import com.nilunder.bdx.Component;
import com.nilunder.bdx.GameObject;
import com.nilunder.bdx.State;

public class TimerCube extends GameObject {
    public boolean start = false;
    public boolean finished = false;

    @Override
    public void init() {
        components.add(new Fader(this));
    }

    public class Fader extends Component {
        private final TimerCube timerCube;

        public Fader(final TimerCube g) {
            super(g);
            timerCube = g;
            state(wait);
        }

        public State wait = new State() {
            @Override
            public void main() {
                if (timerCube.start) {
                    state(fade);
                }
            }
        };

        public State fade = new State() {
            private float time;
            private float wait = 1;

            @Override
            public void enter() {
                time = Bdx.time;
            }

            @Override
            public void main() {
                Color color = timerCube.materials.get(0).color();
                float a = color.a - wait*Bdx.TICK_TIME;
                timerCube.materials.color(new com.nilunder.bdx.utils.Color(color.r, color.g, color.b, a > 0 ? a : 0));
                if (Bdx.time - time > wait) {
                    timerCube.finished = true;
                    state(null);
                }
            }
        };
    }
}
