package by.onliner.test.data.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorMessagesData {

    FIRST_NAME_IS_REQUIRED("First name is required.", "//shared-input[@ng-reflect-label='First name/Given name']//span[@class='input-error ng-star-inserted']"),
    LAST_NAME_IS_SPACES("You cannot write a sequence of white space characters", "//shared-input[@ng-reflect-label='Last name/Surname']//span[@class='input-error ng-star-inserted']");

    @Getter
    private final String errorMessage;
    @Getter
    private final String xPath;


}
