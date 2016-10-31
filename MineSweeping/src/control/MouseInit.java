package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import JFrame.Index;

public class MouseInit extends MouseAdapter{
	private Index id;
	public MouseInit(Index id){
		this.id = id;
	}
	public void mouseClicked(MouseEvent e){
		String level = id.getLevel();
		id.dispose();
		id = new Index(level);
	}
}
