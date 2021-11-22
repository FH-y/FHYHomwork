package JAVA_picture_ping;

import javax.swing.*;

public class Cell extends JButton {
    public Cell(Icon icon){
        super(icon);
        this.setSize(Const.CELL_W,Const.CELL_H);
    }
    public void move(String direction){
        switch(direction){
            case "UP":
                this.setLocation(this.getBounds().x,this.getBounds().y - 150);
                break;
            case "DOWN":
                this.setLocation(this.getBounds().x,this.getBounds().y + 150);
                break;
            case "LEFT":
                this.setLocation(this.getBounds().x-150,this.getBounds().y);
                break;
            case "RIGHT":
                this.setLocation(this.getBounds().x+150,this.getBounds().y);
                break;
            default:
                break;

        }

    }
}
