package ru.netology;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldSubmitForm() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Москва");
        LocalDate date=LocalDate.now().plusDays(3);
        String dayVisit=date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("Елифанова Анастасия");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно!")).waitUntil(visible,15000);
    }

    @Test
    void shouldSubmitFormWithDropDownList() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Ка");
        $$(".menu-item").find(exactText("Казань")).click();
        LocalDate date=LocalDate.now().plusDays(3);
        String dayVisit=date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
        $("[data-test-id=date] input").setValue(dayVisit);
        $("[data-test-id=name] input").setValue("Елифанова Анастасия");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно!")).waitUntil(visible,15000);
    }

    @Test
    void shouldSubmitFormWithCalendar() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Самара");
        LocalDate date=LocalDate.now().plusDays(7);
        String year= String.valueOf(date.getYear());
        int numberMonth=date.getMonthValue();
        String[] month={"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        String day= String.valueOf(date.getDayOfMonth());
        $("[data-test-id=date] button").click();
        String  calendarText=$(".calendar__name").innerText();

        while (calendarText.contains(year)==false)
        {
            $("[data-step='12']").click();
            calendarText =$(".calendar__name").innerText();
        }

        while (calendarText.contains(month[numberMonth-1])==false)
        {
            $("[data-step='1']").click();
            calendarText =$(".calendar__name").innerText();
        }

        ElementsCollection dayInCalendar=$$(".calendar__day");
        for (int i=0; i<dayInCalendar.size(); i++){
            if (dayInCalendar.get(i).innerText().equals(day)){
                dayInCalendar.get(i).click();
                break;
            }
        }

        $("[data-test-id=name] input").setValue("Елифанова Анастасия");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(withText("Успешно!")).waitUntil(visible,15000);
    }

}
