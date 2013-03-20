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
Create a class that implements Player. Optionally extend AbstractPlayer for a nice toString.

Submitting a Player
-------------------
Submit a pull request if you would like your Player contributed to the project.
Open source players should be put in Rules/src/main/java/org/thedoug/farkle/player.
Closed source players may be jar'd and put in Driver/compiled.