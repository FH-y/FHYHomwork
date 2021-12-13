package JAVA_picture_ping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PictureCanvas extends JPanel implements MouseListener {

    public static int STEP_NUM; //  步数
    public static Cell[] cell;
    private boolean hasActionListener = false;//判断是否添加了监听器
    private Rectangle nullCell; //创建一个空方格

    public PictureCanvas(){
        this.setLayout(null);//用自己的布局
        //创建12个小方格，放到PictureCanvas中
        cell = new Cell[12];
        for(int i=0;i<Const.ROW;i++){
            for (int j=0;j<Const.COL;j++){
                int num = i*Const.COL+j;
                String picFile = "pic/" + (num+1) + ".png";
                ImageIcon icon = new ImageIcon(picFile);  //读图片

                cell[num] = new Cell(icon);
                cell[num].setLocation(j*150,i*150);
                this.add(cell[num]);
            }
        }

        this.remove(cell[11]); //移除最后一个，替换成空的方格子
        nullCell = new Rectangle(cell[10].getBounds().x + Const.CELL_W,cell[10].getBounds().y,Const.CELL_H,Const.CELL_W);
    }
    public void start(){
        //如果要是没有给小方格添加监听,就添加监听
        if(!hasActionListener){
            //添加监听
            for(int i=0;i<11;i++){
                  cell[i].addMouseListener(this);
            }
            //更新鼠标点击监听状态
            hasActionListener = true;

        }
        //判断当前第一个小方格距离左上角较近时，进行方格和空方格的互换
        //如果第一个小方格在左上角四个方格位置内，进行左上角方格和空方格位置互换
        while(cell[0].getBounds().x <= 150 && cell[0].getBounds().y <=150){
            //获取空方格的位置
            int nullX = nullCell.getBounds().x;
            int nullY = nullCell.getBounds().y;
            //随机产生一个方向,就进行空方格与小方格的互换操作
            //产生一个0~3的随机数,对应空方格的上下左右移动
            int direction = (int)(Math.random() * 4);//0,1,2,3;
            switch(direction){
                case 0://向左移,与左边互换位置，左侧方格向右移动
                    nullX -= 150;
                    cellMove(nullX,nullY,"RIGHT");
                    break;
                case 1://向右移动，与右边互换位置,
                    nullX += 150;
                    cellMove(nullX,nullY,"LEFT");
                    break;
                case 2:	// 向上移动
                    nullY -= 150;
                    cellMove(nullX,nullY,"DOWN");
                    break;
                default: //向下移动
                    nullY += 150;
                    cellMove(nullX,nullY,"UP");
                    break;
            }
        }
        Const.KEY_STATUS = true;
    }
    //方格与空方个的移动
    private void cellMove(int nullX,int nullY,String direction){
        /*
        nullX 空方格的坐标
       direction方格移动的方向
        */
        for(int i=0;i<11;i++){
            //获取到与空方格位置相同的小方块
            if(cell[i].getBounds().x == nullX && cell[i].getBounds().y == nullY){
                // 当前方格的移动
                cell[i].move(direction);
                //空方格的移动
                nullCell.setLocation(nullX,nullY);
                //交换位置后，结束循环
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Cell button = (Cell) e.getSource();
        //获取所点击方格的x，y
        int clickX = button.getBounds().x;
        int clickY = button.getBounds().y;
        //获取空方个的xy
        int nullX = nullCell.getBounds().x;
        int nullY = nullCell.getBounds().y;
        //进行比较，如果满足条件，互换
        if (clickX == nullX && clickY - nullY == 150) {
            //点击的方格在空方格的下方
            button.move("UP");
        } else if ((clickX == nullX) && (clickY - nullY == -150)) {
            //点击的为空方个上面 的方格
            button.move("DOWN");
        } else if (clickX - nullX == 150 && clickY == nullY) {
            button.move("LEFT");//点击的方格向右移动
        } else if (clickX - nullX == -150 && clickY == nullY) {
            button.move("RIGHT");
        } else {
            return;//不满足移动条件，不处理
        }

        //更新空方个的位置
        nullCell.setLocation(clickX, clickY);
        //拼图区界面重新绘制
        this.repaint(); //更新步数。将游戏状态去的步数重新根性
        STEP_NUM++;
        MainFramework.step.setText("步数：" + STEP_NUM);

        //判断当前游戏是否完成，若完成，给玩家一个提示
        if (this.isFinish()) { //弹出一个消息提示
            JOptionPane.showMessageDialog(this, "恭喜完成拼图 |步数:" + STEP_NUM+ "|时间 :"+MainFramework.minute+"分"+MainFramework.second +"秒｜");
            MainFramework.file.output(MainFramework.minute*60+MainFramework.second);
            //撤销每一个小方格上鼠标点击监听,让鼠标点击小方格不在起作用
            for (int i = 0; i < 11; i++) {
                cell[i].removeMouseListener(this);
            }//更新当前方格监听器的状态
            hasActionListener = false;
        }
    }

    private boolean isFinish(){
        for(int i=0;i<11;i++){
            int x = cell[i].getBounds().x;
            int y = cell[i].getBounds().y;
            if(y/150*4+x/150!=i){
                return false;
            }
        }
        return true;
    }



    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
