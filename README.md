# batailleNavale
Jeu de la bataille navale classique, créé dans le but de s'améliorer dans la conception de la structure d'une appli en partant de 0.
Il est seulement possible de jouer contre un ordinateur.

L'appli est en Java, et tourne pour l'instant seulement dans la console.

Ce fut un bon entraînement pour pratiquer certains patterns de design, comme les observateurs, et aussi pratiquer l'abstraction à l'aide d'interfaces.

## Lancer l'appli

### A partir du code source
Le projet est une appli java ant classique, avec JDK 17.

J'ai utilisé Netbeans, ce qui crée un dossier nbproject à la racine, mais si vous utilisez un autre IDE il devrait être possible de supprimer ce dossier.

Pour lancer l'appli dans votre IDE il suffit de cloner le projet, puis de lancer la classe modele.batailleNavale.main.

### A partir du jar
Il faut avoir le runtime java installé sur son pc, si java est installé en variable d'environnement, vous pouvez lancez l'appli en vous déplaçant dans le dossier ou vous avez téléchargé le .jar, puis en faisant :
`java -jar "batailleNavaleV2.jar"`

## Règles de la bataille navale
### Pour jouer à la bataille navale, il faut par joueur :
- 1 Porte avion (5 cases)
- 1 Croiseur (4 cases)
- 2 Sous-marins (3 cases)
- 1 Torpilleur (2 cases)

### Commencer une partie de bataille navale :

Au début du jeu, chaque joueur place à sa guise tous les bateaux sur sa grille de façon stratégique. Le but étant de compliquer au maximum la tache de son adversaire, c’est-à-dire détruire tous vos navires. Bien entendu, le joueur ne voit pas la grille de son adversaire.
Une fois tous les bateaux en jeu, la partie peut commencer.. 

### Déroulement de la partie
Un à un, le joueur et la console se tirent dessus pour détruire les navires ennemis.

Exemple le joueur entre H7 correspondant à la case au croisement de la lettre H et du numéro 7 sur les côtés des grilles.

Si le joueur tire sur un navire ennemi, la console renvoie « Touché! ». Il ne peut pas jouer deux fois de suite et doit attendre le tour de l’ordinateur.
Si le joueur ne touche pas de navire, la console le signale avec « Manqué! » .
Si le navire est entièrement touché la console le signale avec « Touché coulé! ».

### Signification de la grille

![Grille Alliée](/readmeImg/grilleAlliee.png)

O = eau non touchée / @ = case de Bateau / # = case de bateau touchée / X = eau touchée

![Grille Ennemie](/readmeImg/grilleEnnemie.png)

O = eau non touchée / # = case de bateau touchée / X = eau touchée

## A faire

- [X] Structure et méchanisme de la bataille
- [X] Interface console pour intéragir avec la bataille
- [ ] Interface graphique pour intéragir avec la bataille
- [ ] Améliorer le fonctionnement de l'ordinateur opposant (il tire aléatoirement actuellement)
- [ ] Faire un mode à 2 joueurs ? (selon le niveau de complexité)
