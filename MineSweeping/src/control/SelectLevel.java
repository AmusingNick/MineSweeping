package control;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import JFrame.Index;

public class SelectLevel implements ActionListener{
	private Index i;
	public SelectLevel(Index i){
		this.i = i;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JMenuItem){
			JMenuItem jmi = (JMenuItem)obj;
			if(jmi.getText().equals("初级    Crtl+q")){
				IndexSet("初级");
			}else if(jmi.getText().equals("中级    Crtl+w")){
				IndexSet("中级");
			}else if(jmi.getText().equals("高级    Crtl+e")){
				IndexSet("高级");
			}
		}
	}

	private void IndexSet(String level){
		Dimension d = this.i.LeveltoJFrame(level);
		this.i.setLevel(level);
		this.i.setIndexBounds(d);
	}	
}
