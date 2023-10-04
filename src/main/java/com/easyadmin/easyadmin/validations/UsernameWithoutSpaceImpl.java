package com.easyadmin.easyadmin.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameWithoutSpaceImpl implements ConstraintValidator<UsernameWithoutSpace, String> {

    @Override
    public void initialize(UsernameWithoutSpace constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        if(userName.trim().isEmpty() || userName.contains(" ")){
            return false;
        }
        return true;
    }
    
}
