using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Moq;
using Com.Natoma.HhsPrototype.Proc.DataAccess;
using Com.Natoma.HhsPrototype.Proc.DataContracts;
using Com.Natoma.HhsPrototype.Proc;
using System.ServiceModel;
using System.Collections.Generic;
using System.Configuration;
using System.IO;

namespace Test.HhsPrototype.Proc
{
    [TestClass]
    public class ProtoProcSvcIntgTest
    {
        // Note: UserProfileData stores all data independent of instance

        [TestInitialize]
        public void Initialize()
        {
            string baseDirectory = Utilities.BaseDirectory();

            string[] files = Directory.GetFiles(baseDirectory, "*Data.xml");

            foreach (string fileToDelete in files)
            {
                File.Delete(fileToDelete);
            }
        }

        [TestMethod]
        public void SaveUserProfileIntg_Insert_Success()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            IMessageData messageData = new MessageData();
            var protoProcSvc = new ProtoProcSvc(userProfileData, messageData);
            var userProfile = new UserProfile() { FirstName = "testName", Email = "a@b.ProtoProcSvcIntgTest" };
            long results = -1;

            // Act
            results = protoProcSvc.CreateUserProfile(userProfile);
            userProfile.Uid = results;

            // Assert
            Assert.AreEqual(results, 1);
            Assert.AreEqual(results, userProfile.Uid);
        }

        [ExpectedException(typeof(FaultException<ServiceError>))]
        [TestMethod]
        public void SaveUserProfileIntg_Insert_Fail_UsernameAlreadyExists()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            IMessageData messageData = new MessageData();
            var protoProcSvc = new ProtoProcSvc(userProfileData, messageData);
            var userProfile = new UserProfile() { FirstName = "testName", Email = "a@b.ProtoProcSvcIntgTest" };
            long results = -1;

            // Act
            results = protoProcSvc.CreateUserProfile(userProfile);

            // Assert
            // Should not get here as call to create should throw exception 
        }

        [TestMethod]
        public void SaveUserProfileIntg_Update_Success()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            IMessageData messageData = new MessageData();
            var protoProcSvc = new ProtoProcSvc(userProfileData, messageData);
            var userProfile1 = new UserProfile() { FirstName = "testName 2", Password="abc123", Email = "a2@b.ProtoProcSvcIntgTest" };
            var userProfile2 = new UserProfile() { FirstName = "testName 3", Password="abc1234", Email = "a2@b.ProtoProcSvcIntgTest" };
            long createResults = protoProcSvc.CreateUserProfile(userProfile1);
            bool updateResults = false;
            UserProfile savedUserProfile = null;

            userProfile2.Uid = createResults;

            // Act
            updateResults = protoProcSvc.UpdateUserProfile(userProfile2);
            savedUserProfile = protoProcSvc.LogIn(userProfile2.Email, userProfile2.Password);

            // Assert
            Assert.AreEqual(updateResults, true);
            Assert.AreEqual(savedUserProfile.FirstName, userProfile2.FirstName);
        }

        [ExpectedException(typeof(FaultException<ServiceError>))]
        [TestMethod]
        public void SaveUserProfileIntg_Update_Fail_DoesNotExist()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            IMessageData messageData = new MessageData();
            var protoProcSvc = new ProtoProcSvc(userProfileData, messageData);
            var userProfile = new UserProfile() { FirstName = "testName 2", Email = "a@b.c", Uid = 500 };
            bool results = false;

            // Act
            results = protoProcSvc.UpdateUserProfile(userProfile);

            // Assert
            Assert.AreEqual(results, true);
        }

        [ExpectedException(typeof(FaultException<ServiceError>))]
        [TestMethod]
        public void SaveUserProfileIntg_Update_Fail_NewUsernameAlreadyExists()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            IMessageData messageData = new MessageData();
            var protoProcSvc = new ProtoProcSvc(userProfileData, messageData);
            var userProfile1 = new UserProfile() { FirstName = "testName 2", Password = "abc123", Email = "a2@b.ProtoProcSvcIntgTest4" };
            var userProfile2 = new UserProfile() { FirstName = "testName 3", Password = "abc1234", Email = "a2@b.ProtoProcSvcIntgTest5" };
            long createResults = protoProcSvc.CreateUserProfile(userProfile1);
            bool updateResults = false;

            createResults = protoProcSvc.CreateUserProfile(userProfile2);
            userProfile2.Email = userProfile1.Email;

            // Act
            updateResults = protoProcSvc.UpdateUserProfile(userProfile2);

            // Assert
            // Should not get here as call to create should throw exception 
        }

        public void Login_Success()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            IMessageData messageData = new MessageData();
            var protoProcSvc = new ProtoProcSvc(userProfileData, messageData);
            var userProfile = new UserProfile() { FirstName = "testName 2", Password = "abc123", Email = "Login_Success@b.ProtoProcSvcIntgTest" };
            long createResults = protoProcSvc.CreateUserProfile(userProfile);
            UserProfile loginResults = null;

            userProfile.Uid = createResults;

            // Act
            loginResults = protoProcSvc.LogIn(userProfile.Email, userProfile.Password);

            // Assert
            Assert.IsNotNull(loginResults);
            Assert.AreEqual(loginResults.Uid, userProfile.Uid);
        }

        public void Login_Fail_NoMatchingUser()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            IMessageData messageData = new MessageData();
            var protoProcSvc = new ProtoProcSvc(userProfileData, messageData);
            var userProfile = new UserProfile() { FirstName = "testName 2", Password = "abc123", Email = "Login_Fail_NoMatchingUser@b.ProtoProcSvcIntgTest" };
            UserProfile loginResults = null;

            // Act
            loginResults = protoProcSvc.LogIn(userProfile.Email, userProfile.Password);

            // Assert
            Assert.IsNull(loginResults);
        }

        public void Login_Fail_WrongPassword()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            IMessageData messageData = new MessageData();
            var protoProcSvc = new ProtoProcSvc(userProfileData, messageData);
            var userProfile = new UserProfile() { FirstName = "testName 2", Password = "abc123", Email = "Login_Fail_WrongPassword@b.ProtoProcSvcIntgTest" };
            long createResults = protoProcSvc.CreateUserProfile(userProfile);
            UserProfile loginResults = null;

            userProfile.Uid = createResults;

            // Act
            loginResults = protoProcSvc.LogIn(userProfile.Email, userProfile.Password+"2");

            // Assert
            Assert.IsNull(loginResults);
        }

        #region Messages
        [TestMethod]
        public void SendMessageIntg_Success()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            IMessageData messageData = new MessageData();
            var protoProcSvc = new ProtoProcSvc(userProfileData, messageData);
            var userProfile = new UserProfile() { FirstName = "testName", Email = "a@b.ProtoProcSvcIntgTestMsg" };
            var messageToSend = new Message() { Body = "test body", DateSent = DateTime.Now, IsSent = true };
            long results = -1;

            results = protoProcSvc.CreateUserProfile(userProfile);
            userProfile.Uid = results;
            messageToSend.UserProfileId = results;

            // Act
            long messageResults = protoProcSvc.SendMessage(messageToSend);

            // Assert
            Assert.IsTrue(messageResults > 0);
        }

        [ExpectedException(typeof(FaultException<ServiceError>))]
        [TestMethod]
        public void SendMessageIntg_Fail()
        {
            // Arrange
            IUserProfileData userProfileData = new UserProfileData();
            IMessageData messageData = new MessageData();
            var protoProcSvc = new ProtoProcSvc(userProfileData, messageData);
            var messageToSend = new Message() { Body = "test body", DateSent = DateTime.Now, IsSent = true, UserProfileId = 1000 };

            // Act
            long messageResults = protoProcSvc.SendMessage(messageToSend);

            // Assert
            // Should not get here as call to create should throw exception 
        }

        public void GetMessageIntg_Success() {

        }

        public void GetMessageIntg_Fail()
        {

        }
        #endregion
    }
}
