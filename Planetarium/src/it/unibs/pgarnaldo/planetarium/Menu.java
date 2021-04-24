package it.unibs.pgarnaldo.planetarium;

import it.unibs.fp.mylib.*;

public class Menu {
	
	public static final int EXIT = 0;
	public static final int NEW_PIANETA = 1;
	public static final int NEW_LUNA = 2;
	public static final int C_CELESTE = 3;
	public static final int CATASTROFE = 4;
	public static final int CENTRO_MASSA = 5;
	public static final int STRUTTURA = 6;
	public static final int COLLISIONI=7;

	public static final String BENVENUTO = "***************************************\n* Benvenuto nell'impero dei Burglars! *\n***************************************\n";
	private static int inserito;
	private static final String [] menu= { "1 se hai scoperto un nuovo pianeta",
			                               "2 se hai scoperto una nuova luna",
	                                       "3 per cercare un corpo celeste all interno del sistema" ,       
	                                       "4 se c'e stata una catastrofe naturale ",
	                                       "5 per calcolare il centro di massa",
	                                       "6 per visualizzare la struttura del sistema",
	                                       "7 per vedere le collisioni possibili",
	                                       "0 per uscire dal programma" } ;

	public static void displayMenu() {
		System.out.println("Scegli una di queste operazioni inserendo un numero:  ");
		for(int i=0; i<menu.length;i++){
			System.out.printf("%s \n",menu[i]);
		}
		
	}

	public static int selectChoice() {
		inserito = InputDati.leggiIntero("In attesa di input...", EXIT, COLLISIONI);
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
