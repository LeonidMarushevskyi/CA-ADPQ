using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AgileVendorPool.Models
{
    // 
    public static class GlobalData
    {
        // HW
        // For this prototype, this is email is fixed
        public const string caseWorkerEmail = "CaseWorkerEmail@DomainName.com";
    }

    public class ErrorClass
    {
        public string Summary { get; set; }
        public string Detail { get; set; }
    }
    public class ReturnedData
    {
        public dynamic serverData { get; set; }
        public List<ErrorClass> serverError { get; set; }
    }
    public class LoginData
    {
        public string email{ get; set; }
    }

    public class FosterParent
    {
        public int? fosterParentId { get; set; }
        public string email { get; set; }
        public string lastName { get; set; }
        public string firstName { get; set; }
        public string address1{ get; set; }
        public string address2 { get; set; }
        public string city{ get; set; }
        public string state{ get; set; }
        public string zipCode{ get; set; }
        public string country{ get; set; }

    }

    public class EmailContent
    {
        public int emailId { get; set; }
        public string emailFrom { get; set; }
        public string emailTo { get; set; }
        public string emailSubject { get; set; }
        public string emailBody { get; set; }
        public DateTime emailDate { get; set; }
        public dynamic dynamicEmailDate { get; set; }
    }


    
}