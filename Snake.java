import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Snake{
    public ArrayList<Rectangle> segments;
    public String direction;
    public Rectangle tail;

    public Snake(int x, int y){
        this.segments = new ArrayList<Rectangle>();
        Rectangle head = new Rectangle(GamePanel.TILE_SIZE,GamePanel.TILE_SIZE);
        head.setFill(Color.CORAL);
        head.setX(x);
        head.setY(y);
        segments.add(head);

        tail = new Rectangle(GamePanel.TILE_SIZE,GamePanel.TILE_SIZE);
        tail.setFill(Color.LIMEGREEN);
        tail.setX(x+1);
        tail.setY(y);
        segments.add(tail);
        Rectangle rect = new Rectangle(GamePanel.TILE_SIZE,GamePanel.TILE_SIZE);
        rect.setX(x+2);
        rect.setY(y);
        rect.setFill(Color.LIMEGREEN);
        segments.add(rect);
        
        this.direction = "LEFT";
    }

    public void add1(){
        Rectangle extra = new Rectangle(GamePanel.TILE_SIZE,GamePanel.TILE_SIZE);
        extra.setFill(Color.LIMEGREEN);
        extra.setX(10);
        extra.setY(10);
        segments.add(segments.size(), extra);
    }


}
