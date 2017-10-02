/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.service;

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
 * @author kiruba
 * @author Nivi
 */
public class AuthenticateServiceTest {
     @Mock 
    private AuthenticateService authenticate; 
    private User user1;
    private User user2;
    private Role role1;
    private Role role2;
    private ArrayList<User> users ;
 
   
    public AuthenticateServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        authenticate = Mockito.mock(AuthenticateService.class);
        user1 = new User("UserOne");
        user2 = new User("UserTwo");
        role1 = new Role("Presenter");
        role2 = new Role("Producer");
        role1.setAccessPrivilege("radio program presenter");
        role2.setAccessPrivilege("program producer");
        users = new ArrayList<User>();
      
        user1.setName("UserOne");
        user1.setPassword("UserOne");
        ArrayList<Role> roles = new ArrayList<Role>(){{
            add(role1);
            add(role2);
        }};
        user1.setRoles(roles);
        
        user2.setName("UserTwo");
        user2.setPassword("UserTwo");
        user2.setRoles(roles);
        
        users.add(user1);
        users.add(user2);
        
    Collections.sort(users, new Comparator<User>() {
    @Override
    public int compare(User c1, User c2) {
        return c2.getId().compareTo(c1.getId());
                
    }
});
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateUserIdPassword method, of class AuthenticateService.
     */
    @Test
    public void testValidateUserIdPassword() {
        System.out.println("validateUserIdPassword");
        OngoingStubbing<User> thenReturn = when(authenticate.validateUserIdPassword(user1)).thenReturn(user1);
        User expResult = user1;
        User result = authenticate.validateUserIdPassword(user1);
        assertEquals(expResult.getId(), result.getId());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of evaluateAccessPreviledge method, of class AuthenticateService.
     */
    @Test
    public void testEvaluateAccessPreviledge() {
        System.out.println("evaluateAccessPreviledge");
        when(authenticate.evaluateAccessPreviledge(user1)).thenReturn(user1);
        User expResult = user1;
        User result = authenticate.evaluateAccessPreviledge(user1);
        assertEquals(expResult.getRoles().get(0).getRole(), result.getRoles().get(0).getRole());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of manageUser method, of class AuthenticateService.
     */
    @Test
    public void testManageUser() {
        System.out.println("manageUser");
        when(authenticate.manageUser()).thenReturn(users);
        List<User> expResult = users;
        List<User> result = authenticate.manageUser();
        assertEquals(expResult.get(0).getId(), result.get(0).getId());
        // TODO review the generated test code and remove the default call to fail.
    }
    
}


    
    