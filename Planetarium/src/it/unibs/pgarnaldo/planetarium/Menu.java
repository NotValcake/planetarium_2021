package it.unibs.pgarnaldo.planetarium;

import it.unibs.fp.mylib.*;

public class Menu {
	
	public static final int EXIT = 0;
	public static final int NEW_PIANETA = 1;
	public static final int NEW_LUNA = 2;
	public static final int C_CELESTE = 3;
//	public static final int LUNE = 4;
//	public static final int PERCORSO = 5;
	public static final int CATASTROFE = 4;
	public static final int CENTRO_MASSA = 5;
	public static final int STRUTTURA = 6;
	public static final String BENVENUTO = "***************************************\n* Benvenuto nell'impero dei Burglars! *\n***************************************\n";
	private static int inserito;

	public static void displayMenu() {
		System.out.println("Scegli una di queste operazioni inserendo un numero:  ");
		System.out.println("inserisci 1 se hai scoperto un nuovo pianeta");
		System.out.println("inserisci 2 se hai scoperto una nuova luna");
		System.out.println("inserisci 3 per cercare un corpo celeste all interno del sistema ");
//		System.out.println("inserisci 4 e il nome del pianeta se vuoi conoscere che lune gli orbitano intorno ");
//		System.out.println("inserisci 5 se vuoi visualizzare un percorso stella > pianeta > luna");
		System.out.println("inserisci 4 se c'e stata una catastrofe naturale ");
		System.out.println("inserisci 5 per calcolare il centro di massa ");
		System.out.println("inserisci 6 per visualizzare la struttura del sistema ");		
		System.out.println("inserisci 0 per uscire dal programma");
	}

	public static int selectChoice() {
		inserito = InputDati.leggiIntero("In attesa di input...", EXIT, STRUTTURA);
		return inserito;
	}
	
	public static String inserisciChiaveRicerca() {
		String key = InputDati.leggiStringaNonVuota("Inserisci il nome del corpo celeste che vuoi cercare: ");
		return key;
	}
	
	//stampa un messaggio di benvenuto e fa inserire il nome del nuovo sistema stellare
	public static String stampaBenvenuto() {
		System.out.println(String.format(BENVENUTO));
		String nome = InputDati.leggiStringaNonVuota("Per cominciare inserisci il nome di questo nuovo sistema: ");
		return nome;
	}
}
