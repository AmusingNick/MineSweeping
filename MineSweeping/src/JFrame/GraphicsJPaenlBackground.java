package JFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Timer.Jp3Timer;
import model.AboutTh;
import model.FlagRandomAlready;
import model.RandomArray;
import control.MouseTh;
import control.ShowTh;

public class GraphicsJPaenlBackground extends JPanel{
	/**
	 * 画出下方的大框图形类
	 */
	private static final long serialVersionUID = 1L;
	private Index i;
	private Image imtu = Toolkit.getDefaultToolkit().getImage("image//标定//凸.gif");
	private Jp1Jp2Jp3 jjj = new Jp1Jp2Jp3();
	private JPanel jpUp = new JPanel();
	private JPanel jpDown = new JPanel();
	private int ClickNumber;    //标定第一次一定不是雷
	private int Countiqi;       //标定的旗子数
	private ShowTh sat;
	private FlagRandomAlready completeDegree;  //完成度数组(根据Random生成),只有当这个数组全是1或者是999的时候.才能判定游戏是否结束
	private Jp3Timer jp3timer;
	
	public Jp3Timer getJp3timer() {
		return jp3timer;
	}
	public void setJp3timer(Jp3Timer jp3timer) {
		this.jp3timer = jp3timer;
	}
	public FlagRandomAlready getCompleteDegree() {
		return completeDegree;
	}
	public void setCompleteDegree(FlagRandomAlready completeDegree) {
		this.completeDegree = completeDegree;
	}
	public Jp1Jp2Jp3 getJjj() {
		return jjj;
	}
	public void setJjj(Jp1Jp2Jp3 jjj) {
		this.jjj = jjj;
	}
	public int getCountiqi() {
		return Countiqi;
	}
	public void setCountiqi(int countiqi) {
		Countiqi = countiqi;
	}
	public Index getI() {
		return i;
	}
	public void setI(Index i) {
		this.i = i;
	}
	public int getClickNumber() {
		return ClickNumber;
	}
	public void setClickNumber(int clickNumber) {
		ClickNumber = clickNumber;
	}
	
	public ShowTh getSat() {
		return sat;
	}
	public void setSat(ShowTh sat) {
		this.sat = sat;
	}
	public GraphicsJPaenlBackground(){}
	public GraphicsJPaenlBackground(Index i){
		this.i = i;
		this.setLayout(new BorderLayout());
		setLayoutUp();
		setLayoutDown();
		this.add(jpUp,BorderLayout.NORTH);
		this.add(jpDown,BorderLayout.CENTER);
	}
	private void setLayoutUp(){
		//扫雷界面的剩余旗子数目,是否完成,计数时间的上方标题栏
		//jjj = new Jp1Jp2Jp3(jpUp,this);
		//都在下面的setLayout中了
	}
	private void setLayoutDown(){
		//扫雷界面的主要游戏栏目
		jpDown.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		ImageIcon imi = new ImageIcon(imtu);
		int countRowNumber = this.i.getWidth()/imi.getIconWidth();
		int countLineNumber = this.i.getHeight()/imi.getIconHeight()-5;
		//控制器
		RandomArray ra = new RandomArray(countLineNumber,countRowNumber,"初级");
		JLabel []jltu = new JLabel[countLineNumber*countRowNumber];
		for(int j=0;j<countLineNumber*countRowNumber;j++){
			jltu[j] = new JLabel(imi);
			jltu[j].setName(String.valueOf(j));  //setName,是用来在mouseTh中获取正在点击的点的坐标
			MouseAdapter mouseTh = new MouseTh(countRowNumber,ra,this);
			jltu[j].addMouseListener(mouseTh);
			jpDown.add(jltu[j]);
		}
		jjj = new Jp1Jp2Jp3(jpUp,this,AboutTh.GetThs(ra));
		completeDegree = new FlagRandomAlready(ra);
		sat = new ShowTh(ra,jltu,completeDegree);
	}
}
