import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class BaccaratGame extends Application {





	//----------------------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to Baccarat");

		Image background= new Image("file:baccarat-table-layout.jpg");
		Group root = new Group();

		ImageView mv = new ImageView(background);
		mv.setPreserveRatio(true);
		mv.fitWidthProperty().bind(primaryStage.widthProperty());
		mv.fitHeightProperty().bind(primaryStage.heightProperty());
		root.getChildren().add(mv);

		Button deal = new Button("DEAL");
		deal.setPrefWidth(150);
		deal.setPrefHeight(75);
		Button clear = new Button("Clear Board");
		clear.setPrefWidth(150);
		clear.setPrefHeight(75);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(mv, clear, deal);
		StackPane.setMargin(clear, new javafx.geometry.Insets(-475, 0, 0, -550));
		StackPane.setMargin(deal, new javafx.geometry.Insets(-475, -550, 0, 0));

		Scene scene = new Scene(stackPane, 1200, 690);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
