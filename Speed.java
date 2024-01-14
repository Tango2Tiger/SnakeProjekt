import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Speed extends Application{
    //Refers to the MainMenu
    static MainMenu menu = new MainMenu();

    //Overrides the method to create its own "Speed" menu
    @Override
    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setTitle("Snake");
        Rectangle rect = new Rectangle(800, 600);
        Font font = Font.font(40);

        //Button for the speed-setting low
        Button low = new Button("Low");
        low.setFont(font);
        low.setMinSize(400, 50);
        low.setOnAction(event -> {
        MainMenu.speed = 150000000;
        try {
            menu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        });

        //Button for the speed-setting medium
        Button medium = new Button("Medium");
        medium.setFont(font);
        medium.setMinSize(400, 50);
        medium.setOnAction(event -> {
        MainMenu.speed = 100000000;
        try {
            menu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        });

        //Button for the speed-setting high
        Button high = new Button("High");
        high.setFont(font);
        high.setMinSize(400, 50);
        high.setOnAction(event -> {
        MainMenu.speed = 60000000;
        try {
            menu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        });

        //VBox to organize the buttons vertically
        VBox vBox = new VBox(50, low, medium, high);
        vBox.setTranslateX(200);
        vBox.setTranslateY(50);

        root.getChildren().addAll(rect, vBox);
        stage.setScene(scene);
        stage.show();
    }
}
