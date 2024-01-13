import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Leaderbord extends Application{
    MainMenu menu = new MainMenu();


    @Override
    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root);
        
        stage.setTitle("Snake");

        Rectangle rect = new Rectangle(800, 600);
        Font font = Font.font(40);


        Button exit = new Button("Back");
        exit.setFont(Font.font(30));
        exit.setMinSize(100, 25);
        exit.setOnAction(event -> {
            try {
                menu.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox vBox = new VBox(exit);

        
        vBox.setTranslateX(10);
        vBox.setTranslateY(10);
        root.getChildren().addAll(rect, vBox);
        stage.setScene(scene);
        stage.show();
    }
    
}
