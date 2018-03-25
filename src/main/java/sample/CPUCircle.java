package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

class CPUCircle extends Circle {

    void reduceCircle(int y){
        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(.01),
                        actionEvent -> this.setRadius(this.getRadius()-1))
        );
        int z = (int) this.getRadius()-y;
        if(z>100){
            z=100;
        }else if(z<0){
            z=0;
        }
        animation.setCycleCount(z);
        animation.play();
    }

    void increaseCircle(int y){
        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(.01),
                        actionEvent -> this.setRadius(this.getRadius()+1))
        );
        int z = y-(int) this.getRadius();
        if(z>100){
            z=100;
        }else if(z<0){
            z=0;
        }
        animation.setCycleCount(z);
        animation.play();
    }

}
