import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class IronStick extends GameComponent implements Movable{
	private static final int TYPE=1;

	private boolean tobedeleted=false;
	public int getType(){
		return 1;
	}
	
	public IronStick(int xPos, int yPos, int xSpeed, int ySpeed) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		//this.icon = playerIcon;
		//xSize=playerIcon.getWidth(null);
		//ySize=playerIcon.getHeight(null);
		xSize=70;
		ySize=5;

	}
	
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		xPos+=xSpeed;
		yPos+=ySpeed;
	}

	@Override
	public void draw(Graphics g){
		g.setColor(Color.black);
		if(this.tobedeleted!=true)
			g.fillRect(xPos, yPos, xSize,ySize);
	}

	
	public void relocate(int distance){
		yPos-=distance;
	}    // to make the sticks move
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}








	

}
