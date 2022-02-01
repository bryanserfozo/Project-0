package com.revature.services;

public class EncryptionService {

    public String encrypt(String initial) {
        StringBuilder encrypted;
        StringBuilder sb = new StringBuilder();
        sb = sb.append(initial);
        encrypted = sb.reverse();
        return encrypted.toString();
    }
}
