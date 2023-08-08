package LeadConsult.Lead.Consult.s.task.dto;

import LeadConsult.Lead.Consult.s.task.annotation.IsPersonTypeValid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private static final String NOT_BLANK_ERROR_MESSAGE = "The field must not be empty or null. Fill it";
    private static final String POSITIVE_OR_ZERO_ERROR_MESSAGE ="The age can't be negative number.";
    private static final String DIGIT_ERROR_MESSAGE = "Age must contain only integer digits.";

    private static final String COURSE_ALREADY_TAKEN_MESSAGE ="The desired course of yours is already taken by another teacher. Please choose another one";


    @NotBlank(message = NOT_BLANK_ERROR_MESSAGE)
    private String name;
    @PositiveOrZero(message = POSITIVE_OR_ZERO_ERROR_MESSAGE)
    @Digits(integer = 9, fraction = 0, message =DIGIT_ERROR_MESSAGE )
    private int age;
    @NotBlank(message = NOT_BLANK_ERROR_MESSAGE)
    @IsPersonTypeValid
    private String type;

}
