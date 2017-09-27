/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.delegate.AuthenticateDelegate;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author HP
 */
@Action("manageuser")
public class ManageUserCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AuthenticateDelegate del = new AuthenticateDelegate();
        List<User> data = del.reviewUser();
        String str = "";
        for(User user:data){
            str = user.getRoles().toString();
        }
        req.setAttribute("rps", data);
        return "/pages/cruduser.jsp";
    }
}
