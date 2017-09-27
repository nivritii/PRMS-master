/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDao;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 * Schedule Data Access Object (DAO). This class contains all database
 * handling that is needed to permanently store and retrieve Schedule object
 * instances: AnnualSchedule, WeeklySchedule, ProgramSlot.
 */
public class ScheduleDaoImpl implements ScheduleDao {

    Connection connection;
    
    public ProgramSlot createValueObject() {
		return new ProgramSlot();
	}
    
    @Override
    public List<ProgramSlot> loadAll() throws SQLException {
		openConnection();
		String sql = "SELECT * FROM `program-slot` ORDER BY `program-name` ASC;";
		List<ProgramSlot> searchResults = listQuery(connection.prepareStatement(sql));
		closeConnection();
		System.out.println("record size"+searchResults.size());
		return searchResults;
	}
    
    /**
     *
     * @param valueObject
     * @throws NotFoundException
     * @throws SQLException
     */
    @Override
    public void save(ProgramSlot valueObject) throws NotFoundException, SQLException {
  
        openConnection();
        PreparedStatement stmt1 = null;
        
        String duration = valueObject.getDuration();
        String startTime = valueObject.getStartTime();
        String dateOfPro = valueObject.getDateOfProgram();
        String progName = valueObject.getProgramName();
        String endTime = valueObject.getEndTime();
        String presenter = valueObject.getPresenter();
        String producer = valueObject.getProducer();
        

        String query = "UPDATE `program-slot` SET `startTime` = ?, `program-name` =?, `endTime` =?, `presenter`=?, `producer`=? WHERE (`dateOfProgram` = ? and `duration` =?);";
        try {
            stmt1 = connection.prepareStatement(query);

            stmt1.setString(1, startTime);
            stmt1.setString(2, progName);
            stmt1.setString(3, endTime);
            stmt1.setString(4, presenter);
            stmt1.setString(5, producer);
            stmt1.setString(6, dateOfPro);
            stmt1.setString(7, duration);

            stmt1.executeUpdate();
         
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt1 != null) {
                stmt1.close();
            }
            closeConnection();
        }
	
    }

    /*@Override
    public AnnualSchedule createAnnualObject() {
        return new AnnualSchedule();
    }

    @Override
    public WeeklySchedule createWeeklyObject() {
        return new WeeklySchedule();
    }*/


    public ProgramSlot createProgramSlotObject() {
        return new ProgramSlot();
    }

    @Override
    public synchronized void create(ProgramSlot valueObject) throws SQLException {
       String sql = "";
		PreparedStatement stmt = null;
		openConnection();
		try {
			sql = "INSERT INTO `program-slot` (`program-name`, `dateOfProgram`, `startTime`, `duration`, `endTime`, `presenter`, `producer`) VALUES (?,?,?,?,?,?,?); ";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getProgramName());
			stmt.setString(2, valueObject.getDateOfProgram());
			stmt.setString(3, valueObject.getStartTime());
                        stmt.setString(4, valueObject.getDuration());
                        stmt.setString(5, valueObject.getEndTime());
                        stmt.setString(6, valueObject.getPresenter());
                        stmt.setString(7,valueObject.getProducer());
			int rowcount = databaseUpdate(stmt);
			if (rowcount != 1) {
				// System.out.println("PrimaryKey Error when updating DB!");
				throw new SQLException("PrimaryKey Error when updating DB!");
			}

		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
    }
    
    @Override
    public void delete(ProgramSlot valueObject) throws NotFoundException, SQLException {
            
        PreparedStatement stmt = null;
        String duration = valueObject.getDuration();
        String dateofProgram = valueObject.getDateOfProgram();
        String query = "DELETE FROM `program-slot` WHERE (`dateOfProgram` = ? and `duration` =?);";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, dateofProgram);
            stmt.setString(2, duration);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

 /*   @Override
    public List<ProgramSlot> loadProgramSlot(String date) throws NotFoundException,
            SQLException {
        openConnection();
        String sql_annual = "SELECT * FROM `program-slot` WHERE dateOfProgram >= '" + date + "' and dateOfProgram <= DATE_ADD('" + date + "', INTERVAL 7 DAY);";
        List<ProgramSlot> programSlot_list = queryProgramSlot(connection.prepareStatement(sql_annual));
        closeConnection();
        return programSlot_list;
    }
*/
    public List<ProgramSlot> queryProgramSlot(PreparedStatement query) throws SQLException, NotFoundException {
        ResultSet result = null;
        openConnection();
        List<ProgramSlot> programSlot = new ArrayList<ProgramSlot>();
        try {
            result = query.executeQuery();
            while (result.next()) {
                ProgramSlot progSlot = createProgramSlotObject();
                progSlot.setDuration(result.getTime("duration").toString());
                progSlot.setDateOfProgram(result.getString("dateOfProgram"));
                progSlot.setStartTime(result.getString("startTime"));
                progSlot.setProgramName(result.getString("program-name"));
                progSlot.setEndTime(result.getString("endTime"));
                progSlot.setPresenter(result.getString("presenter"));
                progSlot.setProducer(result.getString("producer"));
                programSlot.add(progSlot);

            }

        } finally {
            if (result != null) {
                result.close();
            }
            closeConnection();
        }

        return programSlot;
    }


    /*@Override
    public HashMap<Integer, List<WeeklySchedule>> loadAll() throws SQLException {
        openConnection();
        String sql_annual = "SELECT * FROM `annual-schedule`;";
        HashMap<Integer, List<WeeklySchedule>> list_annual = listQuery(connection
                .prepareStatement(sql_annual));

        closeConnection();
        //System.out.println("record size"+list_annual.size());
        return list_annual;
    }*/


    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

        int result = stmt.executeUpdate();

        return result;
    }

    /*
     * @return 
     * @throws java.sql.SQLException
     */
    /*protected HashMap<Integer, List<WeeklySchedule>> listQuery(PreparedStatement stmt) throws SQLException {

        HashMap<Integer, List<WeeklySchedule>> searchResults = new HashMap<>();
        ResultSet result = null;
        String sql_weekly;
        openConnection();
        try {
            result = stmt.executeQuery();
            while (result.next()) {
                AnnualSchedule temp = createAnnualObject();
                temp.setYear(result.getInt("year"));
                temp.setAssignedBy(result.getString("assingedBy"));
                sql_weekly = "SELECT * FROM `weekly-schedule` where startDate like '" + temp.getYear() + "%';";
                List<WeeklySchedule> list_weekly = listWeeklySchedule(connection
                        .prepareStatement(sql_weekly));
                searchResults.put(temp.getYear(), list_weekly);
            }

        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }

        return searchResults;
    }*/
    
    protected List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException {

		ArrayList<ProgramSlot> searchResults = new ArrayList<>();
		ResultSet result = null;
		openConnection();
		try {
			result = stmt.executeQuery();

			while (result.next()) {
				ProgramSlot temp = createValueObject();

				temp.setDuration(result.getTime("duration").toString());
                                temp.setDateOfProgram(result.getString("dateOfProgram"));
                                temp.setStartTime(result.getString("startTime"));
                                temp.setEndTime(result.getString("endTime"));
                                temp.setProgramName(result.getString("program-name"));
                                temp.setPresenter(result.getString("presenter"));
                                temp.setProducer(result.getString("producer"));

				searchResults.add(temp);
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
			closeConnection();
		}

		return (List<ProgramSlot>) searchResults;
	}

   /* protected List<WeeklySchedule> listWeeklySchedule(PreparedStatement stmt) throws SQLException {
        ArrayList<WeeklySchedule> weekly_list = new ArrayList<>();
        ResultSet result = null;
        try {
            result = stmt.executeQuery();
            while (result.next()) {
                WeeklySchedule weeklysch = createWeeklyObject();
                weeklysch.setStartDate(result.getString("startDate"));
                weeklysch.setAssignedBy(result.getString("assignedBy"));
                weekly_list.add(weeklysch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weekly_list;
    }
*/
    private void openConnection() {
        try {
            Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            this.connection = DriverManager.getConnection(DBConstants.dbUrl,
                    DBConstants.dbUserName, DBConstants.dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*@Override
    public void modifySchedule(ProgramSlot programSlot, String assignedBy) throws SQLException, NotFoundException {
        openConnection();
        PreparedStatement stmt1 = null;
        boolean checkExist = false;
        String duration = programSlot.getDuration();
        String startTime = programSlot.getStartTime();
        String dateOfPro = programSlot.getDateOfProgram();
        String progName = programSlot.getProgramName();
        String year = FCUtilities.fetchYearFromDate(dateOfPro);
        if (year == null) {
            createAnnualSchedule(year, assignedBy);
        }

        String query = "UPDATE `program-slot` SET `startTime` = ?, `program-name` =? WHERE (`dateOfProgram` = ? and `duration` =?);";
        try {
            stmt1 = connection.prepareStatement(query);

            stmt1.setString(1, startTime);
            stmt1.setString(2, progName);
            stmt1.setString(3, dateOfPro);
            stmt1.setString(4, duration);

            stmt1.executeUpdate();
            String startDate = FCUtilities.getFirstDayOfWeek(dateOfPro);

            checkExist = checkExists(startDate);
            if (checkExist == false) {
                WeeklySchedule weekObj = createWeeklyObject();
                weekObj.setAssignedBy(assignedBy);
                weekObj.setStartDate(startDate);
                updateWeeklySchedule(weekObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt1 != null) {
                stmt1.close();
            }
            closeConnection();
        }
    }*/

  /*  @Override
    public boolean checkExists(String startDate) throws SQLException {
        openConnection();
        String query = "Select * from `weekly-schedule`;";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            List<WeeklySchedule> list_week = listWeeklySchedule(stmt);
            for (WeeklySchedule weekObj : list_week) {
                boolean checkIfExists = weekObj.checkIfExists(startDate);
                if (checkIfExists == true) {
                    return checkIfExists;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
        return false;
    }
*/
    
   /* public void createWeeklySchedule(WeeklySchedule week) throws SQLException {
        openConnection();
        String query = "Insert into `weekly-schedule`(`startDate`,`assignedBy`) values(?,?);";
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, week.getStartDate());
            stmt.setString(2, week.getAssignedBy());
            stmt.executeUpdate();
            // System.out.println("week start date while creating annual schedule object" +week.getStartDate());
            String year = FCUtilities.fetchYearFromDate(week.getStartDate());
            createAnnualSchedule(year, week.getAssignedBy());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }
*/
   /* @Override
    public void updateWeeklySchedule(WeeklySchedule week) throws SQLException {
        openConnection();
        String query = "UPDATE `weekly-schedule` set `assignedBy` = ? WHERE `startDate` =?;";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, week.getAssignedBy());
            stmt.setString(2, week.getStartDate());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }

    public void insertAnnualSchedule(String year, String assignedBy) throws SQLException {
        openConnection();
        PreparedStatement stmt = null;
        String queryYear = "Insert into `annual-schedule` (`year`,`assingedBy`) VALUES (?,?);";
        try {
            stmt = connection.prepareStatement(queryYear);
            stmt.setString(1, year);
            stmt.setString(2, assignedBy);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }

   /* @Override
    public void createAnnualSchedule(String year, String assignedBy) throws SQLException {

        HashMap<Integer, List<WeeklySchedule>> annual_list = loadAll();
        // System.out.println("Key set is null as no annual schedule in db  " +annual_list.keySet());
        if (annual_list.keySet().isEmpty()) {
            // System.out.println("Key set is null as no annual schedule in db  " +annual_list.keySet());
            insertAnnualSchedule(year, assignedBy);
        }
        if (!annual_list.keySet().isEmpty()) {
            for (int annual_year : annual_list.keySet()) {
                if (!(Integer.toString(annual_year).equals(year))) {
                    insertAnnualSchedule(year, assignedBy);
                }
            }
        }
    }*/

   /* @Override
    public void createSchedule(ProgramSlot programSlot, String assignedBy) throws SQLException {
        openConnection();
        PreparedStatement stmt = null;
        String duration = programSlot.getDuration();
        String startTime = programSlot.getStartTime();
        String dateOfPro = programSlot.getDateOfProgram();
        String progName = programSlot.getProgramName();
        String query = "Insert into `program-slot` (`duration`, `dateOfProgram`, `startTime`,`program-name`) VALUES (?,?,?,?);";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, duration);
            stmt.setString(2, dateOfPro);
            stmt.setString(3, startTime);
            stmt.setString(4, progName);

            stmt.executeUpdate();
            String date = FCUtilities.getFirstDayOfWeek(dateOfPro);
            boolean checkExist = checkExists(date);
            if (checkExist == false) {
                WeeklySchedule week = createWeeklyObject();
                week.setAssignedBy(assignedBy);
                week.setStartDate(date);
                createWeeklySchedule(week);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }

    }*/

   /* @Override
    public void deleteSchedule(WeeklySchedule weeklySchedule) throws SQLException, NotFoundException {
        String startDate = weeklySchedule.getStartDate();
        List<ProgramSlot> pSlots = loadProgramSlot(startDate);
        PreparedStatement stmt = null;
        openConnection();
        String query = "DELETE FROM `weekly-schedule` WHERE `startDate` = ?;";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, startDate);
            stmt.executeUpdate();
            for (ProgramSlot ps : pSlots) {
                deleteProgramSlot(ps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }

    }
*/
   /* @Override
    public void deleteProgramSlot(ProgramSlot programSlot) throws SQLException, NotFoundException {
        PreparedStatement stmt = null;
        String duration = programSlot.getDuration();
        String dateofProgram = programSlot.getDateOfProgram();
        String query = "DELETE FROM `program-slot` WHERE (`dateOfProgram` = ? and `duration` =?);";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, dateofProgram);
            stmt.setString(2, duration);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/



   
}
