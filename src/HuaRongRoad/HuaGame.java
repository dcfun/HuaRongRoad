package HuaRongRoad;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class HuaGame extends Frame implements MouseListener, ActionListener {

	Person[] person = new Person[10];
	int step;
	int w;
	
	Button left = new Button();
	Button right = new Button();
	Button above = new Button();
	Button below = new Button();
	
	Button restart = new Button("重新开始");
	Button exit = new Button("退出");
	Button one = new Button("横刀立马1");	
	Button two = new Button("横刀立马2");
	Button three = new Button("齐头并进");
	Button four = new Button("兵分三路");
	Button five = new Button("屯兵东路");
	
	JLabel stepLabel = new JLabel("步数:");
	JLabel stepNumLabel = new JLabel("0");

	public static void main(String[] args) {
		new HuaGame(1);
	}

	public HuaGame(int model) {
		
		initPerson();
		initGUI();
		choose(model);
	}
	
	
	private void initGUI() {
		setLayout(null);
		setTitle("华容道");
		setBounds(100, 100, 450, 360);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent x){
				System.exit(0);
			}
		});
		
		add(this.left);
		this.left.setBounds(49, 49, 5, 260);
		add(this.right);
		this.right.setBounds(254, 49, 5, 260);
		add(this.above);
		this.above.setBounds(49, 49, 210, 5);
		add(this.below);
		this.below.setBounds(49, 304, 210, 5);
		
		add(this.restart);
	    this.restart.setBounds(50, 320, 100, 25);
	    this.restart.addActionListener(this);
	    add(this.exit);
	    this.exit.setBounds(160, 320, 100, 25);
	    this.exit.addActionListener(this);
	    
	    add(this.one);
	    this.one.setBounds(300, 50, 100, 25);
	    this.one.addActionListener(this);
	    add(this.two);
	    this.two.setBounds(300, 80, 100, 25);
	    this.two.addActionListener(this);
	    add(this.three);
	    this.three.setBounds(300, 110, 100, 25);
	    this.three.addActionListener(this);
	    add(this.four);
	    this.four.setBounds(300, 140, 100, 25);
	    this.four.addActionListener(this);
	    add(this.five);
	    this.five.setBounds(300, 170, 100, 25);
	    this.five.addActionListener(this);
	    
	    add(this.stepLabel);
	    this.stepLabel.setBounds(300, 200, 35, 25);
	    add(this.stepNumLabel);
	    this.stepNumLabel.setBounds(335, 200, 30, 25);
	    
	}

	public void initPerson() {
		setLayout(null);

		String[] arrayOfString = { "曹操", "关羽", "马超", "黄忠", "张飞", "赵云", "兵", "兵", "兵", "兵" };
		for (int i = 0; i < arrayOfString.length; i++) {
			this.person[i] = new Person(i, arrayOfString[i]);
			this.person[i].addMouseListener(this);
			add(this.person[i]);
		}

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		Person CurrentButton = (Person) e.getSource();
		
		int x = e.getX();
		int y = e.getY();
		int width = CurrentButton.getBounds().width;
		int height = CurrentButton.getBounds().height;
		
		if (x < width/2) {
			move(CurrentButton, this.left);
		}
		if (x > width/2) {
			move(CurrentButton, this.right);
		}
		if (y < height/2) {
			move(CurrentButton, this.above);
		}
		if (y > height/2) {
			move(CurrentButton, this.below);
		}
	}
	
	public void move(Person CurrentPerson, Button paramButton) {
		boolean goon = true;
		Rectangle localRectangle1 = CurrentPerson.getBounds();
		int x = CurrentPerson.getBounds().x;
		int y = CurrentPerson.getBounds().y;
		if (paramButton == this.below) {
			y += 50;
		} else if (paramButton == this.above) {
			y -= 50;
		} else if (paramButton == this.left) {
			x -= 50;
		} else if (paramButton == this.right) {
			x += 50;
		}
		localRectangle1.setLocation(x, y);
		Rectangle localRectangle2 = paramButton.getBounds();
		for (int m = 0; m < 10; m++) {
			Rectangle localRectangle3 = this.person[m].getBounds();
			if ((localRectangle1.intersects(localRectangle3)) && (CurrentPerson.PersonNum != m)) {
				goon = false;
			}
		}
		if (localRectangle1.intersects(localRectangle2)) {
			goon = false;
		}
		if (goon) {
			this.step += 1;
			this.stepNumLabel.setText(this.step + "");
			CurrentPerson.setLocation(x, y);

			if ((this.person[0].getBounds().x == 104) && (this.person[0].getBounds().y == 204)) {
				finish();
			}
		}
	}
	
	
	public void finish() {
		JOptionPane.showMessageDialog(this, "君乃重义气之人 保重 ！");
		new HuaGame(this.w);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Button CurrentButton = (Button) e.getSource();
		
		if (CurrentButton == this.restart) {
			new HuaGame(1);
			dispose();			
		}else if (CurrentButton == this.exit) {
			System.exit(0);
		}
		else if (CurrentButton == this.one){
			choose(1);
		}else if (CurrentButton == this.two){
			choose(2);
		}else if (CurrentButton == this.three){
			choose(3);
		}else if (CurrentButton == this.four){
			choose(4);
		}else if (CurrentButton == this.five){
			choose(5);
		}

	}


	public void choose(int paramInt) {
		switch (paramInt) {
		case 1:
			this.w = 1;
			this.person[0].setBounds(104, 54, 100, 100);
			this.person[1].setBounds(104, 154, 100, 50);
			this.person[2].setBounds(54, 154, 50, 100);
			this.person[3].setBounds(204, 154, 50, 100);
			this.person[4].setBounds(54, 54, 50, 100);
			this.person[5].setBounds(204, 54, 50, 100);
			this.person[6].setBounds(54, 254, 50, 50);
			this.person[7].setBounds(204, 254, 50, 50);
			this.person[8].setBounds(104, 204, 50, 50);
			this.person[9].setBounds(154, 204, 50, 50);
			break;
		case 2:
			this.w = 2;
			this.person[0].setBounds(104, 54, 100, 100);
			this.person[1].setBounds(104, 154, 100, 50);
			this.person[2].setBounds(54, 204, 50, 100);
			this.person[3].setBounds(204, 204, 50, 100);
			this.person[4].setBounds(54, 54, 50, 100);
			this.person[5].setBounds(204, 54, 50, 100);
			this.person[6].setBounds(54, 154, 50, 50);
			this.person[7].setBounds(204, 154, 50, 50);
			this.person[8].setBounds(104, 204, 50, 50);
			this.person[9].setBounds(154, 204, 50, 50);
			break;
		case 3:
			this.w = 3;
			this.person[0].setBounds(104, 54, 100, 100);
			this.person[1].setBounds(104, 204, 100, 50);
			this.person[2].setBounds(54, 204, 50, 100);
			this.person[3].setBounds(204, 204, 50, 100);
			this.person[4].setBounds(54, 54, 50, 100);
			this.person[5].setBounds(204, 54, 50, 100);
			this.person[6].setBounds(54, 154, 50, 50);
			this.person[7].setBounds(204, 154, 50, 50);
			this.person[8].setBounds(104, 154, 50, 50);
			this.person[9].setBounds(154, 154, 50, 50);
			break;
		case 4:
			this.w = 4;
			this.person[0].setBounds(104, 54, 100, 100);
			this.person[1].setBounds(104, 154, 100, 50);
			this.person[2].setBounds(54, 204, 50, 100);
			this.person[3].setBounds(204, 204, 50, 100);
			this.person[4].setBounds(54, 104, 50, 100);
			this.person[5].setBounds(204, 104, 50, 100);
			this.person[6].setBounds(54, 54, 50, 50);
			this.person[7].setBounds(204, 54, 50, 50);
			this.person[8].setBounds(104, 204, 50, 50);
			this.person[9].setBounds(154, 204, 50, 50);
			break;
		case 5:
			this.w = 5;
			this.person[0].setBounds(54, 54, 100, 100);
			this.person[1].setBounds(54, 154, 100, 50);
			this.person[2].setBounds(54, 204, 50, 100);
			this.person[3].setBounds(104, 204, 50, 100);
			this.person[4].setBounds(154, 54, 50, 100);
			this.person[5].setBounds(204, 54, 50, 100);
			this.person[6].setBounds(154, 154, 50, 50);
			this.person[7].setBounds(204, 154, 50, 50);
			this.person[8].setBounds(154, 204, 50, 50);
			this.person[9].setBounds(204, 204, 50, 50);
		}
	}

}
