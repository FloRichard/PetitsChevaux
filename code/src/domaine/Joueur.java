package domaine;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe caractérisant un joueur.
 */
public abstract class Joueur {
	private String nom;
    private Couleur couleur;
    private ArrayList<Pion> listeChevaux;
    private CaseDeChemin caseDepart;
    private CaseDeChemin caseEntreeEchelle;

    /**
     * Constructeur d'un joueur, instancie ses chevaux.
     * @param nom nom attribué au joueur.
     * @param couleur couleur attribuée au joueur.
     */
    public Joueur (String nom, Couleur couleur){
        this.nom=nom;
        this.couleur=couleur;
        listeChevaux=new ArrayList<Pion>();
        listeChevaux.add(new Pion("1",getCouleur()));
        listeChevaux.add(new Pion("2",getCouleur()));
        listeChevaux.add(new Pion("3",getCouleur()));
        listeChevaux.add(new Pion("4",getCouleur()));
    }

    /**
     * Renvoie la case départ du joueur.
     * @return la case de départ du joueur
     */
    public CaseDeChemin getCaseDepart(){
        return caseDepart;
    }

    /**
     * Définit la case de départ du joueur.
     * @param caseDepart la case de chemin qui va devenir la case de départ.
     */
    public void setCaseDepart(CaseDeChemin caseDepart){
        this.caseDepart=caseDepart;
    }
    /**
     * Renvoie la case d'entrée d'échelle du joueur.
     * @return caseEntreeEchelle
     */
    public CaseDeChemin getCaseEntreeEchelle() {
        return caseEntreeEchelle;
    }

    /**
     * Permet de définir une case d'entrée d'échelle.
     * @param caseEntreeEchelle la case de chemin qui va devinir la case d'entrée de l'échelle du joueur.
     */

    public void setCaseEntreeEchelle(CaseDeChemin caseEntreeEchelle) {
        this.caseEntreeEchelle = caseEntreeEchelle;
    }

    /**
     * Permet de définir le nom du joueur.
     * @param nom le nom du joueur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoie le nom du joueur.
     * @return le nom du joueur.
     */
    public String getNom() {
       return nom;
    }

    /**
     * Renvoie la couleur du joueur.
     * @return la couleur du joueur.
     */
    public Couleur getCouleur() {
        return couleur;
    }

    /**
     * Renvoie la liste de chevaux d'un joueur.
     * @return la liste de chevaux du joueur.
     */
    public ArrayList<Pion> getChevaux(){
        return listeChevaux;
    }
    public String toString(){
        return "Nom : "+getNom()+" Couleur : "+getCouleur()+"\n";
    }

    /**
     * Méthode permettant de choisir un pion pour le déplacement.
     * @param valeurDe valeur du dé
     * @param plateau le plateau où se déroule la partie
     * @param sc le scanner pour choisir le pion
     * @return le pion à déplacer
     * @see Plateau
     * @see Pion
     */
    public abstract Pion choisirPion(int valeurDe, Plateau plateau, Scanner sc);
}

