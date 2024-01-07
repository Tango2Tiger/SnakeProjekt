import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Snake{
    private ArrayList<Rectangle> segments;
    public String direction;

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
        
        this.direction = "LEFT";

    }

    public ArrayList getlist(){
        return this.segments;
    }

    public void move(){
        for(int i=segments.size()-1; i > 0; i--){
            segments.set(i, segments.get(i-1));
        }
        int hovedX = (int) (segments.get(0).getX());
        int hovedY = (int) (segments.get(0).getY());


        switch (direction) {
            case "UP":
                hovedY ++;
                break;

            case "DOWN":
                hovedY --;
                break;

            case "LEFT":
                hovedX --;
                break;

            case "RIGHT":
                hovedX ++;
                break;
        
            default:
                break;
        }
        segments.get(0).setX(hovedX);
        segments.get(0).setY(hovedY);
    }


}
