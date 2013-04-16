# Idées ONAPI #

(Si jamais ceci est un document Markdowm. C'est juste un fichier texte écrit d'une certaine façon qui permet de le mettre en page automatiquement).

## Généralités ##

* Le noir + le laby = les joueurs sont plongés dans l'inconnu
* Les équipes doivent équilibrer leurs stratégie entre :
	- trouver la sortie
	- attendre le bon moment pour sortir (selon mode de jeu)
	- buter les ennemis
	- survivre


## Idées gameplay ##

En vrac...

* Une minimap de la partie déjà découverte du labyrinthe, pour que ce soit pas trop la prise de tête à trouver la sortie (ce qui permettrait aussi de générer un labyrinthe plus grand)
* Mode de victoire : comme discuté
* Couleurs des lampes selon couleur de l'équipe
* Objets/Compétences/Armes spéciales débloquables liées au compte
	* Loot aléatoire en fin de partie => nombre de points du joueur augmente les chances de loot
	* Pas de loot pour l'équipe perdante (pas de bras, pas de chocolat...)
	* Utilisation limitée des objets/compétences, genre 3 slots, ou bien à un certain nombre de points de "omghax!", pour faciliter l'équilibrage (ex : on peut prendre soit 4 items pourris de 2 points chacuns, soit 2 items puissants de 2 points)
	* Exemples d'objets/compétences :
		* Lampe à huile qu'on peut sacrifier en la lançant sur les adversaire (explosion qui fait bobo)
			* remplace la lampe de base
			* rend un joueur dépendant de son équipe pour se déplacer, peut être intéressant
		* Repère qu'on peut poser pour aider l'équipe à se....repérer
		* Flash qui révèle toute la zone et aveugle les ennemis (pendant quelques secondes seulement)
		* Vision infrarouge pendant quelques secondes, rechargeable
		* Invicibilité temporaire + impossibilité de tirer
		* Buffs rechargeable (ou lié à l'arme, selon) à balancer aux ennemis
			* état "confus" qui inverse le sens des déplacements
			* ralentissement
			* vulnérabilité
			* feu
		* Pièges au sol (dommages directs ou buffs comme ci-dessous)
		* Lance-flamme : obligé de tirer pour s'éclairer, jauge limitée qui se recharge avec le temps, crame les ennemis

## Remarques ##

Le labyrinthe devrait avoir le genre de forme suivante :

	###################
	#.#.-.-.-.#.#.-.-.#
	#-#-#######-###-#-#
	#.#.-.#.-.-.-.#.#.#
	#-###-##-##-###-#-#
	#.-.#.-.-.#.-.-.#.#
	#-#-#-#####-###-###
	#.#.-.#.-.-.#.-.-.#
	###################

	(Loucher ou s'éloigner pour mieux voir...)
	PS : en fait on voit pas bien avec le rendu github. Faut regarder le fichier texte direct ^^

* `#` : Mur
* `.` : Croisements (cases de base utilisées par l'algorithme)
* `-` : Couloir (murs supprimés par l'algorithme)

Ceci à cause de la façon dont l'algorithme les génère (adapté ici pour une représentation en grille, mais le principe reste le même).

On voit que déplacer un mur bloquerait donc forcément un passage...
