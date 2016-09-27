using System.Web.Mvc;

namespace AgileVendorPool.Controllers
{
    // HW
    //
    // This prototype didn't require proper Authentication and authorization.
    // If it had required then we would have used the Microsoft security framework to allow only the
    // authenticated users with the appropriate Roles to connect to the web services.
    //For our simple prototype, we are using the "HttpContext.Current.Session" to store user and roles.


    //[SecurityClasses.MVCAuthorizeRoles(UserRoles.Admin, UserRoles.FosterParent, UserRoles.CaseWorker)]
    public class HomeController : Controller
    {

        [AllowAnonymous]
        public ActionResult Logout()
        {
            // This is a fake setup for login/logout
            System.Web.HttpContext.Current.Session["UserName"] = null;
            return RedirectToAction("Index");
        }


        [AllowAnonymous]
        public ActionResult Index()
        {
            // This is a fake setup for login/logout
            if (System.Web.HttpContext.Current.Session["UserName"] != null)
            {
                ViewData["UserEmail"]= System.Web.HttpContext.Current.Session["UserName"];
            }
       
            return View();
        }

        //[SecurityClasses.MVCAuthorizeRoles(UserRoles.Admin, UserRoles.FosterParent)]
        [HttpGet]
        public ActionResult FosterParentProfile(string email)
        {
            // TODO
            // 1. Confirm valid email format
            // 2. check if email exist

            if (email != null)
            {
                // Pass email value to the client using ViewData
                ViewData["UserEmail"]= email;
                System.Web.HttpContext.Current.Session["UserName"] = email;
            }
            else
            {
                if (System.Web.HttpContext.Current.Session["UserName"] != null)
                {
                    ViewData["UserEmail"]= System.Web.HttpContext.Current.Session["UserName"];
                }
                else
                {
                    return RedirectToAction("Index");
                }

            }

            return View();
        }

        public ActionResult Email(string UserType)
        {
            // FosterParent
            // UserType
            // 1. FosterParent
            // 2. CaseWorker

            // HW
            // Pass this data to the front-end code
            ViewData["isCaseWorker"] = false;
            ViewData["UserType"] = UserType;

            if (UserType == "FosterParent")
            {
                if (System.Web.HttpContext.Current.Session["UserName"] != null)
                {
                    ViewData["UserEmail"]= System.Web.HttpContext.Current.Session["UserName"];

                    return View();
                }
                return RedirectToAction("Index");

            }
            else if (UserType == "CaseWorker")
            {
                ViewData["isCaseWorker"] = true;
                ViewData["UserType"] = UserType;
                ViewData["UserEmail"]= AgileVendorPool.Models.GlobalData.caseWorkerEmail;
                return View();
            }

            return RedirectToAction("Index");
        }

        public ActionResult FacilitySearch()
        {
            if (System.Web.HttpContext.Current.Session["UserName"] != null)
            {
                ViewData["UserEmail"]= System.Web.HttpContext.Current.Session["UserName"];
            }

            return View();
        }

    }
}