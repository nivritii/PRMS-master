/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.entity.WeeklySchedule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.stubbing.OngoingStubbing;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author rama
 */
public class ScheduleServiceTest {
    
    public ScheduleServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAllPS method, of class ScheduleService.
     */
    @Test
    public void testFindAllPS() {
        System.out.println("findAllPS");
        ScheduleService instance = new ScheduleService();
        ArrayList<ProgramSlot> expResult = null;
        ArrayList<ProgramSlot> result = instance.findAllPS();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findWeeklySlots method, of class ScheduleService.
     */
    @Test
    public void testFindWeeklySlots() {
        System.out.println("findWeeklySlots");
        String date = "";
        ScheduleService instance = new ScheduleService();
        ArrayList<ProgramSlot> expResult = null;
        ArrayList<ProgramSlot> result = instance.findWeeklySlots(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processModify method, of class ScheduleService.
     */
    @Test
    public void testProcessModify() {
        System.out.println("processModify");
        ProgramSlot ps = null;
        ScheduleService instance = new ScheduleService();
        instance.processModify(ps);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processCreate method, of class ScheduleService.
     */
    @Test
    public void testProcessCreate() {
        System.out.println("processCreate");
        ProgramSlot ps = null;
        ScheduleService instance = new ScheduleService();
        instance.processCreate(ps);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processDelete method, of class ScheduleService.
     */
    @Test
    public void testProcessDelete() {
        System.out.println("processDelete");
        ProgramSlot ps = null;
        ScheduleService instance = new ScheduleService();
        instance.processDelete(ps);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllWS method, of class ScheduleService.
     */
    @Test
    public void testFindAllWS() {
        System.out.println("findAllWS");
        ScheduleService instance = new ScheduleService();
        ArrayList<WeeklySchedule> expResult = null;
        ArrayList<WeeklySchedule> result = instance.findAllWS();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
