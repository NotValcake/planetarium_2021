package it.unibs.pgarnaldo.planetarium;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class Pianeta extends CorpoCeleste {
	

	private ArrayList<Luna> lune = new ArrayList<Luna>();
	
	
	public Pianeta(String nome, int massa, int pos_x, int pos_y) {
		super(nome, massa, pos_x, pos_y);
	}

	public Pianeta(String nome) {
		super(nome);
	}

	//restituisce le lune che ruotano attorno al pianeta
	public ArrayList<Luna> getLune() {
		return lune;
	}
	
	//aggiunge la luna l alla lista delle lune del pianeta se non gia presente
	public boolean addLuna(Luna l) {
		if(cercaLuna(l) == true) {
			System.out.println("Luna già presente!");
			return false;
		}
		
		if ( this.getMassa() <= l.getMassa()) {
			System.out.println("Una luna non puo avere massa superiore al proprio pianeta, inserisci un valore minore di "
							+ this.getMassa() + ": ");
			//l.setMassa(InputDati.leggiInteroPositivo("Massa: "));
			return false;
		}
		

		if (l.getPosX() == this.getPosX() && l.getPosY() == this.getPosY()) {
			System.out.println("La posizione inserita coincide con quella del pianeta, inserire una posizione valida.");
			/*l.setPos_x(InputDati.leggiIntero("Inserire posizione x: "));
			l.setPos_y(InputDati.leggiIntero("Inserire posizione y: "));*/
			return  false;
		}
		this.lune.add(l);

		return true;
	}
	
	//rimuove la luna l
	public boolean removeLuna(Luna l) {
		if(this.lune != null) {			
			for(int i = this.lune.size() - 1; i >= 0; i--) {
				if(this.lune.get(i).equals(l)) {
					System.out.println(String.format(MSG_RIMOSSO, this.lune.remove(i)));
					return true;
				}
			}
		}
		return false;
	}

	public boolean cercaLuna(Luna l) {
		if(this.lune != null) { //controllo che il pianeta abbia delle lune			
			for(int i = this.lune.size() -1; i >= 0; i--) {
				if(this.lune.get(i).equals(l)) return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pianeta) {
			Pianeta cs = (Pianeta) obj;
			return this.nome.equals(cs.nome);			
		}else return false;
	}
	
	
}
