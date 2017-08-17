package com.helospark.tomcatmanagerhoneypot.html.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyMultipartFileValidator.class)
@Documented
public @interface NotEmptyMultipartFile {
    public String message() default "multipart file may not be null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
