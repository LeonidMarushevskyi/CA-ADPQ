using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Com.Natoma.HhsPrototype.UserInterface.Models
{
    public class UserProfileModel
    {
        /// <summary>
        /// Unique identifier for this user profile
        /// </summary>
        public long? Uid { get; set; }

        /// <summary>
        /// First Name of user
        /// </summary>
        [Required(ErrorMessage = "First Name is required.")]
        [Display(Name = "First Name")]
        public string FirstName { get; set; }

        /// <summary>
        /// Middle Name of user creating the user profile
        /// </summary>
        [Display(Name = "Middle Name")]
        public string MiddleName { get; set; }

        /// <summary>
        /// Last Name of user creating the user profile
        /// </summary>
        [Required(ErrorMessage = "Last Name is required.")]
        [Display(Name = "Last Name")]
        public string LastName { get; set; }

        /// <summary>
        /// Physical Address of user creating the user profile
        /// </summary>
        [Required(ErrorMessage = "Address Line 1 is required.")]
        [Display(Name = "Address Line 1")]
        public string AddressLine1 { get; set; }

        /// <summary>
        /// Line 2  of physical address for the user profile
        /// </summary>
        [Display(Name = "Address Line 2")]
        public string AddressLine2 { get; set; }

        /// <summary>
        /// City of physical address for the user profile
        /// </summary>
        [Required(ErrorMessage = "City is required.")]
        [Display(Name = "City")]
        public string City { get; set; }

        /// <summary>
        /// State of physical address for the user profile
        /// </summary>
        [Required(ErrorMessage = "State is required.")]
        [Display(Name = "State")]
        public string State { get; set; }

        /// <summary>
        /// ZIP Code of physical address for the user profile
        /// </summary>
        [Required(ErrorMessage = "ZIP Code is required.")]
        [RegularExpression(@"^\d{5}(-\d{4})?$", ErrorMessage = "Invalid ZIP Code")]
        [Display(Name = "ZIP Code")]
        public string ZipCode { get; set; }

        /// <summary>
        /// Phone Number of user creating the user profile
        /// </summary>
        //[Required]
        [Phone]
        [DataType(DataType.PhoneNumber)]
        [Required(ErrorMessage = "Phone Number is required.")]
        [RegularExpression(@"^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$", ErrorMessage = "Not a valid Phone number")]
        [Display(Name = "Phone Number")]
        public string PhoneNumber { get; set; }

        /// <summary>
        /// Email Address of user creating the user profile
        /// </summary>
        [Required(ErrorMessage = "Email is required.")]
        [Display(Name = "Email")]
        public string Email { get; set; }

        /// <summary>
        /// Password for user profile
        /// </summary>
        [Display(Name = "Password")]
        public string Password { get; set; }

        /// <summary>
        /// Dependents of user creating the user profile
        /// </summary>
        [Display(Name = "Dependents")]
        public string Kids { get; set; }

        /// <summary>
        /// TODO: Not currently implemented 
        /// </summary>
        [Display(Name = "Is Cell Phone?")]
        public bool IsCellPhone { get; set; }

        /// <summary>
        /// Date of Birth of user creating the user profile
        /// </summary>
        [Display(Name = "Date of Birth")]
        [DataType(DataType.Date, ErrorMessage = "Incorrect date format for Date of Birth field.")]
        [DisplayFormat(DataFormatString = "{0:MM/dd/yyyy}", ApplyFormatInEditMode = true)]
        public DateTime? DateOfBirth { get; set; }

        /// <summary>
        /// Gender of user creating the user profile
        /// </summary>
        [Display(Name = "Gender")]
        public string Gender { get; set; }

        /// <summary>
        /// Race of user creating the user profile
        /// </summary>
        [Display(Name = "Race")]
        public string Race { get; set; }

        /// <summary>
        /// Ethnicity of user creating the user profile
        /// </summary>
        [Display(Name = "Ethnicity")]
        public string Ethnicity { get; set; }

        /// <summary>
        /// Marital Status of user creating the user profile
        /// </summary>
        [Display(Name = "Marital Status")]
        public string MaritalStatus { get; set; }

        /// <summary>
        /// Primary Language of user creating the user profile
        /// </summary>
        [Display(Name = "Primary Language")]
        public string PrimaryLanguage { get; set; }

        /// <summary>
        /// Additional Languages spoken by user creating the user profile
        /// </summary>
        [Display(Name = "Additional Languages")]
        public string AdditionalLanguages { get; set; }

        /// <summary>
        /// Employment Information of user creating the user profile
        /// </summary>
        [Display(Name = "Employment Information")]
        public string EmploymentInformation { get; set; }

        /// <summary>
        /// Primary Household Language of user creating the user profile
        /// </summary>
        [Display(Name = "Primary Household Language")]
        public string PrimaryHouseholdLanguage { get; set; }

        /// <summary>
        /// Additional Household Languages of user creating the user profile
        /// </summary>
        [Display(Name = "Additional Household Languages")]
        public string AdditionalHouseHoldLanguages { get; set; }

        /// <summary>
        /// Household Capacity of user creating the user profile
        /// </summary>
        [Display(Name = "Household Capacity")]
        public int Capacity { get; set; }

        /// <summary>
        /// Current number of Dependents in household of user creating the user profile
        /// </summary>
        [Display(Name = "Number of Dependents")]
        public int NumberOfDependentsInHousehold { get; set; }

        /// <summary>
        /// Current Number of Adults in household of user creating the user profile
        /// </summary>
        [Display(Name = "Number of Adults")]
        public int NumberOfAdultsInHousehold { get; set; }

        /// <summary>
        /// Current Household Pets of user creating the user profile
        /// </summary>
        [Display(Name = "Household Pets")]
        public string Pets { get; set; }

        /// <summary>
        /// Is user willing to Accept Pets
        /// </summary>
        [Display(Name = "Accept pets?")]
        public bool IsAvailableForPets { get; set; }

        /// <summary>
        /// Fingerprinting Information of user creating the user profile
        /// </summary>
        [Display(Name = "Fingerprinting Information")]
        public string FingerprintingInformation { get; set; }

        /// <summary>
        /// Fingerprinted Alternate Care Providers in household of user creating the user profile
        /// </summary>
        [Display(Name = "Fingerprinted Alternate Care Providers")]
        public string FingerprintedAlternateCareProviders { get; set; }

        /// <summary>
        /// Certification Information of user creating the user profile
        /// </summary>
        [Display(Name = "Certification Information")]
        public string CertificationInformation { get; set; }

        /// <summary>
        /// Is user Approved for Kinship Care
        /// </summary>
        [Display(Name = "Approved for Kinship Care?")]
        public bool IsApprovedForKinshipCare { get; set; }

        /// <summary>
        /// Is user Approved for Adoptive Care
        /// </summary>
        [Display(Name = "Approved for Adoptive Care?")]
        public bool IsApprovedForAdoptiveCare { get; set; }

        /// <summary>
        /// Training Information of user creating the user profile
        /// </summary>
        [Display(Name = "Training Information")]
        public string TrainingInformation { get; set; }

        /// <summary>
        /// Special Care Preferences of user creating the user profile
        /// </summary>
        [Display(Name = "Special Care Preferences")]
        public string SpecialCarePreferences { get; set; }

        /// <summary>
        /// Neighborhood of user creating the user profile
        /// </summary>
        [Display(Name = "Neighborhood")]
        public string Neighborhood { get; set; }

        /// <summary>
        /// School District Information near user creating the user profile
        /// </summary>
        [Display(Name = "School District Information")]
        public string SchoolDistrictInformation { get; set; }
        
    }
}
