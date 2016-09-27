using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Com.Natoma.HhsPrototype.UserInterface.Models;
using Com.Natoma.HhsPrototype.UserInterface.HhsPrototypeProc;
using AutoMapper;

namespace Com.Natoma.HhsPrototype.UserInterface.Helpers
{
    public class ServiceHelper : IServiceHelper
    {
        private IProtoProcSvc service = null;

        public ServiceHelper()
        {
            //TODO: Remove default constructor once dependency injection framework implemented
            service = new ProtoProcSvcClient();
        }

        public ServiceHelper(IProtoProcSvc protoProcSvc)
        {
            service = protoProcSvc;
        }

        /// <summary>
        /// Persists the user profile and returns the new UID created for this profile
        /// </summary>
        /// <param name="userProfile">User profile to be persisted</param>
        /// <returns>UID of new user profile</returns>
        public long CreateUserProfile(UserProfileModel userProfile)
        {
            UserProfile profileToCreate = Mapper.Map<UserProfile>(userProfile);

            return service.CreateUserProfile(profileToCreate);
        }

        /// <summary>
        /// Updates the user profile
        /// </summary>
        /// <param name="userProfile">User profile to be updated</param>
        /// <returns>true if update is successful</returns>
        public bool UpdateUserProfile(UserProfileModel userProfile)
        {
            UserProfile profileToUpdate = Mapper.Map<UserProfile>(userProfile);

            return service.UpdateUserProfile(profileToUpdate);
        }

        /// <summary>
        /// Persists the message 
        /// </summary>
        /// <param name="messageToSend">Message to be persisted</param>
        /// <returns>true if message save is successful</returns>
        public bool SendMessage(MessageModel messageToSend)
        {
            HhsPrototypeProc.Message message = Mapper.Map<HhsPrototypeProc.Message>(messageToSend);
            bool retVal = false;
            long newId = service.SendMessage(message);

            if (newId > 0)
            {
                message.MessageId = newId;
                retVal = true;
            }

            return retVal;
        }

        /// <summary>
        /// Deletes the specified messages
        /// </summary>
        /// <param name="userProfileId">UID of the user profile whose message is to be deleted</param>
        /// <param name="messageIds">UIDs of the specific messages to delete</param>
        /// <returns>true if messages are deleted</returns>
        public bool DeleteUserMessages(long userProfileId, List<long> messageIds)
        {
            return service.DeleteUserMessages(userProfileId, messageIds);
        }

        /// <summary>
        /// Returns all messages associated to the user profile
        /// </summary>
        /// <param name="userProfileId">UID of the user profile whose messages are to be retrieved</param>
        /// <returns>Messages associated to the user profile</returns>
        public List<MessageModel> GetMessagesForUser(long userProfileId)
        {
            List<Message> results = service.GetMessagesForUser(userProfileId);
            List<MessageModel> retVal = null;

            if (results != null)
            {
                retVal = Mapper.Map<List<Message>, List<MessageModel>>(results);
            }

            return retVal;
        }

        /// <summary>
        /// Determines if username and password identify a user profile
        /// </summary>
        /// <param name="username">User name of the user profile</param>
        /// <param name="password">Password associated with the user profile</param>
        /// <returns>UID of associated user profile. -1 if no user profile matches the criteria.</returns>
        public UserProfileModel LogIn(string username, string password)
        {
            UserProfileModel retVal = null;

            UserProfile results = service.LogIn(username, password);

            if (results != null)
            {
                retVal = Mapper.Map<UserProfileModel>(results);
            }

            return retVal;
        }
    }
}