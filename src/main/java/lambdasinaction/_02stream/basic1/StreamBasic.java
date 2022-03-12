package lambdasinaction._02stream.basic1;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

        System.out.println("---- Method Reference");
        getLowCaloricDishesNamesInJava8MethodRef(Dish.menu).forEach(System.out::println);

        System.out.println("---- Vegetarian");
        getVegetarianDishesName(Dish.menu).forEach(System.out::println);

        System.out.println("---- GroupingBy");
        //getGroupingMenu(Dish.menu); + (ctrl + alt + v)
        Map<String, List<Dish>> groupingMenu = getGroupingMenu(Dish.menu);
        System.out.println("groupingMenu = " + groupingMenu);

        System.out.println("---- MaxCalorieDish");
        Dish maxCalorieDish = getMaxCalorieDish(Dish.menu);
        System.out.println("maxCalorieDish = " + maxCalorieDish);

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
                .sorted(comparing(dish -> dish.getCalories()))  // stream<Dish>
                // map()의 아규먼트 Function<? super T, ? extends R>
                .map(dish -> dish.getName())  // stream<String>
                .collect(toList())
                .subList(0, 3); // List<String>
    }

    /*public static List<String> getLowCaloricDishesNamesInJava8MethodRef(List<Dish> dishes){
        return dishes.stream()
                .filter(dish -> dish.getCalories() <= 400)  // stream<Dish>
                // comparing의 아규먼트 Function<? super T, ? extends U>
                .sorted(comparing(Dish::getCalories))  // stream<Dish>
                // map()의 아규먼트 Function<? super T, ? extends R>
                .map(Dish::getName)  // stream<String>
                // alt + enter해서 static method import 해주면 더 간결해짐(Collectors.toList())
                .collect(toList());  // List<String>
        //.subList(0, 3);
    }*/

    // 정렬을 Descending
    public static List<String> getLowCaloricDishesNamesInJava8MethodRef(List<Dish> dishes){
       /* return dishes.stream()
                .filter(dish -> dish.getCalories() <= 400)  // stream<Dish>
                .sorted(new Comparator<Dish>() {
                    @Override
                    public int compare(Dish d1, Dish d2) {
                        return Integer.compare(d2.getCalories(), d1.getCalories());
                    }
                })  // stream<Dish>
                .map(Dish::getName)  // stream<String>
                .collect(toList());  // List<String>
                //.subList(0, 3);*/
        return dishes.stream()
                .filter(dish -> dish.getCalories() <= 400)  // stream<Dish>
                // Comparator의 두번째 인자인 d2를 먼저 사용하면 내림차순(desc)
                .sorted((d1, d2) -> Integer.compare(d2.getCalories(), d1.getCalories()))  // stream<Dish>
                .map(Dish::getName)  // stream<String>
                .collect(toList());  // List<String>
        //.subList(0, 3);
    }

    // 야채(vegetarian)의 요리를 이름 순으로 정렬하고 Dish 이름을 반환하기
    public static List<String> getVegetarianDishesName(List<Dish>  dishes) {
        return dishes.stream()
                .filter(Dish::isVegetarian)
                .sorted(comparing(Dish::getName))
                .map(Dish::getName)
                .collect(toList());
    }

    // 400칼로리 이하인 메뉴를 다이어트로, 아닐 경우 일반으로 그룹핑해라.
    public static Map<String, List<Dish>> getGroupingMenu(List<Dish> dishes){
        return dishes.stream()
                .collect(groupingBy(dish -> {  //collect(Collectors.groupingBy()) + (alt + enter)
                    if (dish.getCalories() <= 400) return "diet";
                    else return "Normal";
                }));

    }

    //가장 칼로리가 높은 메뉴를 찾아라
    public static Dish getMaxCalorieDish (List<Dish> dishes) {
        return dishes.stream()
                .max(comparingInt(Dish::getCalories))  // Optional<Dish>
                .orElse(null);  // Dish
    }
}
