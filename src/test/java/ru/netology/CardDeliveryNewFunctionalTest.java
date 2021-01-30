package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryNewFunctionalTest {
    @Test
    void shouldSubmitForm() {
        RegistrationByCardInfo dataOrderCard=DataGenerator.Registration.generateByCard();
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue(dataOrderCard.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
        String dayVisit=dataOrderCard.getDateDelivery().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue(dataOrderCard.getName());
        $("[data-test-id=phone] input").setValue(dataOrderCard.getPhone());
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно!")).shouldBe(visible);
        LocalDate date=LocalDate.now().plusDays(7);
        String dayVisitNew=date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisitNew);
        $("button.button").click();
        $(withText("Необходимо подтверждение")).waitUntil(visible,15000);
        $("[data-test-id=replan-notification] .button").click();
        $(withText("Успешно!")).waitUntil(visible,15000);

    }
}
