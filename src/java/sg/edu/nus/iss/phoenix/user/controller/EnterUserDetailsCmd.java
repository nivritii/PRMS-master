/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.controller;
import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.delegate.UserDelegate;

/**
 *
 * @author koushik
 * Mohan
 */
@Action("enteruserdetail")
public class EnterUserDetailsCmd implements Perform  {
     private static final Logger logger = Logger.getLogger(EnterUserDetailsCmd.class.getName());

    @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
                User user = new User();
                UserDelegate userDelegate = new UserDelegate();
                user.setId(req.getParameter("name"));
                user.setName(req.getParameter("name")+","+" the "+req.getParameter("role"));
                user.setPassword(req.getParameter("password"));
                String roles = req.getParameter("role");
                /*
                    Each Indivaidual role is separated by comma separated values
                */
                String[] roleList = roles.split(",");
                ArrayList<Role> role = new ArrayList<Role>();
                for(String individualRole :roleList ){
                    Role r = new Role();
                    r.setRole(individualRole);
                    role.add(r);
                }
                String ins = (String) req.getParameter("ins");
                
                user.setRoles(role);
                try{
                    if (ins.equalsIgnoreCase("true")) {
                        userDelegate.createUser(user);
                    } else {
                        userDelegate.modifyUser(user);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                /*
                    Refresh the list after addition
                */
                List<User> data = userDelegate.manageUser();
                req.setAttribute("users", data);
                
                return "/pages/crudUser.jsp";

    }
}
