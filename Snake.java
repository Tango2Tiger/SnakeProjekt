import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Snake{
    //The creation of the snake with its rectangles, with the direction, size, and tail
    public ArrayList<Rectangle> segments;
    public String direction;
    public Rectangle tail;
    public  int segmentSize;
    
    //This is used in multiplayer to create two different scores
    public int score = 0;
    public Text scoretxt = new Text();

    //Color of the snake
    public Color col = Color.LIMEGREEN;

    //Default snake settings, with direction and length
    public Snake(){
        segments = new ArrayList<Rectangle>();
        direction = "LEFT";
        tail = null;
        segmentSize = segments.size();
        
    }

    //Constructor with other color for multiplayer
    public Snake(int x, int y, Color color){
        col = color;
        segments = new ArrayList<Rectangle>();
        Rectangle head = new Rectangle(GamePanel.TILE_SIZE,GamePanel.TILE_SIZE);
        
        //Head for snake is crated
        head.setFill(Color.CORAL);
        head.setX(x);
        head.setY(y);
        segments.add(head);

        //And so is the tail
        tail = new Rectangle(GamePanel.TILE_SIZE,GamePanel.TILE_SIZE);
        tail.setFill(col);
        tail.setX(x+1);
        tail.setY(y);
        segments.add(tail);
        
        //The initial dirrection of the snake
        this.direction = "LEFT";
        segmentSize = segments.size();
    }

    //Method to create snake segments, with position and size
    public void create(int x, int y, int s){
        Rectangle head = new Rectangle(s,s);
        head.setX(x);
        head.setY(y);
        segments.add(head);

        //Tail created here
        tail = new Rectangle(s,s);
        tail.setX(x+1);
        tail.setY(y);
        segments.add(tail);
    }

    //Method to choose the color of the snake
    public void setCol(Color color){
        col = color;
    }
    



}
