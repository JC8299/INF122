CANDY CRUSH

==========================================================================================================================

CONTROLS

W - Move Up
S - Move Down
A - Move Left
D - Move Right
Spacebar - Select Tile

==========================================================================================================================

GAME RULES

The game board is a 10x10, and initially has tiles randomly distributed. The game logic will always ensure that a valid move is 
available in the current board, otherwise the board will shuffle until there is a valid move. There are 6 different tile colors 
for the players to match. The initial level will allow 30 moves before the game is over.

The original Candy Crush has users selecting tiles by tapping the screen; however, this version of Candy Crush selects 
tiles differently. In a one player game, the highlighted tile has a black outline. The player moves the highlighted tile 
to the desired spot with WASD and selects the tile with the spacebar; the user can also deselect the tile with the spacebar 
as well. The second selected tile must be adjacent to the first selected tile (i.e. one column to the left or right, or one 
row above or below. However, in the original game, the game would restrict the player to only allow two blocks to switch if 
the move would result in a match; in our implementation, the player can move any two blocks as long as they are adjacent.

Matching always occurs after a player has made a move, and will match all horizontal and vertical matches of 3 or more same 
tiles in a row. A player will earn points according to the number of matches found on the entire board after their turn. After 
matching, tiles will drop down to fill the empty spaces and tiles of random colors will drop from the top to fill the resulting 
empty spaces at the top.

In a two player game, selecting works in a very similar manner - however, player two has their tiles outlined with a red outline. 
Players will take turns switching tiles to earn points.
