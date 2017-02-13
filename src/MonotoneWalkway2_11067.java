import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/* 
 * https://www.acmicpc.net/problem/11067
 */

public class MonotoneWalkway2_11067 {

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

	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		totalTestCasesNum = Integer.parseInt(sc.nextLine());
		
		// Testcase 데이터 입력함.
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
				xPosDup[x]++; // 인덱스의 숫자가 0보다 크면 존재함. 
				yPosData[x].add(y); // x좌표를 인덱스로 갖는 리스트에 y좌표 리스트 추가.
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
			int count = 0;
			boolean sortCount = false; 
			
			while(count < cafeNum && targetPosition[curPos.x] == true){

				Cafe tempCafe = new Cafe();
				
				// 존재하면 탐색함. 
				if(xPosDup[curPos.x] == 1){					
					tempCafe.x = curPos.getX(); // Call by Reference가 아니라 Value로 해야함. 주의하자!!!
					tempCafe.y = curPos.getY();
					answer[count++] = tempCafe;
					curPos.x++;
					sortCount = false;
					// 다음에 true만날 때 까지 x 계속 더함
					while(curPos.x < 100000 && targetPosition[curPos.x] == false){
						curPos.x++;
					}
				}
				else if(xPosDup[curPos.x] > 1){
					tempCafe.x = curPos.getX(); // Call by Reference가 아니라 Value로 해야함. 주의하자!!!
					tempCafe.y = curPos.getY();
					if(sortCount == false){
						yPosData[curPos.x].sort(null);
						sortCount = true;
					}
					xPosDup[curPos.x]--;	
					
					// 오름차순이면...
					if(yPosData[curPos.x].get(0) == curPos.y){ 
						tempCafe.y = yPosData[xPosIndex].get(0); // y좌표 구해옴
						answer[count++] = tempCafe;			// 답에 추가함	
						yPosData[curPos.x].remove(0); // 재정렬
						curPos.y = yPosData[xPosIndex].get(0);
					}
					// 내림차순이면...
					else {			
						int endIndex = yPosData[curPos.x].size() - 1;
						tempCafe.y = yPosData[curPos.x].get(endIndex); // y좌표 구해옴
						answer[count++] = tempCafe;			// 답에 추가함	
						
						yPosData[curPos.x].remove(endIndex);
						endIndex--;
						curPos.y = yPosData[curPos.x].get(endIndex);
					}
				}

			} // end of while count < cafeNum

			/* 결과출력 */
			for(int l=0; l<question.length; l++){
				int answerIndex = question[l] -1 ;
				System.out.println(answer[answerIndex].x + " " + answer[answerIndex].y);	
			}

		} // end of 각 TestCase 
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