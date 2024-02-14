package com.softuni.usersystem.annotations;

import javax.validation.ConstraintValidatorContext;

public enum AnnotationsUtil {
    ;

    public static void setErrorMessage(final ConstraintValidatorContext context, final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }
}
