package JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.SelectLevel;

public class Index extends JFrame{
	/**
	 * ���������
	 * ����:�����
	 * ɨ��
	 */
	private static final long serialVersionUID = 1L;
	private Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
	private String level; //���׵ȼ�
	private JMenuBar jmb = new JMenuBar();  //ѡ�ť
	private ImageIcon ico = new ImageIcon("image//��//��.gif");
	private GraphicsJPaenlBackground jpDown;
	private ActionListener selectLevel = new SelectLevel(this);
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Dimension getScrSize() {
		return scrSize;
	}
	public void setScrSize(Dimension scrSize) {
		this.scrSize = scrSize;
	}
	public GraphicsJPaenlBackground getJpDown() {
		return jpDown;
	}
	public void setJpDown(GraphicsJPaenlBackground jpDown) {
		this.jpDown = jpDown;
	}
	public Index(String level){
		this.level = level;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ɨ����Ϸ");
		Dimension dSize = LeveltoJFrame(this.level);
		this.setSize((int)dSize.getHeight(),(int)dSize.getWidth());
		this.setLocation(((int)this.scrSize.getWidth()-(int)dSize.getWidth())/2, ((int)this.scrSize.getHeight()-(int)dSize.getHeight())/2);
		this.setIconImage(this.ico.getImage());
		jpDown = new GraphicsJPaenlBackground(this);
		setLayoutUp();
		setLayoutDown();
		this.setVisible(true);
		this.setResizable(false);
	}
	public Dimension LeveltoJFrame(String level){
		Dimension dms = new Dimension(0,0);//�����С
		//ͨ���ȼ��ж�����Ϸ�����׳̶�
		if(level.equals("����")){
			dms.setSize(278, 278);
		}else if(level.equals("�м�")){
			dms = new Dimension(375,375);
		}else if(level.equals("�߼�")){
			dms = new Dimension(598,598);
		}
		return dms;
	}
	public void setIndexBounds(Dimension d){
		//�����½����ȼ������,�ȼ��ı��,
		//�ı��С,λ��,ɾ����ǰ����,����������
		this.setSize((int)d.getHeight(),(int)d.getWidth());
		this.setLocation(((int)this.scrSize.getWidth()-(int)d.getWidth())/2, ((int)this.scrSize.getHeight()-(int)d.getHeight())/2);
		this.remove(jpDown);
		jpDown = new GraphicsJPaenlBackground(this);
		this.add(jpDown);
	}
	private void setLayoutUp(){
		//�Ϸ��������Ĳ���
		jmb.setSize(this.getWidth(), 20);
		JMenu jm1 = new JMenu("ѡ��(Q)");
		JMenuItem newPlayer = new JMenuItem("����    Crtl+q");
		JMenuItem MediumPlayer = new JMenuItem("�м�    Crtl+w");
		JMenuItem AdvancedPlayer = new JMenuItem("�߼�    Crtl+e");
		newPlayer.addActionListener(selectLevel);
		MediumPlayer.addActionListener(selectLevel);
		AdvancedPlayer.addActionListener(selectLevel);
		jm1.add(newPlayer);
		jm1.add(MediumPlayer);
		jm1.add(AdvancedPlayer);
		jm1.setMnemonic('q');
		jmb.add(jm1);
		this.setJMenuBar(jmb);
	}
	private void setLayoutDown(){
		this.add(jpDown);
	}
	public static void main(String[] args){
		new Index("����");  //Ĭ��Ϊ������ɨ��
	}
}
