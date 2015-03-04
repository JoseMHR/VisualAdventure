package taos.timedestiny.bases;

import java.util.ArrayList;

public class MainActor {

	private static class SingletonHolder {
		private final static MainActor INSTANCE = new MainActor();
	}

	public static MainActor getInstance() {
		return SingletonHolder.INSTANCE;
	}

	ArrayList<String> ubicaciones;

	private String nombre;
	private String genero;

	private int hora;
	private int minuto;
	private int dinero;
	private String localizacion;
	ArrayList<String> objetos;

	protected MainActor() {
		this.ubicaciones = new ArrayList<String>();
		this.ubicaciones.add("Inicio");
		this.nombre = "SinNombre";
		this.genero = "NoDefinido";
		this.hora = 0;
		this.minuto = 0;
		this.dinero = 100;
		this.localizacion = "Inicio";
		this.objetos = new ArrayList<String>();
	}

	public void CargarProtagonista(MainActor p) {
		this.ubicaciones = p.ubicaciones;
		this.nombre = p.nombre;
		this.genero = p.genero;
		this.hora = p.hora;
		this.minuto = p.minuto;
		this.dinero = p.dinero;
		this.localizacion = p.localizacion;
		this.objetos = p.objetos;
	}

	public void Reiniciar() {
		this.ubicaciones = new ArrayList<String>();
		this.ubicaciones.add("Inicio");
		this.nombre = "SinNombre";
		this.genero = "NoDefinido";
		this.hora = 0;
		this.minuto = 0;
		this.dinero = 0;
		this.localizacion = "Inicio";
		this.objetos = new ArrayList<String>();
	}

	// ############## Get & Set ############################################

	public ArrayList<String> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(ArrayList<String> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public ArrayList<String> getObjetos() {
		return objetos;
	}

	public void setObjetos(ArrayList<String> objetos) {
		this.objetos = objetos;
	}
}
