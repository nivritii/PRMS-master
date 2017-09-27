/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.delegate.UserDelegate;
/**
 *
 * @author Surethiran
 */
@Action("deleteuser")
public class DeleteUserCmd implements Perform {
    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UserDelegate del = new UserDelegate();
        String name = req.getParameter("name");
        del.processDelete(name);
     List<User> data = del.manageUser();
        req.setAttribute("users", data);
        return "/pages/crudUser.jsp";        
    }
}
        
    