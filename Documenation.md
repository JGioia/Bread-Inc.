# Classes
## Game.java
- This class runs the game
- Ways to be moved
## Class 2
- This class extends Class 1
- Allows balls to be part of ball groups
# Class 1
## Images arraylist
- It's a three dimmensional array of images
- Dimension 0 is an array of different layers
  - Layer 0 is the background (ex. grass)
  - Layer 1 is parts of the background that are on the background (ex. path on grass)
  - Layer 2 is parts of the background that affect the player (ex. cliffs)
  - Layer 3 is parts of the foreground that the player walks over (ex. crops)
  - Layer 4 is the characters
  - Layer 5 is the buildings
  - Layer 6 is the tooltip background
  - Layer 7 is the tooltip text
  - Layer 8 is the overlay background
  - Layer 9 is the overlay text
  - Layer 10 is the escape menu background
  - Layer 11 is the escape menu text
  - Layer 12 is the main menu background
  - Layer 13 is the main menu text
  - Layer 14 is the cursor
- Dimension 1 is an array of different images with characteristics
- Dimension 2 is the different characteristics
  - Characteristic 0 is the image
  - Characteristic 1 is the x position
  - Characteristic 2 is the y position
  - Characteristic 3 is the x size
  - Characteristic 4 is the y size
  - Characteristic 5 is the visibility
## Paint Method
- The paint method draws all of the images in the arraylist in order if they are visible
```
Code
```