import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InstructionPanel extends JPanel {
	private Dimension ps= new Dimension(500,700);
	
	
	public InstructionPanel(){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(ps);
		this.setFocusable(true);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString("You need to jump on the moving sticks and reach the highest level!",45,100);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			JFrame jf=new JFrame();
			JPanel jp=new InstructionPanel();
			jf.add(jp);
			jf.setVisible(true);
			jf.setSize(500, 700);
			jf.setDefaultCloseOperation(3);
	}

}
