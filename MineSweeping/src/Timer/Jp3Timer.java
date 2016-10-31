package Timer;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TimeGo extends TimerTask{ 
	private JPanel j;
	private long startTime;
	private static JLabel jlge0 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//0.PNG")));
	private static JLabel jlge1 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//1.PNG")));
	private static JLabel jlge2 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//2.PNG")));
	private static JLabel jlge3 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//3.PNG")));
	private static JLabel jlge4 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//4.PNG")));
	private static JLabel jlge5 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//5.PNG")));
	private static JLabel jlge6 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//6.PNG")));
	private static JLabel jlge7 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//7.PNG")));
	private static JLabel jlge8 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//8.PNG")));
	private static JLabel jlge9 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//9.PNG")));
	private static JLabel jlshi0 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//0.PNG")));
	private static JLabel jlshi1 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//1.PNG")));
	private static JLabel jlshi2 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//2.PNG")));
	private static JLabel jlshi3 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//3.PNG")));
	private static JLabel jlshi4 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//4.PNG")));
	private static JLabel jlshi5 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//5.PNG")));
	private static JLabel jlshi6 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//6.PNG")));
	private static JLabel jlshi7 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//7.PNG")));
	private static JLabel jlshi8 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//8.PNG")));
	private static JLabel jlshi9 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//9.PNG")));
	private static JLabel jlbai0 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//0.PNG")));
	private static JLabel jlbai1 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//1.PNG")));
	private static JLabel jlbai2 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//2.PNG")));
	private static JLabel jlbai3 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//3.PNG")));
	private static JLabel jlbai4 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//4.PNG")));
	private static JLabel jlbai5 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//5.PNG")));
	private static JLabel jlbai6 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//6.PNG")));
	private static JLabel jlbai7 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//7.PNG")));
	private static JLabel jlbai8 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//8.PNG")));
	private static JLabel jlbai9 = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//ºì//9.PNG")));
	private static Object [][]jlToNewTime={
		{0,jlge0,jlshi0,jlbai0},
		{1,jlge1,jlshi1,jlbai1},
		{2,jlge2,jlshi2,jlbai2},
		{3,jlge3,jlshi3,jlbai3},
		{4,jlge4,jlshi4,jlbai4},
		{5,jlge5,jlshi5,jlbai5},
		{6,jlge6,jlshi6,jlbai6},
		{7,jlge7,jlshi7,jlbai7},
		{8,jlge8,jlshi8,jlbai8},
		{9,jlge9,jlshi9,jlbai9}};
	public TimeGo(JPanel j,long startTime){
		this.j = j;
		this.startTime = startTime;
	}
	@Override
	public void run(){
		long endTime = System.currentTimeMillis();
		long newTime = endTime-startTime;
		int newIntTime = (int) (newTime/1000);
		int shouldShowge = newIntTime%10;
		int shouldShowshi = (newIntTime%100)/10;
		int shouldShowbai = newIntTime/100;
		j.removeAll();
		for(int i=0;i<jlToNewTime.length;i++)
			if((int)jlToNewTime[i][0]==shouldShowbai)
				j.add((JLabel)jlToNewTime[i][3]);
		for(int i=0;i<jlToNewTime.length;i++)
			if((int)jlToNewTime[i][0]==shouldShowshi)
				j.add((JLabel)jlToNewTime[i][2]);
		for(int i=0;i<jlToNewTime.length;i++)
			if((int)jlToNewTime[i][0]==shouldShowge)
				j.add((JLabel)jlToNewTime[i][1]);
		j.updateUI();
	}
}

public class Jp3Timer extends Thread{
	private JPanel j;
	private long startTime;
	private Timer timer = new Timer();
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public Jp3Timer(JPanel j,long startTime){
		this.j = j;
		this.startTime = startTime;
	}
	public void run(){
		timer.schedule(new TimeGo(j,startTime), 0,1000);
	}
}
