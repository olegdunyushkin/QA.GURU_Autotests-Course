package tests.demoqa.data;

import net.datafaker.Faker;
import tests.demoqa.utils.AdditionalRandomUtilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RegistrationFormTestData {

    private final Faker faker = new Faker();
    private final AdditionalRandomUtilities randomUtils = new AdditionalRandomUtilities();

    public final String formTitle = "Practice Form";
    public final String successModalTitle = "Thanks for submitting the form";
    public final String validatedFormClass = "was-validated";
    public final String invalidBorderColor = "rgba(220, 53, 69, 1)";

    public final String maleGender = "Male";
    public final String femaleGender = "Female";
    public final String otherGender = "Other";

    public final String firstName = faker.name().firstName();
    public final String lastName = faker.name().lastName();
    public final String email = faker.internet().emailAddress();
    public final String invalidEmail = faker.number().digits(3);

    public final String phone = faker.phoneNumber().subscriberNumber(10);
    public final String invalidPhone = "wwwwwwwwww";

    public final String gender = randomUtils.getRandomGender();
    public final LocalDate birthDate = faker.timeAndDate().birthday(18, 99);
    public final String birthDay = birthDate.format(DateTimeFormatter.ofPattern("dd"));
    public final String birthMonth = birthDate.format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH));
    public final String birthYear = String.valueOf(birthDate.getYear());

    public final String subject = randomUtils.getRandomSubject();
    public final List<String> hobbies = List.of(randomUtils.getRandomHobby());
    public final String picturePath = "1.jpg";
    public final String currentAddress = faker.address().streetAddress();

    public final String state = randomUtils.getRandomState();
    public final String city = randomUtils.getRandomCityForState(state);

    public String fullName() {
        return firstName + " " + lastName;
    }

    public String birthDate() {
        return birthDay + " " + birthMonth + "," + birthYear;
    }

    public String hobbiesValue() {
        return String.join(", ", hobbies);
    }

    public String stateAndCity() {
        return state + " " + city;
    }

    public Map<String, String> expectedResults() {
        Map<String, String> expectedResults = new LinkedHashMap<>();
        expectedResults.put("Student Name", fullName());
        expectedResults.put("Student Email", email);
        expectedResults.put("Gender", gender);
        expectedResults.put("Mobile", phone);
        expectedResults.put("Date of Birth", birthDate());
        expectedResults.put("Subjects", subject);
        expectedResults.put("Hobbies", hobbiesValue());
        expectedResults.put("Picture", picturePath);
        expectedResults.put("Address", currentAddress);
        expectedResults.put("State and City", stateAndCity());
        return expectedResults;
    }
}
