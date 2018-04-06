package punchClockApplication;

import java.io.FileInputStream;
import java.io.InputStream;

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
			
			String fxmlDocPath = "PunchClockView.fxml";
			//InputStream fxmlStream = PunchClock.class.getClassLoader().getResourceAsStream(fxmlDocPath);
			
			StackPane root = (StackPane) loader.load(getClass().getClassLoader().getResourceAsStream(fxmlDocPath));
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			
			primaryStage.setTitle("Example");
			
			primaryStage.show();
		}

	}

