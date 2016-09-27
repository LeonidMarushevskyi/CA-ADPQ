using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Com.Natoma.HhsPrototype.Proc.DataContracts;
using System.ServiceModel;
using System.Xml;
using System.Xml.Serialization;
using System.IO;
using System.Configuration;

namespace Com.Natoma.HhsPrototype.Proc.DataAccess
{
    /// <summary>
    /// Manages all data access activities for UserProfiles
    /// </summary>
    public class UserProfileData : IUserProfileData
    {
        private static Dictionary<string, UserProfile> allProfileDataByUsername = null;
        private static Dictionary<long, UserProfile> allProfileDataByUid = null;
        private static long nextId = 1;
        private static readonly string FileName = "UserProfileData.xml";
        private static String token = "";

        public UserProfileData()
        {
            if (allProfileDataByUid == null)
            {
                lock (token)
                {
                    List<UserProfile> loadedData = null;
                    string loadFileName = Path.Combine(BaseDirectory(), FileName);

                    if (File.Exists(loadFileName))
                    {
                        string attributeXml = string.Empty;

                        XmlDocument xmlDocument = new XmlDocument();
                        xmlDocument.Load(loadFileName);
                        string xmlString = xmlDocument.OuterXml;

                        using (StringReader read = new StringReader(xmlString))
                        {
                            Type outType = typeof(List<UserProfile>);

                            XmlSerializer serializer = new XmlSerializer(outType);
                            using (XmlReader reader = new XmlTextReader(read))
                            {
                                List<UserProfile> fileToLoad = (List<UserProfile>)serializer.Deserialize(reader);
                                reader.Close();
                                loadedData = fileToLoad;
                            }

                            read.Close();
                        }
                    }

                    allProfileDataByUid = new Dictionary<long, UserProfile>();
                    allProfileDataByUsername = new Dictionary<string, UserProfile>();
                    if (loadedData != null)
                    {
                        foreach (UserProfile row in loadedData)
                        {
                            nextId = Math.Max(row.Uid.Value + 1, nextId);
                            allProfileDataByUsername.Add(row.Email.ToLower(), row);
                            allProfileDataByUid.Add(row.Uid.Value, row);
                        }
                    }
                }
            }
        }

        /// <summary>
        /// Insert or update a user profile. Will attempt insert if UID is null and update otherwise.
        /// </summary>
        /// <param name="profileToSave">User profile to be inserted or updated</param>
        /// <returns>New UID on successful insert. 0 on successful update, -1 on unsuccessful operation</returns>
        public long SaveUserProfile(UserProfile profileToSave)
        {
            long retVal = -1;
            UserProfile savableProfile = profileToSave.Clone();

            // TODO: Make real call to database
            // Update
            if (profileToSave.Uid.HasValue)
            {
                // Does the UID exist?
                if (!allProfileDataByUid.Keys.Contains(profileToSave.Uid.Value))
                {
                    ServiceError error = new ServiceError()
                    {
                        ErrorMessage = "Invalid user profile ID",
                        ErrorDetails = string.Format("User profile ID {0} does not exist.", profileToSave.Uid.Value)
                    };
                    throw new FaultException<ServiceError>(error, new FaultReason(error.ErrorDetails));
                }
                else
                {
                    UserProfile oldProfile = allProfileDataByUid[profileToSave.Uid.Value];

                    // See if username has changed (and need to update key in username dictionary)
                    if (profileToSave.Email.ToLower() != oldProfile.Email.ToLower())
                    {
                        if (allProfileDataByUsername.Keys.Contains(profileToSave.Email.ToLower()))
                        {
                            ServiceError error = new ServiceError()
                            {
                                ErrorMessage = "Invalid username",
                                ErrorDetails = string.Format("Username {0} is already being used.", profileToSave.Email)
                            };
                            throw new FaultException<ServiceError>(error, new FaultReason(error.ErrorDetails));
                        }
                        allProfileDataByUsername.Remove(oldProfile.Email.ToLower());
                    }

                    allProfileDataByUid[profileToSave.Uid.Value] = savableProfile;
                    allProfileDataByUsername[profileToSave.Email.ToLower()] = savableProfile;

                    retVal = 0;
                }
            }
            else // Insert
            {
                // Does username exist?
                if (allProfileDataByUsername.Keys.Contains(profileToSave.Email.ToLower()))
                {
                    ServiceError error = new ServiceError() { ErrorMessage = "Username (email address) has already been used.", ErrorDetails = string.Format("Username (email address) {0} has already been used.", profileToSave.Email) };
                    throw new FaultException<ServiceError>(error, new FaultReason(error.ErrorDetails));
                }
                else
                {
                    lock (token)
                    {
                        savableProfile.Uid = nextId++;
                        allProfileDataByUsername.Add(savableProfile.Email.ToLower(), savableProfile);
                        allProfileDataByUid.Add(savableProfile.Uid.Value, savableProfile);
                        retVal = savableProfile.Uid.Value;
                    }
                }
            }

            SaveData();

            return retVal;
        }

        /// <summary>
        /// Returns the associated user profile
        /// </summary>
        /// <param name="username">Username of user profile to retrieve</param>
        /// <returns>User profile associated with the given user name. Null if no matching profile.</returns>
        public UserProfile GetUserProfile(string username)
        {
            UserProfile retVal = null;

            // TODO: Make real call to database
            if (allProfileDataByUsername.Keys.Contains(username.ToLower()))
            {
                retVal = allProfileDataByUsername[username.ToLower()];
            }

            return retVal;
        }

        /// <summary>
        /// Returns the associated user profile
        /// </summary>
        /// <param name="userId">User id of user profile to retrieve</param>
        /// <returns>User profile associated with the given user ID</returns>
        public UserProfile GetUserProfile(long userId)
        {
            UserProfile retVal = null;

            // TODO: Make real call to database
            if (allProfileDataByUid.Keys.Contains(userId))
            {
                retVal = allProfileDataByUid[userId];
            }

            return retVal;
        }

        private void SaveData()
        {
            XmlDocument xmlDocument = new XmlDocument();
            XmlSerializer serializer = new XmlSerializer(typeof(List<UserProfile>));
            List<UserProfile> dataToSave = allProfileDataByUid.Values.ToList();

            string saveFileName = Path.Combine(BaseDirectory(), FileName);

            using (MemoryStream stream = new MemoryStream())
            {
                serializer.Serialize(stream, dataToSave);
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