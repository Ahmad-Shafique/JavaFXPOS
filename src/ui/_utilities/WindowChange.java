package ui._utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowChange {

    private static WindowChange _instance;

    public static void Activate(ActionEvent event, String path, String title) throws Exception {
        if(WindowChange._instance != null){
            // System.out.println("Inside if method of WindowChange.Activate()");
            ((Node) (event.getSource())).getScene().getWindow().hide();
            WindowChange._instance.windows(path, title);
        }else {
            // System.out.println("Inside else method of WindowChange.Activate()");
            WindowChange._instance = new WindowChange();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            WindowChange._instance.windows(path, title);
        }
    }

    private WindowChange(){

    }

    private void windows(String path, String title){
        try{
            // System.out.println("Inside try block of windows function()");
            // System.out.println(System.getProperty("user.dir"));
            // path = System.getProperty("user.dir")+"\\"+path;
            // System.out.println(path);
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            // System.out.println("Exception found !!! ");
            System.out.println(e);
        }

    }
}
