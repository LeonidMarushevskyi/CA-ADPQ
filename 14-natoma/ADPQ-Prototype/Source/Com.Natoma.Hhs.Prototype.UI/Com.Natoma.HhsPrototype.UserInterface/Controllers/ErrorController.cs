using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Com.Natoma.HhsPrototype.UserInterface.Controllers
{
    public class ErrorController : HhsPrototypeController
    {
        public ViewResult Index()
        {
            TempData["IsUserLoggedIn"] = this.IsUserLoggedIn;
            return View();
        }
    }
}