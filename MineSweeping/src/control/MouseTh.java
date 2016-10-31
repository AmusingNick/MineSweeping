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
	private int ifWillShow;       //�Ƿ�����ı�־,����Ϊ-1������Ϊ0
	private GraphicsJPaenlBackground ThD;
	private int state4; 
	private long startTime;
	//�Ҽ��궨״̬:����Ϊ1,͹Ϊ2,�ʺ�Ϊ3;
	private static Image imqi = Toolkit.getDefaultToolkit().getImage("image//�궨//��.gif");
	private static Image imtu = Toolkit.getDefaultToolkit().getImage("image//�궨//͹.gif");
	private static Image imwen = Toolkit.getDefaultToolkit().getImage("image//�궨//��.gif");
	private static Image imshu0 = Toolkit.getDefaultToolkit().getImage("image//����//0.gif");
	private static Image imshu1 = Toolkit.getDefaultToolkit().getImage("image//����//1.gif");
	private static Image imshu2 = Toolkit.getDefaultToolkit().getImage("image//����//2.gif");
	private static Image imshu3 = Toolkit.getDefaultToolkit().getImage("image//����//3.gif");
	private static Image imshu4 = Toolkit.getDefaultToolkit().getImage("image//����//4.gif");
	private static Image imshu5 = Toolkit.getDefaultToolkit().getImage("image//����//5.gif");
	private static Image imshu6 = Toolkit.getDefaultToolkit().getImage("image//����//6.gif");
	private static Image imzha = Toolkit.getDefaultToolkit().getImage("image//��//ը.gif");
	private static Image imku = Toolkit.getDefaultToolkit().getImage("image//����.PNG");
	private static Image imxiaoxia = Toolkit.getDefaultToolkit().getImage("image//��Ц��.PNG");
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
			//�Ƚ������- - - �Ƴ���
			ThD.getJjj().getJp3().removeAll();
			ThD.getJjj().getJp3().updateUI();
			startTime = System.currentTimeMillis();
			ThD.setJp3timer(new Jp3Timer(ThD.getJjj().getJp3(),startTime)); 
			ThD.getJp3timer().start();
		}
			//����ǵ�һ�ε���Ļ�,��ô�Ϳ�����ʱ
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
				//�����������������
				ThD.getCompleteDegree().UpdateflagRandom(clickingLine, clickingRow, "LEFT");
				willChangeLabel(ra,willChange.getName(),willChange,e);
				//�ڲ���������Ӻ�,���ߵ���������,������Ϸ�н������ж�;
			}
			if(e.getModifiers()==4&&willChange!=null&&(ThD.getCompleteDegree().isCheckPointIsNoClick(clickingLine, clickingRow)||ThD.getCompleteDegree().isCheckPointIsRight(clickingLine, clickingRow))){
				//�Ҽ��궨�������:����,͹,�ʺ�
				//�����������,͹,�ʺ�֮������ת��
				//�ж��Ƿ��Ѿ������Ϊ����,��Ϊ����,����ת��!!!
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
						//�ı�ͼ��,�ı�������
						willChange.setIcon(iqi);
						//�ı������ܲ����������
						ThD.setCountiqi(ThD.getCountiqi()+1);
						ThD.getJjj().ChangeJp1(ThD.getCountiqi());
						ThD.getCompleteDegree().UpdateflagRandom(clickingLine, clickingRow, "RIGHTTH");
						//�ڲ���������Ӻ�,���ߵ���������,������Ϸ�н������ж�;
						System.out.println("������flag:");
						ThD.getCompleteDegree().ShowFlag();
						System.out.println(ThD.getCompleteDegree().isCheckGameComplete(ra));
						if(AboutTh.GetThs(ra)==ThD.getCountiqi()){
							//��ʾͨ��,Ц����ɴ�Ц
							ImageIcon icxiaoxia = new ImageIcon(imxiaoxia);
							ThD.getJjj().getJlFace().setIcon(icxiaoxia);
							ThD.getJp3timer().getTimer().cancel();
							int newTime = (int)((System.currentTimeMillis()-startTime)/1000);
							JOptionPane.showMessageDialog(null, "���,������ͨ����,����ʱ"+(newTime)+"��~"); 
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
				//������ͳ��,��������ӵĵط�������&&û���ʺ�&&û��͹&&������>1
				//��������������,��ô˵��ͨ��,���Ц��.
			}
		}
	}
	
	private void willChangeLabel(RandomArray ra,String clickingName,JLabel willChange,MouseEvent e){
		//����clickingName:�������
		//����RandomArray�ı��������������
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
		//�����Ǹ���randomArray�ж���ʲô������ʾ;
		int stateTh = randomArray[clickingLine][clickingRow];
		this.ifWillShow = ChangeLabel(stateTh,willChange);
		if(ifWillShow==-1){ //�����������
			//1.��ʾ������Ϣ
			ThD.getSat().Show();
			//2.��Ц����ɿ���
			ImageIcon icku = new ImageIcon(imku);
			ThD.getJjj().getJlFace().setIcon(icku);
			//3.��ͣ��ʱ��
			ThD.getJp3timer().getTimer().cancel();
			//4.ֹͣ����߳�
		}
		if(ifWillShow==0){  //���û������
			ThD.getSat().RealShowSome(clickingLine,clickingRow);
			//����ĺ������и�ڸ��,���ǿ��ܰ���0,����û��
			//Ȼ���ڶ��ǰ���(�������),��0,�����Ѿ���LEFTTH�ĵ��һ��
			for(int i=1;i<ThD.getCompleteDegree().getFlagRandom().length-1;i++)
				for(int j=1;j<ThD.getCompleteDegree().getFlagRandom().length-1;j++)
					if(ThD.getCompleteDegree().getFlagRandom()[i][j].equals("LEFTTH")&&ra.getRandomAs()[i][j]==0){
						ThD.getCompleteDegree().UpdateflagRandom(i, j, FlagRandomAlready.NOCLICK);
						ThD.getSat().RealShowSome(i,j);
					}
		}
	};
	
	public static int ChangeLabel(int stateTh,JLabel willChange){
		//stateTh:�ڼ�����RandomArray�е��ж�
		//willChange :��Ҫ�ı��JLabel
		if(stateTh==0){//0����
			ImageIcon iic = new ImageIcon(imshu0);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==999){ //1����
			ImageIcon iic = new ImageIcon(imshu1);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==2){  //2����
			ImageIcon iic = new ImageIcon(imshu2);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==3){  //3����
			ImageIcon iic = new ImageIcon(imshu3);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==4){  //4����
			ImageIcon iic = new ImageIcon(imshu4);
			willChange.setIcon(iic);
		}else if(stateTh==5){  //5����
			ImageIcon iic = new ImageIcon(imshu5);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==6){  //6����
			ImageIcon iic = new ImageIcon(imshu6);
			willChange.setIcon(iic);
			return 0;
		}else if(stateTh==1){  //��ը��
			ImageIcon iic = new ImageIcon(imzha);
			willChange.setIcon(iic);
			//Game Over ����ʾȫ����������Ϣ,�ڽ�����
			//JOptionPane.showMessageDialog(null, "Game Over");
			return -1;
		}
		return 0;
	}
	
	public static void ChangeLabelNoTh(int stateTh,JLabel willChange){
		//stateTh:�ڼ�����RandomArray�е��ж�
		//willChange :��Ҫ�ı��JLabel
		if(stateTh==0){//0����
			ImageIcon iic = new ImageIcon(imshu0);
			willChange.setIcon(iic);
		}else if(stateTh==999){ //1����
			ImageIcon iic = new ImageIcon(imshu1);
			willChange.setIcon(iic);
		}else if(stateTh==2){  //2����
			ImageIcon iic = new ImageIcon(imshu2);
			willChange.setIcon(iic);
		}else if(stateTh==3){  //3����
			ImageIcon iic = new ImageIcon(imshu3);
			willChange.setIcon(iic);
		}else if(stateTh==4){  //4����
			ImageIcon iic = new ImageIcon(imshu4);
			willChange.setIcon(iic);
		}else if(stateTh==5){  //5����
			ImageIcon iic = new ImageIcon(imshu5);
			willChange.setIcon(iic);
		}else if(stateTh==6){  //6����
			ImageIcon iic = new ImageIcon(imshu6);
			willChange.setIcon(iic);
		}
	}
}
