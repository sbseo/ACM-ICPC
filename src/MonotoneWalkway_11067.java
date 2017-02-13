import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/* 
 * https://www.acmicpc.net/problem/11067
 */

public class MonotoneWalkway_11067 {

	public static int totalTestCasesNum;
	public static int cafeNum; // �� ī�� ����

	public static int questionNum; // ���� ����
	public static int[] question; // ����. �������� ����ؾ��ϴ� ī���ȣ

	public static ArrayList<Integer> xPos; // x��ǥ�� ����
	public static ArrayList<Integer> xPosTemp; // x��ǥ�� ����
	public static int[] xPosDup; // x��ǥ ���� ����. ���񽺰� x��ǥ�� �ǹ���.
	public static ArrayList<Integer>[] yPosData; // �ش� x��ǥ�� ���� y��ǥ�� ������. 
	public static Cafe answer[]; 
	public static int xPosIndex = 0 ;
	public static int conversion[]; 

	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		totalTestCasesNum = Integer.parseInt(sc.nextLine());

		// Testcase ������ �Է���.
		for(int i=0; i<totalTestCasesNum; i++){			
			cafeNum = Integer.parseInt(sc.nextLine());
			answer = new Cafe[cafeNum];

			xPosDup = new int[cafeNum]; // �ʱ�ȭ
			yPosData = new ArrayList[cafeNum]; //
			xPos = new ArrayList<Integer>();
			xPosTemp = new ArrayList<Integer>();

			for (int j=0; j<cafeNum; j++){
				// �ʱ�ȭ
				yPosData[j] = new ArrayList<Integer>();
				xPosDup[j] = 0;
				
				String[] temp = sc.nextLine().split(" ");

				int x = Integer.parseInt(temp[0]);
				int y = Integer.parseInt(temp[1]);

				if(xPos.contains(x)){
					xPosIndex = xPos.indexOf(x);
				}
				else{
					xPos.add(x);
					xPosTemp.add(x);
					xPosTemp.sort(null);
					xPosIndex = xPos.indexOf(x);
				}
				xPosDup[xPosIndex]++; // �ε����� ���ڰ� 0���� ũ�� ������. 
				yPosData[xPosIndex].add(y); // x��ǥ�� �ε����� ���� ����Ʈ�� y��ǥ ����Ʈ �߰�.
			}	

			String[] temp2 = sc.nextLine().split(" ");
			questionNum = Integer.parseInt(temp2[0]);
			question = new int[questionNum];
			for(int k =1; k < temp2.length; k++){
				question[k-1] = Integer.parseInt(temp2[k]);
			}		

			Cafe curPos = new Cafe();
			curPos.x = 0;
			curPos.y = 0;
			int count = 0;

			/* �˰��� ���� */
			conversion = new int[xPos.size()];
		//	xPosTemp.sort(null);
			// �����ϱ�!!
			for(int l=0; l < xPos.size(); l++){								
				conversion[l] = xPos.indexOf(xPosTemp.get(l));
				if(yPosData[l].size() > 1){
					yPosData[l].sort(null);;
				}
			}

			int sortedIndex = 0;

			while(count < cafeNum){

				Cafe tempCafe = new Cafe();
				xPosIndex = conversion[sortedIndex];

				// ī�� ������ ���������� �̵�.
				if (xPosDup[xPosIndex] == 0){ 
					sortedIndex++;
				}
				// �����ϸ� Ž����. 
				else if(xPosDup[xPosIndex] == 1){					
					tempCafe.x = xPos.get(xPosIndex); // Call by Reference�� �ƴ϶� Value�� �ؾ���. ��������!!!
					tempCafe.y = yPosData[xPosIndex].get(0);

					answer[count++] = tempCafe;
					sortedIndex++;
				}
				else{
					tempCafe.x = xPos.get(xPosIndex); // Call by Reference�� �ƴ϶� Value�� �ؾ���. ��������!!!
					xPosDup[xPosIndex]--;	

					// ���������̸�...
					if(yPosData[xPosIndex].get(0) == curPos.y){ 
						tempCafe.y = yPosData[xPosIndex].get(0); // y��ǥ ���ؿ�
						answer[count++] = tempCafe;			// �信 �߰���	
						yPosData[xPosIndex].remove(0); // ������
						curPos.y = yPosData[xPosIndex].get(0);
					}
					// ���������̸�...
					else {			
						int endIndex = yPosData[xPosIndex].size() - 1;
						tempCafe.y = yPosData[xPosIndex].get(endIndex); // y��ǥ ���ؿ�
						answer[count++] = tempCafe;			// �信 �߰���	
						yPosData[xPosIndex].remove(endIndex);
						endIndex = yPosData[xPosIndex].size() - 1;
						curPos.y = yPosData[xPosIndex].get(endIndex);
					}
				} // end of else				
			} // end of while count < cafeNum

			/* ������ */
			for(int l=0; l<question.length; l++){
				int answerIndex = question[l] - 1;
				System.out.println(answer[answerIndex].x + " " + answer[answerIndex].y);	
			}

		} // end of �� TestCase 
	}
}

class Cafe11{

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