package domaine;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe permettant de créer un joueur humain.
 */
public class JoueurHumain extends Joueur {
	/**
	 * Crée un joueur humain(il ne sera pas un bot).
	 * @param nom Le nom de joueur
	 * @param couleur La couleur du joueur
	 */
	public JoueurHumain(String nom, Couleur couleur) {
		super(nom, couleur);
	}


	@Override
	public Pion choisirPion(int valeurDe, Plateau plateau, Scanner sc) {
		boolean case6Occupee=false;
		boolean case5Occupee=false;
		boolean case4Occupee=false;
		ArrayList<CaseDEchelle> echelleJoueurCourant=null;
		ArrayList<String> motsAutorises = new ArrayList<String>();
		motsAutorises.add("o");
		motsAutorises.add("n");
		for(int i=0;i<this.getChevaux().size();i++) {//affiche l'état des chevaux du joueur
			System.out.println("Cheval n°"+(i+1)+" est en "+this.getChevaux().get(i).getEtat().toString().toLowerCase()+".");
		}
		for(int i=0;i<this.getChevaux().size();i++) {//boucle vérifiant les états des pions et les actions qui en découlent
			if(valeurDe==6 && this.getChevaux().get(i).getEtat()==EtatPion.ECURIE) {//cheval à l'écurie et dé = 6
				String rep="";
				do {
					System.out.println("Le cheval n°"+(i+1)+" est à l'écurie, souhaitez-vous le sortir ? (o/n) ");
					if(sc.hasNextLine())rep=sc.nextLine().toLowerCase();//demande si le joueur veut sortir ou non son cheval de l'écurie avec contrôles d'erreurs à la saisie
				}while(!motsAutorises.contains(rep));//vérifie si l'entrée est une entrée attendue, si non la saisie est demandée à nouveau
				if(rep.equals("o")) {//Si la saisie est égale à o, la fonction retourne le pion choisi
					return this.getChevaux().get(i);
				}
			}
			else if(this.getChevaux().get(i).getEtat()!=EtatPion.ECURIE) {
				for(int j=0;j<4;j++) {
					if(this.getChevaux().get(i).getCouleur()==plateau.getEchelles().get(j).get(0).getCouleur()) {//boucle permettant de récupérer l'échelle du joueur qui doit choisir son pion
						echelleJoueurCourant = plateau.getEchelles().get(j);
					}
				}
				if(echelleJoueurCourant.get(5).getChevaux().contains(this.getChevaux().get(i))) {//cas où les pions ne sont plus déplaçables car arrivés.
					System.out.println("Le cheval n°"+(i+1)+" n'est pas déplaçable, il a atteint la dernière case d'échelle !");
					case6Occupee=true;
				}
				else if(echelleJoueurCourant.get(4).getChevaux().contains(this.getChevaux().get(i)) && case6Occupee==true) {
					System.out.println("Le cheval n°"+(i+1)+" n'est pas déplaçable, il ne peut plus avancer sur l'échelle !");
					case5Occupee=true;
				}
				else if(echelleJoueurCourant.get(3).getChevaux().contains(this.getChevaux().get(i)) && case6Occupee==true && case5Occupee==true) {
					System.out.println("Le cheval n°"+(i+1)+" n'est pas déplaçable, il ne peut plus avancer sur l'échelle !");
					case4Occupee=true;
				}
				else if(echelleJoueurCourant.get(2).getChevaux().contains(this.getChevaux().get(i)) && case6Occupee==true && case5Occupee==true && case4Occupee==true) {
					System.out.println("Le cheval n°"+(i+1)+" n'est pas déplaçable, il ne peut plus avancer sur l'échelle !");
				}
//sinon demande au joueur s'il veut déplacer son pion en piste
				else {
					String rep="";
					do {
						System.out.println("Le cheval n°"+(i+1)+" est déplaçable, souhaitez-vous le déplacer ? (o/n) ");
						if(sc.hasNextLine())rep = sc.nextLine().toLowerCase();
					}while(!motsAutorises.contains(rep));//mêmes controles et retour de valeur que pour l'écurie.
					if(rep.contentEquals("o")) {
						return this.getChevaux().get(i);
					}
				}
			}
		}
		return null;
	}


}
