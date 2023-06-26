package it.unisa.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Prodotto {
	private int idProdotto, Quantita, generazione, visitato;
	private Date data;
	private float prezzo;
	private String nome, descrizione;
	private Tipo tipo;
	private String Nazionalita;
	private List<Sprites> sprites;

	public static Prodotto nullProduct() {
		Prodotto nullpr = new Prodotto();
		nullpr.setIdProdotto(0);
		nullpr.setNome("Not Found");
		nullpr.setDescrizione("Error not FOund Product");
		nullpr.setGenerazione(0);
		nullpr.setQuantita(0);
		nullpr.setTipo(Tipo.none);
		String urlimgnull = "https://www.pngall.com/wp-content/uploads/2/Question-Mark-PNG.png";
		Sprites sp = new Sprites();
		sp.setLink(urlimgnull);
		nullpr.addSprites(sp);

		return nullpr;
	}

	public Prodotto() {
		sprites = new ArrayList<>();
	}

	public void addSprites(Sprites pr) {
		sprites.add(pr);
	}

	public void addSprites(Collection<Sprites> pr) {
		sprites.addAll(pr);
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
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
		this.prezzo = (float) (prezzo * 1.22);
	}
	public void setPrezzoWithoutIVA(float prezzo) {
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

	public String getNazionalita() {
		return Nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		Nazionalita = nazionalita;
	}
}
