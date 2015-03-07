package taos.timedestiny.eventos;

import taos.timedestiny.R;
import taos.timedestiny.bases.EventoBase;
import android.os.Bundle;
import android.view.View;

public class Inicio extends EventoBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	// ######### Load Escena #################
	public void loadScene(int escena) {
		switch (escena) {
			case 1:
				lugar.setText(value(R.string.LugarDesconocido));
				hora.setText(value(R.string.FechaDesconocido));

				enableBot(true, false, false, true);

				setActivo("¿?");
				setBGM("Espacio");
				setFondo("espacio");
				setPjA();
				setPjB();

				longText = 0;
				text[0] = value(R.string.EvInicio10);
				setTexto();

				break;
			case 2:
				setPjA("dios");

				longText = 5;
				text[0] = value(R.string.EvInicio20);
				text[1] = value(R.string.EvInicio21);
				text[2] = value(R.string.EvInicio22);
				text[3] = value(R.string.EvInicio23);
				text[4] = value(R.string.EvInicio24);
				text[5] = value(R.string.EvInicio25);
				setTexto();

				break;
			case 3:
				setPjA();
				setPjB();

				setActivo();
				enableBot(false, false, false, false);
				enableOp(null, value(R.string.hombre), null, value(R.string.mujer), null);

				longText = 0;
				text[0] = value(R.string.EvInicio30);
				setTexto();

				break;
			case 4:
				setPjB("dios");

				setActivo("¿?");
				longText = 4;
				if (protagonista.getGenero().equalsIgnoreCase("hombre")) {
					text[0] = value(R.string.EvInicio40a);
				} else {
					text[0] = value(R.string.EvInicio40b);
				}
				text[1] = value(R.string.EvInicio41);
				text[2] = value(R.string.EvInicio42);
				text[3] = value(R.string.EvInicio43);
				text[4] = value(R.string.EvInicio44);
				setTexto();

				break;
			case 5:
				setActivo();
				editText(true);
				editText.setText(R.string.nombre);

				longText = 0;
				text[0] = value(R.string.EvInicio50);
				setTexto();

				break;
			case 6:
				setActivo("¿?");
				editText(false);
				protagonista.setNombre(editText.getText().toString());

				longText = 1;
				text[0] = value(R.string.EvInicio60);
				text[1] = value(R.string.EvInicio61);
				setTexto();

				break;
			case 7:
				setActivo(value(R.string.EvInicio7x));
				setBGM("Burbujas");
				setFondo("nada");
				setPjA();
				setPjB();

				longText = 3;
				if (protagonista.getGenero().equalsIgnoreCase("hombre")) {
					text[0] = value(R.string.EvInicio70a);
				} else {
					text[0] = value(R.string.EvInicio70b);
				}
				text[1] = value(R.string.EvInicio71);
				text[2] = value(R.string.EvInicio72);
				text[3] = value(R.string.EvInicio73) + " " + protagonista.getNombre();
				setTexto();

				break;
			case 8:
				protagonista.setMinuto(30);
				protagonista.setHora(10);
				protagonista.setLocalizacion("Habitacion");
				CargarLugar();

				break;
		}
	}

	public void onOpcion1(View view) {
	}

	public void onOpcion2(View view) {
		disableOp();
		protagonista.setGenero("Hombre");
		enableBot(true, false, false, true);
		escena++;
		loadScene(escena);
	}

	public void onOpcion3(View view) {
	}

	public void onOpcion4(View view) {
		disableOp();
		protagonista.setGenero("Mujer");
		enableBot(true, false, false, true);
		escena++;
		loadScene(escena);
	}

	public void onOpcion5(View view) {
	}
}
