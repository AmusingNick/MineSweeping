package model;

public class AboutTh {
	public static int GetThs(RandomArray ra){
		//���������ɾ������׵ĸ���
		int countTh = 0;
		for(int i=1;i<ra.getRandomAs().length-1;i++)
			for(int j=1;j<ra.getRandomAs()[i].length-1;j++)
				if(ra.getRandomAs()[i][j]==1)
					countTh++;
		return countTh;
	}
}
