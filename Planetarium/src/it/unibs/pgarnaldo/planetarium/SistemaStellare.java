package it.unibs.pgarnaldo.planetarium;

import java.util.ArrayList;
import it.unibs.fp.mylib.*;
import java.math.*;
public class SistemaStellare {

	private static final String SALUTO_FINALE = "ARRIVEDERCI, A MAI PIU! :)";
	private static final String LITTLE_DIV = "_____________________________________________________________________________";
	private static final String DIVIDER = "=============================================================================";
	public static final double EPSILON = 0.1;
	public static final String INSERISCI_STELLA = "Inserisci i dati della stella di posizione (0;0): ";
	public static final String NUOVO_PIANETA = "Inserisci i dati del nuovo pianeta: ";
	public static final String ALERT_MASSA_PIANETA = "Un pianeta non puo avere massa superiore alla propria stella, inserisci un valore minore di ";
	public static final String NUOVA_LUNA = "Inserisci i dati della nuova luna: ";
	public static final String ALERT_LUNA_NON_AGGIUNTA = "La luna non è stata aggiunta perche il pianeta scelto non e presente.";
	public static final String CONTINUARE = "\nPremi 0 per continuare...\n";
    public static final String SUCCESSO="Operazione andata a buon fine";

	private String nome;
	private Stella stella;
	private CentroMassa centro_massa = new CentroMassa();

	public SistemaStellare(String nome, Stella stella) {
		this.nome = nome;
		this.stella = stella;
	}

	//metodo per inserire dati della stella
	public void addStella() {
		System.out.println(DIVIDER);
		System.out.println(INSERISCI_STELLA);
		String nome_stella = InputDati.leggiStringaNonVuota("Nome: ");
		//nome_stella.toLowerCase();
		int massa_stella = InputDati.leggiInteroPositivo("massa: ");
		System.out.println(DIVIDER);
		Stella stella = new Stella(nome_stella, massa_stella, 0, 0);
		this.stella = stella;
		this.centro_massa.calcSommaMasse(massa_stella);
	}

	public Pianeta inserisciPianeta() {
		System.out.println(DIVIDER);
		System.out.println(NUOVO_PIANETA);
		String nome = InputDati.leggiStringaNonVuota("Nome: ");
		//nome.toLowerCase();
		int massa = InputDati.leggiInteroPositivo("massa: ");
		while (massa >= this.stella.getMassa()) { //controllo che la massa del pianeta sia minore della massa della stella
			massa = InputDati.leggiInteroPositivo(
					ALERT_MASSA_PIANETA
							+ this.stella.getMassa() + ": ");
		}
		int pos_x = InputDati.leggiIntero("posizione x: ");
		int pos_y = InputDati.leggiIntero("posizione y: ");
		System.out.println(DIVIDER);
		Pianeta pianeta = new Pianeta(nome, massa, pos_x, pos_y);
		return pianeta;
	}

	public Luna inserisciLuna() {
		System.out.println(DIVIDER);
		System.out.println(NUOVA_LUNA);
		String nome = InputDati.leggiStringaNonVuota("Nome: ");
		//nome.toLowerCase();
		int massa = InputDati.leggiInteroPositivo("massa: ");
		int pos_x = InputDati.leggiIntero("posizione x: ");
		int pos_y = InputDati.leggiIntero("posizione y: ");
		System.out.println(DIVIDER);
		Luna luna = new Luna(nome, massa, pos_x, pos_y);
		return luna;
	}

	//metodo per aggiungere pianeti
	public void addPianeta(Pianeta p) {
		if (this.stella.addPianeta(p))
		{
			this.addInfoCDM(p);
			System.out.println(SUCCESSO);
		}
	}

	//metodo che aggiunge una luna l al pianeta p
	public void addLuna(Pianeta p, Luna l) {
		Pianeta pianeta = this.stella.cercaPianeta(p);
		if (pianeta != null) {
			if (pianeta.addLuna(l))
			{
				this.addInfoCDM(l);
				System.out.println(SUCCESSO);
			}
		} else {
			System.out.println(DIVIDER);
			System.out.println(ALERT_LUNA_NON_AGGIUNTA);
			System.out.println(DIVIDER);
		}
	}

	//metodo che rimuove il pianeta p
	public void removePianeta(Pianeta p) {
		if (this.stella.getPianeti() != null) { //controllo che la stella abbia dei pianeti
			for (int i = this.stella.getPianeti().size() - 1; i >= 0; i--) {
				if (this.stella.getPianeti().get(i).equals(p)) {
					System.out.println(String.format(CorpoCeleste.MSG_RIMOSSO, this.stella.getPianeti().get(i)));
					this.subInfoCDM(this.stella.getPianeta(i));
					//sottraiamo le informazioni delle lune del pianeta dal centro di massa
					for (Luna luna : this.stella.getPianeta(i).getLune()) {
						this.subInfoCDM(luna);
					}
					this.stella.getPianeti().remove(i);
					return;
				}
			}
		}
	}

	//metodo che rimuove la luna l
	public void removeLuna(Luna l) {
		if (this.stella.getPianeti() == null) {
			System.out.println(DIVIDER);
			System.out.println("Nessuna luna da rimuovere!");
			System.out.println(DIVIDER);
			return;//se non ci sono pianeti non posso rimuovere nulla!
		}
		//copio la lista di pianeti in una lista temporanea
		ArrayList<Pianeta> pianeti = this.stella.getPianeti();
		//controllo di ogni pianeta della lista
		for (int i = pianeti.size() - 1; i >= 0; i--) {
			if (pianeti.get(i).getLune() == null) continue;//se il pianeta i-esimo non ha lune, passo al successivo
			//per ogni pianeta copio temporaneamente la sua lista di lune, poi cerco per nome la luna da eliminare
			ArrayList<Luna> lune = pianeti.get(i).getLune();
			for (int j = lune.size() - 1; j >= 0; j--) {
				//se trovo la luna la elimino e stampo un messaggio
				if (lune.get(j).equals(l)) {
					this.subInfoCDM(lune.get(j));
					lune.remove(j);
					System.out.println(DIVIDER);
					System.out.println(l.toString() + " rimossa con successo!");
					System.out.println(DIVIDER);
					return;
				}
			}
		}
		//se la luna alla fine del doppio ciclo non e stata rimossa, stampo un avviso
		System.out.println(DIVIDER);
		System.out.println("luna non trovata!");
		System.out.println(DIVIDER);
	}

	//funzione che permette di ricercare un corpo celeste e dice se presente o meno e, se si tratta di un pianeta ne stampa le lune, se si tratta di una luna visualizza il percorso
	public boolean ricercaCorpo(CorpoCeleste src) {
		if (src instanceof Pianeta) {
			if (this.stella.getPianeti() == null) { //controlla se sono presenti pianeti
				System.out.println(DIVIDER);
				System.out.println("Nessun pianeta presente!");
				System.out.println(DIVIDER);
				return false;
			}
			Pianeta p = (Pianeta) src;
			int i = this.stella.isPianetaPresente(p);
			if (i >= 0) {
				System.out.println(DIVIDER);
				System.out.println("Il pianeta " + p + " e presente nel sistema " + this.nome);
				System.out.println(DIVIDER);
				System.out.println("le lune del pianeta sono:");
				for (Luna luna : this.stella.getPianeta(i).getLune()) {
					System.out.println(luna.toString());
				}
				System.out.println(DIVIDER);
				return true;
			}
		}
		if (src instanceof Luna) {
			Luna l = (Luna) src;
			if (this.stella.getPianeti() != null) { // controlla che siano presenti dei pianeti
				ArrayList<Pianeta> pianeti = this.stella.getPianeti();
				for (int i = 0; i < pianeti.size(); i++) {
					// per ogni pianeta copio temporaneamente la sua lista di lune, poi cerco per
					// nome la luna da eliminare
					if (pianeti.get(i).getLune() != null) { // controllo se il pianeta i-esimo ha delle lune

						ArrayList<Luna> lune = pianeti.get(i).getLune();
						for (int j = lune.size() - 1; j >= 0; j--) {
							// se trovo la luna la elimino e stampo un messaggio
							if (lune.get(j).equals(l)) {
								System.out.println(DIVIDER);
								System.out.println(
										l.toString() + "presente, ed orbita attorno a " + pianeti.get(i).toString());
								System.out.println(
										"Puoi raggiungere questa luna seguendo il percorso: " + this.stella.toString()
												+ "->" + pianeti.get(i).toString() + "->" + lune.get(j).toString());
								System.out.println(DIVIDER);
								return true;
							}
						}
					}
				}
			}
		}
		System.out.println(DIVIDER);
		System.out.println("Nessuna corrispondenza.");
		System.out.println(DIVIDER);
		return false;
	}

	public int getCollisioni() {
		int collisioni = 0;
		collisioni = collisioniLL(collisioni);
		collisioni = collisioniPLeLL(collisioni);
		collisioni = collisioniPP(collisioni);
      return collisioni;
	}

	/*	for(int j = i+1; j < this.stella.getPianeti().size(); j++)
		{
			Pianeta p2 = this.stella.getPianeta(j);
			if(p1.getDistanza(this.stella) - p2.getDistanza(this.stella) < EPSILON){
				collisioni++;
			}
		}
	}*/


        public int collisioniLL (int collisioni){ //collisioni luna-luna dello stesso pianeta

        	for (int i=0;i < this.stella.getPianeti().size() - 1; i++)
        	{
				Pianeta p1 = this.stella.getPianeta(i);

				for (int j = 0; j < p1.getLune().size() - 1; j++)
				{
					Luna l = p1.getLune().get(j);
					for (int k = j + 1; k < p1.getLune().size() - 2; k++)
					{
						if (Math.abs(l.getDistanza(p1) - p1.getLune().get(k).getDistanza(p1)) < EPSILON)
						{ // utilizzo epsilon perché le distanze sono variabili double e a causa degli arrotondamenti potrebbero esserci problemi nel confronto
							collisioni++;
						}
					}
				}
			}
        	return collisioni;
		}

	public int collisioniPP (int collisioni){ //collisioni pianeta-pianeta

		for (int i=0;i < this.stella.getPianeti().size() - 1; i++)
		{
			Pianeta p1 = this.stella.getPianeta(i);
			for(int j = i+1; j < this.stella.getPianeti().size(); j++)
			{
				Pianeta p2 = this.stella.getPianeta(j);
				if( Math.abs(p1.getDistanza(this.stella) - p2.getDistanza(this.stella))  < EPSILON)
				{
					collisioni++;
				}
			}

		}

        	return collisioni;
	}

	public int collisioniPLeLL(int collisioni) { // collisione tra luna e pianeta o tra lune di diversi pianeti

        	for (int i=0; i < this.stella.getPianeti().size() - 1; i++)
        	{
				Pianeta p1 = this.stella.getPianeta(i);
				for (int j = 0; j < p1.getLune().size() - 1; j++)
				{
					Luna l = p1.getLune().get(j);
					for( int k = i+1; k < this.stella.getPianeti().size(); k++)
					{
						Pianeta p2 = this.stella.getPianeta(k);
						if( Math.abs(p1.getDistanza(p2) - p1.getDistanza(l)) < EPSILON)
						{
							collisioni++;
						}

							for(int h=0;h<p2.getLune().size();h++)
							{
								if (Math.abs((p1.getDistanza(this.stella)+p1.getDistanza(l))-(p2.getDistanza(this.stella)-p2.getDistanza(l)))<EPSILON)
								{
									collisioni++;
								}
							}

					}
				}
			}
		return collisioni;
	}



		//funzione che aggiorna il centro di massa quando viene *AGGIUNTO* un corpo celeste
		public void addInfoCDM (CorpoCeleste cs){
			centro_massa.calcSommaPosX((double) cs.getPosX() * (double) cs.getMassa());
			centro_massa.calcSommaPosY((double) cs.getPosY() * (double) cs.getMassa());
			centro_massa.calcSommaMasse((double) cs.getMassa());
		}

		//funzione che aggiorna il centro di massa quando viene *RIMOSSO* un corpo celeste
		public void subInfoCDM (CorpoCeleste cs){
			centro_massa.calcDiffPosX((double) cs.getPosX() * (double) cs.getMassa());
			centro_massa.calcDiffPosY((double) cs.getPosY() * (double) cs.getMassa());
			centro_massa.calcDiffMasse((double) cs.getMassa());
		}


		public void cosaFare ( int scelta){

			switch (scelta) {
				case Menu.EXIT:
					System.out.println(SALUTO_FINALE);

					break;

				case Menu.NEW_PIANETA:
					Pianeta p = inserisciPianeta();
					this.addPianeta(p);
					System.out.println(DIVIDER);
					break;

				case Menu.NEW_LUNA:
					Luna l = inserisciLuna();
					//System.out.println(DIVIDER);
					String nome_pianeta = InputDati.leggiStringaNonVuota("Attorno a che pianeta orbita la nuova luna? ");
					Pianeta p_1 = new Pianeta(nome_pianeta);
					this.addLuna(p_1, l);
					System.out.println(DIVIDER);
					break;

				case Menu.C_CELESTE:
					System.out.println(DIVIDER);
					boolean scelta_2 = InputDati.trueOrfalse("Vuoi cercare una luna o un pianeta? ");
					if (scelta_2) {
						String nome_corpo = InputDati.leggiStringaNonVuota("Inserisci il nome: ");
						Luna l_2 = new Luna(nome_corpo);
						this.ricercaCorpo(l_2);
					} else {
						String nome_corpo = InputDati.leggiStringaNonVuota("Inserisci il nome: ");
						Pianeta p_2 = new Pianeta(nome_corpo);
						this.ricercaCorpo(p_2);
					}
					System.out.println(DIVIDER);
					break;
// il case Lune è stato rimosso e la funzione che stampa il percorso è stata accorpata a ricerca luna
				case Menu.CATASTROFE:
					System.out.println(DIVIDER);
					boolean scelta_3 = InputDati.trueOrfalse("Vuoi eliminare un pianeta o una luna? ");
					if (scelta_3) {
						String nome_corpo = InputDati.leggiStringaNonVuota("Inserisci il nome: ");
						Luna l_2 = new Luna(nome_corpo);
						this.removeLuna(l_2);
					} else {
						String nome_corpo = InputDati.leggiStringaNonVuota("Inserisci il nome: ");
						Pianeta p_2 = new Pianeta(nome_corpo);
						this.removePianeta(p_2);
					}
					System.out.println(DIVIDER);
					break;

				case Menu.CENTRO_MASSA:
					centro_massa.calcolaCMDX();
					System.out.println(DIVIDER);
					break;

				case Menu.STRUTTURA:
					this.stampaStruttura();
					System.out.println(DIVIDER);
					break;

				case Menu.COLLISIONI:
					System.out.println(getCollisioni());

			}


		}



	private void stampaStruttura() {

		System.out.println(DIVIDER);
		System.out.println(this.nome);
		System.out.println(DIVIDER);
		System.out.println(this.stella);
		for (Pianeta p : this.stella.getPianeti()) {
			System.out.println(LITTLE_DIV);
			System.out.println("-" + p);
			for (Luna l : p.getLune()) {
				System.out.println("---" + l);
			}
		}
		System.out.println(DIVIDER);
	}

}
