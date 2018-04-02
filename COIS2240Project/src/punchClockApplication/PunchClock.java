package punchClockApplication;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PunchClock extends Application{
		
		public static void main(String[] args) {
			launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws Exception{
			
			FXMLLoader loader = new FXMLLoader();
			
			String fxmlDocPath = "src/punchClockApplication/PunchClockView.fxml";
			FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
			
			StackPane root = (StackPane) loader.load(fxmlStream);
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			
			primaryStage.setTitle("Example");
			
			primaryStage.show();
		}

	}

