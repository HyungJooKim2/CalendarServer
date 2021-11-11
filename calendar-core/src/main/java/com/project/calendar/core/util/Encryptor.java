package com.project.calendar.core.util;

/**
 * @author Larry
 */
public interface Encryptor {
    String encrypt(String origin);
    boolean isMatch(String origin, String hashed);
}
