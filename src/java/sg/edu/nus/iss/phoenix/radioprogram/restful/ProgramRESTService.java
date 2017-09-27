/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.radioprogram.restful;


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
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.service.ProgramService;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.restful.ProgramSlots;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("radioprogram")
@RequestScoped
public class ProgramRESTService {

    @Context
    private UriInfo context;
    
    private ProgramService service;
    private ScheduleService scheduleService;

    /**
     * Creates a new instance of RadioProgramRESTService
     */
    public ProgramRESTService() {
        service = new ProgramService();
        scheduleService = new ScheduleService();
    }

    /**
     * Retrieves representation of an instance of resource
     * @return an instance of resource
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RadioProgram getRadioProgram() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public RadioPrograms getAllRadioPrograms() {
        ArrayList<RadioProgram> rplist = service.findAllRP();
        RadioPrograms rpsList = new RadioPrograms();
        rpsList.setRpList(new ArrayList<RadioProgram>());
        
        for (int i = 0; i < rplist.size(); i++) {
            rpsList.getRpList().add(
                new RadioProgram(rplist.get(i).getName(), 
                    rplist.get(i).getDescription(), 
                    rplist.get(i).getTypicalDuration()));
        }

        return rpsList;
    }
    
    @GET
    @Path("/psall")
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
    
    /**
     * PUT method for updating or creating an instance of resource
     * @param content representation for the resource
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateRadioProgram(RadioProgram rp) {
        service.processModify(rp);
    }
    
    @POST
    @Path("/updateps")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProgramSlot(ProgramSlot ps) {
        scheduleService.processModify(ps);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param content representation for the resource
     */
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createRadioProgram(RadioProgram rp) {
        service.processCreate(rp);
    }
    
    @PUT
    @Path("/createps")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProgramSlot(ProgramSlot ps) {
        scheduleService.processCreate(ps);
    }
   
    /**
     * DELETE method for deleting an instance of resource
     * @param name name of the resource
     */
    @DELETE
    @Path("/delete/{rpname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteRadioProgram(@PathParam("rpname") String name) {
        String name2;
        try { 
            name2 = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return;
        }

        service.processDelete(name2);
    }
}
