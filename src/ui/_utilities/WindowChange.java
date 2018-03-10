package ui._utilities;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowChange {

    private static WindowChange _instance;
    double xOffset=0, yOffset=0;

    public static void Activate(ActionEvent event, String path, String title) throws Exception {
        if(WindowChange._instance == null) WindowChange._instance = new WindowChange();

        ((Node) (event.getSource())).getScene().getWindow().hide();
        WindowChange._instance.windows(path, title);
    }

    public static void Activate(Event event, String path, String title) throws Exception {
        if(WindowChange._instance == null) WindowChange._instance = new WindowChange();

        ((Node) (event.getSource())).getScene().getWindow().hide();
        WindowChange._instance.windows(path, title);
    }

    public static void Activate(ActionEvent event, String path, String title, String imagePath, String changeType) throws Exception{
        if(changeType.equals("internal")){
            WindowChange._instance.windowsInternal(event, path, title, imagePath);
        }else if(changeType.equals("logout")){
            WindowChange._instance.windowLogout(event, path, title, imagePath);
        }
    }

/*    public static void Activate(Event event, String path, String title) throws Exception {
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
    }*/




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


    private void windowsInternal(ActionEvent event, String path, String title, String imagePath){
        try{
            double width = ((Node) event.getSource()).getScene().getWidth();
            double height = ((Node) event.getSource()).getScene().getHeight();

            Parent root = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(root, width, height);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.getIcons().add(new Image(imagePath));
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
        }
    }

    private void windowLogout(ActionEvent event, String path, String title, String imagePath){
        try{

            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            root.setOnMousePressed((MouseEvent e) -> {
                xOffset = e.getSceneX();
                yOffset = e.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent e) -> {
                stage.setX(e.getScreenX() - xOffset);
                stage.setY(e.getScreenY() - yOffset);
            });
            Scene scene = new Scene(root);
            stage.setTitle(title);
            stage.getIcons().add(new Image(imagePath));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
        }
    }
}
