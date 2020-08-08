package org.fsq.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotation required for use in generated classes
 * that will need to reference another
 * dto in the generated file.
 *
 * Using both parameters provided here
 * together allows control over expected name and package of dto
 *
 */
@Target({ElementType.FIELD})
public @interface ReferencesDto {
    String PACKAGE_DEFAULT = "-1";
    String CLASS_DEFAULT = "Dto";

    String otherDtoPackage() default PACKAGE_DEFAULT;
    String classNameAppend() default CLASS_DEFAULT;

}
