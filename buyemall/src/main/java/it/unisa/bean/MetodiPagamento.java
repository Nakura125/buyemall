package it.unisa.bean;

import java.sql.Date;

public class MetodiPagamento {
	private String tipo_pagamento, numero_carta, cvc;
	private Account username;
	private Date data_scadenza;

	@Override
	public String toString() {
		return "MetodiPagamento [username=" + username + ", tipo_pagamento=" + tipo_pagamento + ", numero_carta="
				+ numero_carta + ", cvc=" + cvc + ", data_scadenza=" + data_scadenza + "]";
	}

	public Account getUsername() {
		return username;
	}

	public void setUsername(Account username) {
		this.username = username;
	}

	public String getTipo_pagamento() {
		return tipo_pagamento;
	}

	public void setTipo_pagamento(String tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
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

	public Date getData_scadenza() {
		return data_scadenza;
	}

	public void setData_scadenza(Date data_scadenza) {
		this.data_scadenza = data_scadenza;
	}
}
