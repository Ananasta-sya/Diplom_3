package userbuilder;

import io.qameta.allure.Step;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import static helper.CreateUser.USER_API;
import static io.restassured.RestAssured.given;
import static pageobject.LogInPage.LOGIN_API;
import static pageobject.LogInPage.LOGIN_URL;
import static pageobject.RegisterPage.REGISTRATION_API;
import static pageobject.RegisterPage.REGISTRATION_URL;

public class CreateUserAPI {

    @Step("Создание пользователя и получение токена")
    public String createUserAndGetToken(User user) {
        ExtractableResponse<Response> response = given()
                .baseUri(REGISTRATION_URL)
                .header("Content-type", "application/json")
                .body(user)
                .post(REGISTRATION_API)
                .then()
                .extract();

        return response.path("accessToken").toString();
    }
    @Step("Удаление пользователя через API запрос")
    public void deleteUser(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .when()
                .delete(USER_API);
    }
    @Step("Авторизация пользователя и получение токена")
    public String logInUserAndGetToken(User user) {
        ExtractableResponse<Response> response = given()
                .baseUri(LOGIN_URL)
                .header("Content-type", "application/json")
                .body(user)
                .post(LOGIN_API)
                .then()
                .extract();

        return response.path("accessToken").toString();
    }
}
