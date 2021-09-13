package com.hkaynar.couriertracking.Exception;

import java.text.MessageFormat;

/**
 * @author hkaynar on 11.09.2021
 */
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8265239511433790315L;

    public NotFoundException(Throwable throwCause) {
        super(throwCause);
    }

    public NotFoundException(String msg, Object... params) {
        this(msg, null, params);
    }

    public NotFoundException(String msg, Throwable throwCause, Object... params) {
        super(MessageFormat.format(msg, params), throwCause);
    }

    public NotFoundException(Enum<?> enumCode, Object... params) {
        this(enumCode, null, params);
    }

    public NotFoundException(Enum<?> enumCode, Throwable throwCause, Object... params) {
        super(generateMsg(enumCode, params), throwCause);
    }

    private static String generateMsg(Enum<?> enumCode, Object... params) {
        String enumMsg = MessageFormat.format(enumCode.toString(), params);
        return enumMsg;
    }
}

