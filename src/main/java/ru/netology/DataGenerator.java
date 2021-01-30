package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationByCardInfo generateByCard() {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationByCardInfo(faker.address().city(), LocalDate.now().plusDays(3), faker.name().fullName(), faker.phoneNumber().phoneNumber());
        }

    }
}