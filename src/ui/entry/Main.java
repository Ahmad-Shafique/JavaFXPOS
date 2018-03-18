package ui.entry;

import infrastructure.invoice.maker.sample.HelloWorldPdf;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../display/fxml/admin/dashboard.fxml"));
        primaryStage.setTitle("Foldie");
        primaryStage.setScene(new Scene(root, 1024, 640));
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        // console.log(new User().run("https://jsonplaceholder.typicode.com/posts/1"));
        // console.log(new User().run("http://localhost:3000/Category/1"));
        // new CreateInvoicePdf().generateReport();
        // new HelloWorldPdf().create();
        launch(args);
    }
}
