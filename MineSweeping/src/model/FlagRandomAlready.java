package model;

public class FlagRandomAlready {
	/*
	 * �궨������ɵ��������,�Ƿ��Ѿ����������,
	 *��ʼ���Ϊ:Random��С��ȫ0����
	 */
	private String [][]flagRandom;
	private static String[] flagRandomState = {
			"LEFTTH","RIGHTTH","RIGHTUNKNOW","NOCLICK"};
	//�������λ��
	public static String LEFT = "LEFT";
	public static String RIGHT = "RIGHT";
	public static String RIGHTTH = "RIGHTTH";
	public static String RIGHTUNKNOW = "RIGHTUNKNOW";
	public static String NOCLICK = "NOCLICK";
	
	public String[][] getFlagRandom() {
		return flagRandom;
	}

	public void setFlagRandom(String[][] flagRandom) {
		this.flagRandom = flagRandom;
	}

	public FlagRandomAlready(RandomArray ra){
		//flagRandomAlready�ĳ�ʼ������
		flagRandom = new String[ra.getRandomAs().length][ra.getRandomAs()[0].length];
		for(int i=1;i<ra.getRandomAs().length-1;i++)
			for(int j=1;j<ra.getRandomAs()[i].length-1;j++)
				flagRandom[i][j] = flagRandomState[3];
	}
	
	public String[][] UpdateflagRandom(int line,int row,String clickAttribute){
		/*
		 * ����һ��line,row�ĵ���ص�,�͵���������(���,�Ҽ�)
		 *Ȼ�󷵻�һ���Ѿ����µ�flagRandom
		 */
		for(int i=1;i<flagRandom.length-1;i++)
			for(int j=1;j<flagRandom[i].length-1;j++){
				if(i==line&&j==row){
					if(clickAttribute.equals(FlagRandomAlready.LEFT))
						flagRandom[i][j] = flagRandomState[0];
					if(clickAttribute.equals(FlagRandomAlready.RIGHTTH))
						flagRandom[i][j] = flagRandomState[1];
					if(clickAttribute.equals(FlagRandomAlready.RIGHTUNKNOW))
						flagRandom[i][j] = flagRandomState[2];
					if(clickAttribute.equals(FlagRandomAlready.NOCLICK))
						flagRandom[i][j] = flagRandomState[3];
				}
			}
		return flagRandom;
	}
	
	public boolean isCheckPointComplete(int line,int row){
		//���������Ƿ��Ѿ�����:
		if(flagRandom[line][row].equals(flagRandomState[3])) return false;
		else return true; 
	}
	
	public boolean isCheckPointIsNoClick(int line,int row){
		//���������Ƿ��Ѿ�����:
		if(flagRandom[line][row].equals(flagRandomState[3])) return true;
		else return false; 
	}
	
	public boolean isCheckPointIsRight(int line,int row){
		//���������Ƿ��Ѿ�����:
		if(flagRandom[line][row].equals(flagRandomState[1])||flagRandom[line][row].equals(flagRandomState[2])) return true;
		else return false; 
	}
	
	public boolean isCheckGameComplete(RandomArray ra){
		for(int i=1;i<ra.getRandomAs().length-1;i++)
			for(int j=1;j<ra.getRandomAs().length-1;j++){
				if(!(ra.getRandomAs()[i][j]!=1&&flagRandom[i][j].equals("LEFT")))
					//û�׶�Ӧ���
					return false;
				if(!(ra.getRandomAs()[i][j]==1&&flagRandom[i][j].equals(flagRandomState[1])))
					//���׶�Ӧ����Ҽ�
					return false;
			}
		//����������������Ļ�,����true
		return true;
	}
	
	public void ShowFlag(){
		for(int i=1;i<flagRandom.length-1;i++){
			for(int j=1;j<flagRandom[i].length-1;j++)
				System.out.print(flagRandom[i][j]+"\t");
			System.out.println();
		}
	};
}
