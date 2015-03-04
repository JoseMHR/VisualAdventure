package taos.timedestiny.bases;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;

import taos.timedestiny.MainMenu;
import taos.timedestiny.R;
import taos.timedestiny.menus.Inventory;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public abstract class EventoBase extends Activity {

	protected MainActor protagonista;
	protected MediaPlayer musicaFondo;
	protected MediaPlayer efectoSonido;
	protected ImageView imagenFondo;
	protected ImageView imagenA;
	protected ImageView imagenB;
	protected TextView texto;
	protected TextView hora;
	protected TextView lugar;
	protected TextView actorActivo;

	protected ImageButton beforeText;
	protected ImageButton nextText;
	protected ImageButton idNext;
	protected ImageButton idBag;
	protected ImageButton idStatus;
	protected ImageButton idOptions;

	protected Button opcion1;
	protected Button opcion2;
	protected Button opcion3;
	protected Button opcion4;
	protected Button opcion5;
	protected EditText editText;

	protected int escena = 1;
	protected String[] text = new String[10];
	protected int longText = 0;
	protected int actualText = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		protagonista = MainActor.getInstance();

		actorActivo = (TextView) findViewById(R.id.actorActivo);
		hora = (TextView) findViewById(R.id.textHora);
		lugar = (TextView) findViewById(R.id.textLugar);
		imagenFondo = (ImageView) findViewById(R.id.idFondo);
		imagenA = (ImageView) findViewById(R.id.imagenA);
		imagenB = (ImageView) findViewById(R.id.imagenB);
		texto = (TextView) findViewById(R.id.texto);
		beforeText = (ImageButton) findViewById(R.id.viajar);
		nextText = (ImageButton) findViewById(R.id.salir);

		idNext = (ImageButton) findViewById(R.id.idnext);
		idBag = (ImageButton) findViewById(R.id.idbag);
		idStatus = (ImageButton) findViewById(R.id.idactions);
		idOptions = (ImageButton) findViewById(R.id.idoptions);

		opcion1 = (Button) findViewById(R.id.opcion1);
		opcion2 = (Button) findViewById(R.id.opcion2);
		opcion3 = (Button) findViewById(R.id.opcion3);
		opcion4 = (Button) findViewById(R.id.opcion4);
		opcion5 = (Button) findViewById(R.id.opcion5);
		editText = (EditText) findViewById(R.id.editText);

		lugar.setText(protagonista.getLocalizacion());
		hora.setText(protagonista.getHora() + ":" + protagonista.getMinuto());
		musicaFondo = new MediaPlayer();
		efectoSonido = new MediaPlayer();

		loadScene(escena);
	}

	// ------ ON CLICKS --------------------------

	public void onOptions(View view) {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialogs);
		dialog.show();

		Button guardar = (Button) dialog.findViewById(R.id.guardar);
		Button cargar = (Button) dialog.findViewById(R.id.cargar);
		Button salir = (Button) dialog.findViewById(R.id.salir);
		Button cancelar = (Button) dialog.findViewById(R.id.regresar);

		guardar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Save();
				dialog.dismiss();
			}
		});
		cargar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Load();
				CargarLugar();
			}
		});
		salir.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Salir();
			}
		});
		cancelar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}

	public void Save() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this
				.getApplicationContext());
		Editor editor = prefs.edit();
		Gson gson = new Gson();
		String json = gson.toJson(protagonista);
		editor.putString("Partida", json);
		editor.commit();
		Toast toast = Toast.makeText(getApplicationContext(), "Saved!!", Toast.LENGTH_SHORT);
		toast.show();
	}

	public void Load() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this
				.getApplicationContext());
		Gson gson = new Gson();
		String json = prefs.getString("Partida", "");
		MainActor p = gson.fromJson(json, MainActor.class);
		protagonista.CargarProtagonista(p);
	}

	public void Salir() {
		Intent intent = new Intent(this, MainMenu.class);
		startActivity(intent);
		this.finish();
	}

	public void onBag(View view) {
		Intent intent = new Intent(this, Inventory.class);
		startActivity(intent);
	}

	public void onAction(View view) {
		enableOp("Ir a...", "Hablar con...", "Examinar...", "Observar alrededor", null);
	}

	public void onBeforeText(View view) {
		if ((actualText > 0) && (longText > 0)) {
			actualText--;
			texto.setText(text[actualText]);
			if (actualText == 0) {
				beforeText.setBackgroundResource(R.drawable.mybotondark);
			}
			nextText.setBackgroundResource(R.drawable.myboton);
		}
	}

	public void onNextText(View view) {
		if (actualText < longText) {
			actualText++;
			texto.setText(text[actualText]);
			if (actualText == longText) {
				nextText.setBackgroundResource(R.drawable.mybotondark);
			}
			beforeText.setBackgroundResource(R.drawable.myboton);
		}
	}

	public void onNext(View view) {
		if (actualText < longText) {
			actualText++;
			texto.setText(text[actualText]);
			if (actualText == longText) {
				nextText.setBackgroundResource(R.drawable.mybotondark);
			}
			beforeText.setBackgroundResource(R.drawable.myboton);
		} else {
			escena++;
			loadScene(escena);
		}
	}

	// ##################################################################
	// ############################ SET UTILIDADES ######################
	// ##################################################################

	protected void setFondo(String fondo) {
		InputStream ims;
		try {
			ims = getAssets().open("Escenarios/" + fondo + ".jpg");
			Drawable d = Drawable.createFromStream(ims, null);
			imagenFondo.setImageDrawable(d);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void nuevoObjeto(String objeto) {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialogsobjecto);
		dialog.show();

		Button aceptar = (Button) dialog.findViewById(R.id.aceptar);
		ImageView imageobjeto = (ImageView) dialog.findViewById(R.id.imageobjetonuevo);

		InputStream ims;
		try {
			ims = getAssets().open("Objetos/" + objeto + ".png");
			Drawable d = Drawable.createFromStream(ims, null);
			imageobjeto.setImageDrawable(d);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!protagonista.getObjetos().contains(objeto)) {
			protagonista.getObjetos().add(objeto);
		}

		aceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}

	protected void setBGM(String bgm) {
		try {
			if (this.musicaFondo != null) {
				this.musicaFondo.stop();
				this.musicaFondo.release();
				this.musicaFondo = null;
			}

			this.musicaFondo = new MediaPlayer();

			AssetFileDescriptor afd = getAssets().openFd("BGM/" + bgm + ".mp3");
			FileDescriptor fd = afd.getFileDescriptor();
			this.musicaFondo.setDataSource(fd, afd.getStartOffset(), afd.getLength());
			this.musicaFondo.prepare();
			this.musicaFondo.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setSonido(String efecto) {
		try {
			if (this.efectoSonido != null) {
				this.efectoSonido.stop();
				this.efectoSonido.release();
				this.efectoSonido = null;
			}

			this.efectoSonido = new MediaPlayer();

			AssetFileDescriptor afd = getAssets().openFd("Sonidos/" + efecto + ".mp3");
			FileDescriptor fd = afd.getFileDescriptor();
			this.efectoSonido.setDataSource(fd, afd.getStartOffset(), afd.getLength());
			this.efectoSonido.prepare();
			this.efectoSonido.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setTexto() {
		actualText = 0;
		texto.setText(text[actualText]);
		beforeText.setBackgroundResource(R.drawable.mybotondark);
		if (actualText == longText) {
			nextText.setBackgroundResource(R.drawable.mybotondark);
		} else {
			nextText.setBackgroundResource(R.drawable.myboton);
		}
	}

	protected void setActivo(String pj) {
		actorActivo.setVisibility(View.VISIBLE);
		actorActivo.setText(pj);
	}

	protected void setActivoA(String pj) {
		actorActivo.setVisibility(View.VISIBLE);
		actorActivo.setText(pj);
		setPjA(pj);
	}

	protected void setActivoB(String pj) {
		actorActivo.setVisibility(View.VISIBLE);
		actorActivo.setText(pj);
		setPjB(pj);
	}

	protected void setActivo() {
		actorActivo.setVisibility(View.GONE);
	}

	protected void setPjA() {
		imagenA.setVisibility(View.GONE);
	}

	protected void setPjB() {
		imagenB.setVisibility(View.GONE);
	}

	protected void setPjA(String pja) {
		imagenA.setVisibility(View.VISIBLE);
		InputStream ims;
		try {
			ims = getAssets().open("ActorImagen/" + pja + ".png");
			Drawable d = Drawable.createFromStream(ims, null);
			imagenA.setImageDrawable(d);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void setPjB(String pjb) {
		imagenB.setVisibility(View.VISIBLE);
		InputStream ims;
		try {
			ims = getAssets().open("ActorImagen/" + pjb + ".png");
			Drawable d = Drawable.createFromStream(ims, null);
			imagenB.setImageDrawable(d);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (this.musicaFondo != null) {
			this.musicaFondo.pause();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (this.musicaFondo != null) {
			this.musicaFondo.start();
		}
	}

	public void disableOp() {
		opcion1.setVisibility(View.GONE);
		opcion2.setVisibility(View.GONE);
		opcion3.setVisibility(View.GONE);
		opcion4.setVisibility(View.GONE);
		opcion5.setVisibility(View.GONE);
	}

	public void enableOp(String s1, String s2, String s3, String s4, String s5) {
		if (s1 != null) {
			opcion1.setVisibility(View.VISIBLE);
			opcion1.setText(s1);
		}
		if (s2 != null) {
			opcion2.setVisibility(View.VISIBLE);
			opcion2.setText(s2);
		}
		if (s3 != null) {
			opcion3.setVisibility(View.VISIBLE);
			opcion3.setText(s3);
		}
		if (s4 != null) {
			opcion4.setVisibility(View.VISIBLE);
			opcion4.setText(s4);
		}
		if (s5 != null) {
			opcion5.setVisibility(View.VISIBLE);
			opcion5.setText(s5);
		}
	}

	public void enableBot() {
		enableBot(true, true, true, true);
	}

	public void enableBot(boolean m1, boolean m2, boolean m3, boolean m4) {
		if (m1) {
			idOptions.setEnabled(true);
			idOptions.setImageResource(R.drawable.options);
		} else {
			idOptions.setEnabled(false);
			idOptions.setImageResource(R.drawable.optionsd);
		}
		if (m2) {
			idStatus.setEnabled(true);
			idStatus.setImageResource(R.drawable.telefono);
		} else {
			idStatus.setEnabled(false);
			idStatus.setImageResource(R.drawable.telefonod);
		}
		if (m3) {
			idBag.setEnabled(true);
			idBag.setImageResource(R.drawable.bag);
		} else {
			idBag.setEnabled(false);
			idBag.setImageResource(R.drawable.bagd);
		}
		if (m4) {
			idNext.setEnabled(true);
			idNext.setImageResource(R.drawable.next);
		} else {
			idNext.setEnabled(false);
			idNext.setImageResource(R.drawable.nextd);
		}
	}

	public void editText() {
		editText(true);
	}

	public void editText(boolean b) {
		if (b) {
			editText.setVisibility(View.VISIBLE);
			editText.requestFocus();
		} else {
			editText.setVisibility(View.GONE);
		}
	}

	protected String value(int value) {
		return getResources().getString(value);
	}

	// ######### Load Escena #################
	abstract public void loadScene(int escena);

	protected void CargarLugar() {
		Intent intent = new Intent(this, ChangeLocation.class);
		startActivity(intent);
		this.finish();
	}
}
