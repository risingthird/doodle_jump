
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 1 is the constant downward acceleration
 * @author htc & wjp
 * The class is the player and 
 */



public class Player extends GameComponent implements Movable{
	private static Image img;
	private int acc;
	private boolean isAlive;
	private boolean canJump=true;
	private IronStick currentStick;
   
	
	private static final int NATURAL_ACC=3;
	private int jump_times=0; // when the player jumps 5 time to the middle of the window, we start refreshing the sticks
	
	public Player(int xPos, int yPos, int xSpeed, int ySpeed, IronStick a) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.currentStick=a;
		img=new ImageIcon("/Users/wangjiaping/Downloads/Alien_58.456216216216px_1197953_easyicon.net.png").getImage();
		
		xSize=20;
		ySize=20;
		acc=0;
		type=0;
		isAlive=true;
		shape="oval";
	}

	public void bounceStick(GameComponent obj){
		if(this.willIntersect(obj) && this.getySpeed()>0) {this.setySpeed(0); this.startJumping(); currentStick=(IronStick) obj;}
		if(this.willIntersect(obj) && this.getySpeed()<0) {this.setySpeed(0);}
	}

	public boolean isAlive() {
		return isAlive;
	}

	public IronStick getCurrentStick() {
		return currentStick;
	}

	public void setCurrentStick(IronStick currentStick) {
		this.currentStick = currentStick;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		xPos+=xSpeed;
		yPos+=ySpeed;
		//ySpeed+=acc;
		if(ySpeed<=MAX_SPEEDY)
			ySpeed+=NATURAL_ACC;
	}

	public void startJumping(){
		//acc<0 jump upward 
		canJump=false;
		this.setySpeed(-18);
	}
	@Override
	public void draw(Graphics g){
		g.drawImage(img, xPos, yPos, xSize, ySize, null);
	}

	public int getAcc() {
		return acc;
	}
	public void setAcc(int acc) {
		this.acc = acc;
	}
	
	public int getJumptimes_PLAYER(){
		return jump_times;
	}
	
	public void update_jumptimes(){
		this.jump_times++;
	}
	
	//unit testing
	public static void main(String[] args){
		@SuppressWarnings("serial")
		class TestPanel extends JPanel{
			Player p;
			IronStick a;
			public TestPanel(){
				this.setBackground(Color.GRAY);
				this.setSize(1000,1000);
				
				a=new IronStick(500,700,0,0);
				p=new Player(500,500,0,0,a);
				Timer timer = new Timer(30, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tick();
					}
				});
				timer.start();
			}

			void tick(){
				
				p.move();
				p.bounceStick(a);
				repaint();	
			}
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				p.draw(g);
				a.draw(g);
			}


		}

		JFrame j=new JFrame();
		TestPanel t=new TestPanel();
		j.add(t);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
