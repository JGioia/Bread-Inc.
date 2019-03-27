import java.awt.event.KeyEvent;     // Needed for key controlled actions
import java.awt.event.KeyListener;  // Needed for key identification

public class Runner implements KeyListener {
    public static void main(String[] args) {
        Game game = new Game(); // Instantiate a game object which will store all our data
        while( true ) { // Endless game loop
            try { Thread.sleep(1000/20); } catch (InterruptedException e) {} // Set framerate to 60 frames per second
            game.heartbeat(); // Execute the game's heartbeat
        }
    }


// We implement a KeyListener so we need three KeyListener methods
public void keyPressed(KeyEvent e) {
    // check if either the 'w' key or the up arrow is the key being pressed
    if( e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP ) {
      // if either is being pressed, set the upKey boolean to true
      upKey = true;
    }
    // check if either the 's' key or the down arrow is the key being pressed
    if( e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN ) {
      // if either is being pressed, set the downKey boolean to true
      downKey = true;
    }
    // check if either the 'd' key or the right arrow is the key being pressed
    if( e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
      // if either is being pressed, set the rightKey boolean to true
      rightKey = true;
    }
    // check if either the 'a' key or the left arrow is the key being pressed
    if( e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT ) {
      // if either is being pressed, set the leftKey boolean to true
      leftKey = true;
    }
  } 
  public void keyReleased(KeyEvent e) {
    // check if either the 'w' key or the up arrow is the key being released
    if( e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP ) {
      // if either is being released, set the rightKey boolean to false
      upKey = false;
    }
    // check if either the 's' key or the down arrow is the key being released
    if( e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN ) {
      // if either is being released, set the rightKey boolean to false
      downKey = false;
    }
    // check if either the 'd' key or the right arrow is the key being released
    if( e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
      // if either is being released, set the rightKey boolean to false
      rightKey = false;
    }
    // check if either the 'a' key or the left arrow is the key being released
    if( e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT ) {
      // if either is being released, set the rightKey boolean to false
      leftKey = false;
    }
  }
  
  // We need to also override the KeyTyped method for a KeyListener, but we don't actually do anything with it
  public void keyTyped(KeyEvent e) { 
  }
}