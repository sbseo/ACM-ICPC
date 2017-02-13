import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/* 
 * https://www.acmicpc.net/problem/11067
 */

public class MonotoneWalkway_11067 {

	public static int totalTestCasesNum;
	public static int cafeNum; // 총 카페 숫자

	public static int questionNum; // 문제 숫자
	public static int[] question; // 문제. 마지막줄 출력해야하는 카페번호

	public static ArrayList<Integer> xPos; // x좌표만 저장
	public static ArrayList<Integer> xPosTemp; // x좌표만 저장
	public static int[] xPosDup; // x좌표 개수 저장. 엔딕스가 x좌표를 의미함.
	public static ArrayList<Integer>[] yPosData; // 해당 x좌표에 들어가는 y좌표를 저장함. 
	public static Cafe answer[]; 
	public static int xPosIndex = 0 ;
	public static int conversion[]; 

	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);
		totalTestCasesNum = Integer.parseInt(sc.nextLine());

		// Testcase 데이터 입력함.
		for(int i=0; i<totalTestCasesNum; i++){			
			cafeNum = Integer.parseInt(sc.nextLine());
			answer = new Cafe[cafeNum];

			xPosDup = new int[cafeNum]; // 초기화
			yPosData = new ArrayList[cafeNum]; //
			xPos = new ArrayList<Integer>();
			xPosTemp = new ArrayList<Integer>();

			for (int j=0; j<cafeNum; j++){
				// 초기화
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
				xPosDup[xPosIndex]++; // 인덱스의 숫자가 0보다 크면 존재함. 
				yPosData[xPosIndex].add(y); // x좌표를 인덱스로 갖는 리스트에 y좌표 리스트 추가.
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

			/* 알고리즘 시작 */
			conversion = new int[xPos.size()];
		//	xPosTemp.sort(null);
			// 정렬하기!!
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

				// 카페 없으면 오른쪽으로 이동.
				if (xPosDup[xPosIndex] == 0){ 
					sortedIndex++;
				}
				// 존재하면 탐색함. 
				else if(xPosDup[xPosIndex] == 1){					
					tempCafe.x = xPos.get(xPosIndex); // Call by Reference가 아니라 Value로 해야함. 주의하자!!!
					tempCafe.y = yPosData[xPosIndex].get(0);

					answer[count++] = tempCafe;
					sortedIndex++;
				}
				else{
					tempCafe.x = xPos.get(xPosIndex); // Call by Reference가 아니라 Value로 해야함. 주의하자!!!
					xPosDup[xPosIndex]--;	

					// 오름차순이면...
					if(yPosData[xPosIndex].get(0) == curPos.y){ 
						tempCafe.y = yPosData[xPosIndex].get(0); // y좌표 구해옴
						answer[count++] = tempCafe;			// 답에 추가함	
						yPosData[xPosIndex].remove(0); // 재정렬
						curPos.y = yPosData[xPosIndex].get(0);
					}
					// 내림차순이면...
					else {			
						int endIndex = yPosData[xPosIndex].size() - 1;
						tempCafe.y = yPosData[xPosIndex].get(endIndex); // y좌표 구해옴
						answer[count++] = tempCafe;			// 답에 추가함	
						yPosData[xPosIndex].remove(endIndex);
						endIndex = yPosData[xPosIndex].size() - 1;
						curPos.y = yPosData[xPosIndex].get(endIndex);
					}
				} // end of else				
			} // end of while count < cafeNum

			/* 결과출력 */
			for(int l=0; l<question.length; l++){
				int answerIndex = question[l] - 1;
				System.out.println(answer[answerIndex].x + " " + answer[answerIndex].y);	
			}

		} // end of 각 TestCase 
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