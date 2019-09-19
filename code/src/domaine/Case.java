package domaine;

import java.util.ArrayList;

/**
 * Classe permettant de créer et de caractériser une case. Chaques cases a une liste de chevaux.
 */
public abstract class Case {
	private ArrayList<Pion> chevaux;

	/**
	 * Créer une case en instanciant sa liste de chevaux.
	 */
	public Case() {
		chevaux=new ArrayList<Pion>();
	}

	/**
	 * Renvoie la liste de chevaux d'une case.
	 * @return la liste de chevaux de la case.
	 */
	public ArrayList<Pion> getChevaux() {
		return chevaux;
	}

	/**
	 * Ajoute un cheval à la liste de la case.
	 * @param cheval le cheval à ajouter sur la case.
	 */
	public void ajouteCheval(Pion cheval) {//Penser à le remove quand il part de la case
		chevaux.add(cheval);
	}

	/**
	 * Méthode permettant de vérifier si un cheval peut passer ou non.
	 * @param chevalOccupant le cheval qui va se déplacer.
	 * @return Retourne vrai si le cheval peut passer, faux sinon.
	 * @see Partie
	 */
	public abstract boolean peutPasser(Pion chevalOccupant);//Faire cette m�thode avant peutSArreter et si retourne faux pas besoin d'appeler peutSArreter

	/**
	 * Méthode permettant de vérifier si un cheval peut s'arrêter sur une case.
	 * @param chevalOccupant le cheval qui va se déplacer.
	 * @return Retourne vrai si le cheval peut s'arrêter, faux sinon.
	 * @see Partie
	 */
	public abstract boolean peutSArreter(Pion chevalOccupant);
}
