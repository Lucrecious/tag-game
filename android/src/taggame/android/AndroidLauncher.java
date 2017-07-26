package taggame.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.RatioResolutionStrategy;
import taggame.BdxApp;

public class AndroidLauncher extends AndroidApplication {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		int width = 1600;
		int height = 900;
		config.resolutionStrategy = new RatioResolutionStrategy(width, height);
		initialize(new BdxApp(), config);
	}
}
