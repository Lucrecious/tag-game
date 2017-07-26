package taggame.inst;

import com.badlogic.gdx.utils.JsonValue;
import com.nilunder.bdx.Instantiator;import com.nilunder.bdx.GameObject;
import taggame.*;
public class iScene extends Instantiator {

	public GameObject newObject(JsonValue gobj){
		String name = gobj.name;

		if (gobj.get("class").asString().equals("TimerCube"))
			return new taggame.player.TimerCube();
		if (gobj.get("class").asString().equals("GameState"))
			return new taggame.GameState();
		if (gobj.get("class").asString().equals("Player"))
			return new taggame.player.Player();

		return super.newObject(gobj);
	}
	
}
