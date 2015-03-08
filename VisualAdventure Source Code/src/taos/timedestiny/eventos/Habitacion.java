package taos.timedestiny.eventos;

import taos.timedestiny.R;
import taos.timedestiny.bases.EventoBase;
import android.os.Bundle;
import android.view.View;

public class Habitacion extends EventoBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	// ######### Load Escena #################
	public void loadScene(int escena) {
		switch (escena) {
			case 1:
				setBGM("Espacio");
				setFondo("habitacion");
				setPjA();
				setPjB();

				longText = 0;
				text[0] = "Estas en " + protagonista.getLocalizacion();
				setTexto();

				break;
			case 2:
				setActivoA("Amiga");

				longText = 5;
				text[0] = value(R.string.EvHab20);
				text[1] = value(R.string.EvHab21);
				text[2] = value(R.string.EvHab22);
				text[3] = value(R.string.EvHab23);
				text[4] = value(R.string.EvHab24);
				text[5] = value(R.string.EvHab25);
				setTexto();

				break;
			case 3:
				nuevoObjeto("jugueteA");

				longText = 0;
				text[0] = value(R.string.EvHab30);
				setTexto();

				break;
			case 4:
				setActivo();
				setPjA();
				longText = 0;
				text[0] = "";
				setTexto();

				break;

		}
	}

	public void onOpcion1(View view) {
		disableOp();
	}

	public void onOpcion2(View view) {
		disableOp();
	}

	public void onOpcion3(View view) {
		disableOp();
	}

	public void onOpcion4(View view) {
		disableOp();
	}

	public void onOpcion5(View view) {
		disableOp();
	}
}