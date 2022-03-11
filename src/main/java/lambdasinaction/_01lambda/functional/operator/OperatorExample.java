package lambdasinaction._01lambda.functional.operator;

import java.util.function.IntBinaryOperator;

public class OperatorExample {
	private static int[] scores = { 92, 95, 87 };

	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];
		for (int score : scores) {
			result = operator.applyAsInt(result, score);
		}
		return result; }

	public static void main(String[] args) {
		// alt + shift + l(엘) : 이클립스 리턴타입과 변수 자동생성 // new ArrayList<String>();
		// ctrl + alt + v : idea 리턴타입과 변수 자동생성
		// 최대값 얻기
//		maxOrMin((n1, n2) -> {if (n1 >= n2) return n1; else return n2;});
		int max = maxOrMin((n1, n2) -> {
			if (n1 >= n2) return n1;
			else return n2;
		});
		System.out.println("max = " + max);

		// 최소값 얻기
		int min = maxOrMin((a, b) -> {
			if (a <= b) return a;
			else return b;
		});
		System.out.println("min = " + min);

	}
}
