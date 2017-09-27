/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;


public class ScheduleDelegate {
    
    public ArrayList<ProgramSlot> findAllPS() {
		ScheduleService service = new ScheduleService();
		return service.findAllPS();
		
	}

   /* public List<ProgramSlot> addEditSchedule(String date) throws SQLException {
        ScheduleService service = new ScheduleService();
        List<ProgramSlot> data = service.addEditSchedule(date);
        return data;

    }

    public void modifySchedule(ProgramSlot programSlot, String assignedBy) {
        ScheduleService service = new ScheduleService();
        service.modifySchedule(programSlot, assignedBy);
    }

    public void processCreate(ProgramSlot programSlot, String assignedBy) throws SQLException {
        ScheduleService service = new ScheduleService();
        service.processCreate(programSlot, assignedBy);
    }

    public void processDelete(String startDate) {
        ScheduleService service = new ScheduleService();
        service.processDelete(startDate);
    }

    public String checkOverlap(String startTime, String duration) {
        ScheduleService service = new ScheduleService();
        return service.checkOverlap(startTime, duration);
    }

    public boolean checkExists(String targetDate) {
        ScheduleService service = new ScheduleService();
        return service.checkExists(targetDate);
    }

    public ProgramSlot copySchedule(ProgramSlot programSlot, String targetStartDate) {
        ScheduleService service = new ScheduleService();
        return service.copySchedule(programSlot, targetStartDate);
    }
*/
}
