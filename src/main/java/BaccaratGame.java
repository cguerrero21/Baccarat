import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class BaccaratGame extends Application {

	private ArrayList<Card> playerHand;
	private ArrayList<Card> bankerHand;
	private BaccaratDealer theDealer;
	private BaccaratGameLogic gameLogic;
	private double currentBet;
	private double totalWinnings;
	private String betOn;
	private IntegerProperty rounds;
	private int playerTot;
	private int bankerTot;
	private String winner;
	private GridPane cardGrid= new GridPane();
	private Label playerTotalLabel;
	private Label bankerTotalLabel;

	public BaccaratGame(){
		playerHand = new ArrayList<Card>();
		bankerHand = new ArrayList<Card>();
		theDealer = new BaccaratDealer();
		gameLogic = new BaccaratGameLogic();
		currentBet = 0.0;
		totalWinnings = 0.0;
		playerTot = 0;
		bankerTot = 0;
		rounds = new SimpleIntegerProperty(0);
		winner = "";
		theDealer.generateDeck();
	}
	public void updateCards() {
		for (int i = 0; i < 2; i++) {
			Card playerCard = playerHand.get(i);
			Card bankerCard = bankerHand.get(i);

			// Update UI with the card values
			Rectangle playerRectangle = new Rectangle(125, 35, Color.WHITE); // Change to the color you want
			Label playerLabel = new Label(playerCard.getSuite() + " " + playerCard.getValue());
			playerLabel.setStyle("-fx-font-size: 12; -fx-text-fill: black;");

			StackPane playerStackPane = new StackPane();
			playerStackPane.getChildren().addAll(playerRectangle, playerLabel);
			cardGrid.add(playerStackPane, 0, i);
			GridPane.setHalignment(playerStackPane, HPos.CENTER);

			// Similar updates for the banker's hand
			Rectangle bankerRectangle = new Rectangle(125, 35, Color.WHITE); // Change to the color you want
			Label bankerLabel = new Label(bankerCard.getSuite() + " " + bankerCard.getValue());
			bankerLabel.setStyle("-fx-font-size: 12; -fx-text-fill: black;");

			StackPane bankerStackPane = new StackPane();
			bankerStackPane.getChildren().addAll(bankerRectangle, bankerLabel);
			cardGrid.add(bankerStackPane, 1, i);
			GridPane.setHalignment(bankerStackPane, HPos.CENTER);
		}

		playerTotalLabel.setText("Player Total: " + playerTot);
		bankerTotalLabel.setText("Banker Total: " + bankerTot);
	}
	public double evaluateWinnings(){
		double winnings = 0;
		String winner = gameLogic.whoWon(bankerHand,playerHand);
		if(betOn.equals(winner) && winner.equals("Banker")){
			double commission = 0.05 * currentBet;
			winnings += (currentBet - commission);
		} else if(betOn.equals(winner) && winner.equals("Draw")){
			winnings += currentBet*8;
		} else if(betOn .equals(winner) && winner.equals("Player")) {
			winnings += currentBet;
		} else if(!betOn.equals(winner)){ // lost the bet
			winnings -= currentBet;
		}
		return winnings;
	}

	//----------------------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Baccarat by: Carlos Guerrero");

		Color customColor = Color.web("#4E6A54");
		BorderPane layout = new BorderPane();
		BackgroundFill backgroundFill = new BackgroundFill(customColor, null, null);
		Background background = new Background(backgroundFill);
		layout.setBackground(background);

		TextField betAmount = new TextField();
		betAmount.setPromptText("Enter your bet");
		betAmount.setPrefWidth(200);
		betAmount.setPrefHeight(30);
		betAmount.setAlignment(Pos.CENTER);
		betAmount.setOnAction(event -> {
			try {
				currentBet = Double.parseDouble(betAmount.getText());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		});

		MenuBar menuBar = new MenuBar();
		Menu options = new Menu("Options");
		menuBar.getMenus().add(options);

		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(event -> System.exit(0));

		MenuItem fresh = new MenuItem("Fresh Start");
		options.getItems().addAll(exit, fresh);

		VBox topBox = new VBox(10);
		topBox.setAlignment(Pos.CENTER);
		topBox.setPadding(new Insets(10));

		Label title = new Label("Welcome to Baccarat");
		Label roundCount = new Label("Current rounds: " + rounds);
		Label totWinnings = new Label("Total winnings: "+ totalWinnings);
		totWinnings.setStyle("-fx-font-size: 15; -fx-text-fill: white;");
		roundCount.setStyle("-fx-font-size: 15; -fx-text-fill: white;");
		roundCount.textProperty().bind(rounds.asString("Current rounds: %d"));
		title.setStyle("-fx-font-size: 30; -fx-text-fill: white;");
		HBox titleBox = new HBox(10);
		titleBox.setAlignment(Pos.CENTER);
		HBox.setMargin(roundCount, new Insets(0, 100, 0, 0));
		HBox.setMargin(totWinnings, new Insets(0, 0, 0, 100));
		titleBox.getChildren().addAll(roundCount, title, totWinnings);
		topBox.getChildren().addAll(menuBar, titleBox);
		layout.setTop(topBox);

		Label center = new Label("Player          Banker");
		center.setStyle("-fx-font-size: 30; -fx-text-fill: white;");
		HBox centerBox = new HBox(10);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.getChildren().add(center);


		VBox botBox = new VBox(10);
		botBox.setAlignment(Pos.CENTER);
		botBox.setPadding(new Insets(10));


		cardGrid.setAlignment(Pos.CENTER);
		cardGrid.setHgap(30);
		cardGrid.setVgap(20);

		playerTotalLabel = new Label("Player Total: " + playerTot);
		playerTotalLabel.setStyle("-fx-font-size: 15; -fx-text-fill: white;");

		bankerTotalLabel = new Label("Banker Total: " + bankerTot);
		bankerTotalLabel.setStyle("-fx-font-size: 15; -fx-text-fill: white;");

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				if (i < 2) {
					List<Card> cardList = j == 0 ? playerHand : bankerHand;
					if (!cardList.isEmpty()) {
						Card currentCard = cardList.get(i);
						Rectangle rect = new Rectangle(125, 35, Color.WHITE);
						StackPane stackPane = new StackPane(rect);

						Label label = new Label(currentCard.getSuite() + " " + currentCard.getValue());
						label.setStyle("-fx-font-size: 12; -fx-text-fill: black;");

						stackPane.getChildren().add(label);
						cardGrid.add(stackPane, j, i);
						GridPane.setHalignment(stackPane, HPos.CENTER);
					} else {
						Rectangle rect = new Rectangle(125, 35, Color.WHITE);
						StackPane stackPane = new StackPane(rect);

						cardGrid.add(stackPane, j, i);
						GridPane.setHalignment(stackPane, HPos.CENTER);
					}
				} else {
					StackPane playerStackPane = new StackPane(playerTotalLabel);
					StackPane bankerStackPane = new StackPane(bankerTotalLabel);

					cardGrid.add(playerStackPane, 0, 2);
					cardGrid.add(bankerStackPane, 1, 2);

					GridPane.setHalignment(playerStackPane, HPos.LEFT);
					GridPane.setHalignment(bankerStackPane, HPos.LEFT);
				}
			}
		}

		Label outcomeLabel = new Label("________ wins \n Win or loss message here");
		outcomeLabel.setStyle("-fx-font-size: 16; -fx-text-fill: white;");
		HBox message = new HBox(10);
		message.setAlignment(Pos.CENTER);
		message.getChildren().add(outcomeLabel);

		VBox midBox = new VBox(10);
		midBox.setAlignment(Pos.TOP_CENTER);
		midBox.setPadding(new Insets(10));
		midBox.getChildren().addAll(centerBox, cardGrid, message);

		layout.setCenter(midBox);

		Button deal = new Button("DEAL");
		deal.setPrefSize(100, 35);
		deal.setOnAction(event -> {
			playerTot = 0;
			bankerTot = 0;
			theDealer.shuffleDeck();
			playerHand = theDealer.dealHand();
			bankerHand = theDealer.dealHand();
			playerTot = gameLogic.handTotal(playerHand);
			bankerTot = gameLogic.handTotal(bankerHand);
			updateCards();
			rounds.set(rounds.get() + 1);
		});
		Button playerB = new Button("Player");
		playerB.setOnAction(event -> {
			betOn = "Player";
			//System.out.println(betOn);
		});
		Button dealerB = new Button("Banker");
		dealerB.setOnAction(event -> {
			betOn = "Banker";
			//System.out.println(betOn);
		});
		Button tie = new Button("Tie");
		tie.setOnAction(event -> {
			betOn = "Tie";
			//System.out.println(betOn);
		});
		playerB.setPrefSize(100, 50);
		dealerB.setPrefSize(100, 50);
		tie.setPrefSize(100, 50);

		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(playerB, tie, dealerB);
		botBox.getChildren().addAll(deal, buttonBox, betAmount);

		layout.setBottom(botBox);

		Scene scene = new Scene(layout, 800, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
