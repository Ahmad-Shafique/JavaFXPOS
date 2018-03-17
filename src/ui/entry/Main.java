package ui.entry;

import core.domain.model.entities._utilities.console;
import infrastructure.sample.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.awt.*;
import java.sql.*;

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
        launch(args);
    }
}
