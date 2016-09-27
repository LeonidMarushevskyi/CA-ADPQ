using System.Web;
using System.Web.Mvc;
using AgileVendorPool.Filters;

namespace AgileVendorPool
{
    public class FilterConfig
    {
        public static void RegisterGlobalFilters(GlobalFilterCollection filters)
        {
            filters.Add(new HandleErrorAttribute());

            // HW
            // These can be added later

            //filters.Add(new AuthorizeAttribute());
            //filters.Add(new MVCFilterAttribute());

        }
    }
}
