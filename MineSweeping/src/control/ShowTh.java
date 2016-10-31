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
		//����ά���������ת����һά���������,�Ա����Change
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
		//��ʾһά����
		for(int i=0;i<jls.length;++i)
			System.out.print(rds[i]+" ");
		//Change
		for(int i=0;i<jls.length;i++){
			MouseTh.ChangeLabel(rds[i], jls[i]);
		}
	}
	
	public void RealShowSome(int line,int row){
		//bug,�����м���,�߼�����ʹ,
		//Ŀ����ShowSome�����Ӧʲô��û������
		if(!completeDegree.isCheckPointComplete(line,row)){
			//��ǰ����:û�����������ջ����
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
		 * ShowSome��������:�ж�һ������Χȫ���ĵ�
		 * �������0,1,2֤�����Ե㿪,��ô����0,���򷵻�-1
		 */
		int[] getBeside = getBeside(line,row,rd);
		//�õ�Ҫ�ĵĵط���Ȩ
		//�õ���Ҫ�ı�ļ���JLabel���±�
		int[] getJLabelSub = getBesideJLabelSub(line, row, rd);
		for(int i=0;i<getBeside.length;i++)
			if(getJLabelSub[i]>=0&&getJLabelSub[i]<204)//���ʱ������ 
				MouseTh.ChangeLabelNoTh(getBeside[i],jls[getJLabelSub[i]]);
	}
	
	public boolean isTH(Point p){
		//�ж�������Ƿ�����
		int line = (int)p.getY();
		int row = (int)p.getX();
		if(rd.getRandomAs()[line][row]==1) return true;
		else return false;
	}
	
	public static boolean ifCanShowSome(RandomArray ra,int line,int row){
		//�ж���line��rowΪ���ĵ�ĵط��Ƿ������ʾ
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
		//����Ǹ�ר���ؼ�if(CountThisCanNumber==8) return true;
	}
	
	public static int[] getBeside(int line ,int row,RandomArray rd){
		//����һ��line,row,��������Աߵ�line,row�����ֵ,����һ��������,����
		int a[][] = rd.getRandomAs();
		int [] get;
		if(line==1&&row==1){
			//�ڽǵ�����
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
			//�ڱ߽������
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
			//���ڱ߽�� 
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
		//����һ��line,row,��������Աߵ�JLabel-line,row���±�,����һ��������,����
		int [] get =new int[8];
		if(line==1&&row==1){
			//�ڽǵ�����
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
			//�ڱ߽������
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
			//���ڱ߽�� 
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
		 *����һ��line,row,�����Աߵ�line,row,ֵ����һ����ά���� 
		 *�����Լ�����
		 */
		int[] a;
		if(line==1&&row==1){
			//�ڽǵ�����
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
			//�ڱ߽������
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
