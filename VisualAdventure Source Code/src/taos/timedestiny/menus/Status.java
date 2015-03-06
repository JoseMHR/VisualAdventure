package taos.timedestiny.menus;

import taos.timedestiny.R;
import taos.timedestiny.bases.MainActor;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Status extends Activity {

	MainActor protagonista;

	TextView textNombre;
	TextView textTime;

	TextView A1;
	TextView A2;
	TextView A3;
	TextView A4;
	TextView B1;
	TextView B2;
	TextView B3;
	TextView B4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);

		protagonista = MainActor.getInstance();

		textNombre = (TextView) findViewById(R.id.textNombreList);
		textTime = (TextView) findViewById(R.id.textTime);

		A1 = (TextView) findViewById(R.id.A1);
		A2 = (TextView) findViewById(R.id.A2);
		A3 = (TextView) findViewById(R.id.A3);
		A4 = (TextView) findViewById(R.id.A4);

		B1 = (TextView) findViewById(R.id.B1);
		B2 = (TextView) findViewById(R.id.B2);
		B3 = (TextView) findViewById(R.id.B3);
		B4 = (TextView) findViewById(R.id.B4);

		textNombre.setText(protagonista.getNombre());
		textTime.setText(String.valueOf(protagonista.getHora() + ":" + protagonista.getMinuto()));

		inicial();
	}

	public void onPersonal(View view) {
		inicial();
	}

	public void onSociable(View view) {
		A1.setText("Proximamente");
		A2.setText("");
		A3.setText("");
		A4.setText("");

		B1.setText("");
		B2.setText("");
		B3.setText("");
		B4.setText("");
	}

	public void onCono(View view) {
		A1.setText("Proximamente");
		A2.setText("");
		A3.setText("");
		A4.setText("");

		B1.setText("");
		B2.setText("");
		B3.setText("");
		B4.setText("");
	}

	public void onFisico(View view) {
		inicial();
	}

	public void inicial() {
		A1.setText("Genero: ");
		A2.setText("Localizacion: ");
		A3.setText("Dinero: ");
		A4.setText("");

		B1.setText(protagonista.getGenero());
		B2.setText(protagonista.getLocalizacion());
		B3.setText(protagonista.getDinero() + "");
		B4.setText("");
	}

	public void onRegresar(View view) {
		this.finish();
	}
}
