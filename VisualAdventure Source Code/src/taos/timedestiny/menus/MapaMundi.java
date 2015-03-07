package taos.timedestiny.menus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import taos.timedestiny.R;
import taos.timedestiny.bases.ChangeLocation;
import taos.timedestiny.bases.MainActor;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MapaMundi extends Activity {

	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;
	String ubicacionSeleccionada;
	private TextView ubicacionView;
	protected MainActor protagonista;
	ImageView fondo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa_mundi);

		ubicacionSeleccionada = "Ninguna";

		mainListView = (ListView) findViewById(R.id.listview);
		ubicacionView = (TextView) findViewById(R.id.textLugar);
		fondo = (ImageView) findViewById(R.id.idFondo);

		String[] ubicaciones = new String[] { "Centro", "Colegio", "Heladeria", "Iglesia", "Museo",
				"Parque", "ParqueAtracciones", "ParqueInfantil", "Piscina", "Playa", "Recreo" };
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList(ubicaciones));

		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, list);
		mainListView.setAdapter(listAdapter);

		mainListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ubicacionSeleccionada = (String) listAdapter.getItem(position);
				ubicacionView.setText(ubicacionSeleccionada);

				switch (ubicacionSeleccionada) {
					case "Centro":
						setFond("centro_dia");
						break;
					case "Colegio":
						setFond("colegio1");
						break;
					case "Heladeria":
						setFond("heladeria1");
						break;
					case "Iglesia":
						setFond("iglesia1");
						break;
					case "Museo":
						setFond("museo1");
						break;
					case "Parque":
						setFond("parque1");
						break;
					case "ParqueAtracciones":
						setFond("parqueAtracciones");
						break;
					case "ParqueInfantil":
						setFond("parqueinfantil");
						break;
					case "Piscina":
						setFond("piscina1");
						break;
					case "Playa":
						setFond("playa1");
						break;
					case "Recreo":
						setFond("recreo1");
						break;
					case "Habitacion":
						setFond("habitacion");
						break;
				}
			}
		});

	}

	public void onViajar(View view) {
		Intent intent = new Intent(this, ChangeLocation.class);
		intent.putExtra("location", ubicacionSeleccionada);
		startActivity(intent);
		this.finish();
	}

	public void onSalir(View view) {
		this.finish();
	}

	protected void setFond(String s) {
		InputStream ims;
		try {
			ims = getAssets().open("Escenarios/" + s + ".jpg");
			Drawable d = Drawable.createFromStream(ims, null);
			fondo.setImageDrawable(d);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
