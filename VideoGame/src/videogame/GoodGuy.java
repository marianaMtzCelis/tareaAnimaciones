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
public class GoodGuy extends Item {
    
    private int direction;
    private Game game;
    private Animation animationGoodGuy;
    
    /**
     * Constructor or GoodGuy
     * @param x
     * @param y
     * @param direction
     * @param width
     * @param height
     * @param game 
     */
    public GoodGuy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.animationGoodGuy = new Animation(Assets.buenosRight,100);
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
        
        // moves the good guy randomly between 1-3 pixels at a time
        setX(getX()+((int)(Math.random() * 3) + 1));
        
        // updates the animation
        this.animationGoodGuy.tick();
        
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
           setX((int) (Math.random() * getWidth()*-1));
        }
        
        
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }
        
        else if (getY() <= -20) {
            setY(-20);
        }

    }

    /**
     * renders the goodGuy
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(animationGoodGuy.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        //g.drawImage(Assets.goodGuy, getX(), getY(), getWidth(), getHeight(), null);
    }
    
}
