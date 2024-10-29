package app;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
        new Product("milk", "Dairy", 55.0),
        new Product("coffee", "Drinks", 335.0),
        new Product("yoghurt", "Dairy", 40.5),
        new Product("tea", "Drinks", 40.5),
        new Product("cheese", "Dairy", 180.5),
        new Product("banana", "Fruits", 60.1)
        );

        Map<String, List<String>> productsByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.mapping(Product::getName, Collectors.toList())));

        System.out.println("\n" + "Продукти по категорії: ");
        productsByCategory.forEach((category, productList) ->
            System.out.println(category + ": " + productList));


        Map<String, Double> averagePriceInCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.averagingDouble(Product::getPrice)));

        System.out.println("\n" + "Середня ціна по категорії: ");
        averagePriceInCategory.forEach((category, price) -> System.out.println(category + ": " + price));


        String highestPriceInCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.averagingDouble(Product::getPrice)))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("нічого не знайдено");


        System.out.println("\n" + "Категорія з найвищою середньою ціною: " + highestPriceInCategory);
    }

}
