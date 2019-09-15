package com.jstef.StudentForum.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//annotation to ensure that users password is strong enough
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "In password there must be at least " +
            "one character from each range: [a-z], [A-Z], [0-9], [!?.@#$]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
