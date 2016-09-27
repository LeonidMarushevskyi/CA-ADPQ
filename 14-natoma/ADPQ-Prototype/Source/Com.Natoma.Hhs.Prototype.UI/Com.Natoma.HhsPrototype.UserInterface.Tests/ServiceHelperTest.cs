using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Com.Natoma.HhsPrototype.UserInterface.Models;
using Com.Natoma.HhsPrototype.UserInterface.HhsPrototypeProc;
using Moq;
using Com.Natoma.HhsPrototype.UserInterface.Helpers;

namespace Com.Natoma.HhsPrototype.UserInterface.Tests
{
    [TestClass]
    public class ServiceHelperTest
    {
        [TestMethod]
        public void CreateUserProfile_Success()
        {
            // Arrange
            UserProfileModel profileToCreate = new UserProfileModel() { Email = "abc@123.com", FirstName = "test", LastName = "user", Password = "abc123" };
            var mockSvc = new Mock<IProtoProcSvc>();
            var serviceHelper = new ServiceHelper(mockSvc.Object);
            long valueToReturn = 4;

            mockSvc.Setup(t => t.CreateUserProfile(It.IsAny<UserProfile>())).Returns(valueToReturn);

            // Act
            long results = serviceHelper.CreateUserProfile(profileToCreate);

            // Assert
            Assert.IsTrue(results > 0);
        }

        [TestMethod]
        public void UpdateUserProfile_Success()
        {
            // Arrange
            UserProfileModel profileToUpdate = new UserProfileModel() { Email = "abc@123.com", FirstName = "test", LastName = "user", Password = "abc123", Uid = 2 };
            var mockSvc = new Mock<IProtoProcSvc>();
            var serviceHelper = new ServiceHelper(mockSvc.Object);

            mockSvc.Setup(t => t.UpdateUserProfile(It.IsAny<UserProfile>())).Returns(true);

            // Act
            bool results = serviceHelper.UpdateUserProfile(profileToUpdate);

            // Assert
            Assert.IsTrue(results);
        }

        [TestMethod]
        public void SendMessage_Success()
        {
            // Arrange
            MessageModel messageToSend = new MessageModel() { Body = "Hi Mom!", DateSent = DateTime.Now, IsSent = true, UserProfileId = 1 };
            var mockSvc = new Mock<IProtoProcSvc>();
            var serviceHelper = new ServiceHelper(mockSvc.Object);

            mockSvc.Setup(t => t.SendMessage(It.IsAny<Message>())).Returns(1);

            // Act
            bool results = serviceHelper.SendMessage(messageToSend);

            // Assert
            Assert.IsTrue(results);
        }

        [TestMethod]
        public void GetMessagesForUser_Success()
        {
            // Arrange
            List<Message> messagesToReturn = new List<Message>();
            var mockSvc = new Mock<IProtoProcSvc>();
            var serviceHelper = new ServiceHelper(mockSvc.Object);

            messagesToReturn.Add(new Message() { Body = "Hi back", UserProfileId = 5, IsSent = true });
            mockSvc.Setup(t => t.GetMessagesForUser(5)).Returns(messagesToReturn);

            // Act
            List<MessageModel> results = serviceHelper.GetMessagesForUser(5);

            // Assert
            Assert.IsNotNull(results);
            Assert.AreEqual(1, results.Count);
        }

        [TestMethod]
        public void LogIn(string username, string password)
        {
            // Arrange
            UserProfile profileToReturn = new UserProfile() { Email = "abc@123.com", FirstName = "test", LastName = "user", Password = "abc123" };
            var mockSvc = new Mock<IProtoProcSvc>();
            var serviceHelper = new ServiceHelper(mockSvc.Object);

            mockSvc.Setup(t => t.LogIn(profileToReturn.Email, profileToReturn.Password)).Returns(profileToReturn);

            // Act
            UserProfileModel results = serviceHelper.LogIn(profileToReturn.Email, profileToReturn.Password);

            // Assert
            Assert.IsNotNull(results);

        }
    }
}