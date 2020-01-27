package by.onliner.test.data.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.RandomStringUtils;

@Getter
@Setter
@Builder
@ToString
public class RCCreateAccountData {

    protected String firstName;
    protected String lastName;
    protected String monthOfBirth;
    protected String dayOfBirth;
    protected String yearOfBirth;
    protected String countryOfResidence;
    protected String email;
    protected String newPassword;
    protected String securityQuestion;
    protected String answer;
}
