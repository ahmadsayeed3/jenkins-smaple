package com.pCloudy.annotation.values;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Shibu Prasad Panda.
 */

@Target(ElementType.TYPE) @Retention(RetentionPolicy.RUNTIME) public @interface PageName {
	String value() default "";
}

