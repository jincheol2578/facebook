package com.koreait.facebook.user;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.koreait.facebook.common.EmailService;
import com.koreait.facebook.common.EmailServiceImpl;
import com.koreait.facebook.common.MySecurityUtils;
import com.koreait.facebook.user.model.UserEntity;
import com.sun.xml.internal.ws.encoding.HasEncoding;
import org.apache.catalina.security.SecurityUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private EmailServiceImpl email;

    @Autowired
    private MySecurityUtils mySecurityUtils;

    public int join(UserEntity param){
        String authCd = mySecurityUtils.getRandomString(5);
        System.out.println(authCd);
        String hashedPw = BCrypt.hashpw(param.getPw(),BCrypt.gensalt());
        param.setPw(hashedPw);
        param.setAuthCd(authCd);
        int result = mapper.join(param);

        if(result == 1) { //메일 보내기
            String subject = "[얼굴책] 인증메일 입니다.";
            String txt = String.format("<a href=\"http://localhost:8090/user/auth?email=%s&authCd=%s\">인증하기</a>",
                    param.getEmail(),authCd);
            email.sendMimeMessage(param.getEmail(),subject,txt);
        }
        return result;
    }
    public int auth(UserEntity param) {
            return mapper.auth(param);
    }
}
