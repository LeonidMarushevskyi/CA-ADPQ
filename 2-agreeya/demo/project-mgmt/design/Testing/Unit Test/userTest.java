package com.agreeya.chhs;

import java.sql.SQLException;
import java.util.Date;

import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agreeya.chhs.bd.impl.MemberServiceBDImpl;
import com.agreeya.chhs.exception.UserException;
import com.agreeya.chhs.exception.WSException;
import com.agreeya.chhs.model.User;
import com.agreeya.chhs.model.UserSession;
import com.agreeya.chhs.request.GetUserInboxRequest;
import com.agreeya.chhs.request.SaveUserRequest;
import com.agreeya.chhs.request.UserRegistrationRequest;
import com.agreeya.chhs.response.SaveUserResponse;
import com.agreeya.chhs.response.UserInboxResponse;
import com.agreeya.chhs.response.UserRegistrationResponse;
import com.agreeya.chhs.service.impl.MemberServiceImpl;
import com.agreeya.chhs.to.UserContextTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/chhs-web.xml" })
public class userTest {

	@Mock
	protected MemberServiceImpl memberService;

	@Mock
	protected MemberServiceBDImpl memberServiceBD;


	
	
	@Test
	public void testSave() throws UserException, HibernateException, SQLException, WSException {
		SaveUserResponse response = new SaveUserResponse();
		response.setMessage("test");
		
		Mockito.when(memberService.saveUser(Mockito.any(SaveUserRequest.class))).thenReturn("saved");
		
		Mockito.when(memberServiceBD.saveUser(Mockito.any(SaveUserRequest.class),
				Mockito.any(SaveUserResponse.class))).thenReturn(response);

		SaveUserResponse res = memberServiceBD.saveUser(new SaveUserRequest(), new SaveUserResponse());

		Assert.assertNotNull(res.getMessage());

	}
	

	
	@Test
	public void testRegister() throws UserException, HibernateException, SQLException, WSException {
		UserRegistrationResponse response = new UserRegistrationResponse();
		response.setMessage("test");
		
		Mockito.when(memberService.registerUser(Mockito.any(UserRegistrationRequest.class))).thenReturn("saved");
		
		Mockito.when(memberServiceBD.registerUser(Mockito.any(UserRegistrationRequest.class),
				Mockito.any(UserRegistrationResponse.class))).thenReturn(response);

		UserRegistrationResponse res = memberServiceBD.registerUser(new UserRegistrationRequest(), new UserRegistrationResponse());

		Assert.assertEquals("test", res.getMessage());

	}
	
	
	public MemberServiceBDImpl getMemberServiceBD() {
		return memberServiceBD;
	}

	public void setMemberServiceBD(MemberServiceBDImpl memberServiceBD) {
		this.memberServiceBD = memberServiceBD;
	}

	public userTest() {
		MockitoAnnotations.initMocks(this);
	}
}
