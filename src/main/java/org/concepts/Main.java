package org.concepts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ExampleData().getProducts();
//        products.stream().filter(product->product.getCategory() == Category.FOOD)
//                .forEach(product->System.out.println(product.getName()));
//        System.out.println(products);
//      Regular expression to split based on spaces
//        Pattern spaces = Pattern.compile("\\s");
//        products.stream().flatMap(prod -> spaces.splitAsStream(prod.getName()))
//                .forEach(System.out::println);

//      Search an element in the stream under OFFICE Category
//        Optional<Product> opt = products.stream()
//                .filter(prod -> prod.getCategory() == Category.OFFICE)
//                .findFirst();
//        opt.ifPresent(System.out::println);

//        Reduce streams
        List<String> filteredProducts =  products.stream()
                .filter(product -> product.getCategory()==Category.FOOD)
                .map(product -> product.getName())
                .collect(Collectors.toList());

        String categories = products.stream()
                                    .map(product -> product.getCategory())
                                            .distinct()
                                                    .map(Category::name) // to convert enum constants to strings
                                                            .collect(Collectors.joining(";"));
        System.out.println(categories);

        System.out.println(filteredProducts);


//      Different ways to create a stream
        Stream<Product> stream = products.stream();
        String[] arr = new String[]{"convert","array","as streams"};
        Stream<String> stream2 = Arrays.stream(arr);
        System.out.println(stream2);

        Stream<String> stream3 = Stream.of("one","two");

        Stream<String> stream4 = Stream.ofNullable("four");
//        To create an empty stream
        Stream<String> stream5 = Stream.empty();

//        Example of Streams==>  start is inclusive,end is exclusive
        IntStream dice = new Random().ints(1,7);

//        Read content of a file
//        try(BufferedReader reader = new BufferedReader(new FileReader("README.md", StandardCharsets.UTF_8))){
//            reader.lines().forEach(System.out::println);
//        } catch(IOException e){
//            e.printStackTrace();
//        }

//
//        Stream<BigInteger> powersOfTwo = Stream.iterate(BigInteger.ONE, n -> n.multiply(BigInteger.TWO));
//        powersOfTwo.limit(20).forEach(System.out::println);
//
//        Stream.iterate('A', letter -> letter <='Z', letter -> (char)(letter+1));
////        The 3 conditions in the iterate function above reads like a for loop
//
//        Stream.Builder<String> builder = Stream.builder();
//        builder.add("one");
//        builder.add("two");
//        builder.add("three");
//        Stream<String> streambuilder = builder.build();
//        streambuilder.forEach(System.out::println);

        BigDecimal total = products.stream().map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("total is :"+ total);
//        Using accumulator function
        BigDecimal total2 = products.stream()
                .reduce(BigDecimal.ZERO, (result,product) -> result.add(product.getPrice()), BigDecimal::add);

        System.out.println("alternate total is :"+ total2);


//        Use of collect() in streams
        List<String> productNames1 = products.stream().reduce(
                new ArrayList<>(),
                (list, product) -> {
                  ArrayList<String> newList = new ArrayList<>();
                  newList.add(product.getName());
                  return newList;
                },
                (list1, list2) ->{
                    ArrayList<String> newList = new ArrayList<>(list1);
                    newList.addAll(list2);
                    return newList;
                }
        );
        System.out.println(productNames1);

//        We can reduce lines of code by using collect() function
        List<String> productNames2 = products.stream().collect(ArrayList::new,
                (list, product) -> list.add(product.getName()),
                ArrayList::addAll
                );
        System.out.println(productNames2);

//        Collectors
        Map<Category, BigDecimal> collectorMap = products.stream().collect(Collectors
                .toMap(Product::getCategory,Product::getPrice, BigDecimal::add));

        System.out.println("collectorMap:" + collectorMap);

//        Grouping Stream Elements
        Map<Category, List<String>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::getName, Collectors.toList())));
    }
}
