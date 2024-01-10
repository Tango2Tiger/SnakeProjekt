import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GridSize extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Snake");

        Rectangle rect = new Rectangle(800, 600);
        Text gridText = new Text();
        gridText.setText("Choose grid size:");
        gridText.setX(10);
        gridText.setY(25);
        gridText.setFont(Font.font("Roboto",40));
        gridText.setFill(Color.CORNFLOWERBLUE);

        VBox vBox = new VBox(50, gridText);
        vBox.setTranslateX(200);
        vBox.setTranslateY(50);
        root.getChildren().addAll(rect, vBox);
        stage.show();
    }
    
}
