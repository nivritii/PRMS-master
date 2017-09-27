/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.presenterproducer.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author Surethiran
 */
public class ReviewSelectpresenterproducerService {
    
    DAOFactoryImpl factory;
    UserDao userDao;
    
     /**
     * Constructors. It takes no arguments and provides the most
     * simple way to create object instance. 
     */
    
    public ReviewSelectpresenterproducerService() {
    super();
      factory = new DAOFactoryImpl();
       userDao = factory.getUserDAO();
    }
    /**
     * Review Select PresenterProducer Method. This method will give the list of users based on the role. 
     * list of Presenter and/or producer. 
     * @return
     *     List(User)
     */
    
    public List<User> reviewSelectpresenterproducerService() {
        List<User> data = null;
        try{
           data = userDao.searchByRole();
        }catch (SQLException ex) {
                Logger.getLogger(ReviewSelectpresenterproducerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
