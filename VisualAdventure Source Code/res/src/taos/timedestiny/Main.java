package taos.timedestiny;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Main extends Activity {

	private final int SPLASH_DISPLAY_LENGTH = 5000;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);

		new Handler().postDelayed(new Runnable() {

			public void run() {
				Intent mainIntent = new Intent(Main.this, MainMenu.class);
				mainIntent.putExtra("MAIN", true);
				startActivity(mainIntent);
				finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}

}
