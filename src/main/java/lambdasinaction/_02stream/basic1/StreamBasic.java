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
    // ???????????? 400 ????????? ????????? ?????? ????????? ????????? ???????????? ?????? 3??? Dish ????????? ????????????
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes.stream()
                .filter(dish -> dish.getCalories() <= 400)  // stream<Dish>
                // comparing??? ???????????? Function<? super T, ? extends U>
                .sorted(comparing(dish -> dish.getCalories()))  // stream<Dish>
                // map()??? ???????????? Function<? super T, ? extends R>
                .map(dish -> dish.getName())  // stream<String>
                .collect(toList())
                .subList(0, 3); // List<String>
    }

    /*public static List<String> getLowCaloricDishesNamesInJava8MethodRef(List<Dish> dishes){
        return dishes.stream()
                .filter(dish -> dish.getCalories() <= 400)  // stream<Dish>
                // comparing??? ???????????? Function<? super T, ? extends U>
                .sorted(comparing(Dish::getCalories))  // stream<Dish>
                // map()??? ???????????? Function<? super T, ? extends R>
                .map(Dish::getName)  // stream<String>
                // alt + enter?????? static method import ????????? ??? ????????????(Collectors.toList())
                .collect(toList());  // List<String>
        //.subList(0, 3);
    }*/

    // ????????? Descending
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
                // Comparator??? ????????? ????????? d2??? ?????? ???????????? ????????????(desc)
                .sorted((d1, d2) -> Integer.compare(d2.getCalories(), d1.getCalories()))  // stream<Dish>
                .map(Dish::getName)  // stream<String>
                .collect(toList());  // List<String>
        //.subList(0, 3);
    }

    // ??????(vegetarian)??? ????????? ?????? ????????? ???????????? Dish ????????? ????????????
    public static List<String> getVegetarianDishesName(List<Dish>  dishes) {
        return dishes.stream()
                .filter(Dish::isVegetarian)
                .sorted(comparing(Dish::getName))
                .map(Dish::getName)
                .collect(toList());
    }

    // 400????????? ????????? ????????? ???????????????, ?????? ?????? ???????????? ???????????????.
    public static Map<String, List<Dish>> getGroupingMenu(List<Dish> dishes){
        return dishes.stream()
                .collect(groupingBy(dish -> {  //collect(Collectors.groupingBy()) + (alt + enter)
                    if (dish.getCalories() <= 400) return "diet";
                    else return "Normal";
                }));

    }

    //?????? ???????????? ?????? ????????? ?????????
    public static Dish getMaxCalorieDish (List<Dish> dishes) {
        return dishes.stream()
                .max(comparingInt(Dish::getCalories))  // Optional<Dish>
                .orElse(null);  // Dish
    }
}
