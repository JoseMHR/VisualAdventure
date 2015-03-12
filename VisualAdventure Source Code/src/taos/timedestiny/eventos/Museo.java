package taos.timedestiny.eventos;

import taos.timedestiny.bases.EventoBase;
import android.os.Bundle;
import android.view.View;

public class Museo extends EventoBase {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	// ######### Load Escena #################
	public void loadScene(int escena) {
		switch (escena) {
			case 1:
				setBGM("Espacio");
				setFondo("museo1");
				setPjA();
				setPjB();

				longText = 1;
				text[0] = "Estas en " + protagonista.getLocalizacion();
				setTexto();

				break;
			case 2:

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