package com.cgi.chhs.adpq.service.mirco;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by michael on 5/25/16.
 */
@Entity
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private Integer ownerId; // current thinking says this is a caseworker
    private String profiles;
  //  private String caseworkers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getProfiles() {
        return profiles;
    }

    public String addPorfile(String profileId) {
        if (profiles == null) {
            profiles = profileId;
        } else {
            profiles = profiles + "," + profileId;
        }
        return profiles;
    }

}
