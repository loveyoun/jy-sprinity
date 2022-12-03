package sprinity.jysprinity.controller;

import sprinity.jysprinity.domain.User;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {

    public static final String USER_SESSION_KEY = "sessionUser";

    //로그인 유무 알 수 있는
    public static boolean isLoginUser(HttpSession session) {
        Object sessionUser = session.getAttribute(USER_SESSION_KEY);
        if (sessionUser == null) return false;
        return true;
    }
    //로그인한 사용자에 대해서 user 객체 받아오기
    public static User getUserFromSession(HttpSession session){
        if(!isLoginUser(session)) return null;
        return (User)session.getAttribute(USER_SESSION_KEY);
    }

}
