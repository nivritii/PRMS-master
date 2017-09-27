/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

import java.util.Date;


public class WeeklySchedule {

    private String startDate;
    private String assignedBy;

    /**
     * Constructors. The first one takes no arguments and provides the most
     * simple way to create object instance. The another one takes one argument,
     * which is the primary key of the corresponding table.
     */
    public WeeklySchedule() {
    }

    public WeeklySchedule(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Get- and Set-methods for persistent variables. The default behaviour does
     * not make any checks against malformed data, so these might require some
     * manual additions.
     *
     * @return
     */
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    /**
     * Checks if Weekly Schedule already exists. This method checks if the
     * weekly schedule with a given start time is already present.
     *
     * @param startDate This parameter consists of the week start date.
     * @return Returns true if weekly schedule exists, else returns false.
     */
    public boolean checkIfExists(String startDate) {
        if (this.startDate.equals(startDate)) {
            return true;
        } else {
            return false;
        }
    }

}
