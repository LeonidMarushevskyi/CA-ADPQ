using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using Com.Natoma.HhsPrototype.Proc.DataContracts;
using Com.Natoma.HhsPrototype.Proc.DataAccess;

namespace Com.Natoma.HhsPrototype.Proc
{
    /// <summary>
    /// Provides data persistence and retrieval for the HHS prototype
    /// </summary>
    public class ProtoProcSvc : IProtoProcSvc
    {
        /// <summary>
        /// Data access for UserProfile
        /// </summary>
        private IUserProfileData userProfileData = null;

        /// <summary>
        /// Data access for Message
        /// </summary>
        private IMessageData messageData = null;

        /// <summary>
        /// Default constructor
        /// </summary>
        public ProtoProcSvc()
        {
            //TODO: Remove default constructor once dependency injection framework implemented
            userProfileData = new UserProfileData();
            messageData = new MessageData();
        }

        public ProtoProcSvc(IUserProfileData userProfileData, IMessageData messageData)
        {
            this.userProfileData = userProfileData;
            this.messageData = messageData;
        }

        /// <summary>
        /// Persists the user profile and returns the new UID created for this profile
        /// </summary>
        /// <param name="userProfileToCreate">User profile to be persisted</param>
        /// <returns>UID of new user profile</returns>
        public long CreateUserProfile(UserProfile userProfileToCreate)
        {
            if (userProfileToCreate.Uid.HasValue)
            {
                ServiceError error = new ServiceError() { ErrorMessage = "Failed to create user profile", ErrorDetails = string.Format("User profile ID {0} already exists.", userProfileToCreate.Uid.Value) };
                throw new FaultException<ServiceError>(error, new FaultReason(error.ErrorDetails));
            }
            return userProfileData.SaveUserProfile(userProfileToCreate);
        }

        /// <summary>
        /// Updates the user profile
        /// </summary>
        /// <param name="userProfileToUpdate">User profile to be updated</param>
        /// <returns>true if update is successful</returns>
        public bool UpdateUserProfile(UserProfile userProfileToUpdate)
        {
            if (!userProfileToUpdate.Uid.HasValue)
            {
                ServiceError error = new ServiceError() { ErrorMessage = "Failed to update user profile", ErrorDetails = "User profile is missing UID." };
                throw new FaultException<ServiceError>(error, new FaultReason(error.ErrorDetails));
            }
            return userProfileData.SaveUserProfile(userProfileToUpdate) > -1;
        }

        /// <summary>
        /// Persists the message 
        /// </summary>
        /// <param name="messageToSend">Message to be persisted</param>
        /// <returns>UID of saved message (-1 if not saved)</returns>
        public long SendMessage(DataContracts.Message messageToSend)
        {
            UserProfile parentProfile = userProfileData.GetUserProfile(messageToSend.UserProfileId);
            long retVal = -1;

            if (parentProfile != null)
            {
                retVal = messageData.SaveMessage(messageToSend);
            }
            else
            {
                ServiceError error = new ServiceError() { ErrorMessage = "Failed to send message", ErrorDetails = string.Format("No user profile has UID {0}.", messageToSend.UserProfileId) };
                throw new FaultException<ServiceError>(error, new FaultReason(error.ErrorDetails));
            }

            return retVal;
        }

        /// <summary>
        /// Returns all messages associated to the user profile
        /// </summary>
        /// <param name="userProfileId">UID of the user profile whose messages are to be retrieved</param>
        /// <returns>Messages associated to the user profile</returns>
        public List<DataContracts.Message> GetMessagesForUser(long userProfileId)
        {
            return messageData.GetMessagesForUserProfile(userProfileId);
        }

        /// <summary>
        /// Deletes the specified messages
        /// </summary>
        /// <param name="userProfileId">UID of the user profile whose message is to be deleted</param>
        /// <param name="messageIds">UIDs of the specific messages to delete</param>
        /// <returns>true if messages are deleted</returns>
        public bool DeleteUserMessages(long userProfileId, List<long> messageIds)
        {
            return messageData.DeleteUserMessages(userProfileId, messageIds);
        }


        /// <summary>
        /// Determines if username and password identify a user profile
        /// </summary>
        /// <param name="username">User name of the user profile</param>
        /// <param name="password">Password associated with the user profile</param>
        /// <returns>User profile associated to username and password. Null if this does not exist.</returns>
        public UserProfile LogIn(string username, string password)
        {
            UserProfile curUserProfile = userProfileData.GetUserProfile(username);

            if (curUserProfile != null)
            {
                if (curUserProfile.Password != password)
                {
                    curUserProfile = null;
                }
            }

            return curUserProfile;
        }
    }
}
