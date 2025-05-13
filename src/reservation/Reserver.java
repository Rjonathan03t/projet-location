package reservation;

import location.Assiette;
import location.Maison;
import location.OnPeutLouer;
import location.Voiture;

import java.util.ArrayList;
import java.util.Scanner;

public class Reserver {

    private final ArrayList<OnPeutLouer> listALouer = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public Reserver() {
        listALouer.add(new Assiette("Assiette"));
        listALouer.add(new Voiture("Voiture"));
        listALouer.add(new Maison("Maison"));
    }

    public void demarrer() {
        while (true) {
            System.out.println("\n==== MENU LOCATION ====");
            System.out.println("1. Voir les objets disponibles");
            System.out.println("2. Réserver un objet");
            System.out.println("3. Passer un jour");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1 -> afficherObjets();
                case 2 -> reserverObjet();
                case 3 -> passerUnJour();
                case 4 -> {
                    System.out.println("Merci et à bientôt !");
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    private void afficherObjets() {
        System.out.println("\nObjets à louer :");
        for (int i = 0; i < listALouer.size(); i++) {
            OnPeutLouer item = listALouer.get(i);
            String etat = item.etat ? "Disponible" : "Réservé (" + item.duree + " j restant)";
            System.out.println(i + ": " + item.nom + " - " + etat);
        }
    }
    private void reserverObjet() {
        afficherObjets();
        System.out.print("Quel objet voulez-vous louer ? (entrez le numéro de l'item) : ");
        int numero = scanner.nextInt();

        if (numero < 0 || numero >= listALouer.size()) {
            System.out.println("Erreur : numero invalide.");
            return;
        }

        OnPeutLouer objet = listALouer.get(numero);

        if (!objet.etat && objet.duree > 0) {
            System.out.println();
            System.out.println("Desolé cet article est déjà réservé.");
            return;
        }

        System.out.print("Pour combien de jours ? : ");
        int duree = scanner.nextInt();

        if (duree < 1) {
            System.out.println("La durée minimale est de 1 jour.");
            return;
        }

        objet.duree = duree;
        objet.etat = false;
        System.out.println("Vous avez réservé '" + objet.nom + "' pour " + duree + " jour(s).");
    }

    private void passerUnJour() {
        System.out.println("\nUn jour s'est écoulé...");
        for (OnPeutLouer item : listALouer) {
            if (!item.etat && item.duree > 0) {
                item.duree--;
                if (item.duree == 0) {
                    item.etat = true;
                    System.out.println(item.nom + " est maintenant disponible !");
                }
            }
        }
    }
}