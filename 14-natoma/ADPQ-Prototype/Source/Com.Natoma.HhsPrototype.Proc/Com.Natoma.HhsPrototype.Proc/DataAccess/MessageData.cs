using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Com.Natoma.HhsPrototype.Proc.DataContracts;
using System.Xml;
using System.Xml.Serialization;
using System.IO;
using System.Configuration;
using System.ServiceModel;

namespace Com.Natoma.HhsPrototype.Proc.DataAccess
{
    /// <summary>
    /// Manages data access activities for Messages
    /// </summary>
    public class MessageData : IMessageData
    {
        private static Dictionary<long, List<Message>> allMessageData = null;
        private static readonly string FileName = "MessageData.xml";
        private static long nextId = 1;
        private static String token = "";

        public MessageData()
        {
            lock (token)
            {
                if (allMessageData == null)
                {
                    string loadFileName = Path.Combine(BaseDirectory(), FileName);

                    if (File.Exists(loadFileName))
                    {
                        string attributeXml = string.Empty;
                        List<List<Message>> messages = null;

                        XmlDocument xmlDocument = new XmlDocument();
                        xmlDocument.Load(loadFileName);
                        string xmlString = xmlDocument.OuterXml;

                        using (StringReader read = new StringReader(xmlString))
                        {
                            Type outType = typeof(List<List<Message>>);

                            XmlSerializer serializer = new XmlSerializer(outType);
                            using (XmlReader reader = new XmlTextReader(read))
                            {
                                List<List<Message>> fileToLoad = (List<List<Message>>)serializer.Deserialize(reader);
                                reader.Close();
                                messages = fileToLoad;
                            }

                            read.Close();
                        }
                        if (messages != null && messages.Count > 0)
                        {
                            allMessageData = new Dictionary<long, List<Message>>();
                            foreach (List<Message> group in messages)
                            {
                                allMessageData.Add(group[0].UserProfileId, group);
                                foreach (Message row in group)
                                {
                                    nextId = Math.Max(row.MessageId + 1, nextId);
                                }
                            }
                        }
                    }
                }

                if (allMessageData == null)
                {
                    allMessageData = new Dictionary<long, List<Message>>();
                }
            }
        }

        /// <summary>
        /// Insert or update message data
        /// </summary>
        /// <param name="messageToSave">Message to save</param>
        /// <returns>UID of saved message (-1 if not saved)</returns>
        public long SaveMessage(Message messageToSave)
        {
            long retVal = -1;

            // TODO: Make real call to database
            lock (token)
            {
                messageToSave.MessageId = nextId++;
                if (allMessageData.Keys.Contains(messageToSave.UserProfileId))
                {
                    allMessageData[messageToSave.UserProfileId].Add(messageToSave);
                }
                else
                {
                    List<Message> newMessageList = new List<Message>();
                    newMessageList.Add(messageToSave);
                    allMessageData.Add(messageToSave.UserProfileId, newMessageList);
                }
                retVal = messageToSave.MessageId;

                SaveData();
            }

            return retVal;
        }

        /// <summary>
        /// Return messages associated with user profile id
        /// </summary>
        /// <param name="userProfileId">id of user profile associated to messages</param>
        /// <returns>All messages associated to user profile</returns>
        public List<Message> GetMessagesForUserProfile(long userProfileId)
        {
            List<Message> retVal = new List<Message>();

            // TODO: Make real call to database
            if (allMessageData.Keys.Contains(userProfileId))
            {
                retVal = allMessageData[userProfileId];
            }

            return retVal;
        }

        /// <summary>
        /// Deletes the specified messages
        /// </summary>
        /// <param name="userProfileId">UID of the user profile whose message is to be deleted</param>
        /// <param name="messageIds">UIDs of the specific messages to delete</param>
        /// <returns>true if message(s) is deleted</returns>
        public bool DeleteUserMessages(long userProfileId, List<long> messageIds)
        {
            // TODO: Make real call to database
            List<Message> userMessages = null;
            List<Message> messagesToRemove = new List<Message>();

            if (allMessageData.Keys.Contains(userProfileId))
            {
                userMessages = allMessageData[userProfileId];
                foreach (Message msgToCheck in userMessages)
                {
                    if (messageIds.Contains(msgToCheck.MessageId))
                    {
                        messagesToRemove.Add(msgToCheck);
                    }
                }
                if (messagesToRemove.Count > 0)
                {
                    foreach (Message row in messagesToRemove)
                    {
                        userMessages.Remove(row);
                    }
                }
            }
            else
            {
                ServiceError error = new ServiceError() { ErrorMessage = "This user does not have any messages to delete", ErrorDetails = "This user does not have any messages to delete" };
                throw new FaultException<ServiceError>(error, new FaultReason(error.ErrorDetails));
            }

            return true;
        }


        private void SaveData()
        {
            string saveFileName = Path.Combine(BaseDirectory(), FileName);

            XmlDocument xmlDocument = new XmlDocument();
            XmlSerializer serializer = new XmlSerializer(typeof(List<List<Message>>));
            using (MemoryStream stream = new MemoryStream())
            {
                serializer.Serialize(stream, allMessageData.Values.ToList<List<Message>>());
                stream.Position = 0;
                xmlDocument.Load(stream);
                xmlDocument.Save(saveFileName);
                stream.Close();
            }

        }

        private string BaseDirectory()
        {
            string baseDirectory = ConfigurationManager.AppSettings.Get("saveDirectory");

            if (string.IsNullOrEmpty(baseDirectory) || !Directory.Exists(baseDirectory))
            {
                baseDirectory = Directory.GetCurrentDirectory();
            }

            return baseDirectory;
        }
    }
}