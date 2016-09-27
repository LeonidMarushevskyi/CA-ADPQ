using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Com.Natoma.HhsPrototype.Proc.DataAccess;
using Com.Natoma.HhsPrototype.Proc.DataContracts;
using Com.Natoma.HhsPrototype.Proc;
using System.ServiceModel;
using System.Collections.Generic;

namespace Test.HhsPrototype.Proc
{
    [TestClass]
    public class UserProfileDataUnitTest
    {
        // Note: UserProfileData stores all data independent of instance

        [TestMethod]
        public void SaveUserProfile_Insert_Success()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            var userProfile = new UserProfile() { FirstName = "testName", Email="a1@b.UserProfileDataUnitTest" };
            long results = -1;

            // Act
            results = userProfileData.SaveUserProfile(userProfile);
            userProfile.Uid = results;

            // Assert
            Assert.IsTrue(results > 0);
            Assert.AreEqual(results, userProfile.Uid);
        }

        [ExpectedException(typeof(FaultException<ServiceError>))]
        [TestMethod]
        public void SaveUserProfile_Insert_Fail_UsernameAlreadyExists()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            var protoProcSvc = new ProtoProcSvc(userProfileData, null);
            var userProfile1 = new UserProfile() { FirstName = "testName1", Email = "a2@b.UserProfileDataUnitTest" };
            var userProfile2 = new UserProfile() { FirstName = "testName2", Email = "a2@b.UserProfileDataUnitTest" };
            long results = -1;

            results = protoProcSvc.CreateUserProfile(userProfile1);

            // Act
            results = protoProcSvc.CreateUserProfile(userProfile2);

            // Assert
            // Should not get here as call to create should throw exception 
        }

        [TestMethod]
        public void SaveUserProfile_Update_Success()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            var userProfile1 = new UserProfile() { FirstName = "testName", Password="123abc", Email = "a3@b.UserProfileDataUnitTest" };
            var userProfile2 = new UserProfile() { FirstName = "testName2", Password = "123abcQ", Email = "a3@b.UserProfileDataUnitTest" };
            UserProfile storedUserProfile = null;
            long createResults = -1;
            long updateResults = -1;
            createResults = userProfileData.SaveUserProfile(userProfile1);
            userProfile1.Uid = createResults;
            userProfile2.Uid = userProfile1.Uid;

            // Act
            updateResults = userProfileData.SaveUserProfile(userProfile2);
            storedUserProfile = userProfileData.GetUserProfile(userProfile2.Email);

            // Assert
            Assert.AreEqual(updateResults, 0);
            Assert.AreEqual(storedUserProfile.Password, userProfile2.Password);
        }

        [TestMethod]
        [ExpectedException(typeof(FaultException<ServiceError>))]
        public void SaveUserProfile_Update_Fail_DoesNotExist()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            var userProfile1 = new UserProfile() { FirstName = "testName", Password = "123abc", Email = "a3@b.UserProfileDataUnitTest", Uid=50 };
            long updateResults = -1;

            // Act
            updateResults = userProfileData.SaveUserProfile(userProfile1);

            // Assert
            // Should not get here as call to create should throw exception 
        }

        [TestMethod]
        [ExpectedException(typeof(FaultException<ServiceError>))]
        public void SaveUserProfile_Update_Fail_NewUsernameAlreadyExists()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            var userProfile1 = new UserProfile() { FirstName = "testName", Password = "123abc", Email = "a3@b.UserProfileDataUnitTest" };
            var userProfile2 = new UserProfile() { FirstName = "testName2", Password = "123abcQ", Email = "a3@b.UserProfileDataUnitTest2" };
            var userProfile3 = new UserProfile() { FirstName = "testName", Password = "123abc", Email = "a3@b.UserProfileDataUnitTest2" };
            long createResults = -1;
            long updateResults = -1;
            createResults = userProfileData.SaveUserProfile(userProfile1);
            createResults = userProfileData.SaveUserProfile(userProfile3);
            userProfile2.Uid = userProfile1.Uid;

            // Act
            updateResults = userProfileData.SaveUserProfile(userProfile2);

            // Assert
            // Should not get here as call to create should throw exception 
        }

        [TestMethod]
        public void GetUserProfileByUserName_Success()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            var userProfile1 = new UserProfile() { FirstName = "testName", Password = "123abc", Email = "GetUserProfileByUserName_Success@b.UserProfileDataUnitTest" };
            long createResults = userProfileData.SaveUserProfile(userProfile1);
            UserProfile returnedProfile = null;

            // Act
            returnedProfile = userProfileData.GetUserProfile(userProfile1.Email);

            // Assert
            Assert.IsNotNull(returnedProfile);
            Assert.AreEqual(returnedProfile.Email, userProfile1.Email);
        }

        [TestMethod]
        public void GetUserProfileByUserName_NotExist()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            var userProfile1 = new UserProfile() { FirstName = "testName", Password = "123abc", Email = "GetUserProfileByUserName_NotExist@b.UserProfileDataUnitTest" };
            UserProfile returnedProfile = null;

            // Act
            returnedProfile = userProfileData.GetUserProfile(userProfile1.Email);

            // Assert
            Assert.IsNull(returnedProfile);
        }

        [TestMethod]
        public void GetUserProfileByUserId_Success()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            var userProfile1 = new UserProfile() { FirstName = "testName", Password = "123abc", Email = "GetUserProfileByUserId_Success3@b.UserProfileDataUnitTest" };
            long createResults = userProfileData.SaveUserProfile(userProfile1);
            UserProfile returnedProfile = null;

            // Act
            returnedProfile = userProfileData.GetUserProfile(createResults);

            // Assert
            Assert.IsNotNull(returnedProfile);
            Assert.AreEqual(returnedProfile.Email, userProfile1.Email);
        }

        [TestMethod]
        public void GetUserProfileByUserId_NotExist()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            var userProfile1 = new UserProfile() { FirstName = "testName", Password = "123abc", Email = "GetUserProfileByUserId_NotExist@b.UserProfileDataUnitTest" };
            UserProfile returnedProfile = null;

            // Act
            returnedProfile = userProfileData.GetUserProfile(-1);

            // Assert
            Assert.IsNull(returnedProfile);
        }
    }
}

