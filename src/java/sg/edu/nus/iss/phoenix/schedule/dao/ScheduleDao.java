package sg.edu.nus.iss.phoenix.schedule.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;


public interface ScheduleDao {
    
    public abstract List<ProgramSlot> loadAll() throws SQLException;

    public abstract void save(ProgramSlot valueObject) throws NotFoundException, SQLException;

    public abstract void create(ProgramSlot valueObject) throws SQLException;

    public abstract void delete(ProgramSlot valueObject) throws NotFoundException, SQLException;
   // public abstract List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException;
    /**
     * createAnnualObject-method. This method is used when the Dao class needs
     * to create new value object instance.
     *
     * @return
     */
  //  public abstract AnnualSchedule createAnnualObject();

    /**
     * createWeeklyObject-method. This method is used when the Dao class needs
     * to create new value object instance.
     *
     * @return
     */
   // public abstract WeeklySchedule createWeeklyObject();

    /**
     * create-Schedule-method. This method is used when the Dao class needs to
     * create new program Slot instance. This will persist a new object of
     * program slot.
     *
     * @param programSlot This parameter contains the instance of ProgramSlot to
     * be persisted.
     * @param assignedBy This parameter is used to add the assign a presenter or
     * producer to the new schedule.
     * @throws java.sql.SQLException
     */
   // public abstract void createSchedule(ProgramSlot programSlot, String assignedBy) throws SQLException;

    /**
     * Create Annual Schedule method. This methods is used when the Dao class
     * needs to create a new annual schedule.
     *
     * @param year This parameter contains the year of which the annual schedule
     * is created.
     * @param assignedBy This parameter contains the user who has been assigned
     * the annual schedule.
     * @throws SQLException
     */
    //public void createAnnualSchedule(String year, String assignedBy) throws SQLException;

    /**
     * LoadsProgramSlot method. This will read all contents (program slots) from
     * database table and build a List containing Program Slots. The List will
     * be in range between Monday and Sunday inclusive (7 days).
     *
     * @param date This parameter contains the start date of the week of which
     * the program slots are to be loaded.
     * @return
     * @throws SQLException
     * @throws NotFoundException
     */
  //  public abstract List<ProgramSlot> loadProgramSlot(String date)throws SQLException, NotFoundException;

    /**
     * LoadAll-method. This will read all contents from database table and build
     * a List containing valueObjects. Please note, that this method will
     * consume huge amounts of resources if table has lot's of rows. This should
     * only be used when target tables have only small amounts of data.
     *
     * @return
     * @throws java.sql.SQLException
     */
    //public abstract HashMap<Integer, List<WeeklySchedule>> loadAll() throws SQLException;

    /**
     * modify Schedule method. This will allow the dao to to save the new values
     * of program slot in the database. Modify can not be used to create new
     * instances in database, so upper layer must make sure that the primary-key
     * is correctly specified. Primary-key will indicate which instance is going
     * to be updated in database. If modify can not find matching row,
     * NotFoundException will be thrown.
     *
     * @param programSlot
     * @param assignedBy
     * @throws SQLException
     * @throws NotFoundException
     */
  //  public abstract void modifySchedule(ProgramSlot programSlot, String assignedBy) throws SQLException, NotFoundException;

    /**
     * delete-Schedule method. This method will remove the weekly schedule from
     * database as identified by primary-key in supplied valueObject. Once
     * valueObject has been deleted it can not be restored by calling save.
     * Restoring can only be done using create method but if database is using
     * automatic surrogate-keys, the resulting object will have different
     * primary-key than what it was in the deleted object. If delete can not
     * find matching row, NotFoundException will be thrown.
     *
     * @param weeklySchedule
     * @throws SQLException
     * @throws NotFoundException
     */
    //public void deleteSchedule(WeeklySchedule weeklySchedule) throws SQLException, NotFoundException;

    /**
     * delete Program Slot method. Deletes the program slot for the This method
     * will remove the weekly schedule from database as identified by
     * primary-key in supplied valueObject. Once valueObject has been deleted it
     * can not be restored by calling save. Restoring can only be done using
     * create method but if database is using automatic surrogate-keys, the
     * resulting object will have different primary-key than what it was in the
     * deleted object. If delete can not find matching row, NotFoundException
     * will be thrown.
     *
     * @param programSlot
     * @throws SQLException
     * @throws NotFoundException
     */
   // public void deleteProgramSlot(ProgramSlot programSlot) throws            SQLException, NotFoundException;

    /**
     * update weekly schedule method. This will allow the dao to to save the new
     * values of program slot in the database. Update can not be used to create
     * new instances in database, so upper layer must make sure that the
     * primary-key is correctly specified. Primary-key will indicate which
     * instance is going to be updated in database. If modify can not find
     * matching row, NotFoundException will be
     *
     * @param week
     * @throws SQLException
     */
    //public void updateWeeklySchedule(WeeklySchedule week) throws SQLException;

    /**
     * check exists method. checks if a particular object of weekly schedule is
     * already existing form the list of weekly schedule object in database. If
     * yes then it will return true otherwise returns true.
     *
     * @param targetDate
     * @return
     * @throws SQLException
     */
    //public boolean checkExists(String targetDate) throws SQLException;

}
