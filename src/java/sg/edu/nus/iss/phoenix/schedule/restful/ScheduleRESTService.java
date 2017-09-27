/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.restful;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 *
 * @author nivi
 */
@Path("scheduleprogram")
@RequestScoped
public class ScheduleRESTService {
    
     @Context
    private UriInfo context;
    
    private ScheduleService scheduleService;
    
    public ScheduleRESTService() {
        scheduleService = new ScheduleService();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlot getProgramSlot() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlots getAllProgramSlots() {
        ArrayList<ProgramSlot> pslist = scheduleService.findAllPS();
        ProgramSlots pssList = new ProgramSlots();
        pssList.setPsList(pslist);  
        
        for (int i = 0; i < pslist.size(); i++) {
            System.out.println(pslist.get(i).getProgramName());      
        }
        return pssList;
    }

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProgramSlot(ProgramSlot ps) {
        scheduleService.processCreate(ps);
    }
    
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProgramSlot(ProgramSlot ps) {
        scheduleService.processModify(ps);
    }
    
    @DELETE
    @Path("/delete/{duration}/{dateOfProgram}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteProgramSlot(@PathParam("duration") String duration, @PathParam("dateOfProgram") String dateOfProgram) {
        String duration2;
        String dateOfProgram2;
        try { 
            duration2 = URLDecoder.decode(duration, "UTF-8");
            dateOfProgram2 = URLDecoder.decode(dateOfProgram, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return;
        }

        scheduleService.processDelete(duration2,dateOfProgram2);
    }
}
