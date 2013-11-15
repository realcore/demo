package no.demo;

import processing.core.PApplet;

import java.util.ArrayList;

public class DemoMain extends PApplet {

	MuzakAnalyser analyser;
	
    public void setup() {
        size(1280, 1024);
        background(0);

        for(int i = 0; i< 10; i++){
            butterflyArrayList.add(GenerateRandomButterfly());
        }
        
        analyser = new MuzakAnalyser(this);
    }

    private Butterfly GenerateRandomButterfly(){
        int Min = 0;
        int Max = 250;

        int random1 = Min + (int)(Math.random() * ((Max - Min) + 1));
        int random2 = Min + (int)(Math.random() * ((Max - Min) + 1));
        int random3 = Min + (int)(Math.random() * ((Max - Min) + 1));

        int random4 = Min + (int)(Math.random() * ((Max - Min) + 1));
        int random5 = Min + (int)(Math.random() * ((Max - Min) + 1));
        int random6 = Min + (int)(Math.random() * ((Max - Min) + 1));

        Color randColor1 = new Color(random1,random2,random3);
        Color randColor2 = new Color(random4,random5,random6);

        int PosMax = 2000;

        int pos = Min + (int)(Math.random() * ((PosMax - Min) + 1));

        int SpeedMax = 10;

        int position = Min + (int)(Math.random() * ((PosMax - Min) + 1));

        int speed =  1 + (int)(Math.random() * ((SpeedMax - 1) + 1));

        Butterfly b1 = new Butterfly(randColor1, randColor2, position, 0, speed);

        return b1;
    }

    int x = 0;
    int y = 0;
    int x2 = 0;
    int y2 = 0;
    int color = 0;
    boolean turn = true;
    int speed = 5;


    int butterflyPosition = 0;
    boolean butterflyReturns = false;

    ArrayList<Butterfly> butterflyArrayList = new ArrayList<Butterfly>();

    public class Butterfly {
        private Color bodyColor, wingColor;
        private int xOffset, yPosition;
        private int speed;


        public Butterfly(Color body, Color wing, int xOffset, int yOffset, int speed) {
            this.bodyColor = body;
            this.wingColor = wing;
            this.xOffset = xOffset;
            this.yPosition = yOffset;
            this.speed = speed;
        }

        public void move() {
            if(yPosition > 2000){
                int Min = 0;
                int PosMax = 2000;

                int pos = Min + (int)(Math.random() * ((PosMax - Min) + 1));

                int SpeedMax = 10;

                int position = Min + (int)(Math.random() * ((PosMax - Min) + 1));

                int speed =  1 + (int)(Math.random() * ((SpeedMax - 1) + 1));
                xOffset = position;
                this.speed = speed;
                yPosition = -300;
            }
            yPosition = yPosition + speed;
        }

        public void draw() {
            DrawButterflyMethod(bodyColor, wingColor, xOffset, yPosition);
        }
    }


    public void draw() {
        clear();

        for(Butterfly butterfly : butterflyArrayList){
            butterfly.move();
            butterfly.draw();
        }

        if (turn) {
            x -= speed;
            y -= speed;
        } else {
            x += speed;
            y += speed;
        }

        if (x > 200) {
            turn = true;
        }

        if (x < 1) {
            turn = false;
        }

        if (butterflyPosition > 2000) {
            butterflyPosition = -600;
            //butterflyReturns = true;
        }
        if (butterflyPosition < 0) {
            butterflyReturns = false;
        }

        if (butterflyReturns) {
            butterflyPosition -= 2;
        } else {
            butterflyPosition += 2;
        }

        float beat = analyser.getBeat();
        if (beat > 0.0) {
        	fill(255, 0, 0);
        	stroke(255, 0, 0);
        	Render.circle(this, width / 2, height / 2, 100 * beat, 32);
        }

    }

    public class Color {
        public int R, G, B;

        public Color(int r, int g, int b) {
            this.R = r;
            this.G = g;
            this.B = b;
        }
    }

    private void DrawButterflyMethod(Color body, Color wings, int xOffset, int yOffset) {
        //Butterfly body
        stroke(50, 50, 100);
        fill(body.R, body.G, body.B);
        quad(
                xOffset, yOffset,
                xOffset + 100, yOffset,   //Punkg - 2
                xOffset + 100, yOffset + 300,   //Punkt - 3
                xOffset, yOffset + 300);


        //Wing color
        stroke(color, 50, 100);
        fill(wings.R, wings.G, wings.B);

        //Wing One
        quad(
                xOffset - 300 + x, yOffset - 100 + y,
                xOffset, yOffset,
                xOffset, yOffset + 300,
                xOffset - 300 + x, yOffset + 400 - y);


        //Wing Two
        quad(
                xOffset + 100, yOffset,
                xOffset + 400 - x, yOffset - 100 + x,
                xOffset + 400 - x, yOffset + 400 - x,
                xOffset + 100, yOffset + 300);

    }
}
