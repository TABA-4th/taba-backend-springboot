package com.taba.nimonaemo.global.generator;


import java.util.UUID;

public class SignupTokenGenerator {

    public static String generateUUIDCode() {
        return UUID.randomUUID().toString();
    }
}
