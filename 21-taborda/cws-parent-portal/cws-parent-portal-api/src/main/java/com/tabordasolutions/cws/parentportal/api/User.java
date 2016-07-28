package com.tabordasolutions.cws.parentportal.api;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "users")
@JsonSerialize(using=UserSerializer.class)
@JsonDeserialize(using=UserDeserializer.class)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "users_id_seq")
    @SequenceGenerator(name="users_id_seq",sequenceName="users_id_seq",allocationSize=1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "in_care_of")
    private String inCareOf;
    @Column(name = "address1")
    private String streetAddress1;
    @Column(name = "address2")
    private String streetAddress2;
    @Column(name = "state_abbreviation")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "zip_code")
    private String zip;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "email", unique=true)
    private String email;

    // TODO: map this association
    @Transient
    private List<User> caseworkers;

    // TODO: implement password hashing
    @Column(name = "password_hash")
    private String password;
    
    @Transient
    private String newPassword;
    
    
    public Long getId() {return id; }
    

    public void setId(Long id) { this.id = id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInCareOf() {
        return inCareOf;
    }

    public void setInCareOf(String inCareOf) {
        this.inCareOf = inCareOf;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public void setStreetAddress1(String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<User> getCaseworkers() {
        return caseworkers;
    }

    public void setCaseworkers(List<User> caseworkers) {
        this.caseworkers = caseworkers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullName(){ return firstName + " " + lastName; }

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", inCareOf=" + inCareOf + ", streetAddress1="
				+ streetAddress1 + ", streetAddress2=" + streetAddress2
				+ ", state=" + state + ", city=" + city + ", zip=" + zip
				+ ", imageUrl=" + imageUrl + ", email=" + email + ", password="
				+ password + "]";
	}
	
    public static class Builder 
    {
    	User user;
        public Builder() {
        	user = new User();
        }
        public Builder id(Long id) {
            user.id = id;
            return this;
        }
        public Builder firstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }
        public Builder lastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }
        public Builder inCareOf(String inCareOf) {
            user.setInCareOf(inCareOf);
            return this;
        }
        public Builder streetAddress1(String streetAddress1) {
            user.setStreetAddress1(streetAddress1);
            return this;
        }
        public Builder streetAddress2(String streetAddress2) {
            user.setStreetAddress2(streetAddress2);
            return this;
        }
        public Builder state(String state) {
            user.setState(state);
            return this;
        }
        public Builder city(String city) {
            user.setCity(city);
            return this;
        }
        public Builder zip(String zip) {
            user.setZip(zip);
            return this;
        }
        public Builder imageUrl(String imageUrl) {
            user.setImageUrl(imageUrl);
            return this;
        }
        public Builder email(String email) {
            user.setEmail(email);
            return this;
        }
        public Builder password(String password) {
            user.setPassword(password);
            return this;
        }
        public Builder newPassword(String password) {
            user.setNewPassword(password);
            return this;
        }

        public User build() {
            validateUserObject(user);
            return user;
        }
        private void validateUserObject(User user) {
            //TODO : validate user
        }
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((caseworkers == null) ? 0 : caseworkers.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result
				+ ((inCareOf == null) ? 0 : inCareOf.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((newPassword == null) ? 0 : newPassword.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((streetAddress1 == null) ? 0 : streetAddress1.hashCode());
		result = prime * result
				+ ((streetAddress2 == null) ? 0 : streetAddress2.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (caseworkers == null) {
			if (other.caseworkers != null)
				return false;
		} else if (!caseworkers.equals(other.caseworkers))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (inCareOf == null) {
			if (other.inCareOf != null)
				return false;
		} else if (!inCareOf.equals(other.inCareOf))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetAddress1 == null) {
			if (other.streetAddress1 != null)
				return false;
		} else if (!streetAddress1.equals(other.streetAddress1))
			return false;
		if (streetAddress2 == null) {
			if (other.streetAddress2 != null)
				return false;
		} else if (!streetAddress2.equals(other.streetAddress2))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}
}
