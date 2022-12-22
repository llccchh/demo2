package com.hikvision.ga.xpluginmgr.modules.groupc.exception;

/**
 * @author liuyinghao5
 * @date 2022/11/17 14:13
 */

public class DhMessageExpireException extends NullPointerException {

    /**
     * Constructs a {@code NullPointerException} with no detail message.
     */
    public DhMessageExpireException() {
    }

    /**
     * Constructs a {@code NullPointerException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public DhMessageExpireException(String s) {
        super(s);
    }
}
