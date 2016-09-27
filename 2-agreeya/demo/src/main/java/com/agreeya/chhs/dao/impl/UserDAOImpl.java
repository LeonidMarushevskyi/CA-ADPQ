package com.agreeya.chhs.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import com.agreeya.chhs.bd.AESSecurityBD;
import com.agreeya.chhs.dao.UserDAO;
import com.agreeya.chhs.exception.UserException;
import com.agreeya.chhs.exception.WSException;
import com.agreeya.chhs.model.Role;
import com.agreeya.chhs.model.User;
import com.agreeya.chhs.model.UserSession;
import com.agreeya.chhs.model.Userdetail;
import com.agreeya.chhs.model.Userfamily;
import com.agreeya.chhs.model.Userinbox;
import com.agreeya.chhs.model.Userkid;
import com.agreeya.chhs.model.Userlicence;
import com.agreeya.chhs.model.Userspouse;
import com.agreeya.chhs.request.GetUserInboxRequest;
import com.agreeya.chhs.request.SaveUserRequest;
import com.agreeya.chhs.request.UserRegistrationRequest;
import com.agreeya.chhs.request.user.UserKidsDetails;
import com.agreeya.chhs.response.UserInboxResponse;
import com.agreeya.chhs.response.inbox.UserEmail;
import com.agreeya.chhs.util.Constants;
import com.agreeya.chhs.util.DateUtil;

/**
 * 
 * implementation class for UserDAO
 * 
 * @author AgreeYa Solutions
 *
 */
@Transactional
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {

	private AESSecurityBD aESSecurityBD;

	/**
	 * @purpose: check user detail is exist or not by user name and email
	 * for API /contextinit/checkuserdetailexist
	 * @param email
	 * @param userName
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User checkUserDetailExist(String userName) {
		Integer userDetailID = null;
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT userID FROM user u where "
						+ " u.status IN ('ACTIVE', 'INCOMPLETE') AND u.userName = '"
						+ userName
						+ "'");
		List<Integer> idList = query.list();
		if (idList.size() > 0) {
			userDetailID = idList.get(0);
		}

		User user = getById(User.class, userDetailID);

		if (null != user) {
			if (user.getUserfamilies().size() > 0) {
				Query kidsQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM userkids uk "
						+ "WHERE uk.familyID = " 
						+ user.getUserfamilies().get(0).getId()).addEntity(Userkid.class);

				user.getUserfamilies().get(0).setKids(kidsQuery.list());
			}
			return user;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @purpose: register new User in system and save profile and family details and license 
	 * for API /member/register
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String registerUser(UserRegistrationRequest request) throws HibernateException, SQLException, UserException, WSException {
		String msg = "";
		String emailId = request.getPersonalProfile().getUseremail();
		String userName = request.getPersonalProfile().getUserName();

		List<User> userList = null;
		/*
		 * used to save the unique user : uniqueness is controlled by emailId, username
		 */

		Session sess = sessionFactory.openSession();
		userList = (List<User>) sessionFactory.getCurrentSession().createQuery("from User as u WHERE u.userName = '" + userName + "' "
				+ "	AND u.useremail = '" + emailId + "' and STATUS IN ('ACTIVE','INCOMPLETE')").list();
		boolean userExist = false;
		int userId = 0;
		User user = new User();
		if (userList != null && !userList.isEmpty()) {
			if (request.getRegistrationStage() == 1) {
				msg = "User already Exist with provided username and email";
				return msg;
			} else {
				userExist = true;
				user = userList.get(0);
				userId = user.getUserID();
			}
		}

		if (!userExist) {
			user.setUserName(request.getPersonalProfile().getUserName());
			user.setPassword(aESSecurityBD.encryptAES(request.getPersonalProfile().getPassword()));
			user.setUseremail(request.getPersonalProfile().getUseremail());
			user.setHomestudy(request.getPersonalProfile().getHomestudy());
			user.setTraining(request.getPersonalProfile().getTraining());
			Role role = getById(Role.class, 1);
			user.setRole(role);
			if (request.getRegistrationStage() < 4) {
				user.setStatus(Constants.UserStatusType.INCOMPLETE.toString());
			} else {
				user.setStatus(Constants.UserStatusType.ACTIVE.toString());
			}
			String caseID = getUniqueCaseID(sess);
			user.setCaseID(caseID);
			user.setStage(request.getRegistrationStage());
			user.setCreatedOn(new Date());
			user.setModifiedOn(new Date());
			userId = (int) sess.save(user);
			user.setCreatedBy(userId);
			user.setModifiedBy(userId);
			sess.save(user);
		}
		List<User> usrList = new ArrayList();
		usrList.add(user);

		if (request.getRegistrationStage() >= 2) {
			Query query = sess.createSQLQuery("SELECT id FROM userdetail ud WHERE ud.UserID = " + user.getUserID());

			int userDetailID = 0;
			List<Integer> idList = query.list();
			if (idList.size() > 0) {
				userDetailID = idList.get(0);
			}

			Userdetail userDtls = new Userdetail();
			if (userDetailID > 0) {
				userDtls = getById(Userdetail.class, userDetailID);
			}

			userDtls.setUser(user);
			userDtls.setFirstName(request.getPersonalDetails().getFirstName());
			userDtls.setLastName(request.getPersonalDetails().getLastName());
			userDtls.setContactNo(request.getPersonalDetails().getContactNo());
			userDtls.setDob(DateUtil.getDateFromString(request.getPersonalDetails().getDob(), "MM/dd/yyyy"));
			userDtls.setGender(request.getPersonalDetails().getGender());
			userDtls.setMaritalStatus(request.getPersonalDetails().getMaritalStatus());
			userDtls.setRace(request.getPersonalDetails().getRace());
			userDtls.setFirstName(request.getPersonalDetails().getFirstName());
			userDtls.setReligion(request.getPersonalDetails().getReligion());
			userDtls.setOccupation(request.getPersonalDetails().getOccupation());
			userDtls.setPreference(request.getPersonalDetails().getPreference());
			userDtls.setHobbies(request.getPersonalDetails().getHobbies());
			userDtls.setIncome(request.getPersonalDetails().getIncome());
			userDtls.setCreatedBy(userId);
			userDtls.setCreatedOn(new Date());
			userDtls.setModifiedBy(userId);
			userDtls.setModifiedOn(new Date());

			sess.saveOrUpdate(userDtls);

			if ("Married".equalsIgnoreCase(userDtls.getMaritalStatus())) {
				Query spouseQuery = sess
						.createSQLQuery("SELECT userSpouseID FROM userspouse us " + "WHERE us.UserID = " + user.getUserID());
				int userSpouseID = 0;
				List<Integer> idListspouse = spouseQuery.list();
				if (idListspouse.size() > 0) {
					userSpouseID = idListspouse.get(0);
				}
				Userspouse spouseDtls = new Userspouse();
				if (userSpouseID > 0) {
					spouseDtls = getById(Userspouse.class, userSpouseID);
				}
				spouseDtls.setUser(user);
				spouseDtls.setFirstName(request.getPersonalDetails().getSpouseDetails().getFirstName());
				spouseDtls.setContactNo(request.getPersonalDetails().getSpouseDetails().getContactNo());
				spouseDtls.setDob(DateUtil.getDateFromString(request.getPersonalDetails().getSpouseDetails().getDob(), "MM/dd/yyyy"));
				spouseDtls.setGender(request.getPersonalDetails().getSpouseDetails().getGender());
				spouseDtls.setRace(request.getPersonalDetails().getSpouseDetails().getRace());
				spouseDtls.setFirstName(request.getPersonalDetails().getSpouseDetails().getFirstName());
				spouseDtls.setReligion(request.getPersonalDetails().getSpouseDetails().getReligion());
				spouseDtls.setOccupation(request.getPersonalDetails().getSpouseDetails().getOccupation());
				spouseDtls.setPreference(request.getPersonalDetails().getSpouseDetails().getPreference());
				spouseDtls.setHobbies(request.getPersonalDetails().getSpouseDetails().getHobbies());
				spouseDtls.setIncome(request.getPersonalDetails().getSpouseDetails().getIncome());
				spouseDtls.setCreatedBy(userId);
				spouseDtls.setCreatedOn(new Date());
				spouseDtls.setModifiedBy(userId);
				spouseDtls.setModifiedOn(new Date());
				sess.saveOrUpdate(spouseDtls);
			}

		}

		if (request.getRegistrationStage() >= 3) {
			Query familyQuery = sess.createSQLQuery("SELECT id FROM userfamily uf " + "WHERE uf.UserID = " + user.getUserID());

			int userFamilyID = 0;
			List<Integer> idListfam = familyQuery.list();
			if (idListfam.size() > 0) {
				userFamilyID = idListfam.get(0);
			}

			Userfamily famDtls = new Userfamily();
			if (userFamilyID > 0) {
				famDtls = getById(Userfamily.class, userFamilyID);
			}

			famDtls.setDescription(request.getFamilyDetails().getDescription());
			famDtls.setHaveKids(request.getFamilyDetails().getHaveKids());
			famDtls.setKid(request.getFamilyDetails().getNumberOfKids());
			famDtls.setKidsInfo(request.getFamilyDetails().getKidsPref());

			famDtls.setUser(user);
			famDtls.setCreatedBy(userId);
			famDtls.setModifiedBy(userId);
			famDtls.setCreatedOn(new Date());
			famDtls.setModifiedOn(new Date());

			sess.saveOrUpdate(famDtls);

			Query kidsQuery = sess.createSQLQuery("SELECT userKidID FROM userkids uk " + "WHERE uk.familyID = " + userFamilyID);

			if ("y".equalsIgnoreCase(famDtls.getHaveKids())) {
				List<Integer> idListkids = kidsQuery.list();
				List<Userkid> kidListEntity = new ArrayList(); 
				if (idListkids.size() > 0) {
					for (int kid : idListkids) {
						Userkid uKid = getById(Userkid.class, kid);
						kidListEntity.add(uKid);
					}
					int i = 0;
					for (UserKidsDetails reqKid : request.getFamilyDetails().getKids()) {
							Userkid uKid = kidListEntity.get(i);
							uKid.setName(reqKid.getKidName());
							uKid.setAgeGroup(reqKid.getAge());
							uKid.setHobbies(reqKid.getHobbies());
							uKid.setUserfamily(famDtls);
							sess.merge(uKid);
							i++;
					}

				} else {
					for (UserKidsDetails kid : request.getFamilyDetails().getKids()) {
						Userkid uKid = new Userkid();
						uKid.setName(kid.getKidName());
						uKid.setAgeGroup(kid.getAge());
						uKid.setHobbies(kid.getHobbies());
						uKid.setUserfamily(famDtls);
						sess.saveOrUpdate(uKid);
					}
				} 
			}

		}
		if (request.getRegistrationStage() >= 4) {
			Userlicence lic = new Userlicence();
			Query licQuery = sess.createSQLQuery("SELECT id FROM userlicence l " + "WHERE l.UserID = " + user.getUserID());

			int userLicID = 0;
			List<Integer> idListLic = licQuery.list();
			if (idListLic.size() > 0) {
				userLicID = idListLic.get(0);
			}

			if (userLicID > 0) {
				lic = getById(Userlicence.class, userLicID);
			}

			lic.setAgencyContact(request.getLicenceDetails().getAgencyContact());
			lic.setAgencyWorker(request.getLicenceDetails().getAgencyWorker());
			lic.setLicenceNo(request.getLicenceDetails().getLicenceNo());
			lic.setDateOfIssue(DateUtil.getDateFromString(request.getLicenceDetails().getDateOfIssue(), "MM/dd/yyyy"));

			lic.setUser(user);
			lic.setCreatedBy(userId);
			lic.setModifiedBy(userId);
			lic.setCreatedon(new Date());
			lic.setModifiedOn(new Date());

			sess.saveOrUpdate(lic);

		}

		sess.flush();
		sess.close();
		
		if (userExist) {
			User usertoUpdate = getById(User.class, userId);
			if (request.getRegistrationStage() < 4) {
				user.setStatus(Constants.UserStatusType.INCOMPLETE.toString());
			} else {
				user.setStatus(Constants.UserStatusType.ACTIVE.toString());
			}
			user.setStage(request.getRegistrationStage());
			sessionFactory.getCurrentSession().merge(usertoUpdate);
		}
		msg = "User Registered successfully";

		return msg;
	}

	/* (non-Javadoc)
	 * @purpose: save/Update User profile and family details and license 
	 * for API /member/save
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String saveUser(SaveUserRequest request) throws HibernateException, SQLException, UserException, WSException {
		String msg = "";
		String emailId = request.getPersonalProfile().getUseremail();
		String userName = request.getPersonalProfile().getUserName();

		List<User> userList = null;

		Session sess = sessionFactory.openSession();
		userList = (List<User>) sessionFactory.getCurrentSession().createQuery("from User as u WHERE u.userName = '" + userName + "' "
				+ "	AND u.useremail = '" + emailId + "' and status IN ('ACTIVE','INCOMPLETE') ").list();

		if (userList != null) {
			if (!userList.isEmpty()) {
				User user = userList.get(0);

				user.setUserName(user.getUserName());
				if (!"none".equalsIgnoreCase(request.getPersonalProfile().getPassword())) {
					user.setPassword(aESSecurityBD.encryptAES(request.getPersonalProfile().getPassword()));
				}
				user.setUseremail(request.getPersonalProfile().getUseremail());
				Role role = getById(Role.class, 1);
				user.setRole(role);
				if (request.getRegistrationStage() < 4) {
					user.setStatus(Constants.UserStatusType.INCOMPLETE.toString());
				} else {
					user.setStatus(Constants.UserStatusType.ACTIVE.toString());
				}

				user.setStage(request.getRegistrationStage());
				user.setCreatedOn(new Date());
				user.setModifiedOn(new Date());

				int userId = user.getUserID();

				user.setCreatedBy(userId);
				user.setModifiedBy(userId);

				sess.merge(user);

				List<User> usrList = new ArrayList();
				usrList.add(user);

				if (request.getRegistrationStage() >= 2) {

					Query query = sess.createSQLQuery("SELECT id FROM userdetail ud WHERE ud.UserID = " + user.getUserID());

					int userDetailID = 0;
					List<Integer> idList = query.list();
					if (idList.size() > 0) {
						userDetailID = idList.get(0);
					}

					Userdetail userDtls = new Userdetail();
					if (userDetailID > 0) {
						userDtls = getById(Userdetail.class, userDetailID);
					}

					userDtls.setUser(user);
					userDtls.setFirstName(request.getPersonalDetails().getFirstName());
					userDtls.setLastName(request.getPersonalDetails().getLastName());
					userDtls.setContactNo(request.getPersonalDetails().getContactNo());
					userDtls.setDob(DateUtil.getDateFromString(request.getPersonalDetails().getDob(), "MM/dd/yyyy"));
					userDtls.setGender(request.getPersonalDetails().getGender());
					userDtls.setMaritalStatus(request.getPersonalDetails().getMaritalStatus());
					userDtls.setRace(request.getPersonalDetails().getRace());
					userDtls.setFirstName(request.getPersonalDetails().getFirstName());
					userDtls.setReligion(request.getPersonalDetails().getReligion());
					userDtls.setOccupation(request.getPersonalDetails().getOccupation());
					userDtls.setPreference(request.getPersonalDetails().getPreference());
					userDtls.setHobbies(request.getPersonalDetails().getHobbies());
					userDtls.setIncome(request.getPersonalDetails().getIncome());
					userDtls.setCreatedBy(userId);
					userDtls.setCreatedOn(new Date());
					userDtls.setModifiedBy(userId);
					userDtls.setModifiedOn(new Date());

					if (idList.size() > 0) {
						sess.merge(userDtls);
					} else {
						sess.saveOrUpdate(userDtls);
					}
					Query spouseQuery = sess
							.createSQLQuery("SELECT userSpouseID FROM userspouse us " + "WHERE us.UserID = " + user.getUserID());

					int userSpouseID = 0;
					List<Integer> idListspouse = spouseQuery.list();
					if (idListspouse.size() > 0) {
						userSpouseID = idListspouse.get(0);
					}

					if ("Married".equalsIgnoreCase(userDtls.getMaritalStatus())) {
						Userspouse spouseDtls = new Userspouse();
						if (userSpouseID > 0) {
							spouseDtls = getById(Userspouse.class, userSpouseID);
						}
						spouseDtls.setUser(user);
						spouseDtls.setFirstName(request.getPersonalDetails().getSpouseDetails().getFirstName());
						spouseDtls.setContactNo(request.getPersonalDetails().getSpouseDetails().getContactNo());
						spouseDtls.setDob(DateUtil.getDateFromString(request.getPersonalDetails()
								.getSpouseDetails().getDob(), "MM/dd/yyyy"));
						spouseDtls.setGender(request.getPersonalDetails().getSpouseDetails().getGender());
						spouseDtls.setRace(request.getPersonalDetails().getSpouseDetails().getRace());
						spouseDtls.setFirstName(request.getPersonalDetails().getSpouseDetails().getFirstName());
						spouseDtls.setReligion(request.getPersonalDetails().getSpouseDetails().getReligion());
						spouseDtls.setOccupation(request.getPersonalDetails().getSpouseDetails().getOccupation());
						spouseDtls.setPreference(request.getPersonalDetails().getSpouseDetails().getPreference());
						spouseDtls.setHobbies(request.getPersonalDetails().getSpouseDetails().getHobbies());
						spouseDtls.setIncome(request.getPersonalDetails().getSpouseDetails().getIncome());
						spouseDtls.setCreatedBy(userId);
						spouseDtls.setCreatedOn(new Date());
						spouseDtls.setModifiedBy(userId);
						spouseDtls.setModifiedOn(new Date());
						if (idListspouse.size() > 0) {
							sess.merge(spouseDtls);
						} else {
							sess.saveOrUpdate(spouseDtls);
						}
					}

				}

				if (request.getRegistrationStage() >= 3) {

					Query familyQuery = sess.createSQLQuery("SELECT id FROM userfamily uf " 
							+ "WHERE uf.UserID = " + user.getUserID());

					int userFamilyID = 0;
					List<Integer> idListfam = familyQuery.list();
					if (idListfam.size() > 0) {
						userFamilyID = idListfam.get(0);
					}

					Userfamily famDtls = new Userfamily();
					if (userFamilyID > 0) {
						famDtls = getById(Userfamily.class, userFamilyID);
					}

					famDtls.setDescription(request.getFamilyDetails().getDescription());
					famDtls.setHaveKids(request.getFamilyDetails().getHaveKids());
					famDtls.setKid(request.getFamilyDetails().getNumberOfKids());
					famDtls.setKidsInfo(request.getFamilyDetails().getKidsPref());

					famDtls.setUser(user);
					famDtls.setCreatedBy(userId);
					famDtls.setModifiedBy(userId);
					famDtls.setCreatedOn(new Date());
					famDtls.setModifiedOn(new Date());

					if (idListfam.size() > 0) {
						sess.merge(famDtls);
					} else {
						sess.saveOrUpdate(famDtls);
					}
					Query kidsQuery = sess.createSQLQuery("SELECT userKidID FROM userkids uk "
					+ "WHERE uk.familyID = " + userFamilyID);

					if ("y".equalsIgnoreCase(famDtls.getHaveKids())) {
						List<Integer> idListkids = kidsQuery.list();
						if (idListkids.size() > 0) {
							for (int kid : idListkids) {
								Userkid uKid = getById(Userkid.class, kid);
								for (UserKidsDetails reqKid : request.getFamilyDetails().getKids()) {
									uKid.setName(reqKid.getKidName());
									uKid.setAgeGroup(reqKid.getAge());
									uKid.setHobbies(reqKid.getHobbies());
									uKid.setUserfamily(famDtls);
									sess.merge(uKid);
								}
							}

						} else {
							for (UserKidsDetails kid : request.getFamilyDetails().getKids()) {
								Userkid uKid = new Userkid();
								uKid.setName(kid.getKidName());
								uKid.setAgeGroup(kid.getAge());
								uKid.setHobbies(kid.getHobbies());
								uKid.setUserfamily(famDtls);
								sess.saveOrUpdate(uKid);
							}
						} 
					}

				}
				if (request.getRegistrationStage() >= 4) {
					Userlicence lic = new Userlicence();
					Query licQuery = sess.createSQLQuery("SELECT id FROM userlicence l "
							+ "WHERE l.UserID = " + user.getUserID());

					int userLicID = 0;
					List<Integer> idListLic = licQuery.list();
					if (idListLic.size() > 0) {
						userLicID = idListLic.get(0);
					}

					if (userLicID > 0) {
						lic = getById(Userlicence.class, userLicID);
					}

					lic.setAgencyContact(request.getLicenceDetails().getAgencyContact());
					lic.setAgencyWorker(request.getLicenceDetails().getAgencyWorker());
					lic.setLicenceNo(request.getLicenceDetails().getLicenceNo());
					lic.setDateOfIssue(DateUtil.getDateFromString(request.getLicenceDetails().getDateOfIssue(), "MM/dd/yyyy"));

					lic.setUser(user);
					lic.setCreatedBy(userId);
					lic.setModifiedBy(userId);
					lic.setCreatedon(new Date());
					lic.setModifiedOn(new Date());

					if (idListLic.size() > 0) {
						sess.merge(lic);
					} else {
						sess.saveOrUpdate(lic);
					}
				}

				sess.flush();
				sess.close();

				msg = "User Saved successfully";
			} else {
				msg = "User Details not Found";
			}

		}
		return msg;
	}

	/* (non-Javadoc)
	 * @purpose: Get User communication 
	 * for API /member/inbox
	 * 
	 * @param request
	 */
	@Override
	@Transactional
	public UserInboxResponse getUserInbox(GetUserInboxRequest request, UserInboxResponse response) {
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery("SELECT * FROM userinbox ib WHERE ib.UserID = 1")
				.addEntity(Userinbox.class);

		List<Userinbox> inboxes = query.list();

		List<UserEmail> emailList = new ArrayList();
		for (Object userinbox : inboxes) {
			UserEmail email = new UserEmail();
			BeanUtils.copyProperties(userinbox, email);
			emailList.add(email);
		}
		response.setEmails(emailList);
		return response;
	}

	public AESSecurityBD getaESSecurityBD() {
		return aESSecurityBD;
	}

	public void setaESSecurityBD(AESSecurityBD aESSecurityBD) {
		this.aESSecurityBD = aESSecurityBD;
	}

	private String getUniqueCaseID(Session sess) {
		boolean flag = false;
		String unqCaseId;
		do {
			unqCaseId = generateUniqueCaseID();
			List<User> userList = (List<User>) sess.createQuery("from User as u WHERE u.caseID ='" + unqCaseId + "'").list();
			if (!(userList.size() > 0)) {
				flag = true;
			}

		} while (!flag);

		return unqCaseId;

	}

	private String generateUniqueCaseID() {

		String prefix = Constants.CASEID;
		String middle = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0, 5).toUpperCase();

		List<Integer> numbers = new ArrayList();
		for (int i = 0; i < 10; i++) {
			numbers.add(i);
		}

		Collections.shuffle(numbers);

		String result = "";
		for (int i = 0; i < 4; i++) {
			result += numbers.get(i).toString();
		}

		return prefix + middle + "-" + result;
	}

	@Override
	@Transactional
	public String getUserSessionForTest() {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM usersession ss WHERE ss.userId = 1")
				.addEntity(UserSession.class);

		List<UserSession> list = query.list();
		String sess = list.get(0).getSessionId();
		return sess;
	}

}
