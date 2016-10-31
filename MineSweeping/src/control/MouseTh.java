package control;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.AboutTh;
import model.FlagRandomAlready;
import model.RandomArray;
import JFrame.GraphicsJPaenlBackground;
import Timer.Jp3Timer;

public class MouseTh extends MouseAdapter{
	private int row;
	private RandomArray ra;
	private int ifWillShow;       //是否结束的标志,结束为-1不结束为0
	private GraphicsJPaenlBackground ThD;
	private int state4; 
	private long startTime;
	//右键标定状态:旗子为1,凸为2,问号为3;
	private static Image imqi = Toolkit.getDefaultToolkit().getImage("image//标定//旗.gif");
	private static Image imtu = Toolkit.getDefaultToolkit().getImage("image//标定//凸.gif");
	private static Image imwen = Toolkit.getDefaultToolkit().getImage("image//标定//问.gif");
	private static Image imshu0 = Toolkit.getDefaultToolkit().getImage("image//数字//0.gif");
	private static Image imshu1 = Toolkit.getDefaultToolkit().getImage("image//数字//1.gif");
	private static Image imshu2 = Toolkit.getDefaultToolkit().getImage("image//数字//2.gif");
	private static Image imshu3 = Toolkit.getDefaultToolkit().getImage("image//数字//3.gif");
	private static Image imshu4 = Toolkit.getDefaultToolkit().getImage("image//数字//4.gif");
	private static Image imshu5 = Toolkit.getDefaultToolkit().getImage("image//数字//5.gif");
	private static Image imshu6 = Toolkit.getDefaultToolkit().getImage("image//数字//6.gif");
	private static Image imzha = Toolkit.getDefaultToolkit().getImage("image//雷//炸.gif");
	private static Image imku = Toolkit.getDefaultToolkit().getImage("image//脸哭.PNG");
	private static Image imxiaoxia = Toolkit.getDefaultToolkit().getImage("image//脸笑下.PNG");
	public int getIfWillShow() {
		return ifWillShow;
	}
	public void setIfWillShow(int ifWillShow) {
		this.ifWillShow = ifWillShow;
	}
	public MouseTh(int row,RandomArray ra,GraphicsJPaenlBackground ThD) {
		super();
		this.row = row;
		this.ra = ra;
		this.ThD = ThD;
	}
	public void mouseClicked(MouseEvent e){
		if(ThD.getClickNumber()==0){
			//先将上面的- - - 移除了
			ThD.getJjj().getJp3().removeAll();
			ThD.getJjj().getJp3().updateUI();
			startTime = System.currentTimeMillis();
			ThD.setJp3timer(new Jp3Timer(ThD.getJjj().getJp3(),startTime)); 
			ThD.getJp3timer().start();
		}
			//如果是第一次点击的话,那么就开启计时
		int name = Integer.parseInt((((JLabel)e.getSource()).getName()));
		int clickingLine = (name/row+1);
		int clickingRow = (name%row+1);
		if(ThD.getCompleteDegree().isCheckPointIsNoClick(clickingLine, clickingRow)||ThD.getCompleteDegree().isCheckPointIsRight(clickingLine, clickingRow)){
			Object obj = e.getSource();
			JLabel willChange =null;
			if(obj instanceof JLabel){
				willChange = (JLabel)obj;
			}
			if(e.getModifiers()==16&&willChange!=null){
				//左键点击生成雷区情况
				ThD.getCompleteDegree().UpdateflagRandom(clickingLine, clickingRow, "LEFT");
				willChangeLabel(ra,willChange.getName(),willChange,e);
				//在插完最后旗子后,或者点完雷区后,进行游戏有结束的判断;
			}
			if(e.getModifiers()==4&&willChange!=null&&(ThD.getCompleteDegree().isCheckPointIsNoClick(clickingLine, clickingRow)||ThD.getCompleteDegree().isCheckPointIsRight(clickingLine, clickingRow))){
				//右键标定雷区情况:旗子,凸,问号
				//点击后在旗子,凸,问号之间来回转换
				//判断是否已经点击成为雷区,成为雷区,不必转换!!!
				ImageIcon iqi = new ImageIcon(imqi);
				ImageIcon iwen = new ImageIcon(imwen);
				ImageIcon itu = new ImageIcon(imtu);
				if(ThD.getJjj().canChangeJp1(ThD.getCountiqi())==false){
					if(state4==1)
						state4 =2;
				}else
					state4++;
				if(state4==1){
					if(ThD.getJjj().canChangeJp1(ThD.getCountiqi())==true){
						//改变图形,改变旗子数
						willChange.setIcon(iqi);
						//改变上面能插的总旗子数
						ThD.setCountiqi(ThD.getCountiqi()+1);
						ThD.getJjj().ChangeJp1(ThD.getCountiqi());
						ThD.getCompleteDegree().UpdateflagRandom(clickingLine, clickingRow, "RIGHTTH");
						//在插完最后旗子后,或者点完雷区后,进行游戏有结束的判断;
						System.out.println("下面是flag:");
						ThD.getCompleteDegree().ShowFlag();
						System.out.println(ThD.getCompleteDegree().isCheckGameComplete(ra));
						if(AboutTh.GetThs(ra)==ThD.getCountiqi()){
							//显示通关,笑脸变成大笑
							ImageIcon icxiaoxia = new ImageIcon(imxiaoxia);
							ThD.getJjj().getJlFace().setIcon(icxiaoxia);
							ThD.getJp3timer().getTimer().cancel();
							int newTime = (int)((System.currentTimeMillis()-startTime)/1000);
							JOptionPane.showMessageDialog(null, "大哥,你终于通关了,共用时"+(newTime)+"秒~"); 
						}
					}
				}
				if(state4==2){
					if(ThD.getJjj().canChangeJp1(ThD.getCountiqi())==true||ThD.getCountiqi()==(AboutTh.GetThs(ra))){
						willChange.setIcon(iwen);
						ThD.setCountiqi(ThD.getCountiqi()-1);
						ThD.getJjj().ChangeJp1(ThD.getCountiqi());
						ThD.getCompleteDegree().UpdateflagRandom(clickingLine, clickingRow, "RIGHTUNKNOW");
					}
				}
				if(state4==3){
					willChange.setIcon(itu);
					state4=0;
					ThD.getCompleteDegree().UpdateflagRandom(clickingLine, clickingRow, "NOCLICK");
				}
				//最后进行统计,如果有旗子的地方都有雷&&没有问号&&没有凸&&旗子数>1
				//如果满足这个条件,那么说明通关,变成笑脸.
			}
		}
	}
	
	private void willChangeLabel(RandomArray ra,String clickingName,JLabel willChange,MouseEvent e){
		//传入clickingName:点击的雷
		//根据RandomArray改变点击的雷区的外观
		int clickingNameInt = Integer.parseInt(clickingName);
		int clickingLine = clickingNameInt/row+1;
		int clickingRow = clickingNameInt%row+1;
		int [][] randomArray = new int[][]{};
		if(ThD.getClickNumber()==0){
			ThD.setClickNumber(999);
			randomArray = ra.getRealUseRandomArray(clickingLine, clickingRow);
		}else {
			randomArray = ra.getRealUseRandomArray(-1, -1);
		}   
		//以下是根据randomArray判定是什么进行显示;
		int stateTh = randomArray[clickingLine][clickingRow];
		this.ifWillShow = ChangeLabel(stateTh,willChange);
		if(ifWillShow==-1){ //如果碰到了雷
			//1.显示雷区信息
			ThD.getSat().Show();
			//2.将笑脸变成哭脸
			ImageIcon icku = new ImageIcon(imku);
			ThD.getJjj().getJlFace().setIcon(icku);
			//3.暂停计时器
			ThD.getJp3timer().getTimer().cancel();
			//4.停止这个线程
		}
		if(ifWillShow==0){  //如果没碰到雷
			ThD.getSat().RealShowSome(clickingLine,clickingRow);
			//上面的函数会有个诟病,就是可能把是0,但是没点
			//然后在对是凹点(点击完了),是0,但是已经置LEFTTH的点击一遍
			for(int i=1;i<ThD.getCompleteDegree().getFlagRandom().length-1;i++)
				for(int j=1;j<ThD.getCompleteDegree().getFlagRandom().length-1;j++)
					if(ThD.getCompleteDegree().getFlagRandom()[i][j].equals("LEFTTH")&&ra.getRandomAs()[i][j]==0){
						ThD.getCompleteDegree().UpdateflagRandom(i, j, FlagRandomAlready.NOCLICK);
						ThD.getSat().RealShowSome(i,j);
					}
		}
	};
	
	public static int ChangeLabel(int stateTh,JLabel willChange){
		//stateTh:第几个雷RandomArray中的判定
		//willChange :将要改变的JLabel
		if(stateTh==0){//0个雷
			ImageIcon iic = new ImageIcon(imshu0);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==999){ //1个雷
			ImageIcon iic = new ImageIcon(imshu1);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==2){  //2个雷
			ImageIcon iic = new ImageIcon(imshu2);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==3){  //3个雷
			ImageIcon iic = new ImageIcon(imshu3);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==4){  //4个雷
			ImageIcon iic = new ImageIcon(imshu4);
			willChange.setIcon(iic);
		}else if(stateTh==5){  //5个雷
			ImageIcon iic = new ImageIcon(imshu5);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==6){  //6个雷
			ImageIcon iic = new ImageIcon(imshu6);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==1){  //大炸弹
			ImageIcon iic = new ImageIcon(imzha);
			willChange.setIcon(iic);
			//Game Over 后显示全部的雷区信息,在界面上
			//JOptionPane.showMessageDialog(null, "Game Over");
			return -1;
		}
		return 0;
	}
	
	public static void ChangeLabelNoTh(int stateTh,JLabel willChange){
		//stateTh:第几个雷RandomArray中的判定
		//willChange :将要改变的JLabel
		if(stateTh==0){//0个雷
			ImageIcon iic = new ImageIcon(imshu0);
			willChange.setIcon(iic);
		}else if(stateTh==999){ //1个雷
			ImageIcon iic = new ImageIcon(imshu1);
			willChange.setIcon(iic);
		}else if(stateTh==2){  //2个雷
			ImageIcon iic = new ImageIcon(imshu2);
			willChange.setIcon(iic);
		}else if(stateTh==3){  //3个雷
			ImageIcon iic = new ImageIcon(imshu3);
			willChange.setIcon(iic);
		}else if(stateTh==4){  //4个雷
			ImageIcon iic = new ImageIcon(imshu4);
			willChange.setIcon(iic);
		}else if(stateTh==5){  //5个雷
			ImageIcon iic = new ImageIcon(imshu5);
			willChange.setIcon(iic);
		}else if(stateTh==6){  //6个雷
			ImageIcon iic = new ImageIcon(imshu6);
			willChange.setIcon(iic);
		}
	}
}
