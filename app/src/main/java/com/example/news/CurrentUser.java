package com.example.news;

public class CurrentUser {
    private static Integer id;
    private static String name;
    private static String login;
    private static String password;
    private static String role;

    public static void authorizeUser (Integer id, String name, String login, String password, String role) {
        CurrentUser.id = id;
        CurrentUser.name = name;
        CurrentUser.login = login;
        CurrentUser.password = password;
        CurrentUser.role = role;
    }

    public static String getRole() {
        return CurrentUser.role;
    }

    public static Integer getId() {
        return CurrentUser.id;
    }
}
