package eu.horyzont.auctions.web.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "Hasła nie pasują dosiebie";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
