package org.fsq.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
public @interface GeneratesDto {
    String USE_DEFAULT = "-1";
    String dtoPackage() default USE_DEFAULT;
}
