package com.revature.projectone.services;

import com.revature.projectone.exceptions.NotAuthorizedException;
import com.revature.projectone.exceptions.NotLoggedInException;
import com.revature.projectone.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class AuthService {

    @Autowired
    private HttpServletRequest req;

    public void guardByUserId(int userId) {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("currentUser") == null) {
            throw new NotLoggedInException("Must be logged in to perform this action");
        }

        User currentUser = (User) session.getAttribute("currentUser");
        if(userId != currentUser.getUserId()) {
            throw new NotAuthorizedException("You are not authorized to perform this action");
        }


    }

}
