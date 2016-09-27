
using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Script.Serialization;
using AgileVendorPool.BusinessLayer;
using AgileVendorPool.Models;

namespace AgileVendorPool.WebApi
{
    [RoutePrefix("api/email")]
    public class EmailController : ApiController
    {
        ReturnedData returnedData = new ReturnedData();
        JavaScriptSerializer jsSerializer = new JavaScriptSerializer();

        [Route("getMyEmail")]
        [HttpPost]
        public HttpResponseMessage GetEmail()
        {
            List<EmailContent> emailList = new List<EmailContent>();

            HttpContent requestContent = Request.Content;
            string jsonContent = requestContent.ReadAsStringAsync().Result;

            EmailContent email = jsSerializer.Deserialize<Models.EmailContent>(jsonContent);

            emailList = EmailQueries.GetEmail(email.emailFrom);

            returnedData.serverData = emailList;
            return Request.CreateResponse(HttpStatusCode.OK, returnedData);
        }

        [Route("getFosterParentEmailList")]
        [HttpGet]
        public HttpResponseMessage GetFosterParentEmailList()
        {
            List<string> emailAddressList = new List<string>();


            emailAddressList = EmailQueries.GetFosterParentEmailList();

            returnedData.serverData = emailAddressList;
            return Request.CreateResponse(HttpStatusCode.OK, returnedData);
        }

        [Route("deleteEmailList")]
        [HttpPost]
        public HttpResponseMessage DeleteEmailList()
        {
            List<int> EmailList = new List<int>();
            HttpContent requestContent = Request.Content;
            string jsonContent = requestContent.ReadAsStringAsync().Result;

            EmailList = jsSerializer.Deserialize<List<int>>(jsonContent);

            returnedData = EmailQueries.DeleteEmailList(EmailList);
            
            if (returnedData.serverError.Count > 0)
            {
                return Request.CreateResponse(HttpStatusCode.NotAcceptable, returnedData);
            }
            else
            {
                return Request.CreateResponse(HttpStatusCode.OK, returnedData);
            }
        }


        [Route("sendEmail")]
        [HttpPost]
        public HttpResponseMessage SendEmail()
        {
            HttpContent requestContent = Request.Content;
            string jsonContent = requestContent.ReadAsStringAsync().Result;

            EmailContent emailContent = jsSerializer.Deserialize<Models.EmailContent>(jsonContent);
            EmailQueries.SendEmail(emailContent);

            return Request.CreateResponse(HttpStatusCode.OK, returnedData);
        }
    }
}
