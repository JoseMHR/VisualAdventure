package taos.timedestiny.bases;

import taos.timedestiny.R;
import taos.timedestiny.eventos.Centro;
import taos.timedestiny.eventos.Colegio;
import taos.timedestiny.eventos.Habitacion;
import taos.timedestiny.eventos.Iglesia;
import taos.timedestiny.eventos.Inicio;
import taos.timedestiny.eventos.InicioBucle;
import taos.timedestiny.eventos.Museo;
import taos.timedestiny.eventos.Parque;
import taos.timedestiny.eventos.ParqueAtracciones;
import taos.timedestiny.eventos.ParqueInfantil;
import taos.timedestiny.eventos.Piscina;
import taos.timedestiny.eventos.Playa;
import taos.timedestiny.eventos.Recreo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class ChangeLocation extends Activity {

	private final int SPLASH_DISPLAY_LENGTH = 1000;
	protected MainActor protagonista;
	protected TextView textUbicacion;
	protected TextView textHora;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		protagonista = MainActor.getInstance();

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.cambio_ubicacion);
		textUbicacion = (TextView) findViewById(R.id.textUbicacion);
		textHora = (TextView) findViewById(R.id.textHora);

		Intent intent = getIntent();
		String location = intent.getStringExtra("location");
		if (location != null) {
			protagonista.setLocalizacion(location);
		}

		final Intent mainIntent = CambiarLocalizacion();

		new Handler().postDelayed(new Runnable() {
			public void run() {
				startActivity(mainIntent);
				finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}

	public Intent CambiarLocalizacion() {
		Intent result;
		String location = protagonista.getLocalizacion();
		textHora.setText(location);

		protagonista.setHora(protagonista.getHora() + 1);
		textUbicacion.setText(protagonista.getHora() + ":" + protagonista.getMinuto());

		if (protagonista.getHora() > 19) {
			protagonista.setHora(10);
			protagonista.setMinuto(30);
			textUbicacion.setText("--:--");
			textHora.setText("En algun lugar");
			return (new Intent(ChangeLocation.this, InicioBucle.class));
		}

		switch (location) {
			case "Inicio":
				textUbicacion.setText("--:--");
				textHora.setText("En algun lugar");
				result = new Intent(ChangeLocation.this, Inicio.class);
				break;
			case "Centro":
				result = new Intent(ChangeLocation.this, Centro.class);
				break;
			case "Colegio":
				result = new Intent(ChangeLocation.this, Colegio.class);
				break;
			case "Heladeria":
				result = new Intent(ChangeLocation.this, Iglesia.class);
				break;
			case "Iglesia":
				result = new Intent(ChangeLocation.this, Iglesia.class);
				break;
			case "Museo":
				result = new Intent(ChangeLocation.this, Museo.class);
				break;
			case "Parque":
				result = new Intent(ChangeLocation.this, Parque.class);
				break;
			case "ParqueAtracciones":
				result = new Intent(ChangeLocation.this, ParqueAtracciones.class);
				break;
			case "ParqueInfantil":
				result = new Intent(ChangeLocation.this, ParqueInfantil.class);
				break;
			case "Piscina":
				result = new Intent(ChangeLocation.this, Piscina.class);
				break;
			case "Playa":
				result = new Intent(ChangeLocation.this, Playa.class);
				break;
			case "Recreo":
				result = new Intent(ChangeLocation.this, Recreo.class);
				break;
			case "Habitacion":
				result = new Intent(ChangeLocation.this, Habitacion.class);
				break;
			default:
				result = new Intent(ChangeLocation.this, Centro.class);
				break;
		}

		return result;
	}
}
