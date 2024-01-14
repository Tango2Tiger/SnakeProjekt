import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Snake{
    public ArrayList<Rectangle> segments;
    public String direction;
    public Rectangle tail;
    public  int segmentSize;
    public int score = 0;
    public Text scoretxt = new Text();
    public Color col = Color.LIMEGREEN;
    public String playerName = "";

    public Snake(){
        segments = new ArrayList<Rectangle>();
        direction = "LEFT";
        tail = null;
        segmentSize = segments.size();
        
    }

    public Snake(int x, int y, Color color){
        col = color;
        segments = new ArrayList<Rectangle>();
        Rectangle head = new Rectangle(GamePanel.TILE_SIZE,GamePanel.TILE_SIZE);
        head.setFill(Color.CORAL);
        head.setX(x);
        head.setY(y);
        segments.add(head);

        tail = new Rectangle(GamePanel.TILE_SIZE,GamePanel.TILE_SIZE);
        tail.setFill(col);
        tail.setX(x+1);
        tail.setY(y);
        segments.add(tail);
        
        this.direction = "LEFT";
        segmentSize = segments.size();
    }

    public void create(int x, int y, int s){
        Rectangle head = new Rectangle(s,s);
        head.setX(x);
        head.setY(y);
        segments.add(head);

        tail = new Rectangle(s,s);
        tail.setX(x+1);
        tail.setY(y);
        segments.add(tail);
    }

    public void setCol(Color color){
        col = color;
    }
    



}
