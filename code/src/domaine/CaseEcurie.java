package domaine;

/**
 * Classe caractérisant l'écurie d'un joueur.
 */
public class CaseEcurie extends CaseColoree {
	/**
	 * Créer une écurie, il y'en a une par joueur.
	 * @param couleur la couleur de l'écurie.
	 */
	public CaseEcurie(Couleur couleur) {
		super(couleur);
	}

	@Override
	public boolean peutPasser(Pion chevalOccupant) {
		return false;
	}

	@Override
	public boolean peutSArreter(Pion chevalOccupant) {
		return false;
	}
	public String toString(){

		return this.getCouleur().getCodeCouleur()+Integer.toString(this.getChevaux().size())+" \u001b[0m";

	}
}
