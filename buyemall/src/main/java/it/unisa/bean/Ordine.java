package it.unisa.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ordine {
	private int idOrdine;
	private float prezzo;
	private Stato stato;
	private Date data;
	private Account u;
	private Indirizzo spedizione;

	private List<Prodotto> list;

	public Ordine() {
		list = new ArrayList<>();
	}

	public List<Prodotto> getList() {
		return list;
	}

	public void addList(Prodotto pr) {
		list.add(pr);
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Indirizzo getSpedizione() {
		return spedizione;
	}

	public void setSpedizione(Indirizzo spedizione) {
		this.spedizione = spedizione;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public Account getU() {
		return u;
	}
}
