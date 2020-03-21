/*
 * Mariana Martínez Celis
 * A01194953
 * Parcial 1
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author marianamtzcelis
 */
public class Enemy extends Item {
    
    private int direction;
    private Game game;
    private Animation animationEnemy;
    
    /**
     * Constructor or Enemy
     * @param x
     * @param y
     * @param direction
     * @param width
     * @param height
     * @param game 
     */
    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        
        this.animationEnemy = new Animation(Assets.malosLeft,100);
    }

    /**
     * gets the direction 
     * @return direction
     */
    public int getDirection() {
        return direction;
    }


    /**
     * sets the direction 
     * 
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * gets the height 
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * gets the width 
     * @return width
     */
    public int getWidth() {
        return width;
    }
    


    @Override
    public void tick() {
        
        // moves the enemy randomly between 3-5 pixels at a time
        setX(getX()-((int)(Math.random() * 3) + 3));
        
        // updating animation
        this.animationEnemy.tick();
        
        // reset x position and y position if colision
        if (getX() <= -30) {
           setX(game.getWidth() - 30);
        }
        
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        
        else if (getY() <= -20) {
            setY(-20);
        }

    }

    /**
     * renders the enemy
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(animationEnemy.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
       // g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
