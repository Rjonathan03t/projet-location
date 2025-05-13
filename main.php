<?php
require_once 'location/Assiette.php';
require_once 'location/Voiture.php';
require_once 'location/Maison.php';

function afficherMenu() {
    echo "\n==== MENU LOCATION ====\n";
    echo "1. Voir les objets disponibles\n";
    echo "2. Réserver un objet\n";
    echo "3. Passer un jour\n";
    echo "4. Quitter\n";
    echo "Votre choix : ";
}

function afficherObjets($objets) {
    foreach ($objets as $index => $obj) {
        $statut = $obj->etat ? "Disponible" : "Réservé ({$obj->duree} j restant)";
        echo "$index : {$obj->nom} - $statut\n";
    }
}

function lireEntree($message) {
    echo $message;
    return trim(fgets(STDIN));
}

// Initialisation des objets
$objets = [
    new Assiette("Assiette"),
    new Voiture("Voiture"),
    new Maison("Maison")
];

$enCours = true;

while ($enCours) {
    afficherMenu();
    $choix = lireEntree("");

    switch ($choix) {
        case "1":
            afficherObjets($objets);
            break;

        case "2":
            afficherObjets($objets);
            $index = (int) lireEntree("Quel objet voulez-vous réserver ? (index) : ");

            if (!isset($objets[$index])) {
                echo "Index invalide.\n";
                break;
            }

            $objet = $objets[$index];
            if (!$objet->etat && $objet->duree > 0) {
                echo "Cet objet est déjà réservé.\n";
                break;
            }

            $jours = (int) lireEntree("Pour combien de jours ? : ");
            if ($jours < 1) {
                echo "Durée minimale = 1 jour.\n";
                break;
            }

            $objet->duree = $jours;
            $objet->etat = false;
            echo "Réservé : {$objet->nom} pour {$jours} jour(s).\n";
            break;

        case "3":
            echo "Un jour passe...\n";
            foreach ($objets as $obj) {
                if (!$obj->etat && $obj->duree > 0) {
                    $obj->duree--;
                    if ($obj->duree === 0) {
                        $obj->etat = true;
                        echo "{$obj->nom} est maintenant disponible.\n";
                    }
                }
            }
            break;

        case "4":
            echo "Merci, à bientôt !\n";
            $enCours = false;
            break;

        default:
            echo "Choix invalide.\n";
    }
}
