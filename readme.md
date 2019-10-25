# __Jeu du Puissance 4__

Mon code est divisé en cinq classes:
* Game
* Board
* Player
* Computer
* Position

Et une énumération:
* Symbol

#### La classe _Game_ gère le déroulement de la partie et les intéractions utilisateurs dans la console:

**_start()_** demande le nom de chaque joueur et le type de jeu (seul ou à deux) elle lance les méthodes initialize et playManche.

**_initialize()_** instancie les joueurs grâce aux noms passé en paramètre et tire aux hasard les symboles et le joueur qui commencera en utilisant la methode randomize.

**_playManche()_** annonce qui commencera, si la partie est gagné et par qui ou si il y a match nul, elle appelle la méthode jouerTourSolo tant que le jeu n'est pas fini. 

**_jouerTourSolo()_**  prend en paramètre un joueur et demande quelle colonne il veut jouer et vérifi sa disponibilié ou fait jouer l'ordinateur.

**_isFinished()_** renvoi True si l'un des joueurs a gagné ou si il y a match nul ou False sinon.

**_randomize()__** renvoi 0 ou 1 pour choisir les couleurs ou la position des joueurs dans le tableu Joueur[].

#### La classe _Board_ contient la grille de jeu et les méthodes qui sont liées à cette dernière:

**_initializeGrid()_** rempli la grille de jeu de Symbol.VIDE qui est un point. 

**_isColumnFull()_** renvoi True si la colonne passé en paramètre est pleine ou False sinon. 

**_getEmptyPositionForColumn()_** renvoi une Position disponible pour la colonne passé en paramètre.

**_setSymbolAtPosition()_** attribut le symbol passé en paramètre à la position de la grille passé en paramètre. 

#### La classe _Player_ contient le nom du joueur, son nombre de jetons restant, les positions des jetons qu'il a joués et le symbole qu'il lui a été affecté.


#### La classe _Computer_ hérite des attributs et méthodes de _Player_ et contient des méthodes de gestion de l'IA.

#### La classe _Position_ représente les positions dans la grille de jeu avec ses attributs x et y.

#### L'énumération _Symbol_ définie les symboles par couleur de chaque jeton ou un symbole vide pour les cases vide.