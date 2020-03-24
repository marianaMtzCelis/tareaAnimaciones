/*
 * Mariana Martínez Celis
 * A01194953
 * Parcial 1
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author antoniomejorado
 */
public class Game implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private KeyManager keyManager;  // to manage the keyboard
    private LinkedList<Enemy> lista; // to use a list of enemies
    private int vidas;
    private int counterVidas;
    private int score = 0;
    private LinkedList<GoodGuy> listaBuenos; // to use a list of enemies
    private boolean isPaused; // to pause or unpause game

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        vidas = (int) (Math.random() * 3) + 3;
        counterVidas = 0;
        isPaused = false;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * To get the player of the game window
     *
     * @return a <code>Playert</code> value with the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();

        // initialices lists both of enemies and goodGuys
        lista = new LinkedList<Enemy>();
        listaBuenos = new LinkedList<GoodGuy>();
        player = new Player(getWidth() / 2 - 50, getHeight() / 2 - 50, 1, 100, 100, this);

        // Calculates betweet 8-10 enemies
        int azar = (int) (Math.random() * 3) + 8;
        // Calculates betweet 10-15 good guys
        int azarBuenos = (int) (Math.random() * 6) + 10;

        // creates each enemy and adds it on the list
        for (int i = 1; i <= azar; i++) {
            Enemy enemy = new Enemy((int) ((Math.random() * getWidth())) + getWidth(), (int) (Math.random() * getHeight()) - 100, 1, 100, 100, this);
            lista.add(enemy);
        }

        // creates each good guy and adds it on the list
        for (int i = 1; i <= azarBuenos; i++) {
            GoodGuy flower = new GoodGuy((int) (Math.random() * getWidth() * -1), (int) (Math.random() * getHeight()) - 100, 1, 100, 100, this);
            listaBuenos.add(flower);
        }
        display.getJframe().addKeyListener(keyManager);
    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                if (vidas > 0) {
                    tick();
                }
                render();
                delta--;
            }
        }
        stop();
    }

    /**
     * gets the keyManager
     *
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    private void tick() {
        keyManager.tick();
        // verifies if the game is paused or not
        if (getKeyManager().pause) {
            isPaused = !isPaused;
        }
        if (!isPaused) {
            // avancing player with colision
            player.tick();
            for (Enemy enemy : lista) {
                enemy.tick();
                // if colision with enemy
                if (player.colision(enemy)) {
                    // increases counterVidas
                    counterVidas++;
                    // repositions enemy that colided
                    enemy.setX((int) ((Math.random() * getWidth())) + getWidth());
                    enemy.setY((int) (Math.random() * getHeight()) - 100);
                    // plays malos sound
                    Assets.hit.play();

                    if (counterVidas >= 5) {
                        vidas--;
                        counterVidas = 0;
                    }
                }
            }
            for (GoodGuy flower : listaBuenos) {
                flower.tick();
                // if colision with good guys
                if (player.colision(flower)) {
                    // repositions good guy that colided
                    flower.setX((int) (Math.random() * getWidth() * -1));
                    flower.setY((int) (Math.random() * getHeight()) - 100);
                    // updates score
                    score += 5;
                    // plays buenos sound
                    Assets.help.play();
                }
            }
        }
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            // renders enemy
            for (Enemy enemy : lista) {
                enemy.render(g);
            }
            // renders good guys
            for (GoodGuy flower : listaBuenos) {
                flower.render(g);
            }
            // displays vidas and score
            g.setColor(Color.black);
            g.drawString("Enemigos chocados en esta vida: " + counterVidas, 100, 80);
            g.drawString("Vidas:" + vidas, 100, 100);
            g.drawString("Score: " + score, 100, 120);

            // displays end image if vidas gets to 0 
            if (vidas <= 0) {
                g.drawImage(Assets.end, 0, 0, getWidth(), getHeight(), null);
            }
            bs.show();
            g.dispose();
        }

    }

    /**
     * setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}