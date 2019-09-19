package domaine;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe qui implémente les méthodes nécessaires au déroulement du jeu.
 */
public class Partie {

    private int de;
    private Plateau plateau;
    private Joueur joueurCourant;
    private ArrayList<Joueur> listeJoueurs;

    /**
     * Renvoie le plateau
     * @return le plateau
     * @see Plateau
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * Renvoie le joueur qui est en train de jouer.
     * @return le joueur dont c'est au tour de jouer
     */
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * Définit qui va jouer.
     * @param joueurCourant le joueur qui va jouer.
     */
    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    /**
     * Méthode permettant de simuler un lancé de dé à 6 faces.
     * @return la valeur tirée aléatoirement
     */
    private int lancerDe() {
        de = (int) (Math.random() * 6) + 1;
        return de;
    }

    /**
     * Méthode permettant d'initialiser tous les joueurs.
     * @param sc le scanner permettant de gérer les entrées clavier.
     * @see Joueur
     * @see Pion
     */

    public void initialiserJoueurs(Scanner sc) {//peut prendre un nombre joueur en param�tre
        listeJoueurs = new ArrayList<Joueur>();
        ArrayList<String> motsAutorises = new ArrayList<String>();
        motsAutorises.add("BLEU");
        motsAutorises.add("ROUGE");
        motsAutorises.add("JAUNE");
        motsAutorises.add("VERT");
        for (int i = 0; i < 4; i++) {
        	String nomCouleur="";
            System.out.println("Rentrez le nom du joueur n°" + (i + 1) + " :\n");
            String nom = sc.nextLine();
            do {
            	System.out.println("Rentrez la couleur du joueur n°" + (i + 1) + " : \n");
            	nomCouleur = sc.nextLine();
            }while(!motsAutorises.contains(nomCouleur.toUpperCase()));
            motsAutorises.remove(motsAutorises.indexOf(nomCouleur.toUpperCase()));
            Couleur couleur = Couleur.valueOf(nomCouleur.toUpperCase());
            listeJoueurs.add(new JoueurHumain(nom, couleur));

        }

    }

    /**
     * Méthode initialisant le plateau ou va se dérouler la partie.
     * @see Plateau
     */
    public void initialiserPlateau() {
        plateau = new Plateau();
        for (int i = 0; i < 56; i++) {
            plateau.getChemin().add(new CaseDeChemin());
        }
        for (int i = 0; i < 4; i++) {
            plateau.getEcuries().add(new CaseEcurie(getJoueurs().get(i).getCouleur()));
            for (int j = 0; j < 4; j++) {
                plateau.getEcuries().get(i).getChevaux().add(listeJoueurs.get(i).getChevaux().get(j));
            }
            if (i == 0) {
                listeJoueurs.get(i).setCaseDepart(plateau.getChemin().get(44));
                listeJoueurs.get(i).setCaseEntreeEchelle(plateau.getChemin().get(43));
            } else if (i == 1) {
                listeJoueurs.get(i).setCaseDepart(plateau.getChemin().get(2));
                listeJoueurs.get(i).setCaseEntreeEchelle(plateau.getChemin().get(1));
            } else if (i == 2) {
                listeJoueurs.get(i).setCaseDepart(plateau.getChemin().get(16));
                listeJoueurs.get(i).setCaseEntreeEchelle(plateau.getChemin().get(15));
            } else if (i == 3) {
                listeJoueurs.get(i).setCaseDepart(plateau.getChemin().get(30));
                listeJoueurs.get(i).setCaseEntreeEchelle(plateau.getChemin().get(29));
            }
        }
        for (int i = 0; i < 4; i++) {
            plateau.getEchelles().add(new ArrayList<CaseDEchelle>());
            for (int j = 0; j < 6; j++) {
                plateau.getEchelles().get(i).add(new CaseDEchelle(getJoueurs().get(i).getCouleur(), j + 1));
            }
        }
    }

    /**
     *
     * @return la liste des joueurs de la partie.
     */
    public ArrayList<Joueur> getJoueurs() {
        return listeJoueurs;
    }

    /**
     * Méthode permettant de manger les pions sur une cases. Elle gère aussi la remise en écurie des pions mangés.
     * @param caseCible la case où les pions peuvent être mangés.
     */
    public void mangerLesPions(Case caseCible) {
       for (int i = 0; i < caseCible.getChevaux().size(); i++) {
            if (!caseCible.getChevaux().isEmpty() && caseCible.getChevaux().get(i).getCouleur() != getJoueurCourant().getCouleur()) {
                	caseCible.getChevaux().get(i).setEtat(EtatPion.ECURIE);
                	for(int k=0;k<this.getJoueurs().size();k++) {
                		if(caseCible.getChevaux().get(i).getCouleur().equals(this.getJoueurs().get(k).getCouleur())) this.getPlateau().getEcuries().get(k).ajouteCheval(caseCible.getChevaux().get(i));
                	}
                    caseCible.getChevaux().remove(caseCible.getChevaux().get(i));
                    System.out.println("Vous avez mangé le cheval ou les chevaux adverses sur la case n°" + this.getPlateau().getChemin().indexOf(caseCible) +" car vous vous êtes arrêtés sur la même case.");
                }
            }
        }

    /**
     * Méthode qui vérifie les échelles de chaques joueurs pour voir si la partie est terminée.
     * @return retourne vrai si les quatres dernières cases d'échelles d'un joueur sont occupées.
     */
    public boolean partieEstTerminee() {
        boolean partieTerminee=false;
        for (int i = 0; i < 4; i++) {
            int cpt = 0;
            for (int j = 0; j < 4; j++) {
                if (!plateau.getEchelles().get(i).get(2+j).getChevaux().isEmpty()){
                    cpt++;
                }
            }
            if (cpt == 4) {
                partieTerminee=true;
                break;
            }
        }
       return partieTerminee;
    }

    /**
     * Méthode qui vérifie si un joueur est prêt de finir son tour(si il est à 6 cases ou moins de sa caseEntreeEchelle).
     *
     * @param indexCase index de la case où se situe le pion.
     * @param caseActuelle case où se situe le pion
     * @return retourne vrai si le joueur est à 6 cases ou moins de sa caseEntreeEchelle, faux sinon.
     */
    public boolean verificationTourComplet(int indexCase, Case caseActuelle) {//verification pour bloquage
        boolean verif;
        if (getJoueurCourant() == getJoueurs().get(1)) {//cas particulier si c'est le joueur 2
            if (indexCase > 52 || (indexCase >= 0 && indexCase < getPlateau().getChemin().indexOf(getJoueurCourant().getCaseDepart()))) {
                verif = true;
            } else {
                verif = false;
            }
        } else {
            if (indexCase < getPlateau().getChemin().indexOf(getJoueurCourant().getCaseDepart()) && indexCase > getPlateau().getChemin().indexOf(getJoueurCourant().getCaseDepart()) - 6
                    && caseActuelle != getJoueurCourant().getCaseEntreeEchelle()) {
                verif = true;
            } else {
                verif = false;
            }
        }
        return verif;

    }

    /**
     * Méthode principale du jeu. Elle permet de choisir un pion et de verifier les conditions de déplacements
     * selon l'état du pion et sa position.
     * @param sc scanner qui permet de choisir un pion à déplacer.
     */
    public void jouerUnTour(Scanner sc) {
        de = lancerDe();
        int indexCase = 0;
        Case caseActuelle = null;
        boolean passage = false;
        boolean arret = false;
        int compteurTour = 0;
        while((compteurTour<2 && de == 6) || (de<6 && compteurTour==0)) {
        	if(compteurTour>0)de = lancerDe();
	        System.out.println("C'est au joueur "+this.getJoueurCourant().getCouleur().getCodeCouleur()+this.getJoueurCourant().getNom()+" \u001b[0mde jouer ! ");
	        System.out.println("Lancé de dé. Vous avez fait : " + de);
	        Pion pionADeplacer=this.getJoueurCourant().choisirPion(de,plateau,sc);
	        if (pionADeplacer == null) {
	            System.out.println("Vous n'avez aucun cheval à déplacer, vous passez votre tour.");
	        } else {
	            if (pionADeplacer.getEtat() == EtatPion.ECURIE) {
	                getPlateau().sortieEcurie(pionADeplacer, getJoueurCourant());
	            } else if (pionADeplacer.getEtat() == EtatPion.ECHELLE) {//déplacement sur l'echelle
	                for (int i = 0; i < 4; i++) {//on récupère la position du cheval
	                    for (int j = 0; j < 6; j++) {
	                        if (getPlateau().getEchelles().get(i).get(j).getChevaux().contains(pionADeplacer)) {
	                            caseActuelle = getPlateau().getEchelles().get(i).get(j);
	                            indexCase = getPlateau().getEchelles().get(i).indexOf(caseActuelle);
	                        }
	                    }
	                }
	                if (indexCase >= de) {
	                    System.out.println("Lancé de dé trop petit, vous restez où vous êtes.");
	                } else {//on verifie si le cheval peut passer
	                    passage = getPlateau().getEchelles().get(getJoueurs().indexOf(getJoueurCourant())).get(de-1).peutPasser(pionADeplacer);
	                    if (passage == true) {
                            CaseDEchelle caseCibleEchelle = getPlateau().getEchelles().get(getJoueurs().indexOf(getJoueurCourant())).get(de-1);
                            getPlateau().deplacerPionA(pionADeplacer, caseCibleEchelle);
                            caseActuelle.getChevaux().remove(pionADeplacer);
	                    } else if(indexCase==5){
                            System.out.println("Vous ne pouvez pas aller plus votre pion est en haut de l'échelle.");
                        } else {
                            System.out.println("Vous ne pouvez pas passser un de vos chevaux vous barre la route.");
                        }
	                }
	            } else if (pionADeplacer.getEtat() == EtatPion.PISTE) {
	                for (int i = 0; i < 56; i++) {//on récupère la position du cheval
	                    if (getPlateau().getChemin().get(i).getChevaux().contains(pionADeplacer)) {
	                        caseActuelle = getPlateau().getChemin().get(i);
	                    }
	                }
	                indexCase = getPlateau().getChemin().indexOf(caseActuelle);
	                if (caseActuelle == getJoueurCourant().getCaseEntreeEchelle() ) {//entrée sur l'échelle
	                	CaseDEchelle caseCibleEchelle = getPlateau().getEchelles().get(getJoueurs().indexOf(getJoueurCourant())).get(de - 1);
	                	passage=caseCibleEchelle.peutPasser(pionADeplacer);
	                	if (passage == true) {	
	 	                    getPlateau().deplacerPionA(pionADeplacer, caseCibleEchelle);
	 	                    getPlateau().getChemin().get(indexCase).getChevaux().remove(pionADeplacer);
	 	                    pionADeplacer.setEtat(EtatPion.ECHELLE);
	 	                    System.out.println("Vous entrez sur l'échelle !");
	                    } 
	                	else {
	                		System.out.println("La case que vous voulez atteindre est déjà occupée par un de vos pions.");
	                	}
	                } else {
	                    int j = indexCase;
	                    do {
	                        if (getPlateau().getChemin().get(j).peutPasser(pionADeplacer)) {
	                            passage = true;
	                        } else {
	                            passage = false;
	                        }
	                        if (j > 55) {
	                            j = 0;
	                        } else {
	                            j++;
	                        }
	                    } while (passage == true && j < indexCase + de && j < 56);
	                    if (passage == true) {
	                        if (indexCase + de > getPlateau().getChemin().size()) {
	                            arret = getPlateau().getChemin().get(indexCase + de - getPlateau().getChemin().size()).peutSArreter(pionADeplacer);
	                        } else {
	                            arret = getPlateau().getChemin().get(indexCase).peutSArreter(pionADeplacer);
	                        }
	                    }
	                    if (arret == true) {
	                        if (verificationTourComplet(indexCase, caseActuelle)) {//si le cheval est pret de finir son tour
	                            //on le bloque sur la case de fin de tour
	                            if (getJoueurCourant() == getJoueurs().get(1)) {//cas particulier pour joueur 2
	                                if (indexCase + de >= 57 || indexCase+de >getPlateau().getChemin().indexOf(getJoueurCourant().getCaseDepart())) {//ajustement pour pourvoir bloquer
	                                    CaseDeChemin caseCible = getPlateau().getChemin().get(getPlateau().getChemin().indexOf(getJoueurCourant().getCaseEntreeEchelle()));
	                                    getPlateau().getChemin().get(indexCase).getChevaux().remove(pionADeplacer);
	                                    mangerLesPions(getJoueurCourant().getCaseEntreeEchelle());//on mange les chevaux si il y'en a
	                                    getPlateau().deplacerPionA(pionADeplacer, caseCible);
	                                    System.out.println("Vous avez fait un tour, vous êtes prêt à monter l'échelle.");
	                                }
	
	                            } else if (indexCase + de >= getPlateau().getChemin().indexOf(getJoueurCourant().getCaseEntreeEchelle())) {
	                                CaseDeChemin caseCible = getPlateau().getChemin().get(getPlateau().getChemin().indexOf(getJoueurCourant().getCaseEntreeEchelle()));
	                                getPlateau().getChemin().get(indexCase).getChevaux().remove(pionADeplacer);
	                                mangerLesPions(getJoueurCourant().getCaseEntreeEchelle());//on mange les chevaux si il y'en a
	                                getPlateau().deplacerPionA(pionADeplacer, caseCible);
	                                System.out.println("Vous avez fait un tour, vous êtes prêt à monter l'échelle.");
	                            }
	                        } else if (indexCase + de > getPlateau().getChemin().size()-1) {//cas si déplacement sup à 55
	                            getPlateau().deplacerPionA(pionADeplacer, getPlateau().getChemin().get((indexCase + de) - getPlateau().getChemin().size()));
	                            mangerLesPions(getPlateau().getChemin().get(indexCase + de-getPlateau().getChemin().size()));
	                        } else {
	                            //System.out.println("Index case actuelle : " + indexCase);
	                            //System.out.println("case cible : " + indexCase + de);
	                            getPlateau().deplacerPionA(pionADeplacer, getPlateau().getChemin().get(indexCase + de));
	                            mangerLesPions(getPlateau().getChemin().get(indexCase + de));//mange les pions si il y en a
	                        }
	                        getPlateau().getChemin().get(indexCase).getChevaux().remove(pionADeplacer);//suppression position actuelle
	                    } else {
	                        System.out.println("Vous ne pouvez pas passer, un cheval adverse vous barre la route.");
	                    }
	
	                }
	
	            }
	        }
        getPlateau().afficher();
        compteurTour++;
        }
    }

    public void jouerUnTourScenario(Scanner sc,int de) {
        int indexCase = 0;
        Case caseActuelle = null;
        boolean passage = false;
        boolean arret = false;
        int compteurTour = 0;
        while((compteurTour<2 && de == 6) || (de<6 && compteurTour==0)) {

            System.out.println("C'est au joueur "+this.getJoueurCourant().getCouleur().getCodeCouleur()+this.getJoueurCourant().getNom()+" \u001b[0mde jouer ! ");
            System.out.println("Lancé de dé. Vous avez fait : " + de);
            Pion pionADeplacer=this.getJoueurCourant().choisirPion(de,plateau,sc);
            if (pionADeplacer == null) {
                System.out.println("Vous n'avez aucun cheval à déplacer, vous passez votre tour.");
            } else {
                if (pionADeplacer.getEtat() == EtatPion.ECURIE) {
                    getPlateau().sortieEcurie(pionADeplacer, getJoueurCourant());
                } else if (pionADeplacer.getEtat() == EtatPion.ECHELLE) {//déplacement sur l'echelle
                    for (int i = 0; i < 4; i++) {//on récupère la position du cheval
                        for (int j = 0; j < 6; j++) {
                            if (getPlateau().getEchelles().get(i).get(j).getChevaux().contains(pionADeplacer)) {
                                caseActuelle = getPlateau().getEchelles().get(i).get(j);
                                indexCase = getPlateau().getEchelles().get(i).indexOf(caseActuelle);
                            }
                        }
                    }
                    if (indexCase >= de) {
                        System.out.println("Lancé de dé trop petit, vous restez où vous êtes.");
                    } else {//on verifie si le cheval peut passer
                        passage = getPlateau().getEchelles().get(getJoueurs().indexOf(getJoueurCourant())).get(de-1).peutPasser(pionADeplacer);
                        if (passage == true) {
                            CaseDEchelle caseCibleEchelle = getPlateau().getEchelles().get(getJoueurs().indexOf(getJoueurCourant())).get(de-1);
                            getPlateau().deplacerPionA(pionADeplacer, caseCibleEchelle);
                            caseActuelle.getChevaux().remove(pionADeplacer);
                        } else if(indexCase==5){
                            System.out.println("Vous ne pouvez pas aller plus votre pion est en haut de l'échelle.");
                        } else {
                            System.out.println("Vous ne pouvez pas passser un de vos chevaux vous barre la route.");
                        }
                    }
                } else if (pionADeplacer.getEtat() == EtatPion.PISTE) {
                    for (int i = 0; i < 56; i++) {//on récupère la position du cheval
                        if (getPlateau().getChemin().get(i).getChevaux().contains(pionADeplacer)) {
                            caseActuelle = getPlateau().getChemin().get(i);
                        }
                    }
                    indexCase = getPlateau().getChemin().indexOf(caseActuelle);
                    if (caseActuelle == getJoueurCourant().getCaseEntreeEchelle() ) {//entrée sur l'échelle
                        CaseDEchelle caseCibleEchelle = getPlateau().getEchelles().get(getJoueurs().indexOf(getJoueurCourant())).get(de - 1);
                        passage=caseCibleEchelle.peutPasser(pionADeplacer);
                        if (passage == true) {
                            getPlateau().deplacerPionA(pionADeplacer, caseCibleEchelle);
                            getPlateau().getChemin().get(indexCase).getChevaux().remove(pionADeplacer);
                            pionADeplacer.setEtat(EtatPion.ECHELLE);
                            System.out.println("Vous entrez sur l'échelle !");
                        }
                        else {
                            System.out.println("La case que vous voulez atteindre est déjà occupée par un de vos pions.");
                        }
                    } else {
                        int j = indexCase;
                        do {
                            if (getPlateau().getChemin().get(j).peutPasser(pionADeplacer)) {
                                passage = true;
                            } else {
                                passage = false;
                            }
                            if (j > 55) {
                                j = 0;
                            } else {
                                j++;
                            }
                        } while (passage == true && j < indexCase + de && j < 56);
                        if (passage == true) {
                            if (indexCase + de > getPlateau().getChemin().size()) {
                                arret = getPlateau().getChemin().get(indexCase + de - getPlateau().getChemin().size()).peutSArreter(pionADeplacer);
                            } else {
                                arret = getPlateau().getChemin().get(indexCase).peutSArreter(pionADeplacer);
                            }
                        }
                        if (arret == true) {
                            if (verificationTourComplet(indexCase, caseActuelle)) {//si le cheval est pret de finir son tour
                                //on le bloque sur la case de fin de tour
                                if (getJoueurCourant() == getJoueurs().get(1)) {//cas particulier pour joueur 2
                                    if (indexCase + de >= 57 || indexCase+de >getPlateau().getChemin().indexOf(getJoueurCourant().getCaseDepart())) {//ajustement pour pourvoir bloquer
                                        CaseDeChemin caseCible = getPlateau().getChemin().get(getPlateau().getChemin().indexOf(getJoueurCourant().getCaseEntreeEchelle()));
                                        getPlateau().getChemin().get(indexCase).getChevaux().remove(pionADeplacer);
                                        mangerLesPions(getJoueurCourant().getCaseEntreeEchelle());//on mange les chevaux si il y'en a
                                        getPlateau().deplacerPionA(pionADeplacer, caseCible);
                                        System.out.println("Vous avez fait un tour, vous êtes prêt à monter l'échelle.");
                                    }

                                } else if (indexCase + de >= getPlateau().getChemin().indexOf(getJoueurCourant().getCaseEntreeEchelle())) {
                                    CaseDeChemin caseCible = getPlateau().getChemin().get(getPlateau().getChemin().indexOf(getJoueurCourant().getCaseEntreeEchelle()));
                                    getPlateau().getChemin().get(indexCase).getChevaux().remove(pionADeplacer);
                                    mangerLesPions(getJoueurCourant().getCaseEntreeEchelle());//on mange les chevaux si il y'en a
                                    getPlateau().deplacerPionA(pionADeplacer, caseCible);
                                    System.out.println("Vous avez fait un tour, vous êtes prêt à monter l'échelle.");
                                }
                            } else if (indexCase + de > getPlateau().getChemin().size()-1) {//cas si déplacement sup à 55
                                getPlateau().deplacerPionA(pionADeplacer, getPlateau().getChemin().get((indexCase + de) - getPlateau().getChemin().size()));
                                mangerLesPions(getPlateau().getChemin().get(indexCase + de-getPlateau().getChemin().size()));
                            } else {
                                //System.out.println("Index case actuelle : " + indexCase);
                                //System.out.println("case cible : " + indexCase + de);
                                getPlateau().deplacerPionA(pionADeplacer, getPlateau().getChemin().get(indexCase + de));
                                mangerLesPions(getPlateau().getChemin().get(indexCase + de));//mange les pions si il y en a
                            }
                            getPlateau().getChemin().get(indexCase).getChevaux().remove(pionADeplacer);//suppression position actuelle
                        } else {
                            System.out.println("Vous ne pouvez pas passer, un cheval adverse vous barre la route.");
                        }

                    }

                }
            }
            getPlateau().afficher();
            compteurTour++;
        }
    }
}