package com.agreeya.chhs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.springframework.context.ApplicationContext;

import com.agreeya.chhs.dao.UserDAO;
import com.agreeya.chhs.request.CreateUserContextRequest;
import com.agreeya.chhs.request.FindAgenciesBytLocationRequest;
import com.agreeya.chhs.request.SaveUserRequest;
import com.agreeya.chhs.request.UserRegistrationRequest;
import com.agreeya.chhs.request.user.UserFamilyDetails;
import com.agreeya.chhs.request.user.UserKidsDetails;
import com.agreeya.chhs.request.user.UserLicenceDetails;
import com.agreeya.chhs.request.user.UserPersonal;
import com.agreeya.chhs.request.user.UserProfile;
import com.agreeya.chhs.request.user.UserSpouseDetails;
import com.agreeya.chhs.response.CreateUserContextResponse;
import com.agreeya.chhs.response.SaveUserResponse;
import com.agreeya.chhs.response.UserRegistrationResponse;
import com.agreeya.chhs.to.UserContextTO;
import com.agreeya.chhs.util.ContextProvider;
import com.google.gson.Gson;

/**
 * Mock Test class to call all API's
 * @author AgreeYa Solutions
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MockPortalTest {
	private static Server server;
	private UserContextTO uCtxTO;
	private String sessionID;

	@BeforeClass
	public static void startServer() throws Exception {
		server = new Server(8181);
		server.setStopAtShutdown(true);
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/chhs");
		webAppContext.setWar("target/chhs.war");
		webAppContext.setResourceBase("src/main/webapp");
		server.addHandler(webAppContext);
		server.start();
	}

	@AfterClass
	public static void shutdownServer() throws Exception {
		server.stop();
	}

	@Before
	public void setUp() {
		ApplicationContext ctx = ContextProvider.getContext();
		UserDAO usrDAO = (UserDAO) ctx.getBean("userDAO");
		this.sessionID = usrDAO.getUserSessionForTest();
	}

	public String returnSession() {
		ApplicationContext ctx = ContextProvider.getContext();
		UserDAO usrDAO = (UserDAO) ctx.getBean("userDAO");
		return usrDAO.getUserSessionForTest();
	}

	
	@Test
	public void getFacilitiesByLocation() throws Exception {
		FindAgenciesBytLocationRequest usReq = new FindAgenciesBytLocationRequest();

		
		Gson gson = new Gson();

		HttpClient client = HttpClientBuilder.create().build();
		StringEntity postingString = new StringEntity(gson.toJson(usReq));
		HttpPost mockRequest = new HttpPost("http://localhost:8181/chhs/rest/facilities/agencynearby");
		mockRequest.setEntity(postingString);
		mockRequest.setHeader("Content-type", "application/json");

		HttpResponse mockResponse = client.execute(mockRequest);

		BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		String thisLine = null;
		StringBuilder builder = new StringBuilder();
		try {
			while ((thisLine = rd.readLine()) != null) {
				builder.append(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		SaveUserResponse reg = gson.fromJson(builder.toString(), SaveUserResponse.class);
		String responseStatus = reg.getStatus();
		Assert.assertEquals("Status not okay", 200, mockResponse.getStatusLine().getStatusCode());
		Assert.assertEquals("Status not okay", "1", responseStatus);
	}
	
	
	
	@Test
	public void getFacilitiesByZipTest() throws Exception {

		@SuppressWarnings("deprecation")
		HttpClient client = new DefaultHttpClient();
		HttpGet mockRequest = new HttpGet("http://localhost:8181/chhs/rest/facilities/zipcode/90008");
		HttpResponse mockResponse = client.execute(mockRequest);
		BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		StringBuilder builder = new StringBuilder();
		String thisLine = null;
		try {
			while ((thisLine = rd.readLine()) != null) {
				builder.append(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int responseCode = mockResponse.getStatusLine().getStatusCode();

		String sub1 = builder.toString();
		boolean flag = sub1.contains("\"status\":\"0\"");

		Assert.assertEquals(responseCode, 200);
	}

	@Test
	public void getFacilitiesByZipFailTest() throws Exception {

		@SuppressWarnings("deprecation")
		HttpClient client = new DefaultHttpClient();
		HttpGet mockRequest = new HttpGet("http://localhost:8181/chhs/rest/facilities/zipcode/95630");
		HttpResponse mockResponse = client.execute(mockRequest);
		BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		StringBuilder builder = new StringBuilder();
		String thisLine = null;
		try {
			while ((thisLine = rd.readLine()) != null) {
				builder.append(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int responseCode = mockResponse.getStatusLine().getStatusCode();

		String sub1 = builder.toString();
		boolean flag = sub1.contains("[{\"county_name\":");

		Assert.assertEquals(responseCode, 200);
		Assert.assertTrue(!flag);

	}

	/**
	 * LogOut API test
	 * 
	 * @throws Exception
	 */
	/*@Test
	public void glogOutTest() throws Exception {

		UserLogoutRequest usReq = new UserLogoutRequest();

		usReq.setUserContext(getUsrContxtTO());

		Gson gson = new Gson();

		HttpClient client = HttpClientBuilder.create().build();
		StringEntity postingString = new StringEntity(gson.toJson(usReq));
		HttpPost mockRequest = new HttpPost("http://localhost:8181/chhs/rest/contextinit/userlogout");
		mockRequest.setEntity(postingString);
		mockRequest.setHeader("Content-type", "application/json");

		HttpResponse mockResponse = client.execute(mockRequest);

		BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		String thisLine = null;
		StringBuilder builder = new StringBuilder();
		try {
			while ((thisLine = rd.readLine()) != null) {
				builder.append(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		UserLogoutResponse reg = gson.fromJson(builder.toString(), UserLogoutResponse.class);
		String responseStatus = reg.getStatus();
		Assert.assertEquals("Status not okay", 200, mockResponse.getStatusLine().getStatusCode());
		Assert.assertEquals("Status not okay", "0", responseStatus);
		Assert.assertEquals("Logout Unsuccesful", "logout successfully", reg.getMessage());
	}*/

	/**
	 * Login API negative test
	 * 
	 * @throws Exception
	 */

	@Test
	public void loginNegativeTest() throws Exception {

		CreateUserContextRequest usReq = new CreateUserContextRequest();

		usReq.setUserName("testUser");
		usReq.setPassword("testUser-wrong");

		Gson gson = new Gson();

		HttpClient client = HttpClientBuilder.create().build();
		StringEntity postingString = new StringEntity(gson.toJson(usReq));
		HttpPost mockRequest = new HttpPost("http://localhost:8181/chhs/rest/contextinit/createusercontext");
		mockRequest.setEntity(postingString);
		mockRequest.setHeader("Content-type", "application/json");

		HttpResponse mockResponse = client.execute(mockRequest);

		BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		String thisLine = null;
		StringBuilder builder = new StringBuilder();
		try {
			while ((thisLine = rd.readLine()) != null) {
				builder.append(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		CreateUserContextResponse reg = gson.fromJson(builder.toString(), CreateUserContextResponse.class);
		String responseStatus = reg.getStatus();
		Assert.assertEquals("Status not okay", 200, mockResponse.getStatusLine().getStatusCode());
		Assert.assertEquals("Status not okay", "1", responseStatus);

	}

	/**
	 * Login API test
	 * 
	 * @throws Exception
	 */
	/*@Test
	public void loginTest() throws Exception {

		CreateUserContextRequest usReq = new CreateUserContextRequest();

		usReq.setUserName("agreeya");
		usReq.setPassword("agreeya");

		Gson gson = new Gson();

		HttpClient client = HttpClientBuilder.create().build();
		StringEntity postingString = new StringEntity(gson.toJson(usReq));
		HttpPost mockRequest = new HttpPost("http://localhost:8181/chhs/rest/contextinit/createusercontext");
		mockRequest.setEntity(postingString);
		mockRequest.setHeader("Content-type", "application/json");

		HttpResponse mockResponse = client.execute(mockRequest);

		BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		String thisLine = null;
		StringBuilder builder = new StringBuilder();
		try {
			while ((thisLine = rd.readLine()) != null) {
				builder.append(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		CreateUserContextResponse reg = gson.fromJson(builder.toString(), CreateUserContextResponse.class);
		String responseStatus = reg.getStatus();
		uCtxTO = reg.getUserContext();
		Assert.assertEquals("Status not okay", 200, mockResponse.getStatusLine().getStatusCode());
		Assert.assertEquals("Status not okay", "0", responseStatus);
		Assert.assertNotNull(reg.getUserContext());

	}*/

	/**
	 * Fail Save API missing license details
	 * 
	 * @throws Exception
	 */

	@Test
	public void saveUserNegativeTest() throws Exception {

		SaveUserRequest usReq = new SaveUserRequest();

		UserProfile personalProfile = new UserProfile("y", "y", "a@a.com", "testUser", "testUser");
		UserSpouseDetails userSpouseDetails = new UserSpouseDetails("9876543210", "05/31/2016", "fnameTest", "M", "hobbies Test", "200",
				"occupation", "preference", "race", "religion");
		UserPersonal personalDetails = new UserPersonal("9872664213", "05/31/2016", "spfN test", "f", "hobbies test", "12345",
				"lastName test", "y", "occupation", "preference", "race", "religion", userSpouseDetails);
		UserKidsDetails userKidsDetail = new UserKidsDetails("kidName1", "12", "hobbies test");
		UserKidsDetails userKidsDetail2 = new UserKidsDetails("kidName1", "12", "hobbies test");
		List<UserKidsDetails> userKidsDetails = new ArrayList<>();
		userKidsDetails.add(userKidsDetail);
		userKidsDetails.add(userKidsDetail2);
		UserFamilyDetails familyDetails = new UserFamilyDetails("description", "Y", "2", "test data", userKidsDetails);

		usReq.setFamilyDetails(familyDetails);
		usReq.setPersonalDetails(personalDetails);
		usReq.setPersonalProfile(personalProfile);
		usReq.setRegistrationStage(4);

		Gson gson = new Gson();

		HttpClient client = HttpClientBuilder.create().build();
		StringEntity postingString = new StringEntity(gson.toJson(usReq));
		HttpPost mockRequest = new HttpPost("http://localhost:8181/chhs/rest/member/save");
		mockRequest.setEntity(postingString);
		mockRequest.setHeader("Content-type", "application/json");

		HttpResponse mockResponse = client.execute(mockRequest);

		BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		String thisLine = null;
		StringBuilder builder = new StringBuilder();
		try {
			while ((thisLine = rd.readLine()) != null) {
				builder.append(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		SaveUserResponse reg = gson.fromJson(builder.toString(), SaveUserResponse.class);
		String responseStatus = reg.getStatus();
		Assert.assertEquals("Status not okay", 200, mockResponse.getStatusLine().getStatusCode());
		Assert.assertEquals("Status not okay", "1", responseStatus);

	}
/*
	@Test
	public void saveUserTest() throws Exception {

		SaveUserRequest usReq = new SaveUserRequest();

		usReq.setUserContext(getUsrContxtTO());

		UserProfile personalProfile = new UserProfile("y", "y", "amit@agreeya.com", "agreeya", "agreeya");
		UserSpouseDetails userSpouseDetails = new UserSpouseDetails("9876543210", "05/31/2016", "fnameTest", "M", "hobbies Test", "200",
				"occupation", "preference", "race", "religion");
		UserPersonal personalDetails = new UserPersonal("9872664213", "05/31/2016", "spfN test", "f", "hobbies test", "12345",
				"lastName test", "y", "occupation", "preference", "race", "religion", userSpouseDetails);
		UserKidsDetails userKidsDetail = new UserKidsDetails("kidName1", "12", "hobbies test");
		UserKidsDetails userKidsDetail2 = new UserKidsDetails("kidName1", "12", "hobbies test");
		List<UserKidsDetails> userKidsDetails = new ArrayList<>();
		userKidsDetails.add(userKidsDetail);
		userKidsDetails.add(userKidsDetail2);
		UserFamilyDetails familyDetails = new UserFamilyDetails("description", "Y", "2", "test data", userKidsDetails);
		UserLicenceDetails licenceDetails = new UserLicenceDetails("123456789", 10, "05/31/2016", "321456789");

		usReq.setFamilyDetails(familyDetails);
		usReq.setLicenceDetails(licenceDetails);
		usReq.setPersonalDetails(personalDetails);
		usReq.setPersonalProfile(personalProfile);
		usReq.setRegistrationStage(4);

		Gson gson = new Gson();

		HttpClient client = HttpClientBuilder.create().build();
		StringEntity postingString = new StringEntity(gson.toJson(usReq));
		System.out.println("$$$$$$$$$$$" + postingString);

		HttpPost mockRequest = new HttpPost("http://localhost:8181/chhs/rest/member/save");
		mockRequest.setEntity(postingString);
		mockRequest.setHeader("Content-type", "application/json");

		HttpResponse mockResponse = client.execute(mockRequest);

		BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		String thisLine = null;
		StringBuilder builder = new StringBuilder();
		try {
			while ((thisLine = rd.readLine()) != null) {
				builder.append(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		SaveUserResponse reg = gson.fromJson(builder.toString(), SaveUserResponse.class);
		String responseStatus = reg.getStatus();
		Assert.assertEquals("Status not okay", 200, mockResponse.getStatusLine().getStatusCode());
		Assert.assertEquals("Status not okay", "0", responseStatus);

	}*/

	/**
	 * failing regigter API test missing personal information
	 * 
	 * @throws Exception
	 */
	@Test
	public void registerUserNegativeTest() throws Exception {

		UserRegistrationRequest urr = new UserRegistrationRequest();

		UserProfile personalProfile = new UserProfile("y", "y", "a@a.com", "testUser", "testUser");
		UserKidsDetails userKidsDetail = new UserKidsDetails("kidName1", "12", "hobbies test");
		UserKidsDetails userKidsDetail2 = new UserKidsDetails("kidName1", "12", "hobbies test");
		List<UserKidsDetails> userKidsDetails = new ArrayList<>();
		userKidsDetails.add(userKidsDetail);
		userKidsDetails.add(userKidsDetail2);
		UserFamilyDetails familyDetails = new UserFamilyDetails("description", "Y", "2", "test data", userKidsDetails);
		UserLicenceDetails licenceDetails = new UserLicenceDetails("123456789", 10, "05/31/2016", "321456789");

		urr.setFamilyDetails(familyDetails);
		urr.setLicenceDetails(licenceDetails);
		urr.setPersonalProfile(personalProfile);
		urr.setRegistrationStage(4);

		Gson gson = new Gson();

		HttpClient client = HttpClientBuilder.create().build();
		StringEntity postingString = new StringEntity(gson.toJson(urr));
		HttpPost mockRequest = new HttpPost("http://localhost:8181/chhs/rest/member/register");
		mockRequest.setEntity(postingString);
		mockRequest.setHeader("Content-type", "application/json");

		HttpResponse mockResponse = client.execute(mockRequest);

		BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		String thisLine = null;
		StringBuilder builder = new StringBuilder();
		try {
			while ((thisLine = rd.readLine()) != null) {
				builder.append(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		UserRegistrationResponse reg = gson.fromJson(builder.toString(), UserRegistrationResponse.class);
		String responseStatus = reg.getStatus();
		Assert.assertEquals("Status not okay", 200, mockResponse.getStatusLine().getStatusCode());
		Assert.assertEquals("Status not okay", "1", responseStatus);
	}

	@Test
	public void registerUserTest() throws Exception {

		UserRegistrationRequest urr = new UserRegistrationRequest();
		
		String userName = "test" + String.valueOf(System.currentTimeMillis());
		String email =  String.valueOf(System.currentTimeMillis()) + "@test.com";
		

		UserProfile personalProfile = new UserProfile("y", "y", email, userName, "testUser");
		UserSpouseDetails userSpouseDetails = new UserSpouseDetails("9876543210", "05/31/2016", "fnameTest", "M", "hobbies Test", "200",
				"occupation", "preference", "race", "religion");
		UserPersonal personalDetails = new UserPersonal("9872664213", "05/31/2016", "spfN test", "f", "hobbies test", "12345",
				"lastName test", "y", "occupation", "preference", "race", "religion", userSpouseDetails);
		UserKidsDetails userKidsDetail = new UserKidsDetails("kidName1", "12", "hobbies test");
		UserKidsDetails userKidsDetail2 = new UserKidsDetails("kidName1", "12", "hobbies test");
		List<UserKidsDetails> userKidsDetails = new ArrayList<>();
		userKidsDetails.add(userKidsDetail);
		userKidsDetails.add(userKidsDetail2);
		UserFamilyDetails familyDetails = new UserFamilyDetails("description", "Y", "2", "test data", userKidsDetails);
		UserLicenceDetails licenceDetails = new UserLicenceDetails("123456789", 10, "05/31/2016", "321456789");

		urr.setFamilyDetails(familyDetails);
		urr.setLicenceDetails(licenceDetails);
		urr.setPersonalDetails(personalDetails);
		urr.setPersonalProfile(personalProfile);
		urr.setRegistrationStage(4);

		Gson gson = new Gson();

		HttpClient client = HttpClientBuilder.create().build();
		StringEntity postingString = new StringEntity(gson.toJson(urr));
		HttpPost mockRequest = new HttpPost("http://localhost:8181/chhs/rest/member/register");
		mockRequest.setEntity(postingString);
		mockRequest.setHeader("Content-type", "application/json");

		HttpResponse mockResponse = client.execute(mockRequest);

		BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		String thisLine = null;
		StringBuilder builder = new StringBuilder();
		try {
			while ((thisLine = rd.readLine()) != null) {
				builder.append(thisLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		UserRegistrationResponse reg = gson.fromJson(builder.toString(), UserRegistrationResponse.class);
		String responseStatus = reg.getStatus();
		Assert.assertEquals("Status not okay", 200, mockResponse.getStatusLine().getStatusCode());
		Assert.assertEquals("Status not okay", "0", responseStatus);

	}

	public UserContextTO getUsrContxtTO() {
		Gson gson = new Gson();
		String session = returnSession();
		UserContextTO usrC = new UserContextTO();
		usrC.setUserId(397);
		usrC.setFirstName("testf");
		usrC.setLastName("lname");
		usrC.setUserName("agreeya");
		usrC.setLoggedInDate("06/04/2016 11:22");
		usrC.setProfileStage(4);
		usrC.setUserStatus("ACTIVE");
		usrC.setUserRoleMap(new HashMap<String, Boolean>());
		usrC.setSessionId(session);
		return usrC;
	}

	public static void main(String[] args) {
		MockPortalTest test = new MockPortalTest();
		System.out.println(test.getUsrContxtTO().toString());
		System.out.println(test.getUsrContxtTO().getSessionId());
	}
}
