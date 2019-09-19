package domaine;

/**
 * Classe caractérisant les cases colorées.
 * @see CaseDEchelle
 * @see CaseEcurie
 */
public abstract class CaseColoree extends Case{

	private Couleur couleur;

	/**
	 * Constructeur d'une case colorée.
	 * @param couleur la couleur attribuée à la case.
	 */
	public CaseColoree(Couleur couleur) {
		super();
		this.couleur=couleur;
	}

	/**
	 * Renvoie la couleur de la case.
	 * @return la couleur de la case.
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
}
