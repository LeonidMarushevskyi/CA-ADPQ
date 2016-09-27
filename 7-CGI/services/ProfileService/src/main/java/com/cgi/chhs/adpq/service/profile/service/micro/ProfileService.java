package com.cgi.chhs.adpq.service.profile.service.micro;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.cgi.chhs.adpq.service.micro.SaveStatus;
import com.cgi.chhs.adpq.service.profile.entity.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@CrossOrigin
public class ProfileService extends CGIService{


	@Inject
	public ProfileRepository repository;

	@Path("/view/{id}")
	@GET
	@Produces("application/json")
	public Profile view(@PathParam("id") Long id) {
		return repository.findOne(id);
	}

	@Path("/updateProfile")
	@POST
	@Produces("application/json")
	public SaveStatus editProfile(@RequestBody HashMap<String, String> params){
		SaveStatus editStatus = new SaveStatus();
		try {
			Profile profile = repository.findOne(Long.parseLong(params.get("id")));
			profile.setAddress1(params.get("address"));
			profile.setEmail(params.get("email"));
			profile.setPassword(params.get("password"));
			profile.setFirstName(params.get("firstName"));
			profile.setLastName(params.get("lastName"));
			profile.setZip(params.get("zip"));
			profile.setPhone(params.get("phone"));
			profile.setAddress2(params.get("address2"));
			profile.setLocation(params.get("location"));
			profile.setFacility(params.get("facility"));
			profile.setRelationship(params.get("relationship"));
			profile.setGroupId(Integer.parseInt(params.get("group")));
			profile.setGoal(params.get("goal"));
			profile.setDob(params.get("dob"));
			profile.setCell(params.get("cell"));
			profile.setGender(params.get("gender"));
			profile.setCity(params.get("city"));
			profile.setState(params.get("state"));
			profile.setHasPlan(Boolean.valueOf(params.get("hasPlan")));
			profile.setProfilePicture(params.get("profilePicture"));
			profile.setParentId(Integer.parseInt(params.get("parentId")));
			repository.save(profile);
			editStatus.setSuccess(true);
			editStatus.setMessage("Profile updated  successfully and a notification has been sent to the case worker.");
			return editStatus;
		} catch(Exception e) {
			editStatus.setSuccess(false);
			editStatus.setMessage("Profile updation failed and notification has been sent to the case worker.");
			return editStatus;
		}
	}

	@Path("/addProfile")
	@POST
	@Produces("application/json")
	public SaveStatus addProfile(@RequestBody HashMap<String, String> params) {
		SaveStatus saveStatus = new SaveStatus();
		try {
			Profile profile = new Profile();
			profile.setAddress1(params.get("address"));
			profile.setEmail(params.get("email"));
			profile.setPassword(params.get("password"));
			profile.setFirstName(params.get("firstName"));
			profile.setLastName(params.get("lastName"));
			profile.setZip(params.get("zip"));
			profile.setPhone(params.get("phone"));
			profile.setAddress2(params.get("address2"));
			profile.setLocation(params.get("location"));
			profile.setFacility(params.get("facility"));
			profile.setRelationship(params.get("relationship"));
			profile.setGroupId(Integer.parseInt(params.get("group")));
			profile.setGoal(params.get("goal"));
			profile.setDob(params.get("dob"));
			profile.setCell(params.get("cell"));
			profile.setCity(params.get("city"));
			profile.setState(params.get("state"));
			profile.setGender(params.get("gender"));
			profile.setHasPlan(Boolean.valueOf(params.get("hasPlan")));
			profile.setProfilePicture(params.get("profilePicture"));
			profile.setParentId(Integer.parseInt(params.get("parentId")));
			repository.save(profile);
			saveStatus.setSuccess(true);
			saveStatus.setMessage("Profile added successfully and a notification has been sent to the case worker.");
			saveStatus.setSavedObjectId(profile.getId());
			return saveStatus;
		} catch (Exception e) {
			saveStatus.setSuccess(false);
			saveStatus.setMessage(e.getMessage());
			return saveStatus;
		}

	}
	@Path("/getFamily/{id}")
	@GET
	@Produces("application/json")
	public List<Profile> getFamily(@PathParam("id") Long id) {
		List<Profile> members = new ArrayList<Profile>();
		for (Profile p : repository.findAll()) {
			if (p.getParentId() != null) {
				if (p.getParentId() == id.intValue()) {
					members.add(p);
				}
			}
		}
		return members;
	}

	@Path("/getCommunityCareInfo/{zipCode}")
	@GET
	@Produces("application/json")
	public SaveStatus communityCareInfo(@PathParam("zipCode") Long zipCode) {
		SaveStatus saveStatus = new SaveStatus();
		try {
			System.out.println("Zip Code entered is " + zipCode);
			RestTemplate restTemplate = new RestTemplate();
			String jsonResp = restTemplate.getForObject("https://chhs.data.ca.gov/resource/mffa-c6z5.json?facility_zip="+zipCode, String.class) ;
			saveStatus.setSuccess(true);
			saveStatus.setMessage(jsonResp);
			return saveStatus;
		} catch (Exception e) {
			saveStatus.setSuccess(false);
			saveStatus.setMessage("Failed invoking service call to community care .");
			return saveStatus;

		}

	}
}
