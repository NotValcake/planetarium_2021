package it.unibs.pgarnaldo.planetarium;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class Stella extends CorpoCeleste {
	
	//lista dei pianeti che orbitano attorno alla stella
	private ArrayList<Pianeta> pianeti = new ArrayList<Pianeta>();
	
	//restituisce la lista di pianeti che orbitano attorno alla stella
	public ArrayList<Pianeta> getPianeti() {
		return pianeti;
	}
	
	public Pianeta getPianeta(int index) {
		return this.pianeti.get(index);
	}

	public Stella(String nome, int massa, int pos_x, int pos_y) {
		super(nome, massa, pos_x, pos_y);
	}
	
	//aggiunge il pianeta p alla stella
	public boolean addPianeta(Pianeta p){
		if(this.isPianetaPresente(p) >= 0) {
			System.out.println("Pianeta già presente!");
			return false;
		}
		while(p.getPosX() == this.getPosX() && p.getPosY() == this.getPosY()) {
			System.out.println("La posizione inserita coincide con quella della stella, inserire una posizione valida.");
			p.setPos_x(InputDati.leggiIntero("Inserire posizione x: "));
			p.setPos_y(InputDati.leggiIntero("Inserire posizione y: "));
		}
		this.pianeti.add(p);
		return true;
	}
	
	//rimuove il pianeta p dalla lista di pianeti che orbitano attorno alla stella
	public boolean removePianeta(Pianeta p) {
		if(this.pianeti != null) { //controllo che la stella abbia dei pianeti			
			for(int i = this.pianeti.size()-1; i >= 0; i--) {
				if(this.pianeti.get(i).equals(p)) {
					System.out.println(String.format(MSG_RIMOSSO, this.pianeti.remove(i)));
					return true;
				}
			}
		}
		return false;
	}
	
	//ritorna l'indice della prima occorenza se presente, -1 altrimenti
	public int isPianetaPresente(Pianeta p) {
		if(this.pianeti != null) { //controllo che la stella abbia dei pianeti
			for(int i = this.pianeti.size()-1; i >= 0; i--) {
				if(this.pianeti.get(i).equals(p)) return i;
			}
		}
		return -1;
	}
	
	public Pianeta cercaPianeta(Pianeta p) {
		if(this.pianeti != null) { //controllo che la stella abbia dei pianeti
			for(int i = this.pianeti.size()-1; i >= 0; i--) {
				if(this.pianeti.get(i).equals(p)) return this.pianeti.get(i);
			}
		}
		return null;
	}
	
}
