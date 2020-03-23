package com.xianjinyi.gameProvider.java8;

import lombok.Data;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author: xianjinyi
 * @date 2020/02/25
 * 接口的默认方法
 */
public class FormulaImpl implements Formula{


    @Override
    public double calculate(int a) {
        return a + a;
    }

//    @Override
//    public double sqrt(int a) {
//        return Math.sqrt(a) + 100;
//    }


//    public static void main(String[] args) {
//        FormulaImpl formula = new FormulaImpl();
//        System.out.println(formula.calculate(16));
//        System.out.println(formula.sqrt(16));


        // 排序 lambda表达式
//        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return b.compareTo(a);
//            }
//        });
//        Collections.sort(names,(String a, String b)-> {return b.compareTo(a);});
//
//        Collections.sort(names,(String a, String b)-> b.compareTo(a));

//        names.sort((a, b) -> b.compareTo(a));

//        Collections.sort(names,(a, b)-> b.compareTo(a));


//        names.sort(new Comparator<String>() {
//                       @Override
//                       public int compare(String a, String b) {
//                           return b.compareTo(a);
//                       }
//                   });

//        System.out.println(names);

        // test2
//        MathOperation addition = (int a, int b) -> a + b;
//
//        System.out.println("10 + 5 = " + addition.operation(10,5));

//    }

    interface MathOperation {
        int operation(int a, int b);
    }


//    public static void main(String[] args) {
//        Predicate<Integer> predicate1 = (a) -> a>100;
//        Predicate<Integer> predicate2 = (s) -> s>30;
//
//        predicate1.and(predicate2).test(120);
//
//        Predicate<String> predicate3 = Predicate.isEqual("aa");
//        predicate3.test("bb");

//        System.out.println(predicate.test(12));
//        System.out.println(predicate.negate().test(12));;


//        Function<Integer, Integer> function = Function.identity();
//        Function<Integer,Integer> function = a->a*a;
//        Function<Integer,Integer> function2 = a->a-1;
//       // System.out.println(function.apply(3));;
//
//        Integer apply = function.compose(function2).apply(3);
//        System.out.println(apply);

//        Comparator<Integer> comparator = (p1, p2) -> p1.compareTo(p2);
//        System.out.println(comparator.compare(400, 400));;             // > 0
//        System.out.println(comparator.reversed().compare(400, 400));;
          // < 0
//        Optional<String> optional = Optional.of(null);
//    }

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }



    // 访问实例字段
    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

    // Stream
//    public static void main(String[] args) {
//        List<String> stringList = new ArrayList<>();
//        stringList.add("ddd2");
//        stringList.add("aaa2");
//        stringList.add("bbb1");
//        stringList.add("aaa1");
//        stringList.add("bbb3");
//        stringList.add("ccc");
//        stringList.add("bbb2");
//        stringList.add("ddd1");
//
//
//        Optional<String> reduced = stringList.stream()
////                .filter((s) -> s.startsWith("b"))
////                .reduce((s1, s2) -> s1 + "#" + s2);
//                .reduce( String::concat);
//        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
//        Optional<String> reduce = Stream.of("A", "B", "C", "D").reduce(String::concat);
//
//        // reduced.ifPresent(System.out::println);//aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2
//        System.out.println(stringList);
//
////        Person person = new Person();
////        person.setName("11");
////
////        Person person2 = new Person();
////        person2.setName("22");
////
////        Optional<Person> optional = Stream.of(person, person2).reduce((p1, p2) -> {
////            p1.setName(p1.name + p2.name);
////            return p1;
////        });
////        optional.ifPresent(System.out::println);
//    }

//    @Data
//    static class Person{
//        private String name;
//        private String age;
//    }


//    public static void main(String[] args) {
//        Map<Integer, String> map = new HashMap<>();
//
//        for (int i = 0; i < 10; i++) {
//            map.putIfAbsent(i, "val" + i);
//        }
//
//
////        map.computeIfPresent(3, (num, val) -> val + num);
////        map.get(3);             // val33
////
////        map.computeIfPresent(9, (num, val) -> null);
////        map.containsKey(9);     // false
////
////        map.computeIfAbsent(23, num -> "val" + num);
////        map.containsKey(23);    // true
////
////        map.computeIfAbsent(3, num -> "bam");
////        map.get(3);             // val33
////
////        map.remove(3, "val3");
////
////        map.getOrDefault(42, "not found");  // not found
//
//        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
//        System.out.println(map);
//    }

//
//    public static void main(String[] args) {
//
////        Clock clock = Clock.systemDefaultZone();
////        long millis = clock.millis();
////        System.out.println(millis);//1552379579043
////
////
////        Instant instant = clock.instant();
////        System.out.println(instant);
////
////        Date legacyDate = Date.from(instant); //2019-03-12T08:46:42.588Z
////        System.out.println(legacyDate);//Tue Mar 12 16:32:59 CST 2019
//
//
////        //输出所有区域标识符
////        System.out.println(ZoneId.getAvailableZoneIds());
////
////        ZoneId zone1 = ZoneId.of("Europe/Berlin");
////        ZoneId zone2 = ZoneId.of("Brazil/East");
////        System.out.println(zone1.getRules());// ZoneRules[currentStandardOffset=+01:00]
////        System.out.println(zone2.getRules());// ZoneRules[currentStandardOffset=-03:00]
//
////        LocalTime now1 = LocalTime.now();
////        LocalTime now2 = LocalTime.parse("18:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
////        System.out.println(now1.isBefore(now2));  // false
////
////        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
////        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
////
////        System.out.println(hoursBetween);       // -3
////        System.out.println(minutesBetween);     // -239
//
//        LocalTime late = LocalTime.of(23, 59, 59);
//        System.out.println(late);       // 23:59:59
//        DateTimeFormatter germanFormatter =
//                DateTimeFormatter
//                        .ofLocalizedTime(FormatStyle.SHORT)
//                        .withLocale(Locale.GERMAN);
//
//        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
//        System.out.println(leetTime);   // 13:37
//    }


//    public static void main(String[] args) {
//        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
//
//        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
//        System.out.println(dayOfWeek);      // WEDNESDAY
//
//        Month month = sylvester.getMonth();
//        System.out.println(month);          // DECEMBER
//
//        long minuteOfDay = sylvester.getLong(ChronoField.HOUR_OF_DAY);
//        System.out.println(minuteOfDay);    // 1439
//
//        Instant instant = sylvester
//                .atZone(ZoneId.systemDefault())
//                .toInstant();
//
//        Date legacyDate = Date.from(instant);
//        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014
//    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Hints {
        Hint[] value();
    }

    @Repeatable(Hints.class)
    @interface Hint {
        String value();
    }

//    @Hints({@Hint("hint1"), @Hint("hint2")})
//    class Person {}

    @Hint("hint1")
    @Hint("hint2")
    class Person {}

    public static void main(String[] args) {
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println(hint);
        // null
        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);  // 2

        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);          // 2
    }
}
