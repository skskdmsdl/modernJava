package lambdasinaction._01lambda.basic1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class FilteringApples {

	public static void main(String... args) {

		List<Apple> inventory =
				Arrays.asList(new Apple(80, "green"),
						new Apple(155, "green"),
						new Apple(120, "red"));

		//filter method 호출 - 익명클래스
		filter(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple a) {
				return a.getColor().equals("green");
			}
		}).forEach(new Consumer<Apple>() { // list를 forEach로 바로 출력
			@Override
			public void accept(Apple apple) {
				System.out.println("apple = " + apple);
			}
		});

		// filter method 호출 - 람다식
		filter(inventory, apple -> apple.getColor().equals("red"))
				.forEach(apple -> System.out.println("apple = " + apple));

		System.out.println("----------------------------------------------");
		filter(inventory, apple -> (apple.getColor().equals("green")) && (apple.getWeight() > 150))
				.forEach(System.out::println);

	}

	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	@FunctionalInterface
	interface ApplePredicate {
		public boolean test(Apple a);
		// void test(); // error FunctionalInterface는 추상메서드를 하나만 선언 할 수 있음
	}

	static class AppleWeightPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return apple.getWeight() > 150;
		}
	}

	static class AppleColorPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "green".equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate {
		public boolean test(Apple apple) {
			return "red".equals(apple.getColor()) && apple.getWeight() > 150;
		}
	}
}