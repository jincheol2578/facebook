package com.koreait.facebook.common;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MySecurityUtils {

    public int getRandomNumber(int sNumber, int eNumber){
        return (int)(Math.random() * eNumber-sNumber + 1) + sNumber;
    }

    // len 길이만큼의 랜덤한 숫자를 문자열로 리턴
    public String getRandomString(int len){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<len; i++){
            sb.append(getRandomNumber(0,9));
        }
        return sb.toString();
    }
}
