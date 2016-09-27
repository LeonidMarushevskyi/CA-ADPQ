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
    public class ProtoProcSvcUnitTest
    {
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

        #region UserProfile
        [TestMethod]
        public void CreateUserProfile_Success()
        {
            // Arrange
            var mockUserProfileData = new Mock<IUserProfileData>();
            var protoProcSvc = new ProtoProcSvc(mockUserProfileData.Object, null);
            var userProfile = new UserProfile() { FirstName = "testName"};
            long results = -1;

            mockUserProfileData.Setup(t => t.SaveUserProfile(userProfile)).Returns((20));

            // Act
            results = protoProcSvc.CreateUserProfile(userProfile);

            // Assert
            mockUserProfileData.Verify(t => t.SaveUserProfile(It.Is<UserProfile>(u => u.FirstName == "testName")));
            Assert.AreEqual(results, 20);
        }

        [TestMethod]
        [ExpectedException(typeof(FaultException<ServiceError>))]
        public void CreateUserProfile_Fail_AlreadyHasUID()
        {
            // Arrange
            var mockUserProfileData = new Mock<IUserProfileData>();
            var protoProcSvc = new ProtoProcSvc(mockUserProfileData.Object, null);
            var userProfile = new UserProfile() { FirstName = "testName", Uid=1 };
            long results = -1;

            mockUserProfileData.Setup(t => t.SaveUserProfile(userProfile)).Returns((20));

            // Act
            results = protoProcSvc.CreateUserProfile(userProfile);

            // Assert
            // Not needed as assignment to results should throw FaultException
        }

        [TestMethod]
        public void UpdateUserProfile_Success()
        {
            // Arrange
            var mockUserProfileData = new Mock<IUserProfileData>();
            var protoProcSvc = new ProtoProcSvc(mockUserProfileData.Object, null);
            var userProfile = new UserProfile() { FirstName = "testName", Uid = 1 };
            bool results = false;

            mockUserProfileData.Setup(t => t.SaveUserProfile(userProfile)).Returns((20));

            // Act
            results = protoProcSvc.UpdateUserProfile(userProfile);

            // Assert
            mockUserProfileData.Verify(t => t.SaveUserProfile(It.Is<UserProfile>(u => u.FirstName == "testName")));
            Assert.IsTrue(results);
        }

        [TestMethod]
        [ExpectedException(typeof(FaultException<ServiceError>))]
        public void UpdateUserProfile_Fail_DoesNotHaveUid()
        {
            // Arrange
            var mockUserProfileData = new Mock<IUserProfileData>();
            var protoProcSvc = new ProtoProcSvc(mockUserProfileData.Object, null);
            var userProfile = new UserProfile() { FirstName = "testName" };
            bool results = false;

            mockUserProfileData.Setup(t => t.SaveUserProfile(userProfile)).Returns((20));

            // Act
            results = protoProcSvc.UpdateUserProfile(userProfile);

            // Assert
            Assert.IsTrue(results);
        }

        [TestMethod]
        public void Login_Success()
        {
            // Arrange
            var mockUserProfileData = new Mock<IUserProfileData>();
            var protoProcSvc = new ProtoProcSvc(mockUserProfileData.Object, null);
            var userProfile = new UserProfile() { FirstName = "testName", Password="pwd", Email="test@test.com"};
            UserProfile results = null;

            mockUserProfileData.Setup(t => t.GetUserProfile(userProfile.Email)).Returns(userProfile);

            // Act
            results = protoProcSvc.LogIn(userProfile.Email, userProfile.Password);

            // Assert
            Assert.AreEqual(results, userProfile);
        }

        [TestMethod]
        public void Login_Failure_WrongPwd()
        {
            // Arrange
            var mockUserProfileData = new Mock<IUserProfileData>();
            var protoProcSvc = new ProtoProcSvc(mockUserProfileData.Object, null);
            var userProfile = new UserProfile() { FirstName = "testName", Password = "pwd", Email = "test@test.com" };
            UserProfile results = null;

            mockUserProfileData.Setup(t => t.GetUserProfile(userProfile.Email)).Returns(userProfile);

            // Act
            results = protoProcSvc.LogIn(userProfile.Email, userProfile.Password+"j");

            // Assert
            Assert.IsNull(results);
        }
        #endregion

        #region Message

        [TestMethod]
        public void SendMessage_Success()
        {
            // Arrange
            var mockMessageData = new Mock<IMessageData>();
            var mockUserProfileData = new Mock<IUserProfileData>();
            var protoProcSvc = new ProtoProcSvc(mockUserProfileData.Object, mockMessageData.Object);
            var messageToSend = new Message() { Body = "Test Body", DateSent = DateTime.Now, IsSent = true, UserProfileId = 1 };
            long results = -1;

            mockMessageData.Setup(t => t.SaveMessage(messageToSend)).Returns(1);
            mockUserProfileData.Setup(t => t.GetUserProfile(1)).Returns(new UserProfile() { Uid = 1 });

            // Act
            results = protoProcSvc.SendMessage(messageToSend);

            // Assert
            mockMessageData.Verify(t => t.SaveMessage(It.Is<Message>(u => u.Body == "Test Body")));
            Assert.AreEqual(results, 1);

        }

        [TestMethod]
        [ExpectedException(typeof(FaultException<ServiceError>))]
        public void SendMessage_Failure()
        {
            // Arrange
            var mockMessageData = new Mock<IMessageData>();
            var mockUserProfileData = new Mock<IUserProfileData>();
            var protoProcSvc = new ProtoProcSvc(mockUserProfileData.Object, mockMessageData.Object);
            var messageToSend = new Message() { Body = "Test Body", DateSent = DateTime.Now, IsSent = true, UserProfileId = 1 };
            long results = -1;
            UserProfile nullProfile = null;

            mockMessageData.Setup(t => t.SaveMessage(messageToSend)).Returns(1);
            mockUserProfileData.Setup(t => t.GetUserProfile(1)).Returns(nullProfile);

            // Act
            results = protoProcSvc.SendMessage(messageToSend);

            // Assert
            // Not needed as assignment to results should throw FaultException
        }

        [TestMethod]
        public void GetMessagesForUser_Success()
        {
            // Arrange
            var mockMessageData = new Mock<IMessageData>();
            var protoProcSvc = new ProtoProcSvc(null, mockMessageData.Object);
            var messageToSend = new Message() { Body = "Test Body", DateSent = DateTime.Now, IsSent = true, UserProfileId = 1 };
            List<Message> results = null;
            List<Message> mockResults = new List<Message>();

            mockResults.Add(new Message() { Body = "msg 1", DateSent = DateTime.Now.AddDays(-2), IsSent = true, UserProfileId = 1 });
            mockResults.Add(new Message() { Body = "msg 2", DateSent = DateTime.Now.AddDays(-1), IsSent = true, UserProfileId = 1 });

            mockMessageData.Setup(t => t.GetMessagesForUserProfile(1)).Returns(mockResults);

            // Act
            results = protoProcSvc.GetMessagesForUser(1);

            // Assert
            Assert.AreEqual(results.Count, 2);
            Assert.AreEqual(results[0].Body, "msg 1");
        }

        [TestMethod]
        public void GetMessagesForUser_None()
        {
            // Arrange
            var mockMessageData = new Mock<IMessageData>();
            var protoProcSvc = new ProtoProcSvc(null, mockMessageData.Object);
            var messageToSend = new Message() { Body = "Test Body", DateSent = DateTime.Now, IsSent = true, UserProfileId = 1 };
            List<Message> results = null;
            List<Message> mockResults = new List<Message>();

            mockMessageData.Setup(t => t.GetMessagesForUserProfile(1)).Returns(mockResults);

            // Act
            results = protoProcSvc.GetMessagesForUser(1);

            // Assert
            Assert.AreEqual(results.Count, 0);
        }

        [TestMethod]
        public void DeleteMessages_Success()
        {
            // Arrange
            var mockMessageData = new Mock<IMessageData>();
            var protoProcSvc = new ProtoProcSvc(null, mockMessageData.Object);
            List<long> messagesToDelete = new List<long>();

            mockMessageData.Setup(t => t.DeleteUserMessages(1, messagesToDelete)).Returns(true);

            messagesToDelete.Add(1);

            // Act
            bool results = protoProcSvc.DeleteUserMessages(1, messagesToDelete);

            // Assert
            Assert.IsTrue(results);
        }

        #endregion
    }
}
