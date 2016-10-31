package control;


import java.awt.Point;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JLabel;

import model.FlagRandomAlready;
import model.RandomArray;

public class ShowTh {
	private RandomArray rd = new RandomArray();
	private JLabel[] jls;
	private LinkedBlockingQueue<Point> sPoints = new LinkedBlockingQueue<Point>();
	private FlagRandomAlready completeDegree;
	public ShowTh(RandomArray rd, JLabel[] jls,FlagRandomAlready completeDegree) {
		super();
		this.rd = rd;
		this.jls = jls;
		this.completeDegree = completeDegree;
	}
	public void Show(){
		//将二维的随机数组转换成一维的随机数组,以便进行Change
		int [][] rdss = rd.getRandomAs();
		int []rds = new int[jls.length+1];
		int sub = 0;
		for(int i=1;i<rdss.length-1;i++){
			for(int j=1;j<rdss[i].length-1;j++){
				rds[j+sub] = rdss[i][j];
			}
			sub += rdss[i].length-2;		
		}
		for(int i=0;i<jls.length;++i)
			rds[i] = rds[i+1];
		//显示一维数组
		for(int i=0;i<jls.length;++i)
			System.out.print(rds[i]+" ");
		//Change
		for(int i=0;i<jls.length;i++){
			MouseTh.ChangeLabel(rds[i], jls[i]);
		}
	}
	
	public void RealShowSome(int line,int row){
		//bug,对于中级和,高级不好使,
		//目测是ShowSome里面对应什么的没整明白
		if(!completeDegree.isCheckPointComplete(line,row)){
			//大前提是:没被点击过或者栈不空
			while(ifCanShowSome(rd, line, row)||!sPoints.isEmpty()){  
				ShowSome(line,row);
				int [] beside = getLineRowBedide(line,row,rd);
				for(int i=0;i<beside.length;i++){
					Point p = new Point(beside[i],beside[++i]);
					if(!completeDegree.isCheckPointComplete((int)p.getY(), (int)p.getX())&&ifCanShowSome(rd, line, row))
						sPoints.add(p);
					if(!isTH(p))
						completeDegree.UpdateflagRandom((int)p.getY(), (int)p.getX(), FlagRandomAlready.LEFT);
				}
				completeDegree.UpdateflagRandom(line, row, FlagRandomAlready.LEFT);
				Point w = sPoints.poll();
				if(w!=null){
					line = (int)w.getY();
					row = (int)w.getX();
				}else break;
			}
		}	
	}
	
	public void ShowSome(int line,int row){
		/*
		 * ShowSome函数作用:判断一个点周围全部的点
		 * 如果都是0,1,2证明可以点开,那么返回0,否则返回-1
		 */
		int[] getBeside = getBeside(line,row,rd);
		//得到要改的地方的权
		//得到将要改变的几个JLabel的下标
		int[] getJLabelSub = getBesideJLabelSub(line, row, rd);
		for(int i=0;i<getBeside.length;i++)
			if(getJLabelSub[i]>=0&&getJLabelSub[i]<204)//这个时候能行 
				MouseTh.ChangeLabelNoTh(getBeside[i],jls[getJLabelSub[i]]);
	}
	
	public boolean isTH(Point p){
		//判断这个点是否是雷
		int line = (int)p.getY();
		int row = (int)p.getX();
		if(rd.getRandomAs()[line][row]==1) return true;
		else return false;
	}
	
	public static boolean ifCanShowSome(RandomArray ra,int line,int row){
		//判断已line和row为中心点的地方是否可以显示
		//int CountThisCanNumber = 0;
		int ad[][] = ra.getRandomAs();
		if(ad[line][row]==0)  return true;
		else return false;
		/*if(line>=1&&row>=1){
			if(ad[line-1][row-1]==999||ad[line-1][row-1]==0||ad[line-1][row-1]==2) CountThisCanNumber++;
			if(ad[line-1][row]==999||ad[line-1][row]==0||ad[line-1][row]==2)CountThisCanNumber++;
			if(ad[line-1][row+1]==999||ad[line-1][row+1]==0||ad[line-1][row+1]==2)CountThisCanNumber++;
			if(ad[line][row-1]==999||ad[line][row-1]==0||ad[line][row-1]==2)CountThisCanNumber++;
			if(ad[line][row+1]==999||ad[line][row+1]==0||ad[line][row+1]==2)CountThisCanNumber++;
			if(ad[line+1][row-1]==999||ad[line+1][row-1]==0||ad[line+1][row-1]==2)CountThisCanNumber++;
			if(ad[line+1][row]==999||ad[line+1][row]==0||ad[line+1][row]==2)CountThisCanNumber++;
			if(ad[line+1][row+1]==999||ad[line+1][row+1]==0||ad[line+1][row+1]==2)CountThisCanNumber++;
		}*/
		//这个是个专属秘籍if(CountThisCanNumber==8) return true;
	}
	
	public static int[] getBeside(int line ,int row,RandomArray rd){
		//传入一个line,row,获得他的旁边的line,row的随机值,存在一个数组中,返回
		int a[][] = rd.getRandomAs();
		int [] get;
		if(line==1&&row==1){
			//在角的雷区
			get = new int[3];
			get[0] = a[line][row+1];
			get[1] = a[line+1][row];
			get[2] = a[line+1][row+1];
		}else if((line==1)&&row==(rd.getRandomAs()[0].length-2)){
			get = new int[3];
			get[0] = a[line][row-1];
			get[1] = a[line+1][row];
			get[2] = a[line+1][row-1];
		}else if((line==(rd.getRandomAs().length-2))&&(row==1)){
			get = new int[3];
			get[0] = a[line-1][row];
			get[1] = a[line-1][row+1];
			get[2] = a[line][row+1];
		}else if(line==(rd.getRandomAs().length-2)&&row==(rd.getRandomAs()[0].length-2)){
			get = new int[3];
			get[0] = a[line-1][row-1];
			get[1] = a[line-1][row];
			get[2] = a[line][row-1];
		}else if(line==1){
			//在边界的雷区
			get = new int[5];
			get[0] = a[line][row-1];
			get[1] = a[line][row+1];
			get[2] = a[line+1][row-1];
			get[3] = a[line+1][row];
			get[4] = a[line+1][row+1];
		}else if(line==(rd.getRandomAs().length-2)){
			get = new int[5];
			get[0] = a[line-1][row-1];
			get[1] = a[line-1][row];
			get[2] = a[line-1][row+1];
			get[3] = a[line][row-1];
			get[4] = a[line][row+1];
		}else if(row==1){
			get = new int[5];
			get[0] = a[line-1][row];
			get[1] = a[line-1][row+1];
			get[2] = a[line][row+1];
			get[3] = a[line+1][row];
			get[4] = a[line+1][row+1];
		}else if(row==(rd.getRandomAs()[0].length-2)){
			get = new int[5];
			get[0] = a[line-1][row-1];
			get[1] = a[line-1][row];
			get[2] = a[line][row-1];
			get[3] = a[line+1][row-1];
			get[4] = a[line+1][row];
		}
		else{
			//不在边界的 
			get = new int[8];
			get[0] = a[line-1][row-1];
			get[1] = a[line-1][row];
			get[2] = a[line-1][row+1];
			get[3] = a[line][row-1];
			get[4] = a[line][row+1];
			get[5] = a[line+1][row-1];
			get[6] = a[line+1][row];
			get[7] = a[line+1][row+1];
		} 
		return get;
	}
	
	public static int[] getBesideJLabelSub(int line ,int row,RandomArray rd){
		//传入一个line,row,获得他的旁边的JLabel-line,row的下标,存在一个数组中,返回
		int [] get =new int[8];
		if(line==1&&row==1){
			//在角的雷区
			int JLabelSub5 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+1;
			int JLabelSub7 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2);
			int JLabelSub8 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2)+1;
			get[0] = JLabelSub5;
			get[1] = JLabelSub7;
			get[2] = JLabelSub8;
		}else if((line==1)&&row==(rd.getRandomAs()[0].length-2)){
			int JLabelSub4 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-1;
			int JLabelSub6 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2);
			int JLabelSub7 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2)-1;
			get[0] = JLabelSub4;
			get[1] = JLabelSub6;
			get[2] = JLabelSub7;
		}else if((line==(rd.getRandomAs().length-2))&&(row==1)){
			int JLabelSub2 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2);
			int JLabelSub3 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2)+1;
			int JLabelSub5 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+1;
			get[0] = JLabelSub2;
			get[1] = JLabelSub3;
			get[2] = JLabelSub5;
		}else if(line==(rd.getRandomAs().length-2)&&row==(rd.getRandomAs()[0].length-2)){
			int JLabelSub1 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2)-1;
			int JLabelSub2 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2);
			int JLabelSub4 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-1;
			get[0] = JLabelSub1;
			get[1] = JLabelSub2;
			get[2] = JLabelSub4;
		}else if(line==1){
			//在边界的雷区
			int JLabelSub4 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-1;
			int JLabelSub5 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+1;
			int JLabelSub6 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2)-1;
			int JLabelSub7 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2);
			int JLabelSub8 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2)+1;
			get[0] = JLabelSub4;
			get[1] = JLabelSub5;
			get[2] = JLabelSub6;
			get[3] = JLabelSub7;
			get[4] = JLabelSub8;
		}else if(line==(rd.getRandomAs().length-2)){
			int JLabelSub1 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2)-1;
			int JLabelSub2 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2);
			int JLabelSub3 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2)+1;
			int JLabelSub4 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-1;
			int JLabelSub5 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+1;
			get[0] = JLabelSub1;
			get[1] = JLabelSub2;
			get[2] = JLabelSub3;
			get[3] = JLabelSub4;
			get[4] = JLabelSub5;
		}else if(row==1){
			int JLabelSub2 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2);
			int JLabelSub3 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2)+1;
			int JLabelSub5 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+1;
			int JLabelSub7 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2);
			int JLabelSub8 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2)+1;
			get[0] = JLabelSub2;
			get[1] = JLabelSub3;
			get[2] = JLabelSub5;
			get[3] = JLabelSub7;
			get[4] = JLabelSub8;
		}else if(row==(rd.getRandomAs()[0].length-2)){
			int JLabelSub1 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2)-1;
			int JLabelSub2 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2);
			int JLabelSub4 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-1;
			int JLabelSub6 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2)-1;
			int JLabelSub7 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2);
			get[0] = JLabelSub1;
			get[1] = JLabelSub2;
			get[2] = JLabelSub4;
			get[3] = JLabelSub6;
			get[4] = JLabelSub7;
		}
		else{
			//不在边界的 
			int JLabelSub1 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2)-1;
			int JLabelSub2 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2);
			int JLabelSub3 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-(rd.getRandomAs()[0].length-2)+1;
			int JLabelSub4 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1-1;
			int JLabelSub5 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+1;
			int JLabelSub6 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2)-1;
			int JLabelSub7 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2);
			int JLabelSub8 = ((line-1)*(rd.getRandomAs()[0].length-2)+row)-1+(rd.getRandomAs()[0].length-2)+1;
			get[0] = JLabelSub1;
			get[1] = JLabelSub2;
			get[2] = JLabelSub3;
			get[3] = JLabelSub4;
			get[4] = JLabelSub5;
			get[5] = JLabelSub6;
			get[6] = JLabelSub7;
			get[7] = JLabelSub8;
		} 
		return get;
	}
	
	public static int[] getLineRowBedide(int line,int row,RandomArray rd){
		/*
		 *传入一个line,row,将他旁边的line,row,值整进一个二维数组 
		 *包括自己本身
		 */
		int[] a;
		if(line==1&&row==1){
			//在角的雷区
			a = new int[6];
			a[1] = line ; a[0] = row+1;
			a[3] = line+1 ; a[2] = row;
			a[5] = line+1 ; a[4] = row+1;
		}else if((line==1)&&row==(rd.getRandomAs()[0].length-2)){
			a = new int[6];
			a[1] = line ; a[0] = row-1;
			a[3] = line+1 ; a[2] = row-1;
			a[5] = line+1 ; a[4] = row;
		}else if((line==(rd.getRandomAs().length-2))&&(row==1)){
			a = new int[6];
			a[1] = line-1 ; a[0] = row;
			a[3] = line-1 ; a[2] = row+1;
			a[5] = line ; a[4] = row+1;
		}else if(line==(rd.getRandomAs().length-2)&&row==(rd.getRandomAs()[0].length-2)){
			a = new int[6];
			a[1] = line-1 ; a[0] = row-1;
			a[3] = line-1 ; a[2] = row;
			a[5] = line ; a[4] = row-1;
		}else if(line==1){
			//在边界的雷区
			a = new int[10];
			a[1] = line ; a[0] = row-1;
			a[3] = line ; a[2] = row+1;
			a[5] = line+1 ; a[4] = row-1;
			a[7] = line+1 ; a[6] = row;
			a[9] = line+1 ; a[8] = row+1;
		}else if(line==(rd.getRandomAs().length-2)){
			a = new int[10];
			a[1] = line-1 ; a[0] = row-1;
			a[3] = line-1 ; a[2] = row;
			a[5] = line-1 ; a[4] = row+1;
			a[7] = line ; a[6] = row-1;
			a[9] = line ; a[8] = row+1;
		}else if(row==1){
			a = new int[10];
			a[1] = line-1 ; a[0] = row;
			a[3] = line-1 ; a[2] = row+1;
			a[5] = line ; a[4] = row+1;
			a[7] = line+1 ; a[6] = row;
			a[9] = line+1 ; a[8] = row+1;
		}else if(row==(rd.getRandomAs()[0].length-2)){
			a = new int[10];
			a[1] = line-1 ; a[0] = row-1;
			a[3] = line-1 ; a[2] = row;
			a[5] = line ; a[4] = row-1;
			a[7] = line+1 ; a[6] = row-1;
			a[9] = line+1 ; a[8] = row;
		}
		else{
			a = new int[16];
			a[1] = line-1 ; a[0] = row-1;
			a[3] = line-1 ; a[2] = row;
			a[5] = line-1 ; a[4] = row+1;
			a[7] = line ; a[6] = row-1;
			a[9] = line ; a[8] = row+1;
			a[11] = line+1 ; a[10] = row-1;
			a[13] = line+1 ; a[12] = row;
			a[15] = line+1 ; a[14] = row+1;
		}
		return a;
	}
}
