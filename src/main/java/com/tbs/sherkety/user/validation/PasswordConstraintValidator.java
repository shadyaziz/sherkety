package com.tbs.sherkety.user.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.WhitespaceRule;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordCustomValidator, String>{
	
	@Override
    public void initialize(PasswordCustomValidator arg0) {
    }
	
	@Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
           new LengthRule(8, 30), 
           new CharacterRule(EnglishCharacterData.UpperCase, 1),
           new CharacterRule(EnglishCharacterData.LowerCase, 1), 
           new CharacterRule(EnglishCharacterData.Digit, 1),  
           new WhitespaceRule()));
        
        return validator.validate(new PasswordData(password)).isValid();
    }
}
