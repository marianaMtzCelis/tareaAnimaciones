/*
 * Mariana Martínez Celis
 * A01194953
 * Parcial 1
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {

    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage enemy;      // to store the enemy image
    public static BufferedImage goodGuy;    // to store the googGuy image
    public static SoundClip help;         // to store the buenos sound
    public static SoundClip hit;          // to store the malos sound
    public static BufferedImage end;        // to store the end (gameOver) image
    
    public static BufferedImage spritesPlayer; // to store the players sprites
    public static BufferedImage spritesChars;  // to store the characters sprites
    public static BufferedImage playerUp[];    // for player to go up
    public static BufferedImage playerDown[];  // for player to go down
    public static BufferedImage playerLeft[];  // for player to go left
    public static BufferedImage playerRight[]; // for player to go right
    public static BufferedImage buenosRight[]; // for buenos to go right
    public static BufferedImage malosLeft[];   // for malos to go left

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.jpg");
        player = ImageLoader.loadImage("/images/mario.png");
        enemy = ImageLoader.loadImage("/images/enemy.png");
        goodGuy = ImageLoader.loadImage("/images/flower.png");
        help = new SoundClip("/sounds/help.wav");
        hit = new SoundClip("/sounds/hit.wav");
        end = ImageLoader.loadImage("/images/gameOver.jpg");
        
        // Getting the sprites from the picture
        spritesPlayer = ImageLoader.loadImage("/images/dog.png");
        spritesChars = ImageLoader.loadImage("/images/characters.png");
        
        // Create the arrays of Images
        SpriteSheet spriteSheetPlayer = new SpriteSheet(spritesPlayer);
        SpriteSheet spriteSheetChars = new SpriteSheet(spritesChars);
        playerUp = new BufferedImage[3];
        playerDown = new BufferedImage[3];
        playerLeft = new BufferedImage[3];
        playerRight = new BufferedImage[3];
        buenosRight = new BufferedImage[2];
        malosLeft = new BufferedImage[2];
        
        // Croping the pictures from the sheet to the array
        for (int i=0; i<3; i++) {
            playerUp[i] = spriteSheetPlayer.crop(i*45+145,345,45,38);
            playerDown[i] = spriteSheetPlayer.crop(i*45+145,205,45,38);
            playerLeft[i] = spriteSheetPlayer.crop(i*49+140,260,49,28);
            playerRight[i] = spriteSheetPlayer.crop(i*49+142,310,49,28);
        }
        for (int i=0; i<2; i++) {
            buenosRight[i] = spriteSheetChars.crop(i*27+54,180,27,24);
        }
        for (int i=0; i<2; i++) {
            malosLeft[i] = spriteSheetChars.crop(i*36, 210, 36, 36);
        }
        
        
    }

}
