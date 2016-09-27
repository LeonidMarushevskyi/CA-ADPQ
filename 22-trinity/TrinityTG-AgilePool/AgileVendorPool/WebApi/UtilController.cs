using System.Net;
using System.Net.Http;
using System.Web.Http;

using System.Web.Script.Serialization;
using AgileVendorPool.BusinessLayer;
using AgileVendorPool.Models;

namespace AgileVendorPool.WebApi
{
    [RoutePrefix("api/util")]
    // [SecurityClasses.HTTPAuthorizeRoles(UserRoles.Admin, UserRoles.FosterParent, UserRoles.FosterParent)]
    public class UtilController : ApiController
    {
        ReturnedData returnedData = new ReturnedData();
        JavaScriptSerializer jsSerializer = new JavaScriptSerializer();
        
        [AllowAnonymous]
        [Route("login")]
        [HttpPost]
        public HttpResponseMessage Login()
        {

            string email = "";

            HttpContent requestContent = Request.Content;
            string jsonContent = requestContent.ReadAsStringAsync().Result;

            LoginData loginData = jsSerializer.Deserialize<Models.LoginData>(jsonContent);
            email = loginData.email;

            returnedData = UtilQueries.Login(email);

            if (returnedData.serverError.Count > 0)
            {
                return Request.CreateResponse(HttpStatusCode.NotAcceptable, returnedData);
            }
            else
            {
                return Request.CreateResponse(HttpStatusCode.OK, returnedData);
            }
        }


        [AllowAnonymous]
        [Route("register")]
        [HttpPost]
        public HttpResponseMessage Register()
        {

            string email = "";

            HttpContent requestContent = Request.Content;
            string jsonContent = requestContent.ReadAsStringAsync().Result;

            LoginData loginData = jsSerializer.Deserialize<Models.LoginData>(jsonContent);
            email = loginData.email;

            returnedData = UtilQueries.Register(email);

            if (returnedData.serverError.Count > 0)
            {
                return Request.CreateResponse(HttpStatusCode.NotAcceptable, returnedData);
            }
            else
            {
                return Request.CreateResponse(HttpStatusCode.OK, returnedData);
            }
        }

    }
}
