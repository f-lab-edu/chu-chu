package com.example.chuchu.common.validation.validator;

import com.example.chuchu.common.utils.MessageUtils;
import com.example.chuchu.common.validation.annotation.LevelValid;
import com.example.chuchu.member.entity.Level;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class LevelValidator implements ConstraintValidator<LevelValid, Level> {

    @Override
    public void initialize(LevelValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(Level level, ConstraintValidatorContext context) {
        if (level == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MessageUtils.getMessage("level.valid.message"))
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
