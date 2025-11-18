package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import object.OBJ_Key;

public class UI {

    GamePanel gp;
    Font timesNewRoman, timesNewRoman_80B;
    Image keyImage;
    public boolean messageOn=false;
    public String message="";
    int messageCounter =0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        timesNewRoman = new Font("Times New Roman", 40);
        timesNewRoman_80B = Font.font("Times New Roman", FontWeight.BOLD, 80);
        OBJ_Key  key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn=true;
    }
    public void draw(GraphicsContext gc){

        if(gameFinished){

            gc.setFont(timesNewRoman);
            gc.setFill(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "you finished level1!";
            Text temp = new Text("you finished level1!");
            temp.setFont(timesNewRoman);
            textLength = (int) temp.getLayoutBounds().getWidth();
            x = gp.screenWidth/2- textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            gc.fillText(text,x,y);

            text = "Congratulation";
            temp = new Text("Congratulation");
            temp.setFont(timesNewRoman_80B);
            textLength = (int) temp.getLayoutBounds().getWidth();
            gc.setFont(timesNewRoman_80B);
            gc.setFill(Color.YELLOW);

            x = gp.screenWidth/2- (textLength/2);
            y = gp.screenHeight/2 + (gp.tileSize*2);
            gc.fillText(text,x,y);
            gp.stopGame();


        }else{
        gc.setFont(timesNewRoman);
        gc.setFill(Color.WHITE);
        gc.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize);
        gc.fillText("x "+ gp.player.hasKey, 74, 65);

        //message
        if(messageOn){

            gc.setFont(new Font("Times New Roman", 30));
            gc.fillText(message, gp.tileSize*5, gp.tileSize*4);
            messageCounter++;
            if(messageCounter>120){
                messageCounter = 0;
                messageOn = false;
            }}
        }
    }
}
