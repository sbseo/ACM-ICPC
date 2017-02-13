import java.util.Scanner;


public class robot_13567 {

	public static int m; // 정사각형 지도의 변 개수
	public static int commandNum; // 명령어 개수 
	public static int[][] map;
	public static String dir = "right"; // 방향
	private static Scanner sc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		sc = new Scanner(System.in);

		String temp = sc.nextLine();
		String[] sep = temp.split(" ");
		
		m = Integer.parseInt(sep[0]);
		commandNum = Integer.parseInt(sep[1]);

		// 2차원 배열 정의 방법
		map = new int[m][m];

		// 위치 저장
		Position curPos = new Position();
		Position mapPos = new Position();

		/* 초기화 */
		curPos.setX(0);
		curPos.setY(0);
		mapPos.setX(m);
		mapPos.setY(m);	

		int count =0;
		while(count < commandNum){
			
			String temp2 = sc.nextLine();
			String[] sep2 = temp2.split(" ");
			
			String cmd = sep2[0];
			int num = Integer.parseInt(sep2[1]);
			
			if(cmd.equals("MOVE")){

				if(dir.equals("right")){
					curPos.x += num;
				}
				else if(dir.equals("left")){
					curPos.x -= num;
				}
				else if(dir.equals("bottom")){
					curPos.y -= num;
				}
				else if(dir.equals("top")){
					curPos.y += num;
				}
			}
			else if(cmd.equals("TURN")){

				if(dir.equals("right")){
					if(num == 1)
						dir = "bottom";
					else
						dir = "top";
				}
				else if(dir.equals("left")){
					if(num == 1)
						dir = "top";
					else
						dir = "bottom";
				}
				else if(dir.equals("bottom")){
					if(num == 1)
						dir = "left";
					else{
						dir = "right";
					}
				}
				else if(dir.equals("top")){
					if (num == 1)
						dir = "right";
					else
						dir = "left";
				}
			}

			/* map 내에 있는지 확인 */
			if(curPos.getX() >= 0 && curPos.getX() <= m  && curPos.getY() >=0 &&  curPos.getY() <= m){

			}	
			else{
				System.out.println(-1);
				return;
			}	

			count++;
		}		// end of while
		sc.close();

		System.out.println(curPos.getX() + " " + curPos.getY());
	} // end of Main

} // end of class

/* 위치저장 */
class Position{
	public int x;
	public int y;

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}