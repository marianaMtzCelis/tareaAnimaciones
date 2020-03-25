/*
 * Mariana Martínez Celis A01194953
 * Diego Gomez Cota A00824758
 * Tarea Animaciones
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author marianamtzcelis and diegomezcota
 */
public class Animation {

    private int speed; // The speed of every frame
    private int index; // The index of the next item to paint
    private long lastTime; // Saves the previuos time of the animation
    private long timer; // Accumulates the time of the animation
    private BufferedImage[] frames; // to store every frame

    /**
     * Creating the animation with all the frames and the speed for each
     *
     * @param frames
     * @param speed
     */
    public Animation(BufferedImage[] frames, int speed) {
        this.frames = frames; // storing frames
        this.speed = speed;   // storing speed
        index = 0;            // initializing index
        lastTime = System.currentTimeMillis();  // getting the initial time
        timer = 0;            // initializing timer
    }

    /**
     * Getting the current frame to paint
     *
     * @return frames[index]
     */
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

    public void tick() {
        // accumulating time from previous tick to this one
        timer += System.currentTimeMillis() - lastTime;
        // upadting lastTime
        lastTime = System.currentTimeMillis();
        // check timer to increase index
        if (timer > speed) {
            index++;
            timer = 0;
            // check if index does not get out of bounds
            if (index >= frames.length) {
                index = 0;
            }
        }
    }

}
