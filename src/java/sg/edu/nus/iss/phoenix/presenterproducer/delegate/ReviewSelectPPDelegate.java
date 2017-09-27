/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.presenterproducer.delegate;
import sg.edu.nus.iss.phoenix.presenterproducer.service.*;
import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author Surethiran
 */
public class ReviewSelectPPDelegate {
    private ReviewSelectpresenterproducerService service;
    
    public ReviewSelectPPDelegate()
    {
        service = new ReviewSelectpresenterproducerService();
    }
    
    public List<User> processSearch()
    {
        return service.reviewSelectpresenterproducerService();
    }
}
