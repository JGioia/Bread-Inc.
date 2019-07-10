public class Test{
    public static void main(String[] args){
        Sprite s = new Sprite("IMGS/trees-and-bushes.png", 0, 0, 100, 100, 2, true, 1);
        GUI graphicalInterface = new GUI(); // Instantiate a game object which will store all our data
        graphicalInterface.addSprite(s);
        while( true ) { // Endless game loop
            try { Thread.sleep(1000/60); } catch (InterruptedException e) {} // Set framerate to 60 frames per second

            graphicalInterface.tick();
            graphicalInterface.heartbeat(); // Execute the game's heartbeat
        }
    }
}