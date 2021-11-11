package com.project.calendar.core.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component //Bean으로 주입을 위함
public class BCryptEncryptor implements Encryptor {
    @Override                       //랜덤하게 salt를 생성(난수? 같은 느낌)
    public String encrypt(String origin) {
        return BCrypt.hashpw(origin, BCrypt.gensalt());
    }

    @Override
    public boolean isMatch(String origin, String hashed) {
        try {
            return BCrypt.checkpw(origin, hashed);
        } catch (Exception e) { // 여러 예외가 있다.
            return false;
        }
    }
}
