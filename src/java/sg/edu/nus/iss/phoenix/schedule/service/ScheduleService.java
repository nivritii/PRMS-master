/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.controller.FCUtilities;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDao;
import sg.edu.nus.iss.phoenix.schedule.dao.impl.ScheduleDaoImpl;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;


public class ScheduleService {

    DAOFactoryImpl factory;
    ScheduleDao scheduledao;

    /**
     *
     */
    public ScheduleService() {
        super();
        // TODO Auto-generated constructor stub
        factory = new DAOFactoryImpl();
        scheduledao = factory.getScheduleDAO();
    }

    public ArrayList<ProgramSlot> findAllPS() {
       ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
		try {
			currentList = (ArrayList<ProgramSlot>) scheduledao.loadAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentList;
    }
    
     public ArrayList<ProgramSlot> findWeeklySlots(String date){
    
         ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
		try {
			currentList = (ArrayList<ProgramSlot>) scheduledao.loadWeeklySlots(date);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e){
                        e.printStackTrace();
                }
		return currentList;
    }
    
    public void processModify(ProgramSlot ps) {
		
			try {
				scheduledao.save(ps);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
    
    public void processCreate(ProgramSlot ps) {
		try {
			scheduledao.create(ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void processDelete(ProgramSlot ps) {
            
        try {               
                scheduledao.delete(ps);
            } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    public ArrayList<WeeklySchedule> findAllWS() {
        
        ArrayList<WeeklySchedule> currentList = new ArrayList<WeeklySchedule>();
		try {
			currentList = (ArrayList<WeeklySchedule>) scheduledao.loadAllWS();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentList;
    }
    
   

}
