package com.example.chuchu.common.validation.validator;

import com.example.chuchu.common.utils.MessageUtils;
import com.example.chuchu.common.validation.annotation.UserRoleValid;
import com.example.chuchu.member.entity.UserRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserRoleValidator implements ConstraintValidator<UserRoleValid, UserRole> {
    @Override
    public void initialize(UserRoleValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserRole userRole, ConstraintValidatorContext context) {
        if (userRole == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MessageUtils.getMessage("userRole.valid.message"))
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
