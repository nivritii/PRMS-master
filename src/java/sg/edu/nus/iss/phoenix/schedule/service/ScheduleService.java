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

    /**
     * addEditSchedule method. This method is used to get the list of program slots for given week parameterized as date. 
     * @param date
     *          This parameter consists of the date by which the program slots list will be returned
     * @return 
     *          List of program Slots
     * @throws SQLException
     */
   /* public List<ProgramSlot> addEditSchedule(String date) throws SQLException {
        List<ProgramSlot> data = null;
        try {
            data = scheduledao.loadProgramSlot(date);
        } catch (Exception ex) {
            Logger.getLogger(ReviewSelectScheduleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
*/
    /**
     * modify schedule method. This method is used to service the request of modifying a program slot 
     * to DAO class. 
     * @param programSlot
     *              This parameter consists of the program slot to be modified. 
     * @param assignedBy
     *              This parameter consists of the user that has assigned this program slot.
     */
   /* public void modifySchedule(ProgramSlot programSlot, String assignedBy) {
        try {
            scheduledao.modifySchedule(programSlot, assignedBy);
        } catch (Exception ex) {
            Logger.getLogger(ReviewSelectScheduleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
    /**
     * Process Create method. This method is used to service the create and add request of the program slot to the db via dao class. 
     * @param programSlot
     *              This parameter consists of the program slot to be inserted into the database. 
     * @param assignedBy
     *              This parameter consists of the user that has assigned this program slot.
     * @throws SQLException
     */
    /*public void processCreate(ProgramSlot programSlot, String assignedBy) throws SQLException {
        try {
            scheduledao.createSchedule(programSlot, assignedBy);
        } catch (Exception ex) {
            Logger.getLogger(ReviewSelectScheduleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
    /**
     * Process Delete method. This method is used to service the delete request of a weekly schedule to the do class. It takes start
     * date as a parameter. 
     * @param startDate
     *          This parameter consists of the start date of the weekly schedule.
     */
   /* public void processDelete(String startDate) {
        try {
            WeeklySchedule ws = new WeeklySchedule(startDate);
            scheduledao.deleteSchedule(ws);

        } catch (Exception ex) {
            Logger.getLogger(ReviewSelectScheduleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
    /**
     * check overlap method. This method checks if there is any overlap between two program slots. 
     * The new program slot sends its start date and duration to this method
     * @param startTime
     *              This parameter consists of the start time of the program slot. 
     * @param duration
     *              This parameter consists of the duration of the program slot. 
     * @return
     */
   /* public String checkOverlap(String startTime, String duration) {
        String checkOverlap = null;
        try {
            String weekStartDate = FCUtilities.getFirstDayOfWeek(startTime);
            if (weekStartDate != null) {
                List<ProgramSlot> list_program = scheduledao.loadProgramSlot(weekStartDate);
                for (ProgramSlot p : list_program) {
                    checkOverlap = p.checkOverlap(startTime, duration);
                    if (checkOverlap != null) {
                        break;
                    }
                }
            }
        } catch (NotFoundException | ParseException ex) {
            Logger.getLogger(ScheduleDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return checkOverlap;
    }
*/
    /**
     * Check if weekly schedule Exists Method. Check if Weekly schedule already exists in the database. 
     * @param targetDate
     *          This parameter consists of the target date of the week which is checked for existence
     * @return
     */
    /*public Boolean checkExists(String targetDate) {
        boolean result = false;
        try {
            result = scheduledao.checkExists(targetDate);
        } catch (Exception ex) {
            Logger.getLogger(ReviewSelectScheduleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
*/
    /**
     * Copy schedule method. Copies a program slot to a target week.
     *
     * @param programSlot
     *          This parameter consists of Program Slot to be copied
     * @param targetStartDate
     *          This parameter consists of Target date on which the program slot will be copied. 
     * @return
     * @author Sanskar Deepak
     */

    public ProgramSlot copySchedule(ProgramSlot programSlot, String targetStartDate) {
        String sourceStartDate = FCUtilities.getFirstDayOfWeek(programSlot.getDateOfProgram());
        String targetStart = FCUtilities.getFirstDayOfWeek(targetStartDate);
        ProgramSlot newPSlot = new ProgramSlot();
        try {
            int diffInDays = FCUtilities.getPeriod(sourceStartDate, targetStart);
            String targetDateofProgram = FCUtilities.addDaysToDate(programSlot.getDateOfProgram(), diffInDays);
            newPSlot.setDateOfProgram(targetDateofProgram);
            String targetStartTime = FCUtilities.addDaysToDate(programSlot.getStartTime(), diffInDays);
            newPSlot.setProgramName(programSlot.getProgramName());
            newPSlot.setDuration(programSlot.getDuration());
            newPSlot.setStartTime(targetStartTime);
        } catch (Exception ex) {
            Logger.getLogger(ReviewSelectScheduleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newPSlot;
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

    public void processDelete(String duration, String dateOfProgram) {
            
        try {
                ProgramSlot ps = new ProgramSlot(duration, dateOfProgram);
                scheduledao.delete(ps);
            } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

}
