package com.example.watch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class MainView implements Initializable {

    static Thread mainViewThread;
    static SimpleDateFormat sdf;

    public void initialize(URL url, ResourceBundle resourceBundle){
        lunchWatch();
    }

    @FXML
    private Label clock;

    @FXML
    void exit(ActionEvent event) {
        mainViewThread.stop();
        App.mainStage.close();
    }

    @FXML
    void switchToStopWatch(ActionEvent event) {
        mainViewThread.stop();
        App.switchTo("stop-watch.fxml");
    }

    @FXML
    void switchToTimeZone(ActionEvent event) {
        mainViewThread.stop();
        App.switchTo("time-zone.fxml");
    }

    @FXML
    void switchToTimer(ActionEvent event) {
        mainViewThread.stop();
        App.switchTo("timer.fxml");
    }

    void lunchWatch(){
        mainViewThread = new Thread(() -> {
            sdf = new SimpleDateFormat("HH : mm : ss");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                Platform.runLater(() -> clock.setText(sdf.format(new Date())));
            }
        });
        mainViewThread.start();
    }

}


