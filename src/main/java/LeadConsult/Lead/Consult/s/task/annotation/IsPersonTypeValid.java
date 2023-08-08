package LeadConsult.Lead.Consult.s.task.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD , PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IsPersonTypeValidValidator.class)
public @interface IsPersonTypeValid {
    String message() default "Please consider one of the following types -> student or teacher";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
