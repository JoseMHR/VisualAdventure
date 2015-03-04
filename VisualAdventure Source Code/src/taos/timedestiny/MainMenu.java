package taos.timedestiny;

import taos.timedestiny.bases.ChangeLocation;
import taos.timedestiny.bases.MainActor;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;

public class MainMenu extends Activity {

	private AdView adView;
	private InterstitialAd interstitial;
	protected MainActor protagonista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		protagonista = MainActor.getInstance();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu);

		// AdView
		adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		// Intersetial.
		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId("ca-app-pub-4116840717941176/1792527249");
		AdRequest adRequestInterstitial = new AdRequest.Builder().build();
		interstitial.loadAd(adRequestInterstitial);
	}

	public void onComenzar(View view) {
		protagonista.Reiniciar();

		Intent intent = new Intent(this, ChangeLocation.class);
		startActivity(intent);
		this.finish();
	}

	public void onContinuar(View view) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this
				.getApplicationContext());
		Gson gson = new Gson();
		String json = prefs.getString("Partida", "");
		MainActor p = gson.fromJson(json, MainActor.class);

		if (p != null) {
			protagonista.CargarProtagonista(p);
		} else {
			protagonista.Reiniciar();
		}

		Intent intent = new Intent(this, ChangeLocation.class);
		startActivity(intent);
		this.finish();
	}

	public void onSalir(View view) {
		displayInterstitial();
		this.finish();
	}

	@Override
	public void onPause() {
		adView.pause();
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		adView.resume();
	}

	@Override
	public void onDestroy() {
		adView.destroy();
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			displayInterstitial();
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void displayInterstitial() {
		if (interstitial.isLoaded()) {
			interstitial.show();
		}
	}

}