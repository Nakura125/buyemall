package it.unisa.bean;


public class Sprites {
	private int idSprites;
	
	private String link;
	private String link_small;
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
	public String getLink_small() {
		return link_small;
	}
	public void setLink_small(String link_small) {
		this.link_small = link_small;
	}

}
