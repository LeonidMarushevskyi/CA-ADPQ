using System.Web;
using System.Diagnostics;
using System.Net.Http;
using System.Threading;
using System.Threading.Tasks;

namespace AgileVendorPool.MessageHandlers
{
    //
    // MESSAGE HANDLERS
    //

    //
    // For more information about MESSAGE HANDLERS and Wep Api pipeline, please visit this link:
    // http://www.asp.net/web-api/overview/advanced/http-message-handlers
    //

    public class MessageHandler1 : DelegatingHandler
    {
        protected async override Task<HttpResponseMessage> SendAsync(HttpRequestMessage request, CancellationToken cancellationToken)
        {

            var userName = HttpContext.Current.User.Identity.Name;

            Debug.WriteLine(request.RequestUri);
            Debug.WriteLine("MessageHandler1 before inner handler");

            // Call the inner handler.
            var response = await base.SendAsync(request, cancellationToken);

            Debug.WriteLine("MessageHandler1 after inner handler");
            return response;
        }
    }
   

}