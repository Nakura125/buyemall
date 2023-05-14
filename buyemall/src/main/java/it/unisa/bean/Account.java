package it.unisa.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Account {
	private String username,nome,cognome,email,password;
	private Indirizzo i;
	private List<Prodotto> carrello;
	private List<MetodiPagamento> pag;
	public Account(){carrello=new ArrayList<>();pag=new ArrayList<>(); }
	public List<MetodiPagamento> getPag() {
		return pag;
	}
	public List<Prodotto> getCarrello() {
		return carrello;
	}
	
	public void addPag(MetodiPagamento pg) {
		pag.add(pg);
	}
	
	public void addPag(Collection<MetodiPagamento> pg) {
		pag.addAll(pg);
	}
	public void addCart(Prodotto pr) {
		carrello.add(pr);
	}
	public void addCart(Collection<Prodotto> pg) {
		carrello.addAll(pg);
	}
	public String getNome() {
		return nome;
	}
	@Override
	public String toString() {
		return "Account [username=" + username + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + "]";
	}
	public void setNome(String nome) {
		if(nome.length()<= 45)
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		if(cognome.length()<= 45)
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(email.length()<= 45)
			this.email = email;
	}
	public Indirizzo getI() {
		return i;
	}
	public void setI(Indirizzo i) {
		this.i = i;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username.length()<= 45)
			this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
