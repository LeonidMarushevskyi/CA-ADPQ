using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Com.Natoma.HhsPrototype.UserInterface.Controllers
{
    public class LocationController : HhsPrototypeController
    {
        // GET: Locations
        public ActionResult Index()
        {
            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;

            if (this.CurrentUser != null && this.CurrentUser.ZipCode != null)
            {
                ViewBag.ZipCode = this.CurrentUser.ZipCode;
            }

            return View();
        }
    }
}