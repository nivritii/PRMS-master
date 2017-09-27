/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ProgramSlot {

    private String duration;
    private String dateOfProgram;
    private String startTime;
    private String endTime;
    private String programName;
    private String presenter;
    private String producer;

    /**
     * Constructors. The first one takes no arguments and provides the most
     * simple way to create object instance. The another one takes one argument,
     * which is the primary key of the corresponding table.
     */
    public ProgramSlot() {
    }

    public ProgramSlot(String programName) {
        this.programName = programName;
    }

    public ProgramSlot(String duration, String dateOfProgram) {
        this.duration = duration;
        this.dateOfProgram = dateOfProgram;
    }

    public ProgramSlot(String duration, String dateOfProgram, String startTime, String endTime, String programName, String presenter, String producer) {
        this.duration = duration;
        this.dateOfProgram = dateOfProgram;
        this.startTime = startTime;
        this.endTime = endTime;
        this.programName = programName;
        this.presenter = presenter;
        this.producer = producer;
    }

    
    /**
     * Get- and Set-methods for persistent variables. The default behaviour does
     * not make any checks against malformed data, so these might require some
     * manual additions.
     *
     * @return
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDateOfProgram() {
        return dateOfProgram;
    }

    public void setDateOfProgram(String dateOfProgram) {
        this.dateOfProgram = dateOfProgram;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
   
    
    public String getEndTime() {
        return endTime;
    }
    
     public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getProducer() {
        return producer;
    }

    /**
     * Check Overlap Method. This method will check if the newly created program
     * slot overlaps with any already existing program slots.
     *
     * @param startTime This parameter consists of the start time of the program
     * slot to be checked for overlap.
     * @param duration This parameter consists of the duration of the program
     * slot to be checked for overlap.
     * @return Returns error message which will be displayed on the UI in the
     * form of pop-up.
     * @throws ParseException
     */
    public String checkOverlap(String startTime, String duration) throws ParseException {
        final long ONE_MINUTE_IN_MILLIS = 60000;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String hours[] = duration.split(":");
        int hour = Integer.parseInt(hours[0]);
        int minutes = Integer.parseInt(hours[1]);

        int minutes_add = (hour * 60) + minutes;
        Date date1 = format1.parse(this.startTime);
        Date date2 = format1.parse(startTime);

        Calendar c_startTime = Calendar.getInstance();
        c_startTime.setTime(date1);

        long t = c_startTime.getTimeInMillis();

        Date afterAddingTenMins = new Date(t + (minutes_add * ONE_MINUTE_IN_MILLIS));

        if ((date1.equals(date2)) || ((date2.before(afterAddingTenMins)) && date2.after(date1))) {
            return "Slot occupied. Select another slot!!";
        } else {
            return null;
        }

    }
}
