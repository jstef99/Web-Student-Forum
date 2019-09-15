package com.jstef.StudentForum.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//validator class for @StrongPassword annotation
//valid password must be consisted of at least one character from each range:
//a-z, A-Z. 0-9, !?@$#.
public class PasswordValidator implements ConstraintValidator<StrongPassword,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value!=null && value.length()>3 && value.matches("(.*)[a-z]+(.*)") &&
                value.matches("(.*)[A-Z]+(.*)") && value.matches("(.*)[0-9]+(.*)") && value.matches("(.*)[!?@#.$]+(.*)");
    }
}
