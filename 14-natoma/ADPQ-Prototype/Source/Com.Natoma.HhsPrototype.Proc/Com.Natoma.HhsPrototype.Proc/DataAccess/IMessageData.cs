using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Com.Natoma.HhsPrototype.Proc.DataContracts;

namespace Com.Natoma.HhsPrototype.Proc.DataAccess
{
    /// <summary>
    /// Manages data access activities for Messages
    /// </summary>
    public interface IMessageData
    {
        /// <summary>
        /// Insert or update message data
        /// </summary>
        /// <param name="messageToSave">Message to save</param>
        /// <returns>UID of saved message (-1 if not saved)</returns>
        long SaveMessage(Message messageToSave);

        /// <summary>
        /// Return messages associated with user profile id
        /// </summary>
        /// <param name="userProfileId">id of user profile associated to messages</param>
        /// <returns>All messages associated to user profile</returns>
        List<Message> GetMessagesForUserProfile(long userProfileId);

        /// <summary>
        /// Deletes the specified messages
        /// </summary>
        /// <param name="userProfileId">UID of the user profile whose message is to be deleted</param>
        /// <param name="messageIds">UIDs of the specific messages to delete</param>
        /// <returns>true if message is deleted</returns>
        bool DeleteUserMessages(long userProfileId, List<long> messageIds);

    }
}
