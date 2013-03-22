Farkle
======
Simulation of a [Farkle](http://en.wikipedia.org/wiki/Farkle) variant.

Modules
-------
* Core - Game engine and interfaces
* Rules - Scoring rules and players
* Driver - Runs the Farkle simulation in various ways

Getting Started
---------------
Run FarkleDriver. Modify the source to add or remove players.

Implementing a Player
---------------------
Create a class that extends AbstractPlayer.

Submitting a Player
-------------------
Submit a pull request if you would like your Player contributed to the project.
Open source players should be put in Rules/src/main/java/org/thedoug/farkle/player.
Closed source players may be jar'd and put in Driver/compiled.

## Rules
See Wikipedia's [Play](http://en.wikipedia.org/wiki/Farkle#Play) and
[Standard Scoring](http://en.wikipedia.org/wiki/Farkle#Standard_scoring) sections.

Specific to this version:
* Winning score is 10,000.
* Only 5 dice are used.
* All players get the same number of turns as the winning player. Players can tie.
