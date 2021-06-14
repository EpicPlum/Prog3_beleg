import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/main/GUI/main.fxml"));
        stage.setTitle("Kupec Kuchen Automat");
        stage.setScene(new Scene(root, 800, 800));
        stage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
