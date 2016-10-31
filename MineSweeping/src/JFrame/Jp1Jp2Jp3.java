package JFrame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.MouseInit;

public class Jp1Jp2Jp3 {
	private JPanel jpUp;
	private GraphicsJPaenlBackground gjb;
	private int allCountiqi;
	private JLabel jl0 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//0.PNG")));
	private JLabel jl1 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//1.PNG")));
	private JLabel jl2 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//2.PNG")));
	private JLabel jl3 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//3.PNG")));
	private JLabel jl4 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//4.PNG")));
	private JLabel jl5 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//5.PNG")));
	private JLabel jl6 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//6.PNG")));
	private JLabel jl7 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//7.PNG")));
	private JLabel jl8 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//8.PNG")));
	private JLabel jl9 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//9.PNG")));
	private JLabel Copyjl0 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//0.PNG")));
	private JLabel Copyjl1 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//1.PNG")));
	private JLabel Copyjl2 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//2.PNG")));
	private JLabel Copyjl3 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//3.PNG")));
	private JLabel Copyjl4 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//4.PNG")));
	private JLabel Copyjl5 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//5.PNG")));
	private JLabel Copyjl6 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//6.PNG")));
	private JLabel Copyjl7 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//7.PNG")));
	private JLabel Copyjl8 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//8.PNG")));
	private JLabel Copyjl9 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//9.PNG")));
	private JLabel hong_ge = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//-.PNG")));
	private JLabel hong_shi = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//-.PNG")));
	private JLabel hong_bai = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//红//-.PNG")));
	private JPanel jp1 = new JPanel();
	private JLabel jlFace = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//脸笑.PNG")));
	private JPanel jp3 = new JPanel();
	
	public JPanel getJp3() {
		return jp3;
	}

	public void setJp3(JPanel jp3) {
		this.jp3 = jp3;
	}

	public GraphicsJPaenlBackground getGjb() {
		return gjb;
	}

	public void setGjb(GraphicsJPaenlBackground gjb) {
		this.gjb = gjb;
	}

	public JLabel getJlFace() {
		return jlFace;
	}

	public void setJlFace(JLabel jlFace) {
		this.jlFace = jlFace;
	}
	public Jp1Jp2Jp3(){}
	public Jp1Jp2Jp3(JPanel j,GraphicsJPaenlBackground gjb,int allQi){
		this.gjb = gjb;
		jpUp = j;
		allCountiqi = allQi;
		//剩余插旗数
		ChangeJp1(0);
		//中间的笑脸
		MouseAdapter mouseHappy = new MouseInit(this.gjb.getI());
		jlFace.addMouseListener(mouseHappy);
		//计时器
		jp3.add(hong_bai);
		jp3.add(hong_shi);
		jp3.add(hong_ge);
		//设置背景色
		jp1.setBackground(Color.cyan);
		jp3.setBackground(Color.cyan);
		jpUp.setBackground(Color.cyan);
		//设置上面三个Jp1,Jp2,Jp3的整体布局
		jpUp.setLayout(new GridLayout(1,3));
		jpUp.add(jp1,0);
		jpUp.add(jlFace,1);
		jpUp.add(jp3,2);
	}
	
	public boolean canChangeJp1(int alreadyHaveQi){
		int shouldShowQi = allCountiqi-alreadyHaveQi;//应当显示的旗子数目
		if(shouldShowQi>0) return true;
		else  return false;
	}
	public void ChangeJp1(int alreadyHaveQi){
		//传入已经有在图中插入的旗子数目
		//返回一个剩余插旗子数目
		int shouldShowQi = allCountiqi-alreadyHaveQi;//应当显示的旗子数目
		int shouldShowQiUnitsDigit = shouldShowQi%10;
		int shouldShowQiTensDigit = shouldShowQi/10;
		jp1.setLayout(new FlowLayout());
		if(alreadyHaveQi!=0){
			jp1.removeAll();
		}
		if(shouldShowQiUnitsDigit!=shouldShowQiTensDigit){
			//十位,个位变化  :不相等的时候
			for(int i=0;i<digitToJLabel.length;i++)
				if((int)digitToJLabel[i][0]==shouldShowQiTensDigit)
					jp1.add((JLabel)digitToJLabel[i][1]);
			for(int i=0;i<digitToJLabel.length;i++)
				if((int)digitToJLabel[i][0]==shouldShowQiUnitsDigit)
					jp1.add((JLabel)digitToJLabel[i][1]);
		}else {
			//相等的时候
			for(int i=0;i<digitToJLabel.length;i++)
				if((int)digitToJLabel[i][0]==shouldShowQiTensDigit){
					jp1.add((JLabel)digitToJLabel[i][1]);
					jp1.add((JLabel)digitToJLabel[i][2]);
				}
		}
		jp1.updateUI();
	}
	private Object [][]digitToJLabel = {
			{0,this.jl0,this.Copyjl0},
			{1,this.jl1,this.Copyjl1},
			{2,this.jl2,this.Copyjl2},
			{3,this.jl3,this.Copyjl3},
			{4,this.jl4,this.Copyjl4},
			{5,this.jl5,this.Copyjl5},
			{6,this.jl6,this.Copyjl6},
			{7,this.jl7,this.Copyjl7},
			{8,this.jl8,this.Copyjl8},
			{9,this.jl9,this.Copyjl9}};
}
