package com.kay;

import java.util.Objects;

public final class Assert {
    private Assert() {
        //no-op
    }

    public static void isTrue(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void isEquals(Object actual, Object target) {
        if (!Objects.equals(actual, target)) {
            throw new IllegalArgumentException(String.format("%s is not equal to %s", actual, target));
        }
    }

    public static void isFalse(boolean expression) {
        if (expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object object) {
        if (null != object) {
            throw new IllegalArgumentException(String.format("The object [%s] is not null.", object));
        }
    }

    public static void isNotNull(Object object) {
        if (null == object) {
            throw new IllegalArgumentException("The object is null.");
        }
    }
}
