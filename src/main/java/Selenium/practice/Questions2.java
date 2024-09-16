package Selenium.practice;

import java.util.*;

public class Questions2 {
	public static void main(String[] args) {

		int num[] = { 11, 20, 39, 46, 5,11,39,20 };
		int num1[] = { 1, 2, 39, 4, 5 };
		String keywords[]= {"java","javascript","Cypress","selenium","API Testing","Cucumber BDD"};
		// System.out.println(generateRandomNumber());
		// System.out.println(Math.random());

		factorialOfGivenNumber(10);
		sumOfElementsInArray(num);
		printOddNEvenNumbersFromArray(num);
		ArraysEqual(num, num1);
		maxAndMinElementsInArray(num);
		findDuplicateEleInArray(num);
		searchElementInArray(keywords,"selenium");
	}

	public static int generateRandomNumber() {

		// random class
		Random random = new Random();

		return random.nextInt(99);
	}

	public static void factorialOfGivenNumber(int num) {

		int factorial = 1;
		for (int i = 0; i < num; i++) {
			factorial = factorial * (num - i);
		}
		System.out.println(factorial);
		// (num-0)*(num-1)*(num-2)*(num-3)*(num-4)
	}

	public static void sumOfElementsInArray(int arr[]) {

		int sum = 0;
		// for(int i=0;i<arr.length;i++){
		// sum=sum+arr[i];
		// }

		for (int n : arr) {
			sum = sum + n;
		}

		System.out.println(sum);
	}

	public static void printOddNEvenNumbersFromArray(int arr[]) {

		ArrayList<Integer> even = new ArrayList<>();
		ArrayList<Integer> odd = new ArrayList<>();

		for (int n : arr) {
			if (n % 2 == 0) {
				even.add(n);
			} else {
				odd.add(n);
			}
		}
		System.out.println("evenNumbers" + even);
		System.out.println("oddNumbers" + odd);
	}

	public static void ArraysEqual(int arr1[], int arr2[]) {
		// boolean status =Arrays.equals(arr1,arr2);
		boolean status = false;

		if (arr1.length == arr2.length) {
			status = true;
			for (int i = 0; i < arr1.length - 1; i++) {
				if (!(arr1[i] == arr2[i])) {
					status = false;
				}
			}

		} else {
			status = false;
		}

		if (status == true) {
			System.out.println("arrays are equal");
		} else {
			System.out.println("arrays are Not  equal");
		}
	}

	public static void maxAndMinElementsInArray(int arr[]) {
		int max = arr[0];
		int min = arr[0];

		for (int i = 1; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);

			// max= max>arr[i]?max:arr[i];
			// min=min<arr[i]?min:arr[i];

		}
		System.out.println("Maximum Number " + max);
		System.out.println("Minimum Number " + min);
	}

	public static void findDuplicateEleInArray(int arr[]) {
		boolean duplicate = false;

		HashSet<Integer> set = new HashSet();

		// for (int i = 0; i < arr.length; i++) {
		// for(int j=i+1;j<arr.length;j++){
		// if(arr[i]==arr[j]){
		// duplicate=true;
		// count++;
		// System.out.println("duplicate Element"+ arr[i]);
		// }
		// }
		// }

		for (int num : arr) {
			if (set.add(num) == false) {
				duplicate = true;
				System.out.println("duplicate Element" + num);
			}
		}

		if (duplicate == false) {
			System.out.println("duplicate Not Found");
		}
		
		if(set.size()==arr.length) {
			System.out.println("duplicate Not Found");
		}
		
		
	}
	
	public static void searchElementInArray(String  arr[],String element ) {
		 
		boolean strelement = false;
		for(String s :arr) {
			if(s==element) {
				strelement =true;
				System.out.println("Element Found");
			}
		}
		
		if(strelement==false) {
			System.out.println("Element Not Found");
		}
		
	}

}
