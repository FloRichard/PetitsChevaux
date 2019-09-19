package domaine;

/**
 * Enumération caractérisant l'état d'un pion.
 */
public enum EtatPion {
	/**
	 * Le cheval est sur la piste.
	 * @see CaseDeChemin
	 */
	PISTE,
	/**
	 * Le cheval est à l'écurie.
	 * @see CaseEcurie
	 */
	ECURIE,
	/**
	 * Le cheval est sur l'échelle.
	 * @see CaseDEchelle
	 */
	ECHELLE;
}
