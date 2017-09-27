/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.delegate;
import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.authenticate.service.AuthenticateService;
import sg.edu.nus.iss.phoenix.user.service.UserService;
/**
 *
 * @author Surethiran
 * Mohan
 */

public class UserDelegate  {
private AuthenticateService service;
private UserService userService;
public UserDelegate()
{
    service = new AuthenticateService();
    userService = new UserService();
}
public List<User> manageUser()
{
    return service.manageUser();
}
/*
     Method to Create User
*/
public void createUser(User user){
    userService.processCreate(user);
}


public void modifyUser(User user){
    userService.processModify(user);        
}
 
public void processDelete(String name)
    {
        UserService service = new UserService();
	service.processDelete(name);
    }
}