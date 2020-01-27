package by.onliner.test.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum  SecurityQuestionData {
    WHAT_WAS_THE_FIRST_CONCERT_YOU_ATTENDED("What was the first concert you attended?"),
    WHAT_IS_THE_NAME_OF_THE_STREET_WHERE_YOU_FIRST_LIVED(" What is the name of the street where you first lived? "),
    WHAT_ELEMENTARY_SCHOOL_DID_YOU_GO_TO(" What elementary school did you go to? "),
    WHERE_DID_YOU_TAKE_YOUR_FIRST_VACATION(" Where did you take your first vacation? "),
    WHAT_WAS_THE_NAME_OF_YOUR_FIRST_PET(" What was the name of your first pet? "),
    WHAT_WAS_THE_FIRST_ALBUM_YOU_BOUGHT(" What was the first album you bought? "),
    WHAT_WAS_YOUR_FIRST_CARS_MAKE_OR_MODEL(" What was your first car's make or model? "),
    WHO_WAS_YOUR_FIRST_KISS(" Who was your first kiss? "),
    WHAT_IS_YOUR_TRAVEL_AGENTS_LAST_NAME(" What is your travel agent’s last name? ");

    @Getter
    private final String questionText;

}
