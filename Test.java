
public class Test {
	public static void initStick(){
		int x=125;
		int y=600;
		int dx;
		int dy;
		int newX;
		int newY;

			do{
				dy = -(int)(Math.random() * 20 + 50);
				
				newY=y+dy;
				
				dx = (int)((2*Math.random()-1)*100);
				
				newX= x+dx;
				
				if(newX < 400 && newX > 50 && Math.abs(x-newX) < 75 && Math.abs(x-newX) > 25 
						&& dy > -75 && dy<-25) {
					x=newX;
					y=newY;
					System.out.println(x+" "+y);
				}
				
				
				} while( y > 50 );
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initStick();
	}

}
