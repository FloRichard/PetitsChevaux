package domaine;

/**
 * Enumération des couleurs utilisées dans le jeu.
 */
public  enum Couleur {
    JAUNE('J',"\u001b[1;33m"), 
    ROUGE('R',"\u001b[1;31m"),
    BLEU('B',"\u001b[1;34m"),
    VERT('V',"\u001b[1;32m");
    /**
     * Symbole de la couleur.
     */
   private final char symbole;
    /**
     * Code couleur de la couleur pour l'affichage sur console.
     */
   private final String codeCouleur;
   private Couleur(char symbole,String codeCouleur){
       this.symbole=symbole;
       this.codeCouleur=codeCouleur;
   }

    /**
     * Renvoie le symbole d'un des éléments de l'énumération.
     * @return le symbole.
     */
    public char getSymbole() {
        return symbole;
    }

    /**
     * Renvoie le code couleur d'une couleur.
     * @return le code couleur correspondant à la couleur.
     */
    public String getCodeCouleur() {
    	return codeCouleur;
    }
}