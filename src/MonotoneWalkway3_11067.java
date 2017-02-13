import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


/* 
 * https://www.acmicpc.net/problem/11067
 */

public class MonotoneWalkway3_11067 {

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
	public static Cafe[] cafes;

	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		totalTestCasesNum = Integer.parseInt(sc.nextLine());

		// Testcase ������ �Է���.
		for(int i=0; i<totalTestCasesNum; i++){			

			cafeNum = Integer.parseInt(sc.nextLine());
			cafes = new Cafe[cafeNum];
			answer = new Cafe[cafeNum];

			for (int j=0; j<cafeNum; j++){
				Cafe cafeTemp = new Cafe();
				String[] temp = sc.nextLine().split(" ");

				cafeTemp.setX(Integer.parseInt(temp[0]));
				cafeTemp.setY(Integer.parseInt(temp[1]));
				cafeTemp.setCafeNum(Integer.MAX_VALUE);
				cafes[j] = cafeTemp;
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
			int startIndex = 0;
			int nextIndex = 0;
			int j = 0;

			Arrays.sort(cafes, new Compare1());

			while(j < cafeNum - 1){
				startIndex = j;
				nextIndex = j+1;
				int length = 0;

				// �������� ����
				if(cafes[0].y != 0){
					int tempEndIndex = nextIndex;

					while(tempEndIndex < cafeNum && cafes[nextIndex].x == cafes[tempEndIndex].x){
						tempEndIndex++;
						length++;
					}
					Arrays.sort(cafes, 0, tempEndIndex -1, new CompareYASC());
					j = tempEndIndex - 1;
					break;
				}
				//	 
				// ���Ʒ� ������ �ȵ� ��
				if(cafes[startIndex].x != cafes[nextIndex].x && cafes[startIndex].y != cafes[nextIndex].y){
					int tempEndIndex = nextIndex;

					while(tempEndIndex < cafeNum && cafes[nextIndex].x == cafes[tempEndIndex].x){
						tempEndIndex++;
						length++;
					}
					Arrays.sort(cafes, nextIndex + 1, tempEndIndex -1, new CompareYDESC());
					j = tempEndIndex - 1;
				}
				else{					
					j++;
				}
			}

			/* ������ */
			for(int l=0; l<question.length; l++){
				int answerIndex = question[l] -1 ;
				System.out.println(cafes[answerIndex].x + " " + cafes[answerIndex].y);	
			}

		} // end of �� TestCase 
	}


	static class Compare1 implements Comparator<Cafe> {

		/**
		 * 
		 */
		@Override
		public int compare(Cafe o1, Cafe o2) {
			// TODO Auto-generated method stub
			return o1.getX() - o2.getX();
		}

	}
	static class CompareYASC implements Comparator<Cafe> {

		/**
		 * 
		 */
		@Override
		public int compare(Cafe o1, Cafe o2) {
			// TODO Auto-generated method stub

			//return o2.getY() > o1.getY() ? -1 : o2.getY() < o1.getY() ? 1 : 0;
			return o1.getY() - o2.getY();
		}


	}
	static class CompareYDESC implements Comparator<Cafe> {

		/**
		 * 
		 */
		@Override
		public int compare(Cafe o1, Cafe o2) {
			// TODO Auto-generated method stub
			// return o2.getY() < o1.getY() ? -1 : o2.getY() > o1.getY() ? 1 : 0;
			return o2.getY() - o1.getY();
		}

	}
}

class Cafe{

	public int x;
	public int y;
	public int cafeNum;

	public int getCafeNum() {
		return cafeNum;
	}
	public void setCafeNum(int cafeNum) {
		this.cafeNum = cafeNum;
	}
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