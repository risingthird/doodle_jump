import java.awt.Graphics;

import javax.swing.JPanel;

public class GameEndPanel extends JPanel{
	public void GameEndPanel(){}
	
	@Override
	public void paintComponents(Graphics g){
		g.drawString("You die", 500, 500);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
