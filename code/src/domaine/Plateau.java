package domaine;

import java.util.ArrayList;

/**
 * Classe permettant d'implémenter le plateau de jeu.
 */
public class Plateau {
	private ArrayList<ArrayList<CaseDEchelle>> echelles;
	private ArrayList<CaseDeChemin> chemin;
	private ArrayList<CaseEcurie> ecuries;

	/**
	 * Crée le plateau en instanciant la piste, les écuries et les échelles.
	 */
	public Plateau(){
		echelles=new ArrayList<ArrayList<CaseDEchelle>>();
		chemin = new ArrayList<CaseDeChemin>();
		ecuries =new  ArrayList<CaseEcurie>();
	}

	/**
	 * Méthode permettant d'afficher le plateau de jeu.
	 * On affiche un tableau à 2 dimensions de String relié aux écuries, aux échelles et à la piste.
	 */
	public void afficher() {
		String[][] plateauAffiche=new String[15][15];
		for(int i=0;i<15;i++) {
			for (int j = 0; j < 15; j++) {
				if (i == 0 && j <= 8 && j >= 6) {
					plateauAffiche[i][j]=this.getChemin().get(j - 6).toString();//path 0,1,2
				} else if (i >= 1 && i <= 5 && j == 8) {
					plateauAffiche[i][j]=this.getChemin().get(i + 2).toString();// path 3,4,5,6,7
				} else if (i == 6 && j >= 8 && j <= 14) {
					plateauAffiche[i][j]=this.getChemin().get(j).toString();//path 8,9,10,11,12,13,14
				} else if (i == 7 && j == 14) {
					plateauAffiche[i][j]=this.getChemin().get(15).toString();//path 15
				} else if (i == 8 && j >= 8 && j <= 14) {
					plateauAffiche[i][j]=this.getChemin().get(30 - j).toString();//path 16,17,18,19,20,21,22
				} else if (i >= 9 && i <= 14 && j == 8) {
					plateauAffiche[i][j]=this.getChemin().get(i + 14).toString();//path 23,24,25,26,27,28
				} else if (i == 14 && j == 7) {
					plateauAffiche[i][j]=this.getChemin().get(29).toString();//path 29
				} else if (i >= 9 && i <= 14 && j == 6) {
					plateauAffiche[i][j]=this.getChemin().get(44 - i).toString();//path 30,31,32,33,34,35
				} else if (i == 8 && j >= 0 && j <= 6) {
					plateauAffiche[i][j]=this.getChemin().get(42 - j).toString();//path 36,37,38,39,40,41,42
				} else if (i == 7 && j == 0) {
					plateauAffiche[i][j]=this.getChemin().get(43).toString();// path 43
				} else if (i == 6 && j >= 0 && j <= 6) {
					plateauAffiche[i][j]=this.getChemin().get(44 + j).toString();//path 44,45,46,47,48,49,50
				} else if (i >= 1 && i <= 5 && j == 6) {
					plateauAffiche[i][j]=this.getChemin().get(56 - i).toString();//path 51,52,53,54,55, jusqu'ici chemin
				}else if(i==7 && j>0 && j<7){//initialisation case échelles
					plateauAffiche[i][j]=this.getEchelles().get(0).get(j-1).toString();
				}else if(i==7 && j>7 && j<14){
					plateauAffiche[i][j]=this.getEchelles().get(2).get(13-j).toString();//pour faire aller le tableau dans le bon sens
				}else if(j==7 && i>0 && i<7){
					plateauAffiche[i][j]=this.getEchelles().get(1).get(i-1).toString();
				}else if(j==7 && i>7  && i<14){
					plateauAffiche[i][j]=this.getEchelles().get(3).get(13-i).toString();
				}else if(i==3 && j==3){//initialisation cases ecuries
					plateauAffiche[i][j]=this.getEcuries().get(0).toString();
				}else if(i==11 && j==3){
					plateauAffiche[i][j]=this.getEcuries().get(3).toString();
				}else if(i==3 && j==11){
					plateauAffiche[i][j]=this.getEcuries().get(1).toString();
				}else if(i==11 && j==11){
					plateauAffiche[i][j]=this.getEcuries().get(2).toString();
				}
				
				if(plateauAffiche[i][j]==null){
					System.out.print("  ");
				}else{
					System.out.print(plateauAffiche[i][j]);
				}

			}
			System.out.println("");
		}

	}

	/**
	 * Renvoie les échelles.
	 * @return la liste des échelles.
	 */
	public ArrayList<ArrayList<CaseDEchelle>> getEchelles() {
		return echelles;
	}

	/**
	 * Renvoie la piste
	 * @return la liste chemin contenant les cases Chemins.
	 */
	public ArrayList<CaseDeChemin> getChemin() {
		return chemin;
	}

	/**
	 * Renvoie les écuries.
	 * @return la liste des cases écuries.
	 */
	public ArrayList<CaseEcurie> getEcuries() {
		return ecuries;
	}

	/**
	 * Méthode permettant de déplacer un pion. Elle gère aussi le changement d'état du pion pour la sortie d'écurie.
	 * @param pion le pion à déplacer.
	 * @param caseArrivee la case sur laquelle le pion va se déplacer.
	 */
	public void deplacerPionA(Pion pion,Case caseArrivee){
			caseArrivee.ajouteCheval(pion);
			if(pion.getEtat()==EtatPion.ECURIE){
				pion.setEtat(EtatPion.PISTE);
			}
			System.out.println("Le pion est deplacé.");

	}

	/**
	 * Méthode permettant de sortir un pion de l'écurie.
	 * @param pionADeplacer le pion à déplacer.
	 * @param joueurCourant le joueur en train de jouer.
	 * @see Joueur
	 * @see Pion
	 */
	public void sortieEcurie(Pion pionADeplacer,Joueur joueurCourant){
		Case case1;
		int indexCase=0;
		for (int i = 0; i < 4; i++) {
			if (this.getEcuries().get(i).getChevaux().contains(pionADeplacer)) {
				case1 = this.getEcuries().get(i);
				indexCase = this.getEcuries().indexOf(case1);
			}
		}
		this.getEcuries().get(indexCase).getChevaux().remove(pionADeplacer);
		this.deplacerPionA(pionADeplacer, joueurCourant.getCaseDepart());


	}
}
