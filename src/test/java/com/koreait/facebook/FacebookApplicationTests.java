package com.koreait.facebook;

import com.koreait.facebook.common.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class FacebookApplicationTests {

    @Autowired
    private EmailService email;

    @Test
    void sendEmail(){
        String to = "as1419_@naver.com";
        String subject = "제목";
        String txt = "내용<a href='http://localhost:8090/user/login'>ㅎㅇ</a>";
        email.sendMimeMessage(to, subject, txt);
    }






}
