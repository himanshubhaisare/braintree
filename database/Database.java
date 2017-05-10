package database;

import resource.Card;
import resource.User;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Database {

    private static Map<String, User> users = new TreeMap<String, User>();

    private static Map<String, Card> cards = new HashMap<String, Card>();

    public static Map<String, User> getUsers() {
        return users;
    }

    public static void setUsers(Map<String, User> users) {
        Database.users = users;
    }

    public static User getUser(String name) {
        return users.get(name);
    }

    public static void setUser(User user) {
        Database.users.put(user.getName(), user);
    }

    public static Map<String, Card> getCards() {
        return cards;
    }

    public static Card getCard(String number) {
        return cards.get(number);
    }

    public static void setCards(Map<String, Card> cards) {
        Database.cards = cards;
    }

    public static void setCard(Card card) {
        Database.cards.put(card.getNumber(), card);
    }

    public static void clearAll() {
        Database.users.clear();
        Database.cards.clear();
    }
}
