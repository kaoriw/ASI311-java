# TP 2: Les bases de Java
### BattleShips CLI
##### Problématique

Le but du projet est de réaliser un jeu de Bataille Navale en ligne de commande (CLI).
Le programme devra répondre aux spécifications suivantes : 
 - Dessiner une grille à taille variable pour les frappes.
 - Dessiner une grille de même taille pour les navires.
 - Placer des navires sur la grille.

Le jeu se déroulera en 2 phases : 
 - La phase de placement. L'utilisateur doit entrer les positions de ses 5 navires.
 - La phase de jeu. L'utilisateur entre les positions de ses frappes.

On compte les types de navires suivants : 
 - 1x Destroyer (D) - taille 2
 - 2x Sub-marine (S) - taille 3
 - 1x BattleShip (B) - taille 4
 - 1x Aircraft-cairrier ( C) - taille 5

### Exercice 1 : Affichage du "Board"
##### Notions abordées:
 - Conditions, manipulation console, exceptions...

Nous allons créer une classe "Board", qui représente les 2 grilles sur lesquelles sont placés les navires et les frappes.

 - Créer la classe Board, composée d'un nom, d'un tableau 2D de "Character" pour les navires, et d'un tableau 2D de "Boolean" pour les frappes.
 - Créer un constructeur avec arguments, qui prends en entrée un nom, et la taille de la grille.
 - Créer un constructeur avec argument, qui prends en entrée un nom seulement et initialise la grille avec une taille de 10.
 - Créer une méthode print dans Board, qui efface la console et dessine les 2 grilles côte à côte.
 - Créer une classe TestBoard et sa méthode main(), afin de tester l'affichage des grilles. 

NB : il est possible de vider la console avec la commande suivante: 
```java
try {Runtime.getRuntime().exec("clear");} catch (IOException e) {}
```

Dans un premier temps, nous ne nous occupons pas des navires. Le "Board" reste donc vide.

Visuellement, le board affiché peut ressembler à ceci :
```sh
Navires : 		 Frappes : 
  A B C D E F G H I J       A B C D E F G H I J
1 . . . . . . . . . .     1 . . . . . . . . . .
2 . . . . . . . . C .     2 . . . . . . . . . .
3 . . S . . . . . C .     3 . . . . . . . . . .
4 . . S . . . . . C .     4 . . . . . . . . . .
5 . . S . D D . . C .     5 . . . . . . . . . .
6 . . . . . . . . C .     6 . . . . . . . . . .
7 . . . . . . . . . .     7 . . . . . . . . . .
8 . . S S S . . . . .     8 . . . . . . . . . .
9 . . . . . . . . . .     9 . . . . . . . . . .
10. . . . . B B B B .     10. . . . . . . . . .
```
Question : 
 - que se passe-t-il si la taille de grille est trop grande ? ( > taille de la fenêtre) ? Proposer une solution pour y remédier.

Lorsque l'étape est terminée, entrer les commandes suivantes pour valider l'étape.
```sh
git add . -A
git commit -m"step 1"
```

### Exercice 2 : Classes ***Ship.
##### Notions abordées:
 - Classe, héritage, surcharge 

Nous allons nous attaquer à la création des navires. Par soucis de pédagogie, nous choisirons de créer une classe par type de navire, chacune d'elles étant une classe fille de "AbstractShip"
La class AbstractShip possède les éléments suivants : 
 - Un constructeur avec arguments, qui prend en entrée un nom, un label (le Character qui le représente), une taille et une orientation.
 - les méthodes getLabel() et getLength(), getOrientation() et setOrientation(/*...*/).

Vous devez : 
 - Créer la classe AbstractShip
 - Créer les classes Destroyer, Submarine, Battleship, Carrier, qui héritent de AbstractShip.
 - Les classes filles devront posséder un constructeur par défaut (qui placent l'orientation à "null"), et un constructeur avec argument qui prends en paramètre l'Orientation).

Questions : 
 - En quoi l'héritage est-il utile dans notre cas ?
 - Il commence à y avoir beaucoup de fichiers source pêle-mêle dans notre projet. Comment remédier à cela?
 - L'orientation est un ensemble fini de 4 valeurs, pouvant être "NORTH", "SOUTH", "EAST", "WEST". Quelle est la meilleure manière de représenter cette information ?
 - Nous allons avoir besoin de placer un navire sur le board. Est-il judicieux de créer une méthode "ship.setPosition(int x, int y)" ?

	```sh
	git add . -A
	git commit -m"step 2"
	```

### Exercice 3 : Placement des navires.
##### Notions abordées:
 - interfaces, exceptions

Il est maintenant temps de placer les navires sur la grille. Il semble nécessaire que la classe Board soit dotée de méthodes pour placer les navires et les frappes.
Nous allons modifier la classe "Board" pour lui faire implémenter l'interface "IBoard".

Rappel: Les interfaces sont des classes 100% abstraites. Elles n'ont que la définition des méthodes, sans leurs implémentations. Elles sont utilisées pour forcer les classes filles à implémenter un comportement générique.
Il existe deux conventions de nommage pour les interfaces : Xxx-able (Drawable, Clickable, Obserable, ...) et I-xxxx (IEngine, IListener, ...). On s'efforce en général d'utiliser l'une des deux conventions.

 - modifier "Board" pour lui faire implémenter l'interface "IBoard".
 - écrire les méthodes "putShip", "hasShip", "putHit" et "hasHit" qui placent les navires et les frappes dans leurs tableaux respectifs.
 - écrire la méthode "canPutShip", qui retourne vrai si le navire donné en paramètre peut être placé aux coordonnées données en paramètre.
 - modifier la méthode main() de TestBoard, afin de placer quelques navires. 
 - modifier la méthode print() de Board, afin d'afficher le label des navires à leurs emplacements respectifs.

Questions : 
 - Les indices x et y commencent-ils à 1 ou 0 ?
 - Que ce passe-t-il si la valeur "position + longueur" d'un navire mène hors de la grille ? Comment gérer ce cas ?
 - Que ce passe-t-il si deux navires se chevauchent? Comment gérer ce cas ?

	```sh
	git add . -A
	git commit -m"step 3"
	```
### Exercice 4 : Entrées utilisateurs.
##### Notions abordées:
 - Scanners, gestion d'exceptions.

Nous souhaitons que notre application propose au joueur de placer chacun des 5 navires, par ordre croissant de longueur.
Pour cela, l'utilisateur devra entrer à la suite les positions des navires, au format "A1 n", "B4 s".
A chaque fois que l'on récupère des données de l'utilisateur (saisie formulaire, etc), il est TRES IMPORTANT de vérifier la cohérence et l'exactitude de ces données. Il faudra donc utiliser un bloc "try... catch()" pour s'assurer que les valeurs entrées sont correctes.

Afin de gagner du temps, vous pouvez utiliser la classe "ShipInputHelper" qui fournit, dont la méthode readShipInput récupère les entrées de l'utilisateur et les convertie en données exploitables. Notez bien la présence du bloc "try... catch" pour sécuriser les entrées utilisateur.

Il ne reste plus qu'a :
 - Créer un tableau des 5 navires :
	```java
	AbstractShip[] ships = {
		new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier()
	};
	```
 - Appeler la méthode readShipInput tant que tous les navires ne sont pas correctement placés
 - Paramétrer le Board avec les valeurs retournées
 - Afficher l'état actuel du Board entre chaque saisie

Question : 
 - Quelle classe Java permet de lire les entrées clavier ?

```sh
git add . -A
git commit -m"step 4"
```
### Exercice 5 : Etat des navires et des frappes.
##### Notions abordées:
 - "refactors", exceptions

La conception actuelle du jeu nous pose maintenant 2 problèmes : 
 - Les "Hits" peuvent en réalité avoir 3 états : "Inconnu", "touché", et "manqué". un **boolean** ne suffis plus. On peut en revanche se servir d'un **Boolean** qui peut être soit vrai, faux ou null. (Un **enum** serait aussi un choix judicieux)
 - Les navires sont placés avec un tableau de **"Character"**. Comment savoir où le navire commence et se termine, et donc s'il est totalement détruit ?

Il est courant de devoir modifier la conception d'un logiciel en cours de développement, et les raisons peuvent être multiples : Changement soudain du cahier des charges, erreur de conception, difficulté technique imprévue... Heureusement, ceci n'est pas toujours dramatique et il est parfois suffisant de quelques petites modifications pour remettre les choses dans l'ordre. C'est ce qu'on appelles dans le jargon un "refactor". Nos IDE sont générlement capables de nous assister dans cette tâche.

Nous aurons besoin d'une classe **"ShipState"** intermédiaire entre le navire et la grille, capable de mémoriser l'état du navire en un point précis. La classe **"ShipState"** possède : 
 - un attribut de type **AbstractShip**, référence vers le navire concerné par cet état.
 - un attribut boolean **"struck"**, qui vaut "vrai" si le navire est touché en cet endroit.
 - une méthode **"void addStrike()"**, pour marqué le navire comme "touché" 
 - une méthode **"boolean isStruck()"**, qui retourne la valeur de l'attribut "struck"
 - une méthode **"String print()"**, qui retourne le label du navire associé (en rouge si le navire est touché en cet endroid)
 - un méthode **"boolean isSunk()"**, qui retourne "vrai" si le navire est totalement détruit.
 - une méthode **"AbstratShip getShip()"** qui retourne ne navire concerné par cet état.

Travail demandé : 
 - Dans **"AbstractShip"**, créer un attribut entier 'strikeCount' ainsi qu'une méthode "addStrike()", permettant de manipuler le nombre de frappes que le navire à reçu au total. Créer la méthode isSunk();
 - Créer la classe **"ShipState"**. 
 - Dans **"Board"**, changer le tableau de **boolean "hits"** en un tableau de **Boolean**
 - Dans **"Board"**, changer le tableau de **Character "ships"** en un tableau de **"ShipState"**
 - Dans **"AbstractShip"**, changer la méthode **"getLabel()"** "**print()**", qui retourne le label en rouge si le navire est touché à cet endroit.
 - Dans **"Board"**, changer la méthode **print()** pour afficher '.' si un Hit est null, 'X' en blanc si un hit est faux, et 'X' en rouge si un hit est vrai.

NB : Ceci pourrait être utile :
```java
public class ColorUtil {

    private ColorUtil() { // do we want to create one of '***Util' instance?
    }
    
    enum Color {
        COLOR_RESET("\u001B[0m"),
        COLOR_BLACK("\u001B[30m"),
        COLOR_RED("\u001B[31m"),
        COLOR_GREEN("\u001B[32m"),
        COLOR_YELLOW("\u001B[33m"),
        COLOR_BLUE("\u001B[34m"),
        COLOR_PURPLE("\u001B[35m"),
        COLOR_CYAN("\u001B[36m"),
        COLOR_WHITE("\u001B[37m");

        private final String value;

        Color(String value) {
            this.value = value;
        }
    }

    public static String colorize(String text, Color color) {
        return String.format("%s%s%s", color.value, text, Color.COLOR_RESET.value);
    }
    public static String colorize(Character text, Color color) {
        return colorize("" + text, color);
    }
}
```

Question : 
 - Si on appelles **addStrike()** plus d'une fois par ShipState, le navire pourra donc être touché plus que le permet sa longueur. Comment gérer cet ***"état illégal"*** ?
```sh
git add . -A
git commit -m"step 5"
```
### Exercice 6 : Envoyer des frappes
##### Notions abordées:
 - enums, interfaces

Avant d'aller plus loin, il va falloir doter notre **"Board"** d'une méthode lui permettant de recevoir les frappes de l'adversaire, et qui permettra à l'adversaire de recevoir les nôtres : 

```java
enum Hit {
	MISS(-1), STRIKE(-2),  DESTROYER(2), SUBMARINE(3), BATTLESHIP(4), CARRIER(5)
	;
	private int value;
	Hit(int value) {
		this.value = value;
	}

	public static Hit fromInt(int value) {
		for (Hit hit : Hit.values()) {
			if (hit.value == value) {
				return hit;
			}
		}
		throw new NoSuchElementException("no enum for value " + value);
	}
};
Hit sendHit(int x, int y);
```
L'adversaire appelera donc la méthode **sendHit()** sur notre **Board**, tandis que nous appelerons **sendHit()** sur le siens. 
L'enum **"Hit"** permet de renvoyer le status d'une frappe lancée. Les valeurs peuvent être **"MISS"** ou **"STRUCK"**, pour une frappe manquée ou réussie, ou bien le nom d'un des 4 navires lorsqu'un navire viens d'être coulé totalement. 

**NB** : L'enum **"Hit"** est particulier : Il possède un constructeur, ce qui nous permet de lui faire porter une valeur. Cela sera pratique lorsque nous voudrons créer l'enum directement à partir de la longueur du navire, grace à la méthode **fromInt()**

Travail demandé :
 - Copier coller le code si dessus dans **IBoard**, puis l'implémenter dans **Board**.
 - implémenter la méthode **"sendHit()"** en prenant soin de retourner la bonne valeur si un navire est détruit.
 - modifier la classe **"TestBoard"** pour envoyer des frappes sur l'unique destroyer de votre board. Vérifier que le destroyer s'affiche en rouge. 
 - Vérifier que **destroyer.isSunk()** retourne "vrai", et que le dernier appel à **"sendHit()"** retourne **Hit.DESTROYER**. Afficher "coulé" le cas échéant.

Question : 
 - Que se passe t-il on appelles **sendHit()** deux fois sur la même position d'un navire ?

```sh
git add . -A
git commit -m"step 6"
```

### Exercice 7 : phase de jeu.
##### Notions abordées:
 - Scanners, interface fonctionnelles

A ce point, nous devrions avoir un programme qui permette de placer les 5 navires, en gérant les erreurs. Lorsque tous les navires sont placés, le programme devrait se quitter normalement. Il est maintenant temps de créer la phase de jeu. Pour ceci, nous allons créer une nouvelle classe "Game", qui possèdera sa propre fonction "main()".

La classe "Game" possède : 
 - 2 attributs privés de type "Board" (un par joueur).
 - Un attribut "username"de type "String", saisi par l'utilisateur (utiliser un Scanner). 


La classe "BattleShipsAI" vous est fournie. Elle propose une Intelligence Artificielle rudimentaire. 

Comme votre "Board" implémente l'interface "IBoard", 

Après avoir initialisé le Board de votre joueur, 
avons besoin d'initialiser 2 Board différents (un par joueur). 
// TODO IA. 



