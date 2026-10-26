package com.isl.gbm.user.management.aspects;

import com.isl.gbm.user.management.annotations.FieldDecryption;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

import java.lang.reflect.Array;

@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class FieldDecryptionAspect {
    @Pointcut("@annotation(decryptionConfig)")
    private void annotationPointCutDefinition(final FieldDecryption decryptionConfig) {
    }

    @Before("annotationPointCutDefinition(decryptionConfig)")
    private void beforeDecryptionMethodAdvice(final JoinPoint joinPoint, final FieldDecryption decryptionConfig) throws Throwable {
        //Checking Argument Position
        Assert.state(Array.getLength(joinPoint.getArgs()) > decryptionConfig.argPosition(), "Invalid Argument Position");

        Object argument = joinPoint.getArgs()[decryptionConfig.argPosition()];

        Class<?> argumentClass = argument.getClass();

        Assert.state(BooleanUtils.isFalse(argumentClass.isPrimitive()), "Invalid Data Type to Decrypt");

        try {
            String encryptedString = "";
            if (argumentClass.equals(String.class)) {
                encryptedString = (String) argument;
                joinPoint.getArgs()[decryptionConfig.argPosition()] = decryptValue(encryptedString);
                ((MethodInvocationProceedingJoinPoint) joinPoint).proceed(joinPoint.getArgs());
            } else {
                Assert.hasLength(decryptionConfig.fieldName(), "Field Name Should Not Be Empty");

                argumentClass.getDeclaredField(decryptionConfig.fieldName());
                encryptedString = (String) FieldUtils.readDeclaredField(argument, decryptionConfig.fieldName(), Boolean.TRUE);
                FieldUtils.writeDeclaredField(argument, decryptionConfig.fieldName(), decryptValue(encryptedString), Boolean.TRUE);
            }
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Invalid Field Name");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Illegal Access to the Field");
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid Data Type to Decrypt");
        } catch (Throwable e) {
            throw e;
        }
    }

    private String decryptValue(String encryptedValue) {
        return UserAccountUtils.decodePassword(encryptedValue);
    }
}
