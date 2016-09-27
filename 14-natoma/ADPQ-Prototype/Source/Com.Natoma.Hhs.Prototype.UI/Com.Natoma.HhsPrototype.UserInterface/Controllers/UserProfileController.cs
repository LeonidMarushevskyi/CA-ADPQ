using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.ServiceModel;

using Com.Natoma.HhsPrototype.UserInterface.Helpers;
using Com.Natoma.HhsPrototype.UserInterface.Models;

namespace Com.Natoma.HhsPrototype.UserInterface.Controllers
{
    public class UserProfileController : HhsPrototypeController
    {
        #region Private Members
        //private ServiceHelper serviceHelper = new ServiceHelper();
        private SelectList yesNoList = new SelectList(new List<SelectListItem>()
        {
            new SelectListItem() { Text = "No", Value = false.ToString() },
            new SelectListItem() { Text = "Yes", Value = true.ToString() }
        }, "Value", "Text", 0);

        private SelectList stateList = new SelectList(new List<SelectListItem>()
        {
            new SelectListItem() { Text = "(Select State)", Value = "" },
            new SelectListItem() {Text="Alabama", Value="AL"},
            new SelectListItem() { Text="Alaska", Value="AK"},
            new SelectListItem() { Text="Arizona", Value="AZ"},
            new SelectListItem() { Text="Arkansas", Value="AR"},
            new SelectListItem() { Text="California", Value="CA"},
            new SelectListItem() { Text="Colorado", Value="CO"},
            new SelectListItem() { Text="Connecticut", Value="CT"},
            new SelectListItem() { Text="District of Columbia", Value="DC"},
            new SelectListItem() { Text="Delaware", Value="DE"},
            new SelectListItem() { Text="Florida", Value="FL"},
            new SelectListItem() { Text="Georgia", Value="GA"},
            new SelectListItem() { Text="Hawaii", Value="HI"},
            new SelectListItem() { Text="Idaho", Value="ID"},
            new SelectListItem() { Text="Illinois", Value="IL"},
            new SelectListItem() { Text="Indiana", Value="IN"},
            new SelectListItem() { Text="Iowa", Value="IA"},
            new SelectListItem() { Text="Kansas", Value="KS"},
            new SelectListItem() { Text="Kentucky", Value="KY"},
            new SelectListItem() { Text="Louisiana", Value="LA"},
            new SelectListItem() { Text="Maine", Value="ME"},
            new SelectListItem() { Text="Maryland", Value="MD"},
            new SelectListItem() { Text="Massachusetts", Value="MA"},
            new SelectListItem() { Text="Michigan", Value="MI"},
            new SelectListItem() { Text="Minnesota", Value="MN"},
            new SelectListItem() { Text="Mississippi", Value="MS"},
            new SelectListItem() { Text="Missouri", Value="MO"},
            new SelectListItem() { Text="Montana", Value="MT"},
            new SelectListItem() { Text="Nebraska", Value="NE"},
            new SelectListItem() { Text="Nevada", Value="NV"},
            new SelectListItem() { Text="New Hampshire", Value="NH"},
            new SelectListItem() { Text="New Jersey", Value="NJ"},
            new SelectListItem() { Text="New Mexico", Value="NM"},
            new SelectListItem() { Text="New York", Value="NY"},
            new SelectListItem() { Text="North Carolina", Value="NC"},
            new SelectListItem() { Text="North Dakota", Value="ND"},
            new SelectListItem() { Text="Ohio", Value="OH"},
            new SelectListItem() { Text="Oklahoma", Value="OK"},
            new SelectListItem() { Text="Oregon", Value="OR"},
            new SelectListItem() { Text="Pennsylvania", Value="PA"},
            new SelectListItem() { Text="Rhode Island", Value="RI"},
            new SelectListItem() { Text="South Carolina", Value="SC"},
            new SelectListItem() { Text="South Dakota", Value="SD"},
            new SelectListItem() { Text="Tennessee", Value="TN"},
            new SelectListItem() { Text="Texas", Value="TX"},
            new SelectListItem() { Text="Utah", Value="UT"},
            new SelectListItem() { Text="Vermont", Value="VT"},
            new SelectListItem() { Text="Virginia", Value="VA"},
            new SelectListItem() { Text="Washington", Value="WA"},
            new SelectListItem() { Text="West Virginia", Value="WV"},
            new SelectListItem() { Text="Wisconsin", Value="WI"},
            new SelectListItem() { Text="Wyoming", Value="WY"}
        }, "Value", "Text", "CA");
        #endregion

        // GET: UserProfile
        public ActionResult Index()
        {
            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;

            if (!this.IsUserLoggedIn)
            {
                return RedirectToAction("Index", "Home");
            }

            ViewData["yesNoList"] = yesNoList;
            ViewData["stateList"] = stateList;

            string saveSuccessful = "SaveSuccessful";
            if (TempData[saveSuccessful] != null)
            {
                ViewBag.SaveSuccessful = TempData[saveSuccessful];
            }

            return View(CurrentUser);
        }

        [HttpPost]
        public ActionResult Save(UserProfileModel model)
        {
            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;

            if (!this.IsUserLoggedIn)
            {
                return RedirectToAction("Index", "Home");
            }

            if (!ModelState.IsValid)
            {
                ModelState.AddModelError("", "Invalid fields.  Did not save.");
                TempData["SaveSuccessful"] = false;
                CurrentUser = model;
                return RedirectToAction("Index");
            }

            try
            {
                if (CurrentUser.Uid.HasValue)
                {
                    // RsSet properties that are not displayed on screen and should not
                    // be added to the screen for security purposes
                    model.Uid = CurrentUser.Uid;
                    model.Password = CurrentUser.Password;
                    ServiceManager.UpdateUserProfile(model);
                }
                else
                {
                    model.Uid = ServiceManager.CreateUserProfile(model);
                }

                CurrentUser = model;

                TempData["SaveSuccessful"] = true;
            }
            catch (FaultException e)
            {
                ModelState.AddModelError("", e.Message);
                TempData["SaveSuccessful"] = false;
            }

            return RedirectToAction("Index");
        }
    }
}