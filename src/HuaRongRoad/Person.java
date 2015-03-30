package HuaRongRoad;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;

class Person extends JButton implements FocusListener {

	Color c = new Color(255, 245, 170);
	int PersonNum;
	
	Person(int PersonNum, String PersonName){
		super(PersonName);
		this.PersonNum = PersonNum;
		setBackground(this.c);
		this.c = getBackground();
		addFocusListener(this);
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		setBackground(Color.red);
	}

	@Override
	public void focusLost(FocusEvent e) {
		setBackground(this.c);
	}
	
	
	
}