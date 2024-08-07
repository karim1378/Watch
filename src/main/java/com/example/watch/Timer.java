package com.example.watch;
import  java.awt.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Timer {
    static Thread timerThread;
    static int hour, minute, second;
    @FXML
    private Label timer;

    @FXML
    private TextField hourTF;

    @FXML
    private TextField minuteTF;

    @FXML
    private TextField secondTF;


    @FXML
    void timerStart(){
        if (timerThread == null || !timerThread.isAlive()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Alert inputAlert = new Alert(Alert.AlertType.ERROR);
            inputAlert.setContentText("فرمت ورودی ها نا معتبر است!");
            try {
                hour = Integer.parseInt(hourTF.getText());
                minute = Integer.parseInt(minuteTF.getText());
                second = Integer.parseInt(secondTF.getText());
                if (hour == 0 && minute == 0 &&second == 0){
                    alert.setContentText("تایمر را مقدار دهی کنید!");
                    alert.show();
                }else if (hour > 99 || hour < 0){
                    alert.setContentText("ساعت  باید بین 0 تا 99 باشد.");
                    alert.show();
                }else if (minute > 59 || minute < 0){
                    alert.setContentText("دقیقه باید بین 0 تا 59 باشد.");
                    alert.show();
                }else if (second > 59 || second < 0){
                    alert.setContentText("ثانیه باید بین 0 تا 59 باشد.");
                    alert.show();
                }else{
                    timerThread = new Thread(() -> {
                        while (true) {
                            try {
                                if (second < 10)
                                    Platform.runLater(() -> timer.setText(hour + " : " + minute + " : 0" + second));
                                else
                                    Platform.runLater(() -> timer.setText(hour + " : " + minute + " : " + second));
                                Thread.sleep(1000);
                                if (hour == 0 && minute == 0 && second == 1) {
                                    Platform.runLater(() -> timer.setText("00 : 00 : 00"));
                                    Toolkit.getDefaultToolkit().beep(); // generate sound for end of timer
                                    timerThread.stop();
                                } else if (second != 0) {
                                    second--;
                                } else if (minute != 0) {
                                    minute--;
                                    second = 59;
                                } else {
                                    second = 59;
                                    minute = 59;
                                    hour--;
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    });
                    timerThread.start();
                }
            }catch (Exception er){
                inputAlert.show();
            }
        }

    }

    @FXML
    void resetTimer(){
        if(timerThread != null && timerThread.isAlive()){
            timerThread.stop();
            timer.setText("00 : 00 : 00");
        }
    }

    @FXML
    void backBtn() {
        if(timerThread != null && timerThread.isAlive())
            timerThread.stop();
        App.switchTo("main-view.fxml");
    }
}
