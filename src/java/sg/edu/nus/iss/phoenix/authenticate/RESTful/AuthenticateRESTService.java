/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.RESTful;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.authenticate.service.AuthenticateService;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/Login")
@RequestScoped
public class AuthenticateRESTService {

    @Context
    private UriInfo context;
    
    private AuthenticateService service; 

    public AuthenticateRESTService() {
            service = new AuthenticateService();
    }

    /**
     * Retrieves representation of an instance of sg.edu.nus.iss.phoenix.authenticate.RESTful.GenericResource
     * @return an instance of java.lang.String
     */
    @GET 
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/doLogin")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    public AuthInfo doLogin(@QueryParam("username") String uname, 
            @QueryParam("password") String pwd) throws NotFoundException, SQLException{
        AuthInfo response = new AuthInfo();
        response.setUsername(uname);
        if(checkCredentials(uname, pwd)){
                response.setAuthStatus(true);
                DAOFactoryImpl factory = new DAOFactoryImpl();
                UserDao udao = factory.getUserDAO();
                User user = udao.getObject(uname);
                String str = new String();
                ArrayList<Role> rl = user.getRoles();
                for(int i =0;i<rl.size();i++){
                    if(i!=0&&i!=rl.size())str += ":";
                    Role role = rl.get(i);
                    str += role.getRole(); 
                }
                response.setRole(str);
        }else{
                response.setAuthStatus(false);	
        }
        return response;		
    }
	
    /**
     * Method to check whether the entered credential is valid
     * 
     * @param uname
     * @param pwd
     * @return
     */
    private boolean checkCredentials(String uname, String pwd){
        System.out.println("Inside checkCredentials");
        User user = new User();
        user.setId(uname);
        user.setPassword(pwd);  
        user = service.validateUserIdPassword(user);
        if (null != user) {
            System.out.println("Login Sucess!");
                    return true;
        } else {
            System.out.println("Login Failed - Wrong username/password!");
            return false;
        }
    }
    
    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
/*    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    } */
    @GET
    @Path("/user/item")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> users() throws SQLException, NotFoundException{
        DAOFactoryImpl factory = new DAOFactoryImpl();
	UserDao udao = factory.getUserDAO();
        RoleDao rdao = factory.getRoleDAO();
        //User user = udao.getObject(uname);
        //System.out.println(user.getRoles());
        return udao.loadAll();  
//        for(Role rol:user.getRoles()){
//            System.out.println(rol);
//            if(rol.getRole().equals("admin"))
//                return udao.loadAll();  
//        }
//        System.out.println("user limited priviledge");
//            return null;
    }
    
    @POST
    @Path("/user/item")
    @Produces(MediaType.APPLICATION_JSON)
    public User itemCreate(@QueryParam("id") String id,@QueryParam("name") String uname,@QueryParam("password") String password,@QueryParam("roles") String roles) throws NotFoundException, SQLException{
        DAOFactoryImpl factory = new DAOFactoryImpl();
	UserDao udao = factory.getUserDAO();
        User user = new User();
        ArrayList<Role> rl = setRoles(roles); 
        user.setId(id);
        user.setName(uname);
        user.setPassword(password);
        user.setRoles(rl);
        udao.create(user);
        return user;
    }
    
    @PUT
    @Path("/user/item")
    @Produces(MediaType.APPLICATION_JSON)
    public User itemUpdate(@QueryParam("id") String id,@QueryParam("name") String uname,@QueryParam("password") String password,@QueryParam("roles") String roles) throws NotFoundException, SQLException{
        DAOFactoryImpl factory = new DAOFactoryImpl();
	UserDao udao = factory.getUserDAO();
        User user = new User();
        ArrayList<Role> rl = setRoles(roles); 
        user.setId(id);
        user.setName(uname);
        user.setPassword(password);
        user.setRoles(rl);
        udao.save(user);
        return user;
    }
    
    private ArrayList<Role> setRoles(String roles) throws NotFoundException, SQLException{
        DAOFactoryImpl factory = new DAOFactoryImpl();
        RoleDao rd = factory.getRoleDAO();
        ArrayList<Role> rl = new ArrayList<>();
        String[] _r = roles.trim().split(":");
        System.out.println(_r);
        for (String r: _r)
            rl.add(rd.getObject(r.trim()));
        
        return rl;
    }
    
    @DELETE
    @Path("/user/item")
    @Produces(MediaType.APPLICATION_JSON)
    public User deleteItem(@QueryParam("id") String id) throws NotFoundException, SQLException{
        User user;
        UserDao ud = new DAOFactoryImpl().getUserDAO();
        user = ud.getObject(id);
        ud.delete(user);
        return user;
    }
    
    @GET
    @Path("/user/manager")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> listProducer(@QueryParam("role") String role) throws SQLException{
        UserDao ud = new DAOFactoryImpl().getUserDAO();
        List<User> pl;
        pl = ud.loadbyRole(role);
        return pl;
    }
    
    
//    @GET
//    @Path("/user/presenter")
//    @Produces(MediaType.APPLICATION_JSON)
//    public ArrayList<User> listPresenter(){
//        ArrayList<User> pl = new ArrayList<>();
//        return pl;
//    }
}
