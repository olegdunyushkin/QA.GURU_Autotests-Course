package tests.demoqa.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RegistrationFormTestData {

    public final String formTitle = "Practice Form";
    public final String successModalTitle = "Thanks for submitting the form";
    public final String validatedFormClass = "was-validated";
    public final String invalidBorderColor = "rgba(220, 53, 69, 1)";

    public final String firstName = "Anton";
    public final String lastName = "Baton";
    public final String email = "abn@mail.ru";
    public final String invalidEmail = "228";

    public final String phone = "1234567890";
    public final String invalidPhone = "wwwwwwwwww";

    public final String subject = "Maths";
    public final List<String> hobbies = List.of("Sports", "Reading", "Music");
    public final String picturePath = "1.jpg";
    public final String currentAddress = "SPB, Deb. 4 v 3";

    public final String maleGender = "Male";
    public final String femaleGender = "Female";
    public final String otherGender = "Other";

    public final String birthDay = "11";
    public final String birthMonth = "October";
    public final String birthYear = "2000";

    public final String state = "NCR";
    public final String city = "Delhi";

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
        expectedResults.put("Gender", maleGender);
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
