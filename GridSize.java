import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GridSize extends Application{
    static MainMenu menu = new MainMenu();
    public GridPane grid = new GridPane();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setTitle("Snake");

        Font font = Font.font(20);

        Rectangle rect = new Rectangle(800, 600);

        Text widthtxt = new Text();
        widthtxt.setText("Width:");
        widthtxt.setX(10);
        widthtxt.setY(25);
        widthtxt.setFont(Font.font("Roboto",30));
        widthtxt.setFill(Color.CORNFLOWERBLUE);

        Slider widthslider = new Slider(5, 50, MainMenu.grid_width);
        widthslider.setShowTickLabels(true);
        widthslider.setShowTickMarks(true);
        widthslider.setMajorTickUnit(5);
        widthslider.setBlockIncrement(10);


        Text width = new Text();
        width.setX(10);
        width.setY(25);
        width.setFont(Font.font("Roboto",30));
        width.setFill(Color.CORNFLOWERBLUE);
        width.textProperty().bind(widthslider.valueProperty().asString("%.0f"));
        

        Text heighttxt = new Text();
        heighttxt.setText("Height:");
        heighttxt.setX(10);
        heighttxt.setY(25);
        heighttxt.setFont(Font.font("Roboto",30));
        heighttxt.setFill(Color.CORNFLOWERBLUE);

        Slider heightslider = new Slider(5, 50, MainMenu.grid_height);
        heightslider.setShowTickLabels(true);
        heightslider.setShowTickMarks(true);
        heightslider.setMajorTickUnit(5);
        heightslider.setBlockIncrement(10);

        Text height = new Text();
        height.setX(10);
        height.setY(25);
        height.setFont(Font.font("Roboto",30));
        height.setFill(Color.CORNFLOWERBLUE);
        height.textProperty().bind(heightslider.valueProperty().asString("%.0f"));


        
        Button done = new Button("Done");
        done.setMinSize(400, 40);
        done.setFont(font);
        done.setOnAction(event -> {
        MainMenu.grid_width = (int) widthslider.getValue();
        MainMenu.grid_height = (int) heightslider.getValue();
        try {
            menu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        });
        

        VBox vBox = new VBox(30, heighttxt, heightslider, height, widthtxt, widthslider, width, done);
        vBox.setTranslateX(200);
        vBox.setTranslateY(50);
        root.getChildren().addAll(rect, vBox);
        stage.setScene(scene);
        stage.show();
    }
    
}
