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
	 * �����·��Ĵ��ͼ����
	 */
	private static final long serialVersionUID = 1L;
	private Index i;
	private Image imtu = Toolkit.getDefaultToolkit().getImage("image//�궨//͹.gif");
	private Jp1Jp2Jp3 jjj = new Jp1Jp2Jp3();
	private JPanel jpUp = new JPanel();
	private JPanel jpDown = new JPanel();
	private int ClickNumber;    //�궨��һ��һ��������
	private int Countiqi;       //�궨��������
	private ShowTh sat;
	private FlagRandomAlready completeDegree;  //��ɶ�����(����Random����),ֻ�е��������ȫ��1������999��ʱ��.�����ж���Ϸ�Ƿ����
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
		//ɨ�׽����ʣ��������Ŀ,�Ƿ����,����ʱ����Ϸ�������
		//jjj = new Jp1Jp2Jp3(jpUp,this);
		//���������setLayout����
	}
	private void setLayoutDown(){
		//ɨ�׽������Ҫ��Ϸ��Ŀ
		jpDown.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		ImageIcon imi = new ImageIcon(imtu);
		int countRowNumber = this.i.getWidth()/imi.getIconWidth();
		int countLineNumber = this.i.getHeight()/imi.getIconHeight()-5;
		//������
		RandomArray ra = new RandomArray(countLineNumber,countRowNumber,"����");
		JLabel []jltu = new JLabel[countLineNumber*countRowNumber];
		for(int j=0;j<countLineNumber*countRowNumber;j++){
			jltu[j] = new JLabel(imi);
			jltu[j].setName(String.valueOf(j));  //setName,��������mouseTh�л�ȡ���ڵ���ĵ������
			MouseAdapter mouseTh = new MouseTh(countRowNumber,ra,this);
			jltu[j].addMouseListener(mouseTh);
			jpDown.add(jltu[j]);
		}
		jjj = new Jp1Jp2Jp3(jpUp,this,AboutTh.GetThs(ra));
		completeDegree = new FlagRandomAlready(ra);
		sat = new ShowTh(ra,jltu,completeDegree);
	}
}
