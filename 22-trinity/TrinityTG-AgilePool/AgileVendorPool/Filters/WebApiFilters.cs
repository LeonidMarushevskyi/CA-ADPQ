using System;
using System.Net.Http;
using System.Web.Http.Filters;
using System.Net;

namespace AgileVendorPool.Filters
{
    // HW
    // This is not plugged in for this prototype, but it can be added in the future
    //

    //
    // EXCEPTION FILTERS 
    //

    //
    // Add code to take advantage of the Wep Api pipeline 
    // For more information:
    // http://www.asp.net/web-api/overview/error-handling/exception-handling
    //

    public class WebApiExceptionFilterAttribute : ExceptionFilterAttribute
    {
        public override void OnException(HttpActionExecutedContext context)
        {
            Exception Ex = context.Exception;

            //
            // We can use NotImplementedException for Testing & Development
            //
            if (Ex is NotImplementedException)
            {
                context.Response = new HttpResponseMessage(HttpStatusCode.NotImplemented);
            }
            else
            {
                // Log Error 
                // (Ex, Ex.StackTrace, context.Request.RequestUri.ToString());
                context.Response = new HttpResponseMessage(HttpStatusCode.InternalServerError);
            }
        }
    }
}