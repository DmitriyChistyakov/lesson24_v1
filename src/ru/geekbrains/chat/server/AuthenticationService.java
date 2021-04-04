package ru.geekbrains.chat.server;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class AuthenticationService {
    private static final Set<Entry> entries = Set.of(
            new Entry("User1", "l1", "p1"),
            new Entry("User2", "l2", "p2"),
            new Entry("User3", "l3", "p1"),
            new Entry("User4", "l4", "p1"),
            new Entry("User5", "l5", "p5")
    );

    public Optional<Entry> findEntryByCredentials(String login, String password) { //метод ищет данные аутентификации

//        Iterator<Entry> iterator = entries.iterator();
//        while (iterator.hasNext()){
//            Entry next = iterator.next();
//            if (next.getLogin().equals(login) && next.getPassword().equals(password)){
//                return Optional.of(next);
//            }
//        }
//        return Optional.empty();

//        entries.stream().filter(new Predicate<Entry>() {
//            @Override
//            public boolean test(Entry entry) {
//                return entry.getLogin().equals(login) && entry.getPassword().equals(password);
//            }
//        });

        return entries.stream()
                .filter(entry -> entry.getLogin().equals(login) && entry.getPassword().equals(password))
//                .findAny(); /**ищет все значения*/
                .findFirst(); /** ищет первое значение*/

    }




    /**Следующий метод был написан  в рамках задания 7 */
    public Optional<Entry> findEntryByNick(String name) { //метод ищет есть ли клиент с именем

        return entries.stream()
                .filter(entry -> entry.getName().equals(name))
//                .findAny(); /**ищет все значения*/
                .findFirst(); /** ищет первое значение*/


//        Iterator<Entry> iterator = entries.iterator();
//        while (iterator.hasNext()){
//            Entry next = iterator.next();
//            if (next.getName().equals(name)){
//                return Optional.of(next);
//            }
//        }
//        return Optional.empty();
    }





    public static class Entry {
        private String name;
        private String login;
        private String password;

        public Entry(String name, String login, String password) {
            this.name = name;
            this.login = login;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }
}
