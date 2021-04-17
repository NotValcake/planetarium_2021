package it.unibs.pgarnaldo.planetarium;

public class CentroMassa {
	
	private static final String DIVIDER = "==================================================";
	private double somma_masse = 0;
	private double somma_posx = 0;
	private double somma_posy = 0;
	private double centro_massaX = 0;
	private double centro_massaY = 0;
	
	//getters
	public double getSomma_masse() {
		return somma_masse;
	}

	public double getSomma_posx() {
		return somma_posx;
	}


	public double getSomma_posy() {
		return somma_posy;
	}

	//calcolo somma masse
	public double calcSommaMasse (double massa) {
		this.somma_masse += massa; 
		return somma_masse; 
		}
	
	
	//calcolo la somma pesata di coordinate x, in cui posx_massa è x*massa(del corpo celeste)
	public double calcSommaPosX(double posx_massa) {
		this.somma_posx += posx_massa;
		return somma_posx;
	}
	
	
	//calcolo la somma pesata di coordinate y, in cui posy_massa è y*massa(del corpo celeste)
	public double calcSommaPosY(double posy_massa) {
		this.somma_posy += posy_massa;
		return somma_posy;
	}
	
	public void calcolaCMDX() {
		centro_massaX = this.somma_posx / this.somma_masse;
	    centro_massaY = this.somma_posy / this.somma_masse;
	    System.out.println(DIVIDER);
	    System.out.format("Il centro di massa del sistema stellare attualmente è :( %.2f ; %.2f ) \n", centro_massaX, centro_massaY);
	    System.out.println(DIVIDER);
	}

	public void calcDiffMasse(double massa) {
		this.somma_masse -= massa;	
	}

	public void calcDiffPosY(double posy_massa) {
		this.somma_posy -= posy_massa;
	}

	public void calcDiffPosX(double posx_massa) {
		this.somma_posx -= posx_massa;		
	}
	
	
}

