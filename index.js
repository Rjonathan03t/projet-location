const prompt = require('prompt-sync')({sigint: true});
const Assiette = require('./location/Assiette');
const Voiture = require('./location/Voiture');
const Maison = require('./location/Maison');

const objets = [
    new Assiette("Assiette"),
    new Voiture("Voiture"),
    new Maison("Maison")
];

function afficherMenu() {
    console.log("\n==== MENU LOCATION ====");
    console.log("1. Voir les objets disponibles");
    console.log("2. Réserver un objet");
    console.log("3. Passer un jour");
    console.log("4. Quitter");
}

function afficherObjets() {
    objets.forEach((obj, i) => {
        const statut = obj.etat ? "Disponible" : `Réservé (${obj.duree} j restant)`;
        console.log(`${i} : ${obj.nom} - ${statut}`);
    });
}

let running = true;
while (running) {
    afficherMenu();
    const choix = prompt("Votre choix : ");

    switch (choix) {
        case "1":
            afficherObjets();
            break;

        case "2":
            afficherObjets();
            const index = parseInt(prompt("Quel objet voulez-vous louer ? (index) : "));
            if (isNaN(index) || index < 0 || index >= objets.length) {
                console.log("Index invalide.");
            } else {
                const obj = objets[index];
                if (!obj.etat && obj.duree > 0) {
                    console.log("Cet objet est déjà réservé.");
                } else {
                    const jours = parseInt(prompt("Pour combien de jours ? : "));
                    if (jours < 1) {
                        console.log("Durée minimale = 1 jour.");
                    } else {
                        obj.etat = false;
                        obj.duree = jours;
                        console.log(`Réservé : ${obj.nom} pour ${jours} jour(s).`);
                    }
                }
            }
            break;

        case "3":
            console.log("Un jour passe...");
            objets.forEach(obj => {
                if (!obj.etat && obj.duree > 0) {
                    obj.duree--;
                    if (obj.duree === 0) {
                        obj.etat = true;
                        console.log(`${obj.nom} est maintenant disponible.`);
                    }
                }
            });
            break;

        case "4":
            console.log("Merci, à bientôt !");
            running = false;
            break;

        default:
            console.log("Choix invalide.");
    }
}
