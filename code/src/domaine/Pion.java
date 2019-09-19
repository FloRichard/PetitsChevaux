package domaine;

/**
 * Classe décrivant un pion
 */
public class Pion {
    private String id;
	private Couleur couleur;
	private EtatPion etat;

	/**
	 * Créer un pion avec le nom et la couleur passés en paramètre.
	 * @param id le nom du pion
	 * @param couleur la couleur du pion
	 * @see Couleur
	 */
    public Pion(String id, Couleur couleur){
        this.id=id;
        this.couleur=couleur;
        etat=EtatPion.ECURIE;
    }

	/**
	 * Retourne la couleur du pion.
	 * @return la couleur du pion
	 * @see Couleur
	 */
	public Couleur getCouleur() {
        return couleur;
    }

	/**
	 * Renvoie l'état du pion.
	 * @return l'état du pion
	 * @see EtatPion
	 */
	public EtatPion getEtat() {
		return etat;
	}

	/**
	 * Permet d'attribuer un état au pion.
	 * @param etat l'etat à attribuer.
	 * @see EtatPion
	 */
	public void setEtat(EtatPion etat) {
		this.etat = etat;
	}

	/**
	 * Retourne le nom du pion.
	 * @return le nom du pion.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Permet de modifier le nom d'un pion.
	 * @param id nom du pion.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}