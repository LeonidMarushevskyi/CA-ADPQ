package com.cgi.chhs.adpq.service.profile.service.micro;

import com.cgi.chhs.adpq.service.micro.SaveStatus;
import com.cgi.chhs.adpq.service.profile.entity.Caseworker;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.HashMap;

/**
 * Created by michael on 6/5/16.
 */
@Component
@CrossOrigin(origins = "*", maxAge = 3600)
@Path("/caseworker")
public class CaseworkerService extends CGIService {
    @Inject
    public CaseworkerRepository repository;

    @Path("/view/{id}")
    @GET
    @Produces("application/json")
    public Caseworker view(@PathParam("id") Long id) {
        return repository.findOne(id);
    }

    @Path("/addCaseworker")
    @POST
    @Produces("application/json")
    public SaveStatus addProfile(@RequestBody HashMap<String, String> params) {
        SaveStatus saveStatus = new SaveStatus();
        try {
            Caseworker worker = new Caseworker();
            worker.setAddress(params.get("address"));
            worker.setEmail(params.get("email"));
            worker.setPassword(params.get("password"));
           // worker.setHouseholdId(Integer.parseInt(params.get("household_id")));
            repository.save(worker);
            saveStatus.setSuccess(true);
            saveStatus.setMessage("Profile added successfully and a notification has been sent to the case worker.");
            saveStatus.setSavedObjectId(worker.getId());
            return saveStatus;
        } catch (Exception e) {
            saveStatus.setSuccess(false);
            saveStatus.setMessage("Adding  profile failed, a notification has been sent to the case worker.");
            return saveStatus;
        }
    }

}
