
import java.awt.Container;

import java.awt.Graphics;
import java.awt.Image;



public abstract class GameComponent implements Movable{

	protected int type; //0 for player 1 for glass stick 2 for iron stick
	protected String shape;
	protected int xPos;
	protected int yPos;
	protected int xSpeed;
	protected int ySpeed;
	protected int xSize;
	protected int ySize;
	public int max_x;
	public int max_y;
	
	protected final static int MAX_SPEEDX=100;
	protected final static int MAX_SPEEDY=100;
	
	
	public int getMax_x() {
		return max_x;
	}
	public void setMax_x(int max_x) {
		this.max_x = max_x;
	}
	public int getMax_y() {
		return max_y;
	}
	public void setMax_y(int max_y) {
		this.max_y = max_y;
	}

	protected Image icon;
	public int getType(){return type;}
	public int getxPos() {
		return xPos;
	}

	public String getShapeName(){
		return shape;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public Image getIcon() {
		return icon;
	}
	public void setPlayerIcon(Image playerIcon) {
		this.icon = playerIcon;
	}

	
	public boolean isAtBottomEdge(Container p) {
		// TODO Auto-generated method stub
		return yPos>=p.getHeight();
	}

	public boolean isAtSideEdge(Container p){
		return xPos<=0 || xPos>=p.getWidth()-xSize;
	}
	
	
	public boolean intersects(GameComponent obj){			// while it returns true, it is actually not intersecet
		return ((xPos + xSize >= obj.xPos
				&&obj.xPos + obj.xSize >= xPos) && (yPos + ySize >= obj.yPos
				&& obj.yPos + obj.ySize >= yPos));
	}

	public void bounce(Direction d) {
		if (d == null) return;
		switch (d) {
		case UP:    ySpeed = Math.abs(ySpeed); break;  
		case DOWN:  ySpeed = -Math.abs(ySpeed); break;
		case LEFT:  xSpeed = Math.abs(xSpeed); break;
		case RIGHT: xSpeed = -Math.abs(xSpeed); break;
		}
	}
	//stub TODO


	public boolean willIntersect(GameComponent obj){
		int next_x = xPos + xSpeed;
		int next_y = yPos + ySpeed;
		int next_obj_x = obj.xPos + obj.xSpeed;
		int next_obj_y = obj.yPos + obj.ySpeed;
		return ((next_x + xSize >= next_obj_x
				&& next_obj_x + obj.xSize >= next_x)
				&& (next_y + ySize >= next_obj_y
				&& next_obj_y + obj.ySize >= next_y));
	}

	public Direction hitObj(GameComponent other) {
		if (this.willIntersect(other)) {
			double dx = other.xPos + other.xSize /2 - (xPos + xSize /2);
			double dy = other.yPos + other.ySize/2 - (yPos + ySize/2);

			double theta = Math.acos(dx / (Math.sqrt(dx * dx + dy *dy)));
			double diagTheta = Math.atan2(ySize / 2, xSize / 2);

			if (theta <= diagTheta ) {
				return Direction.RIGHT;
			} else if ( theta > diagTheta && theta <= Math.PI - diagTheta ) {
				if ( dy > 0 ) {
					// Coordinate system for GUIs is switched
					return Direction.DOWN;
				} else {
					return Direction.UP;
				}
			} else {
				return Direction.LEFT;
			}
		} else {
			return null;
		}

	}

	public int getxSize() {
		return xSize;
	}
	public void setxSize(int xSize) {
		this.xSize = xSize;
	}
	public int getySize() {
		return ySize;
	}
	public void setySize(int ySize) {
		this.ySize = ySize;
	}
	public void draw(Graphics g){}

}
