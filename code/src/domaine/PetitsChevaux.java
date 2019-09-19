package domaine;

import java.util.Scanner;

/**
 * Classe permettant de lancer la partie.
 */
public class PetitsChevaux {
	/**
	 * Le main du jeu, il instancie un scanner nécessaire aux entrées clavier.
	 * Il créer une partie et effectue l'initialisation des joueurs et du plateau et déroule la partie.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Partie p = new Partie();
		p.initialiserJoueurs(sc);
		p.initialiserPlateau();
		p.getPlateau().afficher();
		while(p.partieEstTerminee()==false) {
			for(int i=0;i<4;i++) {
				p.setJoueurCourant(p.getJoueurs().get(i));
				p.jouerUnTour(sc);
			}
		}
		System.out.println("Partie terminée. Le joueur "+p.getJoueurCourant().getCouleur().getCodeCouleur()+p.getJoueurCourant().getNom()+" \u001b[0ma gagné !");

	}

}
