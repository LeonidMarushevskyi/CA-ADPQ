using System.Web.Mvc;

namespace AgileVendorPool.Filters
{
    // HW
    // This is not plugged in for this prototype, but it can be added in the future
    //
    // MVC pipeline 
    // Add code to take advantage of the MVC pipeline 
    //



    public class MVCFilterAttribute : System.Web.Mvc.ActionFilterAttribute
    {
        public override void OnActionExecuting(ActionExecutingContext filterContext)
        {
            // Called before the action method is invoked.
        }

        public override void OnActionExecuted(ActionExecutedContext filterContext)
        {
            // Called after the action method is invoked.
        }

        public override void OnResultExecuting(ResultExecutingContext filterContext)
        {
            // Called before the action result that is returned by an action method is executed
        }

        public override void OnResultExecuted(ResultExecutedContext filterContext)
        {
            // Called after the action result that is returned by an action method is executed.
        }
    }
}