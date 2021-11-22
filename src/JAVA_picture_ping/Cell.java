package JAVA_picture_ping;

import javax.swing.*;

public class Cell extends JButton {
    public Cell(Icon icon){
        super(icon);
        this.setSize(Const.CELL_W,Const.CELL_H);
    }
}
