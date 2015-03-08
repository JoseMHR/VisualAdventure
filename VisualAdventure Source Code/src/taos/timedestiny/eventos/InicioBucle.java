package taos.timedestiny.eventos;

import taos.timedestiny.R;
import taos.timedestiny.bases.EventoBase;
import android.os.Bundle;
import android.view.View;

public class InicioBucle extends EventoBase {

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
				text[0] = value(R.string.EvInicioBucle10);
				setTexto();

				break;
			case 2:
				setPjA("dios");

				longText = 3;
				text[0] = value(R.string.EvInicioBucle20);
				text[1] = value(R.string.EvInicioBucle21);
				text[2] = value(R.string.EvInicioBucle22);
				text[3] = value(R.string.EvInicioBucle23);
				setTexto();

				break;

			case 3:
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
