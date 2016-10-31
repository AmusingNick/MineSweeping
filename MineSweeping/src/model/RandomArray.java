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
					if(level.equals("初级")){
						RandomAs[i][j] = rd.nextInt(5);  //3/4是没雷
						if(RandomAs[i][j]!=0&&RandomAs[i][j]!=1)
							RandomAs[i][j] = 0;
					}else if(level.equals("中级")){
						RandomAs[i][j] = rd.nextInt(3);  //3/4是没雷
						if(RandomAs[i][j]!=0&&RandomAs[i][j]!=1)
							RandomAs[i][j] = 0;
					}
				}
			}
		}   //将line+2和row+2都随机
	}
	
	public int[][] getRealUseRandomArray(int notThLine,int notThRow){
		//周围的0是地图的边界,1代表有雷的地方,数字代表周围的雷的个数
		//特殊:999代表周围有1个雷
		//3带代表周围有3个雷,0代表周围没雷,
		//notThLine,notThLine代表 最初刚开始点的点一定不是雷的横纵坐标
		//如果notThLine和notThLine为-1,-1的话,那么就说明点得点不是第一次点的点
		if(notThLine!=-1&&notThRow!=-1)
			RandomAs[notThLine][notThRow] = 0;
		//以下为统计雷数
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
					//问题所在:标记只有雷1的地方,在下一回,会被当做雷记录.
					//加完了如果等于1,先让他等于999
				}
			}
		//显示雷区
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
		RandomArray ra = new RandomArray(5,5,"初级");
		ra.getRealUseRandomArray(2, 2);
		System.out.println(AboutTh.GetThs(ra));
	}
}
