using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Com.Natoma.HhsPrototype.Proc.DataContracts;

namespace Com.Natoma.HhsPrototype.Proc.DataAccess
{
    /// <summary>
    /// Manages data access activities for UserProfiles
    /// </summary>
    public interface IUserProfileData
    {
        /// <summary>
        /// Insert or update a user profile
        /// </summary>
        /// <param name="profileToSave">User profile to be inserted or updated</param>
        /// <returns>New UID on successful insert. 1 on successful update, 0 on unsuccessful operation</returns>
        long SaveUserProfile(UserProfile profileToSave);

        /// <summary>
        /// Returns the associated user profile
        /// </summary>
        /// <param name="username">Username of user profile to retrieve</param>
        /// <returns>User profile associated with the given user name</returns>
        UserProfile GetUserProfile(string username);

        /// <summary>
        /// Returns the associated user profile
        /// </summary>
        /// <param name="userId">User id of user profile to retrieve</param>
        /// <returns>User profile associated with the given user ID</returns>
        UserProfile GetUserProfile(long userId);
    }
}
