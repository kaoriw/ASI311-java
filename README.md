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
 - 1x Aircraft-cairrier (C) - taille 5

### Exercice 1 : Affichage du "Board"
##### Notions abordées:
 - Conditions, manipulation console, exceptions...

Nous allons créer une classe "Board", qui représente les 2 grilles sur lesquelles sont placés les navires et les frappes.

 - Créer la classe Board, composée d'un nom, d'un tableau 2D de "Character" pour les navires, et d'un tableau 2D de "boolean" pour les frappes.
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

Question : 
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

 - écrire les méthodes "putShip", "hasShip", "putHit" et "hasHit" qui placent les navires et les frappes dans leurs tableaux respectifs.
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

Afin de gagner du temps, voici l'implémentation de la méthode qui récupère les entrées de l'utilisateur.
```java
public static class ShipInput {
		public String orientation;
		public int x;
		public int y;
	}

	public static ShipInput readShipInput() {
		@SuppressWarnings("resource")
		Scanner sin = new Scanner(System.in);
		ShipInput res = new ShipInput();
		String[] validOrientations = {"n", "s", "e", "o"};
		boolean done = false;
		do {
			try {
				String[] in = sin.nextLine().toLowerCase().split(" ");
				if (in.length == 2) {
					String coord = in[0];
					if (Arrays.asList(validOrientations).contains(in[1])) {
						res.orientation = in[1];
						res.x = coord.charAt(0) - 'a';
						res.y = Integer.parseInt(coord.substring(1, coord.length())) - 1;
						done = true;
					}
				}
			} catch (Exception e) {
				// nop
			}

			if (!done) {
				System.err.println("Format incorrect! Entrez la position sous forme 'A0 n'");
			}

		} while (!done && sin.hasNextLine());

		return res;
	}
```
Il ne reste plus qu'a copier coller ce code dans la classe TestBoard, puis :
 - Créer un tableau des 5 navires :
	```java
	AbstractShip[] ships = {
		new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier()
	};
	```
 - Appeler la méthode readShipInput tant que tous les navires ne sont pas correctement placés
 - Paramétrer le Board avec les valeurs retournées
 - Afficher l'état actuel du Board entre chaque saisie


### Exercice 5 : phase de jeu.
A ce point, nous devrions avoir un programme qui permette de placer les 5 navires, en gérant les erreurs. Lorsque tous les navies sont placés, le programme devrait se quitter normalement. Il est maintenant temps de créer la phase de jeu. Pour ceci, nous avons besoin d'icitialiser 2 Board différents (un par joueur). 
// TODO IA. 



