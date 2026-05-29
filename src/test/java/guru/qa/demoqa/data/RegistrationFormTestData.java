package guru.qa.demoqa.data;

import net.datafaker.Faker;
import guru.qa.demoqa.utils.AdditionalRandomUtilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RegistrationFormTestData {

    private final Faker faker;
    private final AdditionalRandomUtilities randomUtils = new AdditionalRandomUtilities();

    public final String formTitle = "Practice Form";
    public final String successModalTitle = "Thanks for submitting the form";
    public final String validatedFormClass = "was-validated";
    public final String invalidBorderColor = "rgba(220, 53, 69, 1)";

    public final String maleGender = "Male";
    public final String femaleGender = "Female";
    public final String otherGender = "Other";

    public final String firstName;
    public final String lastName;
    public final String email;
    public final String invalidEmail;

    public final String phone;
    public final String invalidPhone = "wwwwwwwwww";

    public final String gender;
    public final LocalDate birthDate;
    public final String birthDay;
    public final String birthMonth;
    public final String birthYear;

    public final String subject;
    public final List<String> hobbies;
    public final String picturePath = "1.jpg";
    public final String currentAddress;

    public final String state;
    public final String city;

    public RegistrationFormTestData() {
        this(Locale.ENGLISH);
    }

    public RegistrationFormTestData(Locale locale) {
        faker = new Faker(locale);

        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        invalidEmail = faker.number().digits(3);

        phone = faker.phoneNumber().subscriberNumber(10);

        gender = randomUtils.getRandomGender();
        birthDate = faker.timeAndDate().birthday(18, 99);
        birthDay = birthDate.format(DateTimeFormatter.ofPattern("dd"));
        birthMonth = birthDate.format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH));
        birthYear = String.valueOf(birthDate.getYear());

        subject = randomUtils.getRandomSubject();
        hobbies = List.of(randomUtils.getRandomHobby());
        currentAddress = faker.address().streetAddress();

        state = randomUtils.getRandomState();
        city = randomUtils.getRandomCityForState(state);
    }

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
