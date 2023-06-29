package it.unisa.bean;

import java.sql.Date;

public class PagamentoOrdine {
	private Ordine ordine;
	private Date data;
	private String tipo, numero_carta, cvc;
	Account user;




	public Ordine getOrdine() {

		return ordine;
	}



	public void setOrdine(Ordine ordine) {

		this.ordine = ordine;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero_carta() {
		return numero_carta;
	}

	public void setNumero_carta(String numero_carta) {
		this.numero_carta = numero_carta;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public Account getUsername() {
		return user;
	}

	public void setUsername(Account username) {
		this.user = username;
	}

	@Override
	public String toString() {
		return "PagamentoOrdine [idOrdine=" + ordine + ", data=" + data + ", tipo=" + tipo + ", numero_carta="
				+ numero_carta + ", cvc=" + cvc + ", username=" + user + "]";
	}

}
