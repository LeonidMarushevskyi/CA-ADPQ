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
    public class MessageDataUnitTest
    {
        // Note: MessageData stores all data independent of instance

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
        public void SaveMessage_Success()
        {
            // Arrange
            IMessageData messageData = new MessageData();
            var messageToSave = new Message() { Body = "test", DateSent = DateTime.Now, UserProfileId = 1, IsSent = true };
            long results = -1;

            // Act
            results = messageData.SaveMessage(messageToSave);

            // Assert
            Assert.IsTrue(results > 0);
        }

        [TestMethod]
        public void GetMessagesForUserProfile_Success()
        {
            // Arrange
            IMessageData messageData = new MessageData();
            var messageToSave = new Message() { Body = "test", DateSent = DateTime.Now, UserProfileId = 1, IsSent = true };
            long results = messageData.SaveMessage(messageToSave);
            List<Message> userMessages = null;

            // Act
            userMessages = messageData.GetMessagesForUserProfile(1);

            // Assert
            Assert.IsNotNull(userMessages);
        }

        [TestMethod]
        public void GetMessagesForUserProfile_NoMessages()
        {
            // Arrange
            IMessageData messageData = new MessageData();
            List<Message> userMessages = null;

            // Act
            userMessages = messageData.GetMessagesForUserProfile(100);

            // Assert
            Assert.AreEqual(userMessages.Count, 0);
        }

        [TestMethod]
        public void DeleteMessages_Success()
        {
            // Arrange
            IMessageData messageData = new MessageData();
            var messageToSave = new Message() { Body = "test", DateSent = DateTime.Now, UserProfileId = 1, IsSent = true };
            long saveResults = messageData.SaveMessage(messageToSave);
            List<long> messagesToDelete = new List<long>();

            messageToSave.MessageId = saveResults;
            messagesToDelete.Add(saveResults);

            // Act
            bool results = messageData.DeleteUserMessages(messageToSave.UserProfileId, messagesToDelete);

            // Assert
            Assert.IsTrue(results);
        }

        [TestMethod]
        public void DeleteMessages_Success_NoMatchingMessagesToDelete()
        {
            // Arrange
            IMessageData messageData = new MessageData();
            var messageToSave = new Message() { Body = "test", DateSent = DateTime.Now, UserProfileId = 1, IsSent = true };
            long saveResults = messageData.SaveMessage(messageToSave);
            List<long> messagesToDelete = new List<long>();

            messageToSave.MessageId = saveResults;
            messagesToDelete.Add(saveResults);
            messagesToDelete.Add(saveResults+1);

            // Act
            bool results = messageData.DeleteUserMessages(messageToSave.UserProfileId, messagesToDelete);

            // Assert
            Assert.IsTrue(results);
        }

        [ExpectedException(typeof(FaultException<ServiceError>))]
        [TestMethod]
        public void DeleteMessages_Fail_NoUserProfile()
        {
            // Arrange
            IMessageData messageData = new MessageData();
            var messageToSave = new Message() { Body = "test", DateSent = DateTime.Now, UserProfileId = 1, IsSent = true };
            long saveResults = messageData.SaveMessage(messageToSave);
            List<long> messagesToDelete = new List<long>();

            messageToSave.MessageId = saveResults;
            messagesToDelete.Add(saveResults);
            messagesToDelete.Add(saveResults + 1);

            // Act
            bool results = messageData.DeleteUserMessages(1000, messagesToDelete);

            // Assert
            // Not needed as assignment to results should throw FaultException
        }
    }
}
