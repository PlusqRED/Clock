import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;

public class Clock {
    private Circle clockFace;
    private double posX, posY, radius;
    private ArrayList<Node> clockDetails;
    public Line hourHand, minuteHand, secondHand;
    private int hours, minutes, seconds;
    private Calendar calendar;
    private Formatter current;
    public Clock(double pX, double pY, double radius) {
        posX = pX;
        posY = pY;
        this.radius = radius;

        clockDetails = new ArrayList<>();
        calendar = Calendar.getInstance();

        createClockFace();
        setStripeAroundCircle();
        setClockHands();
        rotateAllHandsToNormalView();
    }

    private void createClockFace() {
        clockFace = new Circle(posX, posY, radius);
        clockFace.setFill(Color.WHITE);
        clockFace.setStroke(Color.BLACK);
        clockFace.setStrokeWidth(4);
        clockDetails.add(clockFace);
    }
    private void rotateAllHandsToNormalView() {
        for(Node element: clockDetails)
            element.setRotate(180);
    }
    private void setClockHands() {
          current = new Formatter();
          current.format("%tS", calendar);
          seconds = Integer.parseInt(current.toString());
          secondHand = new Line(clockFace.getCenterX(), clockFace.getCenterY(), clockFace.getCenterX() + Math.cos(2*Math.PI*seconds/60 - Math.PI/2)*radius, clockFace.getCenterX() + Math.sin(2*Math.PI*seconds/60 - Math.PI/2)*radius);
          secondHand.setStroke(Color.RED);
          secondHand.setStrokeWidth(2);
          clockDetails.add(secondHand);

        current = new Formatter();
        current.format("%tM", calendar);
        minutes = Integer.parseInt(current.toString());
        minuteHand = new Line(clockFace.getCenterX(), clockFace.getCenterY(), clockFace.getCenterX() + Math.cos(2*Math.PI*minutes/60 - Math.PI/2)*radius * 4/5, clockFace.getCenterX() + Math.sin(2*Math.PI*minutes/60 - Math.PI/2)*radius * 4/5);
        minuteHand.setStroke(Color.GREEN);
        minuteHand.setStrokeWidth(4);
        clockDetails.add(minuteHand);

        current = new Formatter();
        current.format("%tI", calendar);
        hours = Integer.parseInt(current.toString());
        hourHand = new Line(clockFace.getCenterX(), clockFace.getCenterY(), clockFace.getCenterX() + Math.cos(2*Math.PI*hours/12 - Math.PI/2)*radius * 1/2, clockFace.getCenterX() + Math.sin(2*Math.PI*hours/12 - Math.PI/2)*radius * 1/2);
        hourHand.setStroke(Color.BLUE);
        hourHand.setStrokeWidth(6);
        clockDetails.add(hourHand);
    }

    public void updateClock() {
        calendar = Calendar.getInstance();

        current = new Formatter();
        current.format("%tS", calendar);
        seconds = Integer.parseInt(current.toString());
        secondHand.setEndX(clockFace.getCenterX() + Math.cos(2*Math.PI*seconds/60 - Math.PI/2)*clockFace.getRadius());
        secondHand.setEndY(clockFace.getCenterX() + Math.sin(2*Math.PI*seconds/60 - Math.PI/2)*clockFace.getRadius());

        current = new Formatter();
        current.format("%tM", calendar);
        minutes = Integer.parseInt(current.toString());
        minuteHand.setEndX(clockFace.getCenterX() + Math.cos(2*Math.PI*(minutes*60 + seconds)/3600 - Math.PI/2)*clockFace.getRadius() * 3/4);
        minuteHand.setEndY(clockFace.getCenterX() + Math.sin(2*Math.PI*(minutes*60 + seconds)/3600 - Math.PI/2)*clockFace.getRadius() * 3/4);

        current = new Formatter();
        current.format("%tI", calendar);
        hourHand.setEndX(clockFace.getCenterX() + Math.cos(2*Math.PI*(double)(hours*60 + minutes)/720 - Math.PI/2)*clockFace.getRadius() * 1/2);
        hourHand.setEndY(clockFace.getCenterX() + Math.sin(2*Math.PI*(double)(hours*60 + minutes)/720 - Math.PI/2)*clockFace.getRadius() * 1/2);
    }

    private void setStripeAroundCircle() {
        double j = Math.PI/6;
        int stripeLength = 10;
        for(int i = 0; i < 12; i++, j += Math.PI/6) {
            Line stripe = new Line(clockFace.getCenterX() + Math.cos(j)*(clockFace.getRadius() - stripeLength), clockFace.getCenterY() + Math.sin(j)*(clockFace.getRadius() - stripeLength), (clockFace.getCenterX() + Math.cos(j)*clockFace.getRadius()), (clockFace.getCenterY() + Math.sin(j)*clockFace.getRadius()));
            stripe.setStrokeWidth(3);
            clockDetails.add(stripe);
        }

    }

    public ArrayList<Node> getClock() {
        return clockDetails;
    }
}
