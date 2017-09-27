package sg.edu.nus.iss.phoenix.schedule.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;


public interface ScheduleDao {
    
    public abstract List<ProgramSlot> loadAll() throws SQLException;

    public abstract void save(ProgramSlot valueObject) throws NotFoundException, SQLException;

    public abstract void create(ProgramSlot valueObject) throws SQLException;
    
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
    public abstract void delete(ProgramSlot valueObject) throws NotFoundException, SQLException;
    
    public abstract List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException;
   
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
    public abstract List<ProgramSlot> loadWeeklySlots(String date)throws SQLException, NotFoundException;

    /**
     * check exists method. checks if a particular object of weekly schedule is
     * already existing form the list of weekly schedule object in database. If
     * yes then it will return true otherwise returns true.
     *
     * @param targetDate
     * @return
     * @throws SQLException
     */
    public boolean checkExists(String targetDate) throws SQLException;
    
    /**
     * LoadAllWS-method. This will read all contents from database table and build
     * a List containing valueObjects. Please note, that this method will
     * consume huge amounts of resources if table has lot's of rows. This should
     * only be used when target tables have only small amounts of data.
     *
     * @return
     * @throws java.sql.SQLException
     */
    public abstract List<WeeklySchedule> loadAllWS() throws SQLException;

}
