/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDao;


public class ReviewSelectScheduleService {

    DAOFactoryImpl factory;
    ScheduleDao scheduledao;

    /**
     * Constructors. It takes no arguments and provides the most
     * simple way to create object instance. 
     */
    public ReviewSelectScheduleService() {
        super();
        // TODO Auto-generated constructor stub
        factory = new DAOFactoryImpl();
        scheduledao = factory.getScheduleDAO();
    }

}
