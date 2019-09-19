package domaine;

/**
 * Classe qui créée des cases de chemins, ces cases constituent la piste principale du jeu.
 */
public class CaseDeChemin extends Case{
	/**
	 * Ajoute un cheval sur la case.
	 * Si l'état du cheval est ECURIE alors il passe en PISTE .
	 * @param cheval le cheval à ajouter sur la case.
	 * @see EtatPion
	 */
	public void ajouteCheval(Pion cheval) {
		super.ajouteCheval(cheval);
		if (cheval.getEtat()==EtatPion.ECURIE){
			cheval.setEtat(EtatPion.PISTE);
		}
	}

	/**
	 * Créer une case de la piste
	 */
	public CaseDeChemin(){
	}
	@Override
	public boolean peutPasser(Pion chevalPretADeplacer) {
		boolean passe=false;
		int i=0;
		do{
			if(this.getChevaux().isEmpty()){
				passe=true;
			} else if (this.getChevaux().get(i).getCouleur()==chevalPretADeplacer.getCouleur()) {
				passe=true;
			}
			i++;
		}while(passe==false && i<this.getChevaux().size());
		return passe;
	}

	@Override
	public boolean peutSArreter(Pion chevalPretADeplacer) {
		return true;
	}
	public String toString(){
		if (this.getChevaux().isEmpty()) {
			return "0 ";
		}else{
			return this.getChevaux().get(0).getCouleur().getCodeCouleur()+Integer.toString(this.getChevaux().size())+" \u001b[0m";
		}
	}
}
