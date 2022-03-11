package lambdasinaction._02stream.basic1;

import java.util.*;
import java.util.stream.Collectors;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() <= 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        List<String> lowCaloricLimit3DishesName = new ArrayList<>();
        lowCaloricLimit3DishesName = lowCaloricDishesName.subList(0,3);

        return lowCaloricLimit3DishesName;
    }

    //Java 8
    // 칼로리가 400 이하인 요리를 추출 칼로리 순으로 정렬하고 상위 3개 Dish 이름을 반환하기
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes.stream()
                .filter(dish -> dish.getCalories() <= 400)  // stream<Dish>
                // comparing의 아규먼트 Function<? super T, ? extends U>
                .sorted(Comparator.comparing(dish -> dish.getCalories()))  // stream<Dish>
                // map()의 아규먼트 Function<? super T, ? extends R>
                .map(dish -> dish.getName())  // stream<String>
                .collect(Collectors.toList())
                .subList(0, 3); // List<String>
    }

    //400칼로리 이하인 메뉴를 다이어트로, 아닐 경우 일반으로 그룹핑해라.
    public static Map<String, List<Dish>>  getGroupingMenu(List<Dish> dishes){
        return null;

    }


    //가장 칼로리가 높은 메뉴를 찾아라
    public static Dish getMaxCaloryDish (List<Dish> dishes) {
        return null;


    }
}
