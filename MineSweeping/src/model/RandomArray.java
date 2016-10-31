package model;

import java.util.Random;

public class RandomArray {
	private int [][]RandomAs;
	
	public int[][] getRandomAs() {
		return RandomAs;
	}

	public void setRandomAs(int[][] randomAs) {
		RandomAs = randomAs;
	}
	public RandomArray(){}
	public RandomArray(int line,int row,String level){
		this.RandomAs = new int[line+2][row+2];
		Random rd = new Random();
		for(int i=0;i<RandomAs.length;i++){
			for(int j=0;j<RandomAs[i].length;j++){
				if(i!=0&i!=line+1&&j!=0&&j!=row+1){
					if(level.equals("����")){
						RandomAs[i][j] = rd.nextInt(5);  //3/4��û��
						if(RandomAs[i][j]!=0&&RandomAs[i][j]!=1)
							RandomAs[i][j] = 0;
					}else if(level.equals("�м�")){
						RandomAs[i][j] = rd.nextInt(3);  //3/4��û��
						if(RandomAs[i][j]!=0&&RandomAs[i][j]!=1)
							RandomAs[i][j] = 0;
					}
				}
			}
		}   //��line+2��row+2�����
	}
	
	public int[][] getRealUseRandomArray(int notThLine,int notThRow){
		//��Χ��0�ǵ�ͼ�ı߽�,1�������׵ĵط�,���ִ�����Χ���׵ĸ���
		//����:999������Χ��1����
		//3��������Χ��3����,0������Χû��,
		//notThLine,notThLine���� ����տ�ʼ��ĵ�һ�������׵ĺ�������
		//���notThLine��notThLineΪ-1,-1�Ļ�,��ô��˵����õ㲻�ǵ�һ�ε�ĵ�
		if(notThLine!=-1&&notThRow!=-1)
			RandomAs[notThLine][notThRow] = 0;
		//����Ϊͳ������
		for(int i=1;i<RandomAs.length-1;i++)
			for(int j=1;j<RandomAs[i].length-1;j++){
				if(RandomAs[i][j]==0){
					if(RandomAs[i-1][j-1]==1)
						RandomAs[i][j]+=RandomAs[i-1][j-1];
					if(RandomAs[i-1][j]==1)
						RandomAs[i][j]+=RandomAs[i-1][j];
					if(RandomAs[i-1][j+1]==1)
						RandomAs[i][j]+=RandomAs[i-1][j+1];
					
					if(RandomAs[i][j-1]==1)
						RandomAs[i][j]+=RandomAs[i][j-1];
					if(RandomAs[i][j+1]==1)
						RandomAs[i][j]+=RandomAs[i][j+1];
					
					if(RandomAs[i+1][j-1]==1)
						RandomAs[i][j]+=RandomAs[i+1][j-1];
					if(RandomAs[i+1][j]==1)
						RandomAs[i][j]+=RandomAs[i+1][j];
					if(RandomAs[i+1][j+1]==1)
						RandomAs[i][j]+=RandomAs[i+1][j+1];
					if(RandomAs[i][j]==1)
						RandomAs[i][j]=999;
					//��������:���ֻ����1�ĵط�,����һ��,�ᱻ�����׼�¼.
					//�������������1,����������999
				}
			}
		//��ʾ����
		for(int i=0;i<RandomAs.length;i++){
			for(int j=0;j<RandomAs[i].length;j++){
				System.out.print(RandomAs[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("---------------------");
		return RandomAs;
	}
	
	public static void main(String[] args) {
		RandomArray ra = new RandomArray(5,5,"����");
		ra.getRealUseRandomArray(2, 2);
		System.out.println(AboutTh.GetThs(ra));
	}
}
