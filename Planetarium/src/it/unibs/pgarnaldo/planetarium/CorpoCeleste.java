package it.unibs.pgarnaldo.planetarium;


public class CorpoCeleste {
	
	protected static final String MSG_RIMOSSO = "%s è stato distrutto con successo :)!";

	private int massa;
	private int pos_x;
	private int pos_y;
	
	protected String nome;

	public CorpoCeleste(String nome, int massa, int pos_x, int pos_y) {
		this.nome = nome;
		this.massa = massa;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}

	public CorpoCeleste(String nome) {
		this.nome = nome;
	}

	public int getMassa() {
		return massa;
	}

	public int getPosX() {
		return pos_x;
	}

	public int getPosY() {
		return pos_y;
	}


	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	public void setMassa(int massa) {
		this.massa = massa;
	}

	public double getDistanza(CorpoCeleste cs){
		return Math.sqrt(Math.pow((double)this.getPosX()-cs.getPosX(), 2) + Math.pow((double)this.getPosY()-cs.getPosY(), 2));
	}

	@Override
	public String toString() {
		return this.nome;
	}
	//due corpi celesti sono uguali se hanno lo stesso nome
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CorpoCeleste) {
			CorpoCeleste cs = (CorpoCeleste) obj;
			return this.nome.equals(cs.nome);			
		}else return false;
	}
	
	
	
}
