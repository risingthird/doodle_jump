import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStart extends JFrame {
		private JPanel _jp;
		private Dimension ps= new Dimension(500,700);
		private JButton start;
		private JButton instruction;
		private Container c=this.getContentPane();
	
	
		public GameStart(){
			//this.setBackground(Color.WHITE);
			this.setSize(ps);
			_jp= new JPanel();
			_jp.setBackground(Color.WHITE);
			start=new JButton("START");
			start.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					_jp= new GamePanel();
					c.removeAll();
					c.add(_jp);
					//c.setFocusable(true);
				}
			});
			instruction=new JButton("INSTRUCTION");
			instruction.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					_jp= new InstructionPanel();
					c.removeAll();
					c.add(_jp);
					//c.setFocusable(true);
				}
			});
			_jp.add(start);
			_jp.add(instruction);
			c.add(_jp);
			this.setDefaultCloseOperation(3);
			this.setVisible(true);
		}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameStart panel=new GameStart();
	}
			
}
