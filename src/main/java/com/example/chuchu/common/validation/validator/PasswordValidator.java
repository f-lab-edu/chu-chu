package com.example.chuchu.common.validation.validator;

import com.example.chuchu.common.utils.MessageUtils;
import com.example.chuchu.common.validation.annotation.Password;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final int MIN_SIZE = 8;
    private static final int MAX_SIZE = 15;
    private static final String regexPassword = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{"
            + MIN_SIZE + "," + MAX_SIZE + "}$";

    @Override
    public void initialize(Password constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        boolean isValidPassword = password.matches(regexPassword);
        if (!isValidPassword) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            MessageUtils.getMessage("password.valid.message", new Object[]{MIN_SIZE, MAX_SIZE}))
                    .addConstraintViolation();
        }
        return isValidPassword;
    }
}
