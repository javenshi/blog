package com.centling.utils.exception;

/**
 * Created by lin on 17-6-15.
 */
public final class Preconditions {

    public static void checkArgument(boolean expression,ApplicationErrorEnum QHErrorEnum) {
        if(!expression) {
            throw new BusinessException(QHErrorEnum);
        }
    }

    public static <T> T checkNotNull(T reference, ApplicationErrorEnum QHErrorEnum) {
        if(reference == null) {
            throw new BusinessException(QHErrorEnum);
        } else {
            return reference;
        }
    }

    public static void checkState(boolean expression,ApplicationErrorEnum QHErrorEnum) {
        if(!expression) {
            throw new BusinessException(QHErrorEnum);
        }
    }
}
