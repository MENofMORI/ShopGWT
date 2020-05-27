package packageShop.client;

public class Data {
	static int licznik =0;
	
	int IdProdoktu;
	String nazwa;
	String opis;
	float cena;
	int sztuki;
	int kategoria;
	
	public Data(int idProdoktu, String nazwa, String opis, float cena, int sztuki, int kategoria) {
		super();
		IdProdoktu = idProdoktu;
		this.nazwa = nazwa;
		this.opis = opis;
		this.cena = cena;
		this.sztuki = sztuki;
		this.kategoria = kategoria;
		
		licznik ++;
	}
	
	public int getIdProdoktu() {
		return IdProdoktu;
	}
	public void setIdProdoktu(int idProdoktu) {
		IdProdoktu = idProdoktu;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public float getCena() {
		return cena;
	}
	public void setCena(float cena) {
		this.cena = cena;
	}
	public int getSztuki() {
		return sztuki;
	}
	public void setSztuki(int sztuki) {
		this.sztuki = sztuki;
	}
	public int getKategoria() {
		return kategoria;
	}
	public void setKategoria(int kategoria) {
		this.kategoria = kategoria;
	}
	
	static int AUTO_INCREMENT()	{
		return ++licznik;
	}
}
