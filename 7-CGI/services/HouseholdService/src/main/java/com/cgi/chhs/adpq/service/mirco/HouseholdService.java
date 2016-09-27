package com.cgi.chhs.adpq.service.mirco;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.HashMap;

/**
 * Created by michael on 6/4/16.
 */
@Component
@Path("/household")
public class HouseholdService  {

    @Inject
    public HouseholdRepository repository;

    @Path("/index")
    @GET
    public String getIndex() {
        return "index";
    }


    @Path("/view/{id}")
    @GET
    @Produces("application/json")
    public Household view(@PathParam("id") Long id) {
        return repository.findOne(id);
    }

    @Path("/estabilish")
    @POST
    @Produces("application/json")
    public Household addHouseHold(@RequestBody HashMap<String, String> params) {

        System.out.println("we are here");
        try {
            Household household = new Household();
            household.setAddress(params.get("address"));
            household.setName(params.get("name"));
            household.setOwnerId(Integer.parseInt(params.get("caseworker")));
            household.addPorfile(params.get("profile"));
            repository.save(household);
            return household;
        } catch (Exception e) {
            HashMap<String, String> errorDict = new HashMap<>();
            errorDict.put("Error", e.getMessage());
            return new Household();
        }
    }
}
