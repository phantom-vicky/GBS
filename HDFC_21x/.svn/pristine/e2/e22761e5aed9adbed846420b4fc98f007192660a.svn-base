package com.isl.gbm.user.management.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProcName {
    Class<? extends Enum<?>> enumClass();  // the enum type
    String enumConstant();                 // which constant in that enum
}

