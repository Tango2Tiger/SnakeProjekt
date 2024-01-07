
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Snake{
    private ArrayList<Rectangle> segments;

    public Snake(int x, int y){
        this.segments = new ArrayList<Rectangle>();
        Rectangle head = new Rectangle(GamePanel.TILE_SIZE,GamePanel.TILE_SIZE);
        head.setFill(Color.CORAL);
        head.setX(x);
        head.setY(y);
        segments.add(head);

        Rectangle tail = new Rectangle(GamePanel.TILE_SIZE,GamePanel.TILE_SIZE);
        tail.setFill(Color.LIMEGREEN);
        tail.setX(x+1);
        tail.setY(y);
        segments.add(tail);

    }

    public ArrayList getlist(){
        return this.segments;
    }


}
