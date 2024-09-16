package Selenium.practice;

public class Questions {
	public static void main(String[] args) {

		int num = 10253695;
		String name = "madam";

		// palindromeNumber(num,reverseNumber(num));

		// palindromeString(name,reverseString(name));
		System.out.println(countDigitsInNumber(num));
		countEvenAndOddDigits(num);
		sumOfDigitsinNumber(num);

		largestOfThreeNumbers(38, 90, 101);

	}

	public static void sumOfDigitsinNumber(int num) {

		int sum = 0;

		while (num > 0) {
			// getting last digit
			int lastdigit = num % 10;
			sum = sum + lastdigit;
			// eliminating last digit
			num = num / 10;
		}
		System.out.println("sum of digits in Number:" + String.valueOf(sum));
	}

	public static int countDigitsInNumber(int num) {

		int count = 0;

		while (num > 0) {
			num = num / 10;
			count++;
		}
		return count;
	}

	public static void largestOfThreeNumbers(int a, int b, int c) {

		// if(a>b && a>c){
		// System.out.println("a is largest Number");
		// }else if(b>a && b>c){
		// System.out.println("b is largest Number");
		// }else{
		// System.out.println("c is largest Number");

		// }
		// using ternary operator
		int largest = (a > b ? a : b) > c ? (a > b ? a : b) : c;
		// largest=largest>c?largest:c;
		System.out.println(largest + "" + " is largest Number");
	}

	public static void countEvenAndOddDigits(int num) {
		int evenDigitCount = 0;
		int oddDigitCount = 0;

		while (num > 0) {
			int lastdigit = num % 10;

			if (lastdigit % 2 == 0) {
				evenDigitCount++;
			} else {
				oddDigitCount++;
			}
			num = num / 10;
		}
		System.out.println("no of even digits:" + String.valueOf(evenDigitCount));
		System.out.println("no of odd digits:" + String.valueOf(oddDigitCount));
	}

	public static int reverseNumber(int num) {

		StringBuffer sb = new StringBuffer(String.valueOf(num));

		return Integer.parseInt(String.valueOf(sb.reverse()));

	}

	public static void palindromeNumber(int num, int revnum) {
		if (num == revnum) {
			System.out.println("Number is Palindrome");
		} else {
			System.out.println("Number is Not a Palindrome");
		}
	}

	public static String reverseString(String s) {
		String revStr = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			revStr = revStr + s.charAt(i);

		}
		return revStr;
	}

	public static void palindromeString(String str, String revstr) {
		if (str.equals(revstr)) {
			System.out.println(str + "-String is Palindrome");
		} else {
			System.out.println(str + "-String is Not a  Palindrome");
		}
	}

}
