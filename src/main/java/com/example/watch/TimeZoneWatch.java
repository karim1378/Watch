package com.example.watch;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneWatch {
    static Thread timeZoneThread;
    static SimpleDateFormat sdf;
    @FXML
    private Label timeZone;


    protected void zone(String zone) {
        timeZoneThread = new Thread(() -> {
            sdf = new SimpleDateFormat("HH : mm : ss");
            sdf.setTimeZone(TimeZone.getTimeZone(zone));
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                String time = sdf.format(new Date());
                Platform.runLater(() -> timeZone.setText(time));
            }
        });
        timeZoneThread.start();
    }

    @FXML
    protected void japan(){
        zone("Asia/Tokyo");
    }

    @FXML
    protected void iran(){
        zone("Asia/Tehran");
    }

    @FXML
    protected void canada(){
        zone("America/Regina");
    }

    @FXML
    protected void turkey(){
        zone("Europe/Istanbul");
    }

    @FXML
    protected void russia(){
        zone("Europe/Moscow");
    }

    @FXML
    protected void spain(){
        zone("Europe/Madrid");
    }

    @FXML
    protected void australia(){
        zone("Australia/Sydney");
    }

    @FXML
    protected void usa(){
        zone("America/Chicago");
    }

    @FXML
    void backBtn() {
        if(timeZoneThread != null)
            timeZoneThread.stop();
        App.switchTo("main-view.fxml");
    }
}

