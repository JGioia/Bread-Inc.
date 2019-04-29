import java.awt.*;                  // Needed for graphics
import java.awt.event.*;
import javax.swing.*;               // Needed for windows and frames
import java.util.*;

// Declare a new class called Game which uses JFrame and KeyListener
public class GraphicalInterface extends JFrame implements KeyListener, MouseListener, MouseMotionListener {

    public static ArrayList<Sprite> images;

    public int xOffset;
    public int yOffset;
    
    private boolean upKey, downKey, rightKey, leftKey, mouseKey; 
    private static boolean[] boolInput = new boolean[5];

    private int mouseX, mouseY;
    private static int[] intInput = new int[2];

    private static Game game;
    
    // Main method runs automatically
    public static void main( String[] args ) {
        GraphicalInterface graphicalInterface = new GraphicalInterface(); // Instantiate a game object which will store all our data
        game = new Game();
        while( true ) { // Endless game loop
            try { Thread.sleep(1000/20); } catch (InterruptedException e) {} // Set framerate to 60 frames per second
            images=game.tick(boolInput, intInput);
            graphicalInterface.heartbeat(); // Execute the game's heartbeat
        }
    }
    
    // Game contructor to initialize all our data
    public GraphicalInterface() {
        super("Game"); // Call the super (in this case JFrame) and set the window's title to "Game"
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Tell the application to close the whole application when the window is closed
        setSize( 640, 480 ); // Set the size of the window
        setResizable(false); // Disable the window from being resized
        addKeyListener(this); // Attach a key listener to the window
        addMouseListener(this);
        addMouseMotionListener(this);
        setVisible(true); // Show the window
        
        // Initialize our default keys
        upKey = false;
        downKey = false;
        rightKey = false;
        leftKey = false;
        mouseKey = false;

        mouseX=0;
        mouseY=0;

        xOffset=0;
        yOffset=0;
        
    }
    
    // Heartbeat executes at the beginning of every frame
    public void heartbeat() {
        // Call the screen to redraw the content, calls paint below
        boolInput[0]=upKey;
        boolInput[1]=downKey;
        boolInput[2]=rightKey;
        boolInput[3]=leftKey;
        boolInput[4]=mouseKey;
        intInput[0]=mouseX;
        intInput[1]=mouseY;
        repaint();
    }
    
    // The paint method tells the screen where to draw things, in this case we use double-buffering
    public void paint( Graphics page ) {
        // Create an image, this image is where we will draw the next frame
        Image frame = createImage(getWidth(),getHeight());
        // Instantiate a graphics object to store all our items drawn to our frame
        Graphics frameGraphics = frame.getGraphics();
        try{
            for(int i=0;i<GraphicalInterface.images.size();i++){//why is there a null pointer exception
                if(images.get(i).visibility)
                    frameGraphics.drawImage(
                        images.get(i).getImage(), 
                        images.get(i).getXPos()-game.getXOffset(), 
                        images.get(i).getYPos()-game.getYOffset(),
                        images.get(i).getXSize(),
                        images.get(i).getYSize(), null);
            }
        }catch(Exception nulLPointerException){}
        
        
        //Debug Graphics
        frameGraphics.setColor( Color.BLUE );
        frameGraphics.drawString(images.get(0).getHitboxToString(), 10, 82 );
        frameGraphics.drawString(images.get(2).getHitboxToString(), 10, 62 );
        if(mouseKey)
            frameGraphics.drawString("True", 10, 102 );
        else
            frameGraphics.drawString("False", 10, 102 );
        frameGraphics.drawString(mouseX+", "+mouseY, 10, 122 );
        page.drawImage(frame,0,0,null);
        
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

    public void mousePressed(MouseEvent e) {
        mouseKey=true;
    }
 
    public void mouseReleased(MouseEvent e) {
        mouseKey=false;
    }
 
    public void mouseEntered(MouseEvent e) {
    }
 
    public void mouseExited(MouseEvent e) {
    }
 
    public void mouseClicked(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e){
    }

    public void mouseMoved(MouseEvent e){
        mouseX=e.getX()+game.getXOffset();
        mouseY=e.getY()+game.getYOffset();
    }
}