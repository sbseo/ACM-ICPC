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
	public static int cafeNum; // 총 카페 숫자

	public static int questionNum; // 문제 숫자
	public static int[] question; // 문제. 마지막줄 출력해야하는 카페번호

	public static ArrayList<Integer> xPos; // x좌표만 저장
	public static ArrayList<Integer> xPosTemp; // x좌표만 저장
	public static int[] xPosDup; // x좌표 개수 저장. 엔딕스가 x좌표를 의미함.
	public static ArrayList<Integer>[] yPosData; // 해당 x좌표에 들어가는 y좌표를 저장함. 
	public static Cafe answer[]; 
	public static int xPosIndex = 0;
	public static int conversion[]; 
	public static boolean[] targetPosition;
	public static Cafe[] cafes;

	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		totalTestCasesNum = Integer.parseInt(sc.nextLine());

		// Testcase 데이터 입력함.
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


			/* 알고리즘 시작 */
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

				// 시작지점 변경
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
				// 위아래 연결이 안될 때
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

			/* 결과출력 */
			for(int l=0; l<question.length; l++){
				int answerIndex = question[l] -1 ;
				System.out.println(cafes[answerIndex].x + " " + cafes[answerIndex].y);	
			}

		} // end of 각 TestCase 
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