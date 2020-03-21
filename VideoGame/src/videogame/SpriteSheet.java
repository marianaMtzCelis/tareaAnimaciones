/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 * 
 * @author marianamtzcelis
 */
public class SpriteSheet {
    private BufferedImage sheet; // To store spreadsheet
    
    /**
     * Creates a new SpriteSheet
     * @param sheet 
     */
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }
    
    /**
     * Crop a sprite from the spritesheet
     * @param x
     * @param y
     * @param width
     * @param height
     * @return 
     */
    public BufferedImage crop( int x, int y, int width, int height ) {
        return sheet.getSubimage(x,y,width,height);
    }
    
}
