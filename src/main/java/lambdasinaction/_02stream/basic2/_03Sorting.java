package lambdasinaction._02stream.basic2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class _03Sorting {

    public static void main(String...args){
        List<Integer> integerList = List.of(10, 8, 3, 38, 40, 1);
        System.out.println("---- Anonymous Inner Class");
        // Anonymous Inner Class
        integerList.stream()
                        .sorted(new Comparator<Integer>() {
                            @Override
                            public int compare(Integer o1, Integer o2) {
                                return o1.compareTo(o2);
                            }
                        }).forEach(System.out::println);

        System.out.println("---- Lambda Expression");
        // Lambda Expression
        integerList.stream()
                .sorted((i1, i2) -> i1.compareTo(i2)).forEach(System.out::println);

        System.out.println("---- Method Reference");
        // Method Reference
        integerList.stream()
                .sorted(Integer::compareTo)
                .forEach(System.out::println);

        // 1. static inner class 
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80,"green"), 
        		                       new Apple(155,"green"), 
        		                       new Apple(120,"red")));

        // [Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
        inventory.sort(new AppleComparator());
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(30, "green"));
        
        // 2. 구현 anonymous inner class 
        // [Apple{color='green', weight=30}, Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        System.out.println(inventory);

        // reshuffling things a little
        inventory.set(1, new Apple(20, "red"));
        
        // 3. 구현 lambda 
        // [Apple{color='red', weight=20}, Apple{color='green', weight=30}, Apple{color='green', weight=155}]
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(inventory);
        
        // reshuffling things a little
        inventory.set(1, new Apple(10, "red"));
        
        // 4. 구현 Method Reference
        // [Apple{color='red', weight=10}, Apple{color='red', weight=20}, Apple{color='green', weight=155}]
        inventory.sort(Comparator.comparing(apple -> apple.getWeight()));
        System.out.println(inventory);

        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);

    }

    public static class Apple {
        private Integer weight = 0;
        private String color = "";

        public Apple(Integer weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                   "color='" + color + '\'' +
                   ", weight=" + weight +
                   '}';
        }
    }

    static class AppleComparator implements Comparator<Apple> {
        public int compare(Apple a1, Apple a2){
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }
}
