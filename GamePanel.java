import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


// TODO 
// ow to refresh sticks
// Parameter optimization
// Add sound effect
// Add boosts and replay function
@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	private int score=0;
	private JLabel status=new JLabel("running");
	private JLabel scoreBoard = new JLabel("Score: "+score);
	private Player p;
	private ArrayList<GameComponent> components=new ArrayList<GameComponent>();
	private ArrayList<IronStick> sticks=new ArrayList<IronStick>();
	private IronStick currentStick;

	private Dimension ps=new Dimension(500,700);


	public GamePanel(){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(ps);
		this.add(status, BorderLayout.PAGE_START);
		this.add(scoreBoard,BorderLayout.PAGE_START);
		currentStick=new IronStick(200,650,0,0);
		p=new Player(200,500,0,10,currentStick);

		components.add(p);
		initStick(currentStick);
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					p.setxSpeed(-10);
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					p.setxSpeed(10);
				//else if (e.getKeyCode() == KeyEvent.VK_UP)
				//p.setySpeed(-20);
				//p.startJumping();
			}

			public void keyReleased(KeyEvent e) {
				p.setxSpeed(0);
			}
		});

		Timer timer = new Timer(60, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();
	}

	void tick(){
		if(p.getyPos()>850) p.setAlive(false);

		if(isAlive()){

			p.move();
			for(IronStick i: sticks){
				i.move(); 
				if(i.isAtSideEdge(this)) i.setxSpeed(-i.getxSpeed());
				p.bounceStick(i);
			}

			for(IronStick a: sticks){
				a.setySpeed(0);
			}
			
			//refresh the sticks 
			if(currentStick!=p.getCurrentStick()){
				
				score+=50;
				scoreBoard.setText(score+"");
				
				
				sticks.remove(currentStick);
				components.remove(currentStick);
				
				currentStick=p.getCurrentStick();
				
				Iterator<IronStick> iter= sticks.iterator();
				while(iter.hasNext()){
					IronStick temp=iter.next();
					if(temp.getyPos()>currentStick.getyPos()){
						iter.remove();
					}
					//else{
					//temp.setyPos(temp.getyPos()+rolldown);
				}
				
				addTop();
				/**
				 * for(IronStick a: sticks){
						a.setySpeed(50);
					}
				 */
				
				//p.setyPos(p.getyPos()+rolldown);
			}
			
			if(currentStick.getyPos()<670){
				for(IronStick a: sticks){
					a.setySpeed(50);
				}
			}

			//addTop();
			repaint();
		}
		else{
			status.setText("You die");
		}
	}

	public void initStick(IronStick currentStick){
		IronStick newstick;
		int x=currentStick.getxPos();
		int y=currentStick.getyPos();
		int dx=0;
		int dy=0;
		int newX=x;
		int newY=y;
		components.add(currentStick);
		sticks.add(currentStick);
		do{
			dy = -(int)(Math.random() * 20 + 60);
			newY=y+dy;
			dx = (int)((2*Math.random()-1)*100);
			newX= x+dx;
			if(newX < 450 && newX > 40 && Math.abs(x-newX) < 75 && Math.abs(x-newX) > 25 
					&& dy < -60 && dy > -80) {
				newstick=new IronStick(newX,newY,0,0);
				x=newX;
				y=newY;
				sticks.add(newstick);
				components.add(newstick);
				System.out.println(newX+newY+"");
			}
		} while( y > 50 );
	}


	public void addTop(){
		IronStick topStick=sticks.get(sticks.size()-1);
		IronStick newstick;
		int x=topStick.getxPos();
		int y=topStick.getyPos();
		int dx=0;
		int dy=0;
		int newX=x;
		int newY=y;
		do{
			dy = -(int)(Math.random() * 20 + 60);
			newY=y+dy;
			dx = (int)((2*Math.random()-1)*100);
			newX= x+dx;
			if(newX < 450 && newX > 40 && Math.abs(x-newX) < 75 && Math.abs(x-newX) > 25 
					&& dy < -60 && dy > -80) {
				newstick=new IronStick(newX,newY,0,0);
				x=newX;
				y=newY;
				sticks.add(newstick);
				components.add(newstick);
				//System.out.println(newX+newY+"");

			}
		} while( y > 50 );
	}

	/** 
	 * public boolean StickOverlap(IronStick a){
		if(sticks.isEmpty())
			return false;
		for(int i=0;i<sticks.size();i++){
			if(Math.abs(a.getyPos()-sticks.get(i).getyPos())>100)
				break;
			else if(a.getyPos()==sticks.get(i).getyPos())
				if((a.getxPos()+a.getxSize()>=sticks.get(i).getxPos()) && (sticks.get(i).getxPos()>=a.getxPos()))
					return true;
				else if((a.getxPos()<=sticks.get(i).getxPos()+sticks.get(i).getxSize()) && (a.getxPos()>=sticks.get(i).getxPos()))
					return true;
		}
		return false;
	}
	 * @param a
	 * @return
	 */


	@Override
	public void paintComponent(Graphics g) {
		if(isAlive()){
			super.paintComponent(g);
			p.draw(g);

			for(IronStick a:sticks){
				
				a.draw(g);
			}
		}
	}

	public boolean isAlive(){
		return p.isAlive();
	}

	//test the game panel
	public static void main(String[] args){
		JFrame j=new JFrame();
		GamePanel p=new GamePanel();
		j.setExtendedState(JFrame.NORMAL);
		p.setVisible(true);
		j.add(p, BorderLayout.CENTER);
		j.setVisible(true);

		j.pack();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
