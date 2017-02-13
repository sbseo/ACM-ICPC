import java.util.Scanner;


public class robot_13567 {

	public static int m; // ���簢�� ������ �� ����
	public static int commandNum; // ��ɾ� ���� 
	public static int[][] map;
	public static String dir = "right"; // ����
	private static Scanner sc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		sc = new Scanner(System.in);

		String temp = sc.nextLine();
		String[] sep = temp.split(" ");
		
		m = Integer.parseInt(sep[0]);
		commandNum = Integer.parseInt(sep[1]);

		// 2���� �迭 ���� ���
		map = new int[m][m];

		// ��ġ ����
		Position curPos = new Position();
		Position mapPos = new Position();

		/* �ʱ�ȭ */
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

			/* map ���� �ִ��� Ȯ�� */
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

/* ��ġ���� */
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