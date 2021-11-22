package JAVA_picture_ping;

import javax.swing.*;
import java.awt.*;

public class PictureCanvas extends JPanel {

    public static int STEP_NUM; //  步数

    private Cell[] cell;
    private boolean hasActionListener = false;//判断是否添加了监听器
    private Rectangle nullCell; //创建一个空方格

    public PictureCanvas(){
        this.setLayout(null);//永自己的布局
        //创建12个小方格，放到PictureCanvas中
        cell = new Cell[12];
        for(int i=0;i<Const.ROW;i++){
            for (int j=0;j<Const.COL;j++){
                int num = i*4+j;
                String picFile = "pic/" + (num+1) + ".jpg";
                ImageIcon icon = new ImageIcon(picFile);  //读图片
                cell[num] = new Cell(icon);
                cell[num].setLocation(j*150,i*150);
                this.add(cell[num]);
            }
        }

        this.remove(cell[11]); //移除最后一个，替换成空的方格

        nullCell = new Rectangle(450,300,Const.CELL_H,Const.CELL_W);
    }
}
