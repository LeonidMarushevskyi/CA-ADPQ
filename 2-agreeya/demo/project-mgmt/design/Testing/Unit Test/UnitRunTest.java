package com.agreeya.chhs;

import java.util.Date;

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
import com.agreeya.chhs.exception.WSException;
import com.agreeya.chhs.model.User;
import com.agreeya.chhs.model.UserSession;
import com.agreeya.chhs.service.impl.MemberServiceImpl;
import com.agreeya.chhs.to.UserContextTO;
import com.agreeya.chhs.to.UserTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/chhs-web.xml" })
public class UnitRunTest {

	@Mock
	protected MemberServiceImpl memberService;

	@InjectMocks
	protected MemberServiceBDImpl memberServiceBD;
	
	
	@Test
	public void testAuthenticate() throws WSException {
		// GIVEN
		User usr = new User();
		usr.setUserID(1);
		usr.setUserName("TEST");
		usr.setStage(1);
		usr.setStatus("ACTIVE");
		
		UserSession userSession = new UserSession();
		userSession.setId(1);
		userSession.setCreatedOn(new Date());
		userSession.setCreatedBy(1);
		
		Mockito.when(memberService.checkUserDetailExist(Mockito.anyString())).thenReturn(usr);
		Mockito.when(memberServiceBD.decryptAESPassword(Mockito.anyString(), Mockito.anyString())).thenReturn("agreeya");
		Mockito.when(memberService.findPrevUserSession(Mockito.any(User.class))).thenReturn(userSession);
		Mockito.doNothing().when(memberService).createOrUpdateUserSession(Mockito.any(UserSession.class));
		
		
		UserContextTO uto = memberServiceBD.authenticateUser("", "");
		Assert.assertNull(uto);
		//Assert.assertEquals("true", "exists", msg);
	}
	
	
	@Test
	public void testIsValidSession() {

		UserSession userSession = new UserSession();
		userSession.setId(1);
		userSession.setCreatedOn(new Date());
		userSession.setCreatedBy(1);

		Mockito.when(memberService.findUserSession(Mockito.anyString(), Mockito.anyInt())).thenReturn(userSession);

		boolean flag = memberServiceBD.isUserSessionValid("x", 1);

		Assert.assertFalse(flag);

	}
	
	
	
	public MemberServiceBDImpl getMemberServiceBD() {
		return memberServiceBD;
	}

	public void setMemberServiceBD(MemberServiceBDImpl memberServiceBD) {
		this.memberServiceBD = memberServiceBD;
	}

	public UnitRunTest() {
		MockitoAnnotations.initMocks(this);
	}
}
