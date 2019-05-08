import java.awt.*;                  // Needed for graphics
import java.awt.event.*;
import javax.swing.*;               // Needed for windows and frames
import java.util.*;

// This is actually the main class which runs the game
public class GraphicalInterface extends JFrame implements KeyListener, MouseListener, MouseMotionListener {

    //This is the ArrayList of sprites this class paints
    public static ArrayList<Sprite> images;

    //
    public int xOffset, yOffset, mouseX, mouseY;
    private boolean upKey, downKey, rightKey, leftKey, mouseKey, escapeKey; 
    private static boolean[] boolInput = new boolean[6];
    private static int[] intInput = new int[2];
    private static double fps;

    private static Game game;
    
    // Main method runs automatically
    public static void main( String[] args ) {
        GraphicalInterface graphicalInterface = new GraphicalInterface(); // Instantiate a game object which will store all our data
        game = new Game();
        long time = System.currentTimeMillis();
        while( true ) { // Endless game loop
            try { Thread.sleep(1000/60); } catch (InterruptedException e) {} // Set framerate to 60 frames per second
            
            //FPS checker
            fps=1000/(System.currentTimeMillis()-(double)time);
            time=System.currentTimeMillis();
            
            images=game.tick(boolInput, intInput);
            graphicalInterface.heartbeat(); // Execute the game's heartbeat
        }
    }
    
    // Game contructor to initialize all our data
    public GraphicalInterface() {
        super("Game"); // Call the super constructor and set the window's title to "Game"
        
        //Set properties of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize( 640, 480 ); 
        setResizable(false);
        setVisible(true);
        setBackground(Color.black);
        
        //TODO: create loading screen

        //add listeners to the frame
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        // Initialize default keys
        upKey = false;
        downKey = false;
        rightKey = false;
        leftKey = false;
        mouseKey = false;

        // Initialize default mouse position
        mouseX=0;
        mouseY=0;

        //Initialize default offsets
        xOffset=0;
        yOffset=0;
        
    }
    
    // Heartbeat executes at the beginning of every frame
    public void heartbeat() {
        //this puts our inputs into an array
        boolInput[0]=upKey;
        boolInput[1]=downKey;
        boolInput[2]=rightKey;
        boolInput[3]=leftKey;
        boolInput[4]=mouseKey;
        boolInput[5]=escapeKey;
        intInput[0]=mouseX;
        intInput[1]=mouseY;

        //this runs the paint method indirectly
        repaint();
    }
    
    // The paint method tells the screen where to draw things, in this case we use double-buffering
    public void paint( Graphics page ) {
        // Create an image, this image is where we will draw the next frame
        Image frame = createImage(getWidth(),getHeight());
        // Instantiate a graphics object to store all our items drawn to our frame
        Graphics frameGraphics = frame.getGraphics();

        //this iterates through all of our sprites and draws them
        try{
            for(int i=0;i<GraphicalInterface.images.size();i++){
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
            frameGraphics.drawString(""+escapeKey,10,82);
        /*
            frameGraphics.drawString("FPS: "+fps, 10, 82 );    
            frameGraphics.drawString(images.get(1).getHitboxToString(), 10, 82 );
            frameGraphics.drawString(images.get(2).getHitboxToString(), 10, 62 );
            if(mouseKey)
                frameGraphics.drawString("True", 10, 102 );
            else
                frameGraphics.drawString("False", 10, 102 );
            frameGraphics.drawString(game.getXOffset()+", "+game.getYOffset(), 10, 122 );
        */
        
        //this draws the image we just made to the screen
        page.drawImage(frame,0,0,null);
    }
    
    // We implement a KeyListener so we need the KeyListener methods
    public void keyPressed(KeyEvent e) {
        if( e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP ) {
            upKey = true;
        }
        if( e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN ) {
            downKey = true;
        }
        if( e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            rightKey = true;
        }
        if( e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT ) {
            leftKey = true;
        }
        if( e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            escapeKey=true;
        }
    } 
    public void keyReleased(KeyEvent e) {
        if( e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP ) {
            upKey = false;
        }
        if( e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN ) {
            downKey = false;
        }
        if( e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT ) {
            rightKey = false;
        }
        if( e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT ) {
            leftKey = false;
        }
        if( e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            escapeKey=false;
        }
    }
    public void keyTyped(KeyEvent e) { 
    }

    // We implement a MouseListener so we need the MouseListener methods
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

    // We implement a MouseMotionListener so we need the MouseMotionListener methods
    public void mouseDragged(MouseEvent e){
    }
    public void mouseMoved(MouseEvent e){
        try{
            mouseX=e.getX()+game.getXOffset();
            mouseY=e.getY()+game.getYOffset();
        }catch(NullPointerException i){}
    }
}