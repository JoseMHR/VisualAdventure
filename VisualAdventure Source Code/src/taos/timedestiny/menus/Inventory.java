package taos.timedestiny.menus;

import taos.timedestiny.R;
import taos.timedestiny.bases.MainActor;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Inventory extends Activity {

	MainActor protagonista;

	ImageButton Item1;
	ImageButton Item2;
	ImageButton Item3;
	ImageButton Item4;
	ImageButton Item5;
	ImageButton Item6;
	ImageButton Item7;
	ImageButton Item8;
	ImageButton Item9;
	TextView Dinero;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory);

		protagonista = MainActor.getInstance();
		Item1 = (ImageButton) findViewById(R.id.item1);
		Item2 = (ImageButton) findViewById(R.id.item2);
		Item3 = (ImageButton) findViewById(R.id.item3);
		Item4 = (ImageButton) findViewById(R.id.item4);
		Item5 = (ImageButton) findViewById(R.id.item5);
		Item6 = (ImageButton) findViewById(R.id.item6);
		Item7 = (ImageButton) findViewById(R.id.item7);
		Item8 = (ImageButton) findViewById(R.id.item8);
		Item9 = (ImageButton) findViewById(R.id.item9);
		Dinero = (TextView) findViewById(R.id.TextDinero);
		Dinero.setText(protagonista.getDinero() + "");
	}

	public void onRegresar(View view) {
		this.finish();
	}

	public void onItem1(View view) {

	}

	public void onItem2(View view) {

	}

	public void onItem3(View view) {

	}

	public void onItem4(View view) {

	}

	public void onItem5(View view) {

	}

	public void onItem6(View view) {

	}

	public void onItem7(View view) {

	}

	public void onItem8(View view) {

	}

	public void onItem9(View view) {

	}

	public void onMap(View view) {
		Intent intent = new Intent(this, MapaMundi.class);
		startActivity(intent);
	}

	public void onStatus(View view) {
		Intent intent = new Intent(this, Status.class);
		startActivity(intent);
	}

}
