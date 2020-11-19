package ru.appline.Logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JUVA on 14.11.2020.
 */
public class Model implements Serializable {
    private static final Model instance = new Model();
    private final Map<Integer,User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("Ivan", "Ivanov", 100000));
        model.put(2, new User("Piter", "Petrov", 120000));
        model.put(3, new User("Nikolay", "Nikolaev", 130000));
        model.put(4, new User("Anton", "Antonov", 130000));
    }
    public  void add(User user, int number) {
        model.put(number, user);
    }

    public Map<Integer,User> getUsers() {
        return model;
    }
}
