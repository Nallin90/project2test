package com.revature.projectone.aspect;


import com.revature.projectone.annotations.Authenticate;
import com.revature.projectone.exceptions.NotLoggedInException;
import com.revature.projectone.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class AuthenticationAspect {

    @Autowired
    private HttpServletRequest rep;

    @Around("@annotation(authenticate)")
    public Object authenticate(ProceedingJoinPoint pjp, Authenticate authenticate) throws Throwable {
        HttpSession session = rep.getSession(false);
        //check to see if there exists a session with someone logged in or not
        if (session == null || session.getAttribute("currentUser") == null) {
            throw new NotLoggedInException("Must be logged in to perform this action");
        }
        //sets "currentUser" of the user object in session
        User currentUser = (User) session.getAttribute("currentUser");
        return pjp.proceed(pjp.getArgs());
    }


}
