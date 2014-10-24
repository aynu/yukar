package com.github.aynu.yukar.framework.scratch;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
@SuppressWarnings("javadoc")
public class StreamTest {
    public static void main(final String[] args) {
        forEach();
        map();
        filter();
        peek();
        flatMap();
        mapTo();
        collect();
    }
    static void forEach() {
        Stream.of("abc", "def").forEach(t -> System.out.println(t));
        Stream.of("abc", "def").forEach(System.out::println);
        System.out.println(Stream.of("abc", "def").collect(Collectors.toList()));
        System.out.println(IntStream.of(123, 456).sum());
    }
    static void map() {
        System.out.println(Arrays.asList("a.txt", "b.txt").stream().map(s -> Paths.get(s))
            .collect(Collectors.toList()));
        System.out.println(Arrays.asList("a.txt", "b.txt").stream().map(Paths::get)
            .collect(Collectors.toList()));
    }
    static void filter() {
        final Stream<Method> stream = Arrays.stream(String.class.getMethods());
        System.out
            .println(stream.filter(method -> method.isVarArgs()).collect(Collectors.toList()));
    }
    static void peek() {
        final Stream<String> s0 = Stream.of("a", "bcd", "z");
        // 文字数が1文字だけのものを抽出し、2文字に増幅させる
        final Stream<String> s1 = s0.peek(t -> System.out.printf("peek1=%s%n", t));
        final Stream<String> s2 = s1.filter(t -> t.length() == 1);
        final Stream<String> s3 = s2.peek(t -> System.out.printf("peek2=%s%n", t));
        final Stream<String> s4 = s3.map(t -> t + t);
        final Stream<String> s5 = s4.peek(t -> System.out.printf("peek3=%s%n", t));
        final List<String> r = s5.collect(Collectors.toList());
        System.out.println(r);
    }
    static void flatMap() {
        Stream.of(Set.class, List.class, Map.class)
            .flatMap(clazz -> Arrays.stream(clazz.getMethods())).map(method -> method.getName())
            .distinct().sorted().forEach(System.out::println);
    }
    static void mapTo() {
        Stream.of("12", "34", "56").mapToInt(s -> Integer.parseInt(s)).forEach(System.out::println);
    }
    static void collect() {
        final Supplier<List<String>> supplier = () -> new ArrayList<>(10);
        final BiConsumer<List<String>, String> accumulator = (l, t) -> l.add(t);
        final BiConsumer<List<String>, List<String>> combiner = (l1, l2) -> l1.addAll(l2);
        Stream.of("a", "b", "c").collect(supplier, accumulator, combiner)
        .forEach(System.out::println);
    }
}
