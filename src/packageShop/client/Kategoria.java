package packageShop.client;

import java.util.ArrayList;
import java.util.List;

public class Kategoria {
	
	int IdKategori;
	String nazwa;
	
	List<Integer> potkategia;
	
	public Kategoria(int idKategori, String nazwa, Kategoria potkategia) {
		super();
		IdKategori = idKategori;
		this.nazwa = nazwa;
		this.potkategia = new ArrayList();
	}
	
	public int getIdKategori() {
		return IdKategori;
	}

	public void setIdKategori(int idKategori) {
		IdKategori = idKategori;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public List<Integer> getPotkategia() {
		return potkategia;
	}

	public void setPotkategia(List<Integer>potkategia) {
		this.potkategia=potkategia;
	}
	
	public void addPotkategia(int potkategia) {
		this.potkategia.add(potkategia);
	}
}