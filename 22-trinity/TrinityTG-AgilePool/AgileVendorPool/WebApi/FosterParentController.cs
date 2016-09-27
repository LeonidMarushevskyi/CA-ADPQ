using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Script.Serialization;
using AgileVendorPool.BusinessLayer;
using AgileVendorPool.Models;

// https://chhs.data.ca.gov/resource/mffa-c6z5.json?facility_zip=95501
// https://chhs.data.ca.gov/resource/mffa-c6z5.geojson?facility_zip=95501

namespace AgileVendorPool.WebApi
{

    [RoutePrefix("api/fosterparent")]
    //[SecurityClasses.HTTPAuthorizeRoles(UserRoles.Admin, UserRoles.FosterParent, UserRoles.FosterParent)]
    public class FosterParentController : ApiController
    {
        ReturnedData returnedData = new ReturnedData();
        JavaScriptSerializer jsSerializer = new JavaScriptSerializer();
   

        [Route("getfosterparent")]
        [HttpPost]
        public HttpResponseMessage GetFosterParent()
        {
            FosterParent parent = new Models.FosterParent();

            HttpContent requestContent = Request.Content;
            string jsonContent = requestContent.ReadAsStringAsync().Result;

            parent = jsSerializer.Deserialize<Models.FosterParent>(jsonContent);

            FosterParent fosterParent = FosterParentQueries.GetFosterParent(parent.email);

            returnedData.serverData = fosterParent;
            return Request.CreateResponse(HttpStatusCode.OK, returnedData);
        }

        [Route("fosterparent")]
        [HttpPost]
        public HttpResponseMessage AddParent()
        {
            FosterParent parent = new Models.FosterParent();
            
            HttpContent requestContent = Request.Content;
            string jsonContent = requestContent.ReadAsStringAsync().Result;

            parent = jsSerializer.Deserialize<Models.FosterParent>(jsonContent);

            returnedData = FosterParentQueries.InsertFosterParent(parent);

            if (returnedData.serverError.Count > 0)
            {
                return Request.CreateResponse(HttpStatusCode.NotAcceptable, returnedData);
            }
            else
            {
                return Request.CreateResponse(HttpStatusCode.OK, returnedData);
            }
        }


        [Route("fosterparent/{FosterParentId:int}")]
        [HttpPut]
        public HttpResponseMessage UpdateParent(int fosterParentId)
        {
            FosterParent parent = new Models.FosterParent();

            HttpContent requestContent = Request.Content;
            string jsonContent = requestContent.ReadAsStringAsync().Result;

            parent = jsSerializer.Deserialize<Models.FosterParent>(jsonContent);

            returnedData = FosterParentQueries.UpdateFosterParent(parent);

            if (returnedData.serverError.Count > 0)
            {
                return Request.CreateResponse(HttpStatusCode.NotAcceptable, returnedData);
            }
            else
            {
                return Request.CreateResponse(HttpStatusCode.OK, returnedData);
            }
        }


        // facilities 
        //[Route("zipCode")]
        //public HttpResponseMessage GetFacilities()

        //{
        //    object facilities = null;
        //    return Request.CreateResponse(HttpStatusCode.OK, returnedData);
        //}

    }

}


