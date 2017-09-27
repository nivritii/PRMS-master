/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;

/**
 *
 * @author nivi
 */
public class WeeklySchedules {
    
    private List <WeeklySchedule> wsList;

    public List<WeeklySchedule> getWsList() {
        return wsList;
    }
 
    public void setWsList(List<WeeklySchedule> wsList) {
        this.wsList = wsList;
    }
    
}
