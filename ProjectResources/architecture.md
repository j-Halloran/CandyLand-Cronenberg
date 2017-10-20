# Architecture Overview
Note: although its probably obvious from reading this, I am only trying to design that which is due in deliverable 1 at this point.

## Java Swing Application
* Project will follow typical swing project design where there is a modified MVC architecture where a Model and unified Controller/View object exists for each element.

# Individual Components

## Game Window
* Entire Game will exist within a single Swing panel that encapsulates the entire game board
* Within the window will exist two panels
  * Larger of the two containing the game board and the character pieces on the board
  * Smaller/along the side panel containing the card deck as well as the discards with the most recently used card face up on top

## Board Design
* To start the board will be 52 spaces long alternating in color starting from the second spaces
  * The colors cycle 10 times as follows: [red]->[yellow]->[blue]->[green]->[orange]
  * The initial space is a start space that is independant of the coloring scheme
  * The final space, grandma's house is independant of the coloring scheme
* Despite the mod 5 relationship between board spaces and colors, board spaces will be backed by a HashMap of the form <int,BoardSpace> so that special spaces can be added later without issue
  * The hashmap objects are as follows: integer representing board space number, BoardSpace view object to set/track the properties of each space
* Initial Board design will follow a snaking path using Swing's Grid Layout where each space is a modified swing object (likely  a panel)
  * Example Inital grid can be found in src/main/resources/images/exampleInitialGridLayout.png
  
## Card Decks
* Two card decks to the left? side (this is not really important just somewhere)
  * Top Deck will have whatever card back we choose and will be the draw pile face down
  * Bottom Deck will be face up with most recently used card on top to serve as discards
* Card Decks will be implemented as JButtons such that actionlisteners can be easily attached to handle mouse events
* For the future (aka this is not necessary for D1) clicking top deck will advance turn and zoom on the new card
 
## Player Markers
* For deliverable one these can just be simple squares that have a color separate from those that comprise the board

## Additional Features
* In the future we should definitely add start/finish screens
* Buttons along top to restart game and modify players

## Ending Note
* Nothing here is set in stone please feel free to add or change anything at any time