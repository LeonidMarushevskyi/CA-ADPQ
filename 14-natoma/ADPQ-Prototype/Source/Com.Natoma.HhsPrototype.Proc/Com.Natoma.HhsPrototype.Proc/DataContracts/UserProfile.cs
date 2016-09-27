using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace Com.Natoma.HhsPrototype.Proc.DataContracts
{
    /// <summary>
    /// The profile of the parent logged in
    /// </summary>
    [DataContract]
    public class UserProfile
    {
        /// <summary>
        /// Unique identifier for this user profile
        /// </summary>
        [DataMember]
        public long? Uid { get; set; }

        /// <summary>
        /// First name of parent associated with this profile
        /// </summary>
        [DataMember(IsRequired = true)]
        public string FirstName { get; set; }

        /// <summary>
        /// Middle name of parent associated with this profile
        /// </summary>
        [DataMember]
        public string MiddleName { get; set; }

        /// <summary>
        /// Last name of parent associated with this profile
        /// </summary>
        [DataMember(IsRequired = true)]
        public string LastName { get; set; }

        /// <summary>
        /// Physical address of parent associated with this profile
        /// </summary>
        // [DataMember(IsRequired = true)]
        [DataMember]
        public string AddressLine1 { get; set; }

        /// <summary>
        /// Line 2 of physical address of parent associated with this profile
        /// </summary>
        [DataMember]
        public string AddressLine2 { get; set; }

        /// <summary>
        /// City of physical address of parent associated with this profile
        /// </summary>
        //[DataMember(IsRequired = true)]
        [DataMember]
        public string City { get; set; }

        /// <summary>
        /// State (e.g. California) of physical address of parent associated with this profile
        /// </summary>
        //[DataMember(IsRequired = true)]
        [DataMember]
        public string State { get; set; }

        /// <summary>
        /// Zip code of physical address of parent associated with this profile
        /// </summary>
        //[DataMember(IsRequired = true)]
        [DataMember]
        public string ZipCode { get; set; }

        /// <summary>
        /// Phone number of parent associated with this profile
        /// </summary>
        //[DataMember(IsRequired = true)]
        [DataMember]
        public string PhoneNumber { get; set; }

        /// <summary>
        /// Email of parent associated with this profile. Also used as username.
        /// </summary>
        //[DataMember(IsRequired = true)]
        [DataMember]
        public string Email { get; set; }

        /// <summary>
        /// Password of parent associated with this profile
        /// </summary>
        [DataMember(IsRequired = true)]
        public string Password { get; set; }

        /// <summary>
        /// List of foster kids associated with this profile
        /// </summary>
        [DataMember]
        public string Kids { get; set; }

        /// <summary>
        /// Date of user's birth
        /// </summary>
        [DataMember]
        public DateTime DateOfBirth { get; set; }

        /// <summary>
        /// Gender of user
        /// </summary>
        [DataMember]
        public string Gender { get; set; }

        /// <summary>
        /// Race of user
        /// </summary>
        [DataMember]
        public string Race { get; set; }

        /// <summary>
        /// Ethnicity of user
        /// </summary>
        [DataMember]
        public string Ethnicity { get; set; }

        /// <summary>
        /// Marital status of user
        /// </summary>
        [DataMember]
        public string MaritalStatus { get; set; }

        /// <summary>
        /// Primary language of user
        /// </summary>
        [DataMember]
        public string PrimaryLanguage { get; set; }

        /// <summary>
        /// Additional languages user speaks
        /// </summary>
        [DataMember]
        public string AdditionalLanguages { get; set; }

        /// <summary>
        /// User's employment information - flexibility around transportation, sources of income, schedule may be more important
        /// </summary>
        [DataMember]
        public string EmploymentInformation { get; set; }

        /// <summary>
        /// Primary language of user's household
        /// </summary>
        [DataMember]
        public string PrimaryHouseholdLanguage { get; set; }

        /// <summary>
        /// Other languages spoken in user's household
        /// </summary>
        [DataMember]
        public string AdditionalHouseholdLanguages { get; set; }

        /// <summary>
        /// Number of foster kids that 
        /// </summary>
        [DataMember]
        public long? Capacity { get; set; }

        [DataMember]
        public long? NumberOfDependentsInHousehold { get; set; }

        [DataMember]
        public long? NumberOfAdultsInHousehold { get; set; }

        [DataMember]
        public string Pets { get; set; }

        [DataMember]
        public bool IsAvailableForPets { get; set; }

        [DataMember]
        public string FingerprintingInformation { get; set; }

        [DataMember]
        public string FingerprintedAlternateCareProviders { get; set; }

        [DataMember]
        public string CertificationInformation { get; set; }

        [DataMember]
        public bool IsApprovedForKinshipCare { get; set; }

        [DataMember]
        public bool IsApprovedForAdoptiveCare { get; set; }

        [DataMember]
        public string TrainingInformation { get; set; }

        [DataMember]
        public string SpecialCarePreferences { get; set; }

        [DataMember]
        public string Neighborhood { get; set; }

        [DataMember]
        public string SchoolDistrictInformation { get; set; }

        /// <summary>
        /// Returns copy of UserProfile
        /// </summary>
        /// <returns>Copy of user profile</returns>
        public UserProfile Clone()
        {
            UserProfile clone = (UserProfile)this.MemberwiseClone();
            return clone;
        }
    }
}