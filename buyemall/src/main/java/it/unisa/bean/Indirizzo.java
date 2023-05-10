package it.unisa.bean;

public class Indirizzo {
	private int idIndirizzo;
	private String via, citta, provincia, n_civico;
	public String getVia() {
		return via;
	}
	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}
	public void setVia(String via) {
		if(via.length()<= 200)
		this.via = via;
	}
	public String getCitta() {
		return citta;
	}
	@Override
	public String toString() {
		return "Indirizzo [idIndirizzo=" + idIndirizzo + ", via=" + via + ", citta=" + citta + ", provincia="
				+ provincia + ", n_civico=" + n_civico + "]";
	}
	public void setCitta(String citta) {
		if(citta.length()<= 45)
		this.citta = citta;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		if(provincia.length()<= 45)
		this.provincia = provincia;
	}
	public String getN_civico() {
		return n_civico;
	}
	public void setN_civico(String n_civico) {
		if(n_civico.length()<= 3)
		this.n_civico = n_civico;
	}
	public int getIdIndirizzo() {
		return idIndirizzo;
	} 
}
