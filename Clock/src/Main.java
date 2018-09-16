import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private Pane pane;
    private AnimationTimer timer;
    private Clock clock;
    private void setUpObjects() {
        clock = new Clock(pane.getPrefWidth()/2, pane.getPrefHeight()/2, 200);
        pane.getChildren().addAll(clock.getClock());
    }

    private Parent createContent() {
        pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.BISQUE, new CornerRadii(0), new Insets(0,0,0,0))));
        pane.setPrefSize(640, 640);
        setUpObjects();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();
        return pane;
    }

    private void onUpdate() {
        clock.updateClock();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("Clock");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
