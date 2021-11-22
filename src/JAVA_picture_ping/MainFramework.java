package JAVA_picture_ping;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MainFramework extends JFrame {

    private JButton start_button;
    private JButton tip_button;
    private JButton rank_button;
    private JTextField step;
    private JTextField time;

    public MainFramework(){
        Frame_init();
        upComponent();

    }

    private void Frame_init() {
        this.setTitle(Const.TITLE);
        this.setSize(Const.MAIN_F_W,Const.MAIN_F_H);
        this.setLocation(Const.MAIN_F_X,Const.MAIN_F_Y);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void upComponent(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(Const.GRID_R,Const.GRID_C));
        //创建5个区域，从左到右，为开始游戏，提示，排行榜;   步数，时间

        //左边的三个按钮
        JPanel ButtonPanel = new JPanel();
        //ButtonPanel.setBorder(new TitledBorder("按钮区"));//添加边框
        //ButtonPanel.setBackground(Color.BLUE);//设置背景色

        start_button = new JButton(Const.START_BUTTON);
        tip_button = new JButton(Const.TIP_BUTTON);
        rank_button = new JButton(Const.RANK_BUTTON);

        ButtonPanel.add(start_button);
        ButtonPanel.add(tip_button);
        ButtonPanel.add(rank_button);

        panel.add(ButtonPanel,BorderLayout.EAST);

        //右边的两个状态
        JPanel StatusPanel = new JPanel();
        //StatusPanel.setBorder(new TitledBorder(Const.STATUS_PANEL));
        //StatusPanel.setBackground(Color.BLUE);
        StatusPanel.setLayout(new GridLayout(Const.GRID_R,Const.GRID_C));

        //步长
        step = new JTextField(Const.STEP+ 0);
        step.setEditable(false);

        //时间
        time = new JTextField(Const.TIME+0);
        step.setEditable(false);

        StatusPanel.add(step,BorderLayout.WEST);
        StatusPanel.add(time,BorderLayout.EAST);

        panel.add(StatusPanel,BorderLayout.WEST);

        this.add(panel,BorderLayout.NORTH);
    }

}
