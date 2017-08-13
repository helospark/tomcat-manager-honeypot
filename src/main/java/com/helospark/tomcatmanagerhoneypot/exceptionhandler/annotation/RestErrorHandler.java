package com.helospark.tomcatmanagerhoneypot.exceptionhandler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.helospark.tomcatmanagerhoneypot.exceptionhandler.RestHandlerControllerAdvice;

/**
 * Marker annotation to handle errors via {@link RestHandlerControllerAdvice}.
 * @author helospark
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestErrorHandler {

}
