package it.unibs.pgarnaldo.planetarium;

public class SistemaStellareMain {

	public static final String COLLISIONE = "Attenzione!! Ci sono %d corpi in rotta di collisione!";

	public static void main(String[] args) {
		
		//stampa benvenuto nel programma e crea subito un nuovo sistema stellare
		String nome_sistema = Menu.stampaBenvenuto();
		
		SistemaStellare sistema_stellare = new SistemaStellare(nome_sistema, null);
		sistema_stellare.addStella();
		
		int scelta;
		//esegue il programma fino alla pressione di 0 = EXIT
		do {
	         Menu.displayMenu();
	         int collisioni = sistema_stellare.getCollisioni();
	         if(collisioni > 0)
	         	System.out.println(String.format(COLLISIONE, collisioni));
	         scelta = Menu.selectChoice();
	         sistema_stellare.cosaFare(scelta);

	      } while(scelta != Menu.EXIT);
		
	}
}
