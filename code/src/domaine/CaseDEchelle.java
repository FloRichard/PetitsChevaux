package domaine;

/**
 * Classe caractérisant une case de l'échelle d'un joueur. Une échelle est constituée de 6 cases échelles.
 */
public class CaseDEchelle extends CaseColoree {
	private int numeroCase=1;

	/**
	 * Créer une case de l'échelle d'un joueur.
	 * @param couleur la couleur de l'échelle.
	 * @param numeroCase numéro pour l'affichage.
	 */
	public CaseDEchelle(Couleur couleur, int numeroCase) {
		super(couleur);
		this.numeroCase=numeroCase;
	}

	@Override
	public boolean peutPasser(Pion chevalOccupant) {
		if(this.getChevaux().isEmpty()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean peutSArreter(Pion chevalOccupant) {
		if(this.peutPasser(chevalOccupant)){
			return true;
		}else{
			return false;
		}
	}
	public String toString(){
		if (this.getChevaux().isEmpty()) {
			return this.getCouleur().getCodeCouleur()+Integer.toString(numeroCase)+" \u001b[0m";
		}
		else {
			return this.getChevaux().get(0).getCouleur().getCodeCouleur()+Integer.toString(this.getChevaux().size())+" \u001b[0m";
		}
	}

}
