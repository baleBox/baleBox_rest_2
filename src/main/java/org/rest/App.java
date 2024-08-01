package org.rest;

import org.rest.configuration.MyConfig;
import org.rest.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication",
                Communication.class);
        List<User> allUsers = communication.getAll();
        System.out.println(allUsers);

        communication.getSessionId();

        // сохранение новый user
        User newUser = new User(3L, "James", "Brown", (byte) 22);
        String respSaveRes = communication.save(newUser);
        System.out.println("Ответ на сохранение: " + respSaveRes);

        // обновление
        User userUpdate = new User(3L, "Thomas", "Shelby", (byte) 33);
        String respUpdateRes = communication.update(userUpdate);
        System.out.println("Ответ на обновление: " + respUpdateRes);

        // удаление
        Long userIdDel = 3L;
        String respDelRes = communication.delete(userIdDel);
        System.out.println("Ответ на удаление: " + respDelRes);

        System.out.println("Итого: " + respSaveRes + respUpdateRes + respDelRes);
    }
}
