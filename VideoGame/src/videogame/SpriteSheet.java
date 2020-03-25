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
public class SpriteSheet {

    private BufferedImage sheet; // To store spreadsheet

    /**
     * Creates a new SpriteSheet
     *
     * @param sheet
     */
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    /**
     * Crop a sprite from the spritesheet
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }

}
