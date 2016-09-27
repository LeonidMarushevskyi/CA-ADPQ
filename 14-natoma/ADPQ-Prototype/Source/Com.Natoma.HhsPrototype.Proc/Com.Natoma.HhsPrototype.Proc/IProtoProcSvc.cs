using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using Com.Natoma.HhsPrototype.Proc.DataContracts;

namespace Com.Natoma.HhsPrototype.Proc
{
    /// <summary>
    /// Provides data persistence and retrieval for the HHS prototype
    /// </summary>
    [ServiceContract]
    public interface IProtoProcSvc
    {
        /// <summary>
        /// Persists the user profile and returns the new UID created for this profile
        /// </summary>
        /// <param name="userProfile">User profile to be persisted</param>
        /// <returns>UID of new user profile</returns>
        [OperationContract]
        long CreateUserProfile(UserProfile userProfile);

        /// <summary>
        /// Updates the user profile
        /// </summary>
        /// <param name="userProfile">User profile to be updated</param>
        /// <returns>true if update is successful</returns>
        [OperationContract]
        bool UpdateUserProfile(UserProfile userProfile);

        /// <summary>
        /// Persists the message 
        /// </summary>
        /// <param name="messageToSend">Message to be persisted</param>
        /// <returns>true if message save is successful</returns>
        [OperationContract]
        long SendMessage(Message messageToSend);

        /// <summary>
        /// Returns all messages associated to the user profile
        /// </summary>
        /// <param name="userProfileId">UID of the user profile whose messages are to be retrieved</param>
        /// <returns>Messages associated to the user profile</returns>
        [OperationContract]
        List<Message> GetMessagesForUser(long userProfileId);

        /// <summary>
        /// Deletes the specified messages
        /// </summary>
        /// <param name="userProfileId">UID of the user profile whose message is to be deleted</param>
        /// <param name="messageIds">UIDs of the specific messages to delete</param>
        /// <returns>true if messages are deleted</returns>
        [OperationContract]
        bool DeleteUserMessages(long userProfileId, List<long> messageIds);

        /// <summary>
        /// Determines if username and password identify a user profile
        /// </summary>
        /// <param name="username">User name of the user profile</param>
        /// <param name="password">Password associated with the user profile</param>
        /// <returns>UID of associated user profile. -1 if no user profile matches the criteria.</returns>
        [OperationContract]
        UserProfile LogIn(string username, string password);
    }

}
