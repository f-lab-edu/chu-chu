package com.example.chuchu.common.validation.annotation;

import com.example.chuchu.common.validation.validator.LevelValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {LevelValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface LevelValid {

    String message() default "Wrong Level";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
