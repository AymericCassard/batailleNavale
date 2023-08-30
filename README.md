# batailleNavale
Jeu de la bataille navale classique, créé dans le but de s'améliorer dans la conception de la structure d'une appli en partant de 0.
Il est seulement possible de jouer contre un ordinateur.

L'appli est codée en Java, en utilisant la librairie Swing pour l'interface graphique.

Ce fut un bon entraînement pour pratiquer certains patterns de design, comme les observateurs, et aussi pratiquer l'abstraction à l'aide d'interfaces. J'aimerais un jour implémenter un mode à deux joueurs, mais j'imagine que cela me prendrait beaucoup de temps supplémentaire.

## Lancer l'appli

### A partir du code source
Le projet est une appli java ant classique, avec JDK 17.

J'ai utilisé Netbeans, ce qui crée un dossier nbproject à la racine, mais si vous utilisez un autre IDE il devrait être possible de supprimer ce dossier.

Pour lancer l'appli dans votre IDE il suffit de cloner le projet, puis de lancer la classe modele.batailleNavale.main.

### A partir du jar
Il faut avoir le runtime java installé sur son pc, si java est installé en variable d'environnement, vous pouvez lancez l'appli en vous déplaçant dans le dossier ou vous avez téléchargé le .jar, puis en faisant :
`java -jar "batailleNavaleV2.jar"`

## Comment jouer à la Bataille navale?
Les règles et le fonctionnement de l'interface sont expliqués dans la section règles de l'application.

![Grille Alliée](/readmeImg/regleHighlight.png)


## A faire

- [X] Structure et méchanisme de la bataille
- [X] Interface console pour intéragir avec la bataille
- [X] Interface graphique pour intéragir avec la bataille
- [ ] Améliorer le fonctionnement de l'ordinateur opposant (il tire aléatoirement actuellement)
- [ ] Faire un mode à 2 joueurs ? (selon le niveau de complexité)
