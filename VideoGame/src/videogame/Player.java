/*
 * Mariana Martínez Celis
 * A01194953
 * Parcial 1
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author antoniomejorado
 */
public class Player extends Item{

    private int direction;
    private Game game;
    private Animation animationUp; // stores the animation of player going up
    private Animation animationDown; // stores the animation of player going down
    private Animation animationRight; // stores the animation of player going right
    private Animation animationLeft; // stores the animation of player going left
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        
        // creates the animations
        this.animationUp = new Animation(Assets.playerUp,100);
        this.animationDown = new Animation(Assets.playerDown,100);
        this.animationRight = new Animation(Assets.playerRight,100);
        this.animationLeft = new Animation(Assets.playerLeft,100);
    }

    /**
     * gets the direction of the player
     * @return direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * gets the width of the player
     * @return width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * gets the height of the player
     * @return height
     */
    public int getHeight() {
        return height;
    }
    
    
    /**
     * sets the direction of the player
     * @param direction 
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * sets the width of the player
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * sets the height of the player
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public void tick() {
        
        // moving player depending on flags
        if (game.getKeyManager().up) {
           // updating animation
           this.animationUp.tick();
           direction = 1;
           setY(getY() - 1);
        }
        if (game.getKeyManager().down) {
            // updating animation
           this.animationDown.tick();
           direction = 2;
           setY(getY() + 1);
        }
        if (game.getKeyManager().left) {
           // updating animation
           this.animationLeft.tick();
           direction = 3;
           setX(getX() - 1);
        }
        if (game.getKeyManager().right) {
           // updating animation
           this.animationRight.tick();
           direction = 4;
           setX(getX() + 1);
        }
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        }
        else if (getX() <= -30) {
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        else if (getY() <= -20) {
            setY(-20);
        }
    }

    @Override
    public void render(Graphics g) {
        switch (direction) {
            case 1:
                g.drawImage(animationUp.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 2:
                g.drawImage(animationDown.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 3:
                g.drawImage(animationLeft.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 4:
                g.drawImage(animationRight.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
        }
       // g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}
