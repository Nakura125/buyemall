package it.unisa.bean;


public class Sprites {
	private int idSprites;
	
	private String link;
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
		return "Sprites [ idSprites=" + idSprites + ", link=" + link + "]";
	}

}
