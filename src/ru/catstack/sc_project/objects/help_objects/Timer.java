package ru.catstack.sc_project.objects.help_objects;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Timer {

    Timeline timeline;

    public Timer(int seconds, Runnable action){
        timeline = new Timeline(new KeyFrame(Duration.seconds(seconds), event -> action.run()));
    }

    public Timer(Duration duration, Runnable action){
        timeline = new Timeline(new KeyFrame(duration, event -> action.run()));
    }

    public void run(){
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void stop(){
        timeline.stop();
    }
}
