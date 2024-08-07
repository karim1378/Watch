package com.example.watch;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class StopWatch implements Initializable {
    static int  minute, second1, second2, second3; // second1 unit -> 1s  ,  second2 unit -> 0.1s  ,  second3 unit -> 0.01s
    static Thread stopWatchThread;
    static boolean timerReset = true;

    @FXML
    private ToggleGroup accuracy;

    @FXML
    private RadioButton item1;

    @FXML
    private RadioButton item2;

    @FXML
    private RadioButton item3;

    @FXML
    private ListView<String> list;

    @FXML
    private Label time;

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1){
        item1.setSelected(true);
    }

    @FXML
    void deleteDataBtn() {
        list.getItems().clear();
    }

    @FXML
    void resetBtn() {
        stopWatchThread.stop();
        timerReset = true;
        minute = 0;
        second1 = 0;
        second2 = 0;
        second3 = 0;
        time.setText("00 : 00 : 00");
    }

    @FXML
    void saveBtn() {
        list.getItems().add(time.getText());
    }

    @FXML
    void startBtn() {
        if (timerReset){
            timerReset = false;
            stopWatchThread = new Thread(() -> {
                while (true) {
                    try {
                        if ((second3 == 0 || second3 % 10 != 0) && (second3 != 99)){
                            second3++;
                        }else if ((second3 % 10 == 0) && (second2 != 9)){
                            second3++;
                            second2++;
                        }else if ((second3 == 99) && (second2 != 9)){
                            second3 = 0;
                            second2++;
                        }else if ((second3 == 99) && (second1 != 59)){
                            second3 = 0;
                            second2 = 0;
                            second1++;
                        }else if (second3 == 99){
                            second3 = 0;
                            second2 = 0;
                            second1 = 0;
                            minute++;
                        }
                        Thread.sleep(10);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    Platform.runLater(() -> time.setText(showTime()));
                }
            });
            stopWatchThread.start();
        }
    }

    String showTime(){
        if (item1.isSelected())
            return minute+" : "+second1;
        else if (item2.isSelected())
            return minute+" : "+second1+" : "+second2;
        else if (second3 < 10)
            return minute+" : "+second1+" : 0"+second3;
        return minute+" : "+second1+" : "+second3;
    }

    @FXML
    void backBtn() {
        if(stopWatchThread != null && stopWatchThread.isAlive())
            stopWatchThread.stop();
        App.switchTo("main-view.fxml");
    }

}
