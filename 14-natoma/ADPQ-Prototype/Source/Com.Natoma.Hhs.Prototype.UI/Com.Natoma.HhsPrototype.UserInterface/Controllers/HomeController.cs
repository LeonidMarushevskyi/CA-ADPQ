using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Com.Natoma.HhsPrototype.UserInterface.Helpers;

namespace Com.Natoma.HhsPrototype.UserInterface.Controllers
{
    public class HomeController : HhsPrototypeController
    {
        public HomeController(): base() { }

        public HomeController(IServiceHelper serviceHelper): base(serviceHelper)
        {
        }

        public ActionResult Index()
        {
            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;

            return View();
        }

        public ActionResult About()
        {
            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;
            ViewBag.Message = "HHS Prototype";

            return View();
        }
    }
}