package helper;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateUser {
    public static final String USER_API = "https://stellarburgers.nomoreparties.site/api/auth/user";

    public static String generateName() {
        return RandomStringUtils.randomAlphabetic(10);
    }
    public static String generateEmail() {
        return RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    }
    public static String generatePassword() {
        return RandomStringUtils.randomAlphabetic(10);
    }
    public static String generateInvalidPassword() {
        return RandomStringUtils.randomAlphabetic(4);
    }

}
