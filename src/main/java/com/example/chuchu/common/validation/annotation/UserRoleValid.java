package com.example.chuchu.common.validation.annotation;

import com.example.chuchu.common.validation.validator.UserRoleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {UserRoleValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface UserRoleValid {

    String message() default "Wrong UserRole";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
