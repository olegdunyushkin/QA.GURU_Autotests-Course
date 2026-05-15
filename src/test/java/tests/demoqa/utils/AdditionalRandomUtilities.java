package tests.demoqa.utils;

import net.datafaker.Faker;

public class AdditionalRandomUtilities {

    private final Faker faker = new Faker();

    public String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        return faker.options().option(genders);
    }

    public String getRandomHobby() {
        String[] hobbies = {"Sports", "Reading", "Music"};

        return faker.options().option(hobbies);
    }

    public String getRandomSubject() {
        String[] subjects = {"Maths", "English", "Chemistry", "Computer Science", "Commerce", "Arts"};

        return faker.options().option(subjects);
    }

    public String getRandomState() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return faker.options().option(states);
    }

    public String getRandomCityForState(String state) {
        if (state.equals("NCR")) {
            return faker.options().option("Delhi", "Gurgaon", "Noida");
        } else if (state.equals("Uttar Pradesh")) {
            return faker.options().option("Agra", "Lucknow", "Merrut");
        } else if (state.equals("Haryana")) {
            return faker.options().option("Karnal", "Panipat");
        } else if (state.equals("Rajasthan")) {
            return faker.options().option("Jaipur", "Jaiselmer");
        }

        return "Delhi";
    }
}
