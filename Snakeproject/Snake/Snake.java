
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;


public class Snake{
    private ArrayList<Rectangle> segments;

    public Snake(int x, int y, int gridsize){
        this.segments = new ArrayList<Rectangle>();
        Rectangle head = new Rectangle(gridsize,gridsize);
        head.setX(x);
        head.setY(y);
        segments.add(head);

        Rectangle tail = new Rectangle(gridsize,gridsize);
        head.setX(x+gridsize);
        head.setY(y);
        segments.add(tail);

    }


}
