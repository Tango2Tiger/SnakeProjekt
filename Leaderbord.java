import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Leaderbord extends Application{
    //Refers to the MainMenu
    MainMenu menu = new MainMenu();

    //This is the Leaderboards override method for the leaderboard menu
    @Override
    public void start(Stage stage){
        //Group to hold all the UI elements
        Group root = new Group();
        Scene scene = new Scene(root);
        
        stage.setTitle("Snake");

        Rectangle rect = new Rectangle(800, 600);
        /* Font font = Font.font(40); */

        //Button to come back to the Main menu
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

        //VBox to organize UI elements
        VBox vBox = new VBox(exit);
        vBox.setTranslateX(10);
        vBox.setTranslateY(10);
        root.getChildren().addAll(rect, vBox);
        stage.setScene(scene);
        stage.show();
    }
    
}
