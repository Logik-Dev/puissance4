# <center>Puissance 4</center>

Mon code est divisé en cinq classes:
* Game
* Board
* Player
* Computer
* Position

Et une énumération:
* Symbol

La classe **Game** gère le déroulement de la partie et les intéractions utilisateurs dans la console.

La classe **Board** contient la grille de jeu et les méthodes qui sont liées à cette dernière.

La classe **Player** contient le nom du joueur, son nombre de jetons restant, la position des jetons qu'il a joué (dans un tableau) et le symbole qu'il lui a été affecté.
Elle contient aussi la méthode _hasWin_ permettant de savoir si le joueur a gagné.

La classe **Computer** hérite des attributs et méthodes de _Player_ et contient des méthodes de gestion de l'IA.

La classe **Position** représente les positions dans la grille de jeu avec ses attributs x et y.

L'énumération **Symbol** définie les symboles par couleur de chaque jeton ou un symbole vide pour les cases vide.