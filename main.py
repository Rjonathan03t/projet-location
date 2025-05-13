from location.assiette import Assiette
from location.voiture import Voiture
from location.maison import Maison

def afficher_menu():
    print("\n==== MENU LOCATION ====")
    print("1. Voir les objets disponibles")
    print("2. Réserver un objet")
    print("3. Passer un jour")
    print("4. Quitter")

def afficher_objets(objets):
    for i, obj in enumerate(objets):
        statut = "Disponible" if obj.etat else f"Réservé ({obj.duree} j restant)"
        print(f"{i} : {obj.nom} - {statut}")

def main():
    objets = [
        Assiette("Assiette"),
        Voiture("Voiture"),
        Maison("Maison")
    ]

    running = True
    while running:
        afficher_menu()
        choix = input("Votre choix : ")

        if choix == "1":
            afficher_objets(objets)

        elif choix == "2":
            afficher_objets(objets)
            try:
                index = int(input("Quel objet voulez-vous louer ? (index) : "))
                if index < 0 or index >= len(objets):
                    print("Index invalide.")
                    continue

                obj = objets[index]
                if not obj.etat and obj.duree > 0:
                    print("Cet objet est déjà réservé.")
                    continue

                jours = int(input("Pour combien de jours ? : "))
                if jours < 1:
                    print("Durée minimale = 1 jour.")
                    continue

                obj.duree = jours
                obj.etat = False
                print(f"Réservé : {obj.nom} pour {jours} jour(s).")

            except ValueError:
                print("Entrée invalide.")

        elif choix == "3":
            print("Un jour passe...")
            for obj in objets:
                if not obj.etat and obj.duree > 0:
                    obj.duree -= 1
                    if obj.duree == 0:
                        obj.etat = True
                        print(f"{obj.nom} est maintenant disponible.")

        elif choix == "4":
            print("Merci, à bientôt !")
            running = False

        else:
            print("Choix invalide.")

if __name__ == "__main__":
    main()
