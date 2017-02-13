import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author SeungBeomSeo -- BrunoSeo
 * @Jan 31, 2017
 * CopyRight -- Strict Commercial Use
 */

/**
 * @author SeungBeomSeo
 *
 */
public class canoe{

	public static int testCases;
	public static int caseCount = 0;
	public static int target; 
	public static int studentNum;
	public static String[] classNum;

	public static int[] answers;

	public static void main(String[] args){	

		Scanner sc = new Scanner(System.in);
	
		String temp0 = sc.nextLine().trim(); // ù��
		testCases = Integer.parseInt(temp0);
		classNum = new String[4];

		answers = new int[testCases];
		
		/* ���⼭ ������ �Է� �� */
		while(caseCount < testCases){

			String temp = sc.nextLine().trim();
			String[] sep = temp.split(" ");
			
			target = Integer.parseInt(sep[0]);
			studentNum = Integer.parseInt(sep[1]);
			
			for (int i=0; i<4; i++){	
				classNum[i] = sc.nextLine().trim();
			}	

			/* �˰���. �� �ݿ��� target/4�� ���� ����� ����������.
			 *  Greedy Method!!�ϸ� �ȵ�!!!!!   
			 *  Divide and Conquer�ؾߵ�  !!
			 *  i�� ����   */	
			int[] row1 = new int[studentNum];
			int[] row2 = new int[studentNum];
			int[] row3 = new int[studentNum];
			int[] row4 = new int[studentNum];

			/* �� �ݿ� ���� �л� ����  int �迭�� ���� */
			String[] temp2 = classNum[0].split(" ");
			for(int i=0; i<studentNum; i++){
				row1[i] = Integer.parseInt(temp2[i]);	
			}
			temp2 = classNum[1].split(" ");
			for(int i=0; i<studentNum; i++){
				row2[i] = Integer.parseInt(temp2[i]);	
			}
			temp2 = classNum[2].split(" ");
			for(int i=0; i<studentNum; i++){
				row3[i] = Integer.parseInt(temp2[i]);	
			}
			temp2 = classNum[3].split(" ");
			for(int i=0; i<studentNum; i++){
				row4[i] = Integer.parseInt(temp2[i]);	
			}

			
			/* row1�� row2�� �� ������ ���� ���� */ 
			ArrayList<Integer> list1 = new ArrayList<Integer>();
			ArrayList<Integer> list2 = new ArrayList<Integer>();
			
			for(int i=0; i < studentNum; i++){
				for(int j=0; j< studentNum; j++){
				
					list1.add(row1[i] + row2[j]); 
				}
			}
			for(int i=0; i < studentNum; i++){
				for(int j=0; j< studentNum; j++){
					list2.add(row3[i] + row4[j]); 
				}
			}
			list1.sort(null);	
			list2.sort(null);	
			
			// ���⼭ ���� �߸�ó������. �ٺ�....
			int i=0; int j=list2.size() - 1;
			int sum = 0; 
			int diffMinVal=Integer.MAX_VALUE; // Ÿ�ٰ� ���� ���̰� ���� ������ ��. 

			while(i < list1.size() && j >= 0){
				sum = list1.get(i) + list2.get(j);	
				
				if(sum > target){
					j--;
					if(Math.abs(sum - target) < Math.abs(diffMinVal - target)){						
						diffMinVal = sum;
					}
					else if(Math.abs(sum - target) == Math.abs(diffMinVal - target)){						
						if(sum < diffMinVal){
							diffMinVal = sum;
						}
					}
				}
				else if(sum == target){					
					diffMinVal = sum;
					break;
				}
				else if(sum < target){
					i++;
					if(Math.abs(sum - target) < Math.abs(diffMinVal - target)){						
						diffMinVal = sum;
					}
					else if(Math.abs(sum - target) == Math.abs(diffMinVal - target)){						
						if(sum < diffMinVal){
							diffMinVal = sum;
						}
					}
				}
			
			} // end of while (������)
		
			answers[caseCount] = diffMinVal;
			System.out.println(diffMinVal);
			caseCount++; // ���� �׽�Ʈ ���̽�. Move to Test Case		
			
		} // end of while(caseCount < testCases)
	
	//	for(int data : answers){
	//		System.out.println(data);
	//	}
		sc.close();

	}
}
