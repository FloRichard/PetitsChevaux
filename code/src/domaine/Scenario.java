package domaine;

import java.util.Scanner;

public class Scenario {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Partie p = new Partie();
        p.initialiserJoueurs(sc);
        p.initialiserPlateau();
        p.getPlateau().afficher();
        //Premier cas : sortie écurie
        System.out.println("Premier cas : sortie écurie.");
        sc.nextLine();
        p.setJoueurCourant(p.getJoueurs().get(0));
        p.jouerUnTourScenario(sc,6);
        //deuxième cas : bloquage

        System.out.println("Deuxième cas : le bloquage");
        p.getPlateau().getChemin().get(52).ajouteCheval(p.getJoueurs().get(1).getChevaux().get(0));
        p.getJoueurs().get(1).getChevaux().get(0).setEtat(EtatPion.PISTE);
        p.getPlateau().getEcuries().get(1).getChevaux().remove(p.getJoueurs().get(1).getChevaux().get(0));
        p.getPlateau().afficher();
        p.jouerUnTourScenario(sc,4);

        //Troisième cas : manger
        System.out.println("Troisième cas : Manger les pions");
        p.jouerUnTourScenario(sc,2);

        //Quatrième cas : bloquage tour complet

        System.out.println("Quatrième cas : tour complet");
        p.getPlateau().getChemin().get(52).getChevaux().remove(p.getJoueurCourant().getChevaux().get(0));
        p.getPlateau().getChemin().get(40).ajouteCheval(p.getJoueurCourant().getChevaux().get(0));
        p.getPlateau().afficher();
        p.jouerUnTourScenario(sc,5);

        //5ème cas

        System.out.println("Cinquième : monter sur l'échelle et partie terminée");
        p.getJoueurCourant().getCaseEntreeEchelle().ajouteCheval(p.getJoueurCourant().getChevaux().get(1));
        p.getJoueurCourant().getCaseEntreeEchelle().ajouteCheval(p.getJoueurCourant().getChevaux().get(2));
        p.getJoueurCourant().getCaseEntreeEchelle().ajouteCheval(p.getJoueurCourant().getChevaux().get(3));
        p.getPlateau().afficher();
        p.jouerUnTourScenario(sc,6);
        p.jouerUnTourScenario(sc,5);
        p.jouerUnTourScenario(sc,4);
        p.jouerUnTourScenario(sc,3);
        if(p.partieEstTerminee()){
            System.out.println("Partie terminée. Le joueur "+p.getJoueurCourant().getCouleur().getCodeCouleur()+p.getJoueurCourant().getNom()+" \u001b[0ma gagné !");
        }
    }
}
