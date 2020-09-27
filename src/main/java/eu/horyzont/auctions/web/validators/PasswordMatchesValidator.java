package eu.horyzont.auctions.web.validators;

import eu.horyzont.auctions.web.forms.RegistrationForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        RegistrationForm form = (RegistrationForm) o;
        return form.getPassword().equals(form.getRepeatedPassword());
    }
}
