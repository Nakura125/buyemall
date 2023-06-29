package it.unisa.bean;

public class Sprites {
	private int idSprites;
	private String link;
	private String link_small;

	private Prodotto prodotto;
	
	public static Sprites nullSprites() {
		Sprites p=new Sprites();
		p.setIdSprites(20000);
		String urlimgnull="https://www.pngall.com/wp-content/uploads/2/Question-Mark-PNG.png";
		p.setLink(urlimgnull);
		return p;
	}
	

	public int getIdSprites() {
		return idSprites;
	}

	public void setIdSprites(int idSprites) {
		this.idSprites = idSprites;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}



	@Override
	public String toString() {
		return "Sprites [idSprites=" + idSprites + ", link=" + link + ", link_small=" + link_small + "]";
	}
	public String getLink_small() {
		return link_small;
	}
	public void setLink_small(String link_small) {
		this.link_small = link_small;
	}
	public Prodotto getProdotto() {
		return prodotto;
	}
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

}
