import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/* 
 * https://www.acmicpc.net/problem/11067
 */

public class MonotoneWalkway2_11067 {

	public static int totalTestCasesNum;
	public static int cafeNum; // �� ī�� ����

	public static int questionNum; // ���� ����
	public static int[] question; // ����. �������� ����ؾ��ϴ� ī���ȣ

	public static ArrayList<Integer> xPos; // x��ǥ�� ����
	public static ArrayList<Integer> xPosTemp; // x��ǥ�� ����
	public static int[] xPosDup; // x��ǥ ���� ����. ���񽺰� x��ǥ�� �ǹ���.
	public static ArrayList<Integer>[] yPosData; // �ش� x��ǥ�� ���� y��ǥ�� ������. 
	public static Cafe answer[]; 
	public static int xPosIndex = 0;
	public static int conversion[]; 
	public static boolean[] targetPosition;

	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		totalTestCasesNum = Integer.parseInt(sc.nextLine());
		
		// Testcase ������ �Է���.
		for(int i=0; i<totalTestCasesNum; i++){			
			targetPosition = new boolean[100001];
			xPosDup = new int[100001];
			yPosData = new ArrayList[100001]; 
			xPos = new ArrayList();
			for(int j=0; j<100001; j++){
				targetPosition[j] = false;
				xPosDup[j] = 0;
				yPosData[j] = new ArrayList<Integer>();
			}
			
			cafeNum = Integer.parseInt(sc.nextLine());

			answer = new Cafe[cafeNum];

			for (int j=0; j<cafeNum; j++){
				String[] temp = sc.nextLine().split(" ");

				int x = Integer.parseInt(temp[0]);
				int y = Integer.parseInt(temp[1]);

				if(xPos.contains(x)){
					
				}
				else{
					targetPosition[x] = true;
					xPos.add(x);
				}
				xPosDup[x]++; // �ε����� ���ڰ� 0���� ũ�� ������. 
				yPosData[x].add(y); // x��ǥ�� �ε����� ���� ����Ʈ�� y��ǥ ����Ʈ �߰�.
			}	

			String[] temp2 = sc.nextLine().split(" ");
			questionNum = Integer.parseInt(temp2[0]);
			question = new int[questionNum];
			for(int k =1; k < temp2.length; k++){
				question[k-1] = Integer.parseInt(temp2[k]);
			}		


			/* �˰��� ���� */
			Cafe curPos = new Cafe();
			curPos.x = 0;
			curPos.y = 0;
			int count = 0;
			boolean sortCount = false; 
			
			while(count < cafeNum && targetPosition[curPos.x] == true){

				Cafe tempCafe = new Cafe();
				
				// �����ϸ� Ž����. 
				if(xPosDup[curPos.x] == 1){					
					tempCafe.x = curPos.getX(); // Call by Reference�� �ƴ϶� Value�� �ؾ���. ��������!!!
					tempCafe.y = curPos.getY();
					answer[count++] = tempCafe;
					curPos.x++;
					sortCount = false;
					// ������ true���� �� ���� x ��� ����
					while(curPos.x < 100000 && targetPosition[curPos.x] == false){
						curPos.x++;
					}
				}
				else if(xPosDup[curPos.x] > 1){
					tempCafe.x = curPos.getX(); // Call by Reference�� �ƴ϶� Value�� �ؾ���. ��������!!!
					tempCafe.y = curPos.getY();
					if(sortCount == false){
						yPosData[curPos.x].sort(null);
						sortCount = true;
					}
					xPosDup[curPos.x]--;	
					
					// ���������̸�...
					if(yPosData[curPos.x].get(0) == curPos.y){ 
						tempCafe.y = yPosData[xPosIndex].get(0); // y��ǥ ���ؿ�
						answer[count++] = tempCafe;			// �信 �߰���	
						yPosData[curPos.x].remove(0); // ������
						curPos.y = yPosData[xPosIndex].get(0);
					}
					// ���������̸�...
					else {			
						int endIndex = yPosData[curPos.x].size() - 1;
						tempCafe.y = yPosData[curPos.x].get(endIndex); // y��ǥ ���ؿ�
						answer[count++] = tempCafe;			// �信 �߰���	
						
						yPosData[curPos.x].remove(endIndex);
						endIndex--;
						curPos.y = yPosData[curPos.x].get(endIndex);
					}
				}

			} // end of while count < cafeNum

			/* ������ */
			for(int l=0; l<question.length; l++){
				int answerIndex = question[l] -1 ;
				System.out.println(answer[answerIndex].x + " " + answer[answerIndex].y);	
			}

		} // end of �� TestCase 
	}
}

class Cafe222{

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