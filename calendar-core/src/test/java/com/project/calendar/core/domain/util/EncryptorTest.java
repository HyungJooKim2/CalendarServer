package com.project.calendar.core.domain.util;

import com.project.calendar.core.util.BCryptEncryptor;
import com.project.calendar.core.util.Encryptor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EncryptorTest {

    @Test
    void bcryptTest() {
        final String origin = "my_password";
        final Encryptor encryptor = new BCryptEncryptor();
        final String hashed = encryptor.encrypt(origin);

        assertTrue(encryptor.isMatch(origin, hashed));

        final String wrong = "my_passwordd";
        assertFalse(encryptor.isMatch(wrong, hashed));
    }
}
