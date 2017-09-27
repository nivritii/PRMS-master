/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author nivi
 */
public class UserService {
    
	DAOFactoryImpl factory;
        UserDao userDao;
/**
     * Constructors. It takes no arguments and provides the most
     * simple way to create object instance. 
     */
        
   public UserService(){
       super();
       factory = new DAOFactoryImpl();
       userDao = factory.getUserDAO();
   }
   
    /**
     * ProcessCreate Method. This method will allow to define a User form the parameter passed.
     */
    
   public void processCreate(User user){
       try{
           userDao.create(user);
       }catch(SQLException e){
           e.printStackTrace();
       }
   }
   
    /**
     * ProcessCreate Method. Method used to Amendment the User details from the passed User Object
     */
   
   //
   
    public void processModify(User user) {
         try{
             userDao.save(user);
         }catch (NotFoundException e) {
		// TODO Auto-generated catch block
                e.printStackTrace();
         }catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
     }
/**
     * ProcessDelete Method. Method used to delete the User details using the passed User Object
     */    
         
     public void processDelete(String name)
     {
         try{
             User user = new User(name);
             userDao.delete(user);
            } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
     }
      }

