package Lesson2_4;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 1. Создать коллекцию типа List. Наполнить ее значениями и
 * вывести значения в консолько при помощи ее метода forEach.
 * <p>
 * 2. Создать утилитарный метод forItem. Метод принимает два параметра:
 * Коллекция Set<String> и консьюмер типа Consumer<String>.
 * Внутри метода проитерироваться по коллекции и для каждого элемента
 * выполнить метод консьюмера accept, который выводит значение элемента в консоль.
 * <p>
 * 3. Создать утилитарный метод doubleUp. Метод принимает два параметра:
 * значение типа int и консьюмер типа Supplier<Integer>.
 * Внутри метода выполнить метод саплаера get, который возвращает множитель и
 * затем переданное значение на него умножается. Фукнция возращает результат произведения.
 * <p>
 * 4. Создать метод findAllChars. Метод принимает два параметра: String target и char toFind.
 * Первый параметр является входной строкой, а второй - символ, который необходимо найти в входящей строке.
 * Учесть что искомый символ может повторяется (напр.: 'ccch').
 * Необходимо найти все повторения и вернуть в виде конкатенированной строки обвернутый в Optional.
 * Если ни одного совпадения не найдено, тогда необходимо вернуть пустой Optional.
 * Пример выполнения: Optional<String> opt = findAllChars("ccch", 'c'); opt.get(); // вернет "ссс".
 * <p>
 * 5. Создать окно для клиентской части чата: большое текстовое поле для отображения переписки в центре окна.
 * Однострочное текстовое поле для ввода сообщений и кнопка для отсылки сообщений на нижней панели.
 * Сообщение должно отсылаться либо по нажатию кнопки на форме, либо по нажатию кнопки Enter.
 * При «отсылке» сообщение перекидывается из нижнего поля в центральное. (ОПЦИОНАЛЬНО)
 */


public class Main {
    public static void main(String[] args) {
        doTack1();
        doTack2();
        doTack3();
        doTack4();
    }

    /**
     * задание №1
     */
    static void doTack1() {
        System.out.println("Задание 1 : ");
        List<Integer> sl = new ArrayList<>();
        sl.add(2);
        sl.add(3);
        sl.add(4);
        sl.add(5);
        sl.add(5);
        sl.add(5);
        sl.add(5);
/** как это работает и что означает совсем не понятно. Понятно, что можно краткую запись создать,
 * а можно полную...
 * */
//        sl.forEach(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println(integer);
//
//            }
//        });


//        sl.forEach(integer -> System.out.println(integer));


//        for (Integer integer : sl) {
//            System.out.println(integer);
//        }

        for (Integer integer : sl) System.out.println(integer);
    }

    /**
     * задание №2
     */
    static void doTack2() {
        System.out.println("Задание 2 :  ");
        Set<String> st = Set.of("1", "2", "33", "455");
//        forItem(st, new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });

//        forItem(st, s -> System.out.println(s));

        forItem(st, System.out::println);

    }

    static void forItem(Set<String> values, Consumer<String> action) {
        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            action.accept(val);
        }
    }


    /**
     * задание №3
     */
    static void doTack3() {
        System.out.println(" Задание 3 : ");
//        doubleUp(2, new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                return 2;
//            }
//        });

        System.out.println(doubleUp(10, () -> 2));
        System.out.println(doubleUp(10, getMultyiplier()));


    }

    static Supplier<Integer> getMultyiplier() {
//        return new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                return 2;
//            }
//        };
        return () -> 2;
    }

    static int doubleUp(int v, Supplier<Integer> sup) {
        return v * sup.get();

    }

    /**
     * задание №4
     */
    static void doTack4() {
        System.out.println("Задание №4 : ");
        System.out.println(findAllChars("fdfdffddgdwweefffrggdss", 'f'));
        System.out.println(findAllChars("fdfdffddgdwweefffrggdss", 'f').get());


    }

    static Optional<String> findAllChars (String target, char toFind){
        char[] chars = target.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == toFind) {
                sb.append(chars[i]);
            }
        }
        if (sb.length() > 0) {
            return Optional.of(sb.toString());

        }
        return Optional.empty();

    }

}
