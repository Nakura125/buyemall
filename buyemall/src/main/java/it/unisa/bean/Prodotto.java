package it.unisa.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prodotto {
	private int idProdotto, Quantita, generazione, visitato;
	private Date data;
	private float prezzo;
	private String nome, descrizione;
	private Tipo tipo;
	private List<Sprites> sprites;

	public Prodotto() {
		sprites = new ArrayList<>();
	}

	public void addSprites(Sprites pr) {
		sprites.add(pr);
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getQuantita() {
		return Quantita;
	}

	public void setQuantita(int quantita) {
		Quantita = quantita;
	}

	public int getGenerazione() {
		return generazione;
	}

	public void setGenerazione(int generazione) {
		this.generazione = generazione;
	}

	@Override
	public String toString() {
		return "Prodotto [idProdotto=" + idProdotto + ", Quantita=" + Quantita + ", generazione=" + generazione
				+ ", prezzo=" + prezzo + ", nome=" + nome + ", descrizione=" + descrizione + "]";
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.length() <= 45)
			this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		if (descrizione.length() <= 200)
			this.descrizione = descrizione;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public List<Sprites> getSprites() {
		return sprites;
	}

	public int getVisitato() {
		return visitato;
	}

	public void setVisitato(int visitato) {
		this.visitato = visitato;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
