# Helix Snake

The goal of the project is to develop a snake game version which can generate amino acids’ codons.

# General Information
The game is played controlling a moving snake in 25*60 area. There are four letters which are A (Adenine), C (Cytosine), G (Guanine) and T (Thymine) in the game area. 
The snake starts with 3 letters assigned randomly out of four letter (A, C, G, T). When the snake eats these letters, it becomes longer. 
If the snake bumps into a wall or its own body the game is over. Also, there are two different maps as normal and advanced and three different speed options as slow, normal and fast. The scoring algorithm for each maps and speed options is different.

# Amino Acids
There are twenty amino acids found in proteins. Amino acids are represented with codons. Each codon
contains 3 letter of the DNA coding units T, C, A and G. All 64 possible 3-letter combinations of T, C, A and
G are used either to encode one of these amino acids or as one of the three stop codons that signals the
end of a sequence.

Detail Information:

     - The snake starts with 3 letters assigned randomly out of four letter (A, C, G, T) as highlighted in the figure.
     - There will be 3 letters randomly generated in the game area at the start. When the snake eats a
       letter, a new letter must be generated in the game area to maintain starting number of letters.
     - The snake will be moved by the player using arrow keys. The snake must be kept as Single Linked
       List (SLL), so it will not be able to move back. Every move take half of a second.
     - When the snake eats a letter, the letter will be added to the front (head) of the snake.
     - When the snake eats a letter, the player earns 5 points. The player will also earn extra points when
       the snake completes an amino-acid codon that was given in the input file.
     - All amino acids with their corresponding codons and points are given in “aminoacids.txt” file and this
       information will be added to a Multi Linked List (MLL).
     - “#” character shows the wall, if the snake bumps into a wall or its own body the game will be over.
       The player can record his name and the score that he/she has earned and if he/she earns a score
       within top-10 results, he/she will be displayed in the High-Score table. High Score table will contain
       highest 10 game scores of all time and will be created using only Double Linked List (DLL).
     - When the game ends, the player will be asked to start a new game or exit the game (or list the High
       Score as an alternative option). If the player exits, High Score table will be saved to a text file for
       future usage. Your program should read this High Score file at the next start of the game and create
       the necessary DLL to store and use it in game.
     - One “#” wall character will appear randomly in every 20 seconds and level increases.
       
             
# Input file 
“aminoacids.txt” text file contains the names of amino acids, SLC (Single Letter Code) of amino acids, and the codons-points(separated with “-”) for each amino acid in a single line separated by comma (,). Even though the acquired letters will be inserted from the front of the snake, the scoring and codon analysis will be done from back to front of the snake, in reverse direction. 

# Example
Assume the snake started with letters CAT. Then snake collected G, T, T and A letters
respectively. After that, the snake will become the following string of letters; ATTGCAT. The player will earn
5 points for each letter snake has eaten (4 * 5 = 20 points in total) and after that, the reverse of the snake
will be taken and scored; TACGTTA (TAC- 10 points and GTT- 20 points). The total score will be 50 points.
       
    
