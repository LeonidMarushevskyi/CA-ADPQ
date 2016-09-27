using System;
using System.ServiceModel;
using System.Web.Mvc;
using Microsoft.AspNet.Identity;

using Com.Natoma.HhsPrototype.UserInterface.Models;

namespace Com.Natoma.HhsPrototype.UserInterface.Controllers
{
    [Authorize]
    public class AccountController : HhsPrototypeController
    {
        public ActionResult Index()
        {
            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;

            return View();
        }

        // GET: /Account/Login
        [AllowAnonymous]
        public ActionResult Login(string returnUrl)
        {
            if (this.IsUserLoggedIn)
            {
                return RedirectToAction("Index", "Home");
            }

            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;
            ViewBag.ReturnUrl = returnUrl;

            return View();
        }

        // POST: /Account/Login
        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult Login(LoginViewModel model, string returnUrl)
        {
            if (!ModelState.IsValid)
            {
                return View(model);
            }

            try
            { 
                // This doesn't count login failures towards account lockout
                // To enable password failures to trigger account lockout, change to shouldLockout: true
                CurrentUser = ServiceManager.LogIn(model.Email, model.Password);
            }
            catch (FaultException e)
            {
                ModelState.AddModelError("", e.Message); //TODO: Determine if we really need this one
            }

            if (CurrentUser != null)
            {
                return RedirectToLocal(returnUrl);
            }
            else
            {
                ModelState.AddModelError("", "Invalid login attempt.");
                return View(model);
            }
        }

        // GET: /Account/Register
        [AllowAnonymous]
        public ActionResult Register()
        {
            if (this.IsUserLoggedIn)
            {
                return RedirectToAction("Index", "Home");
            }

            return View();
        }

        //
        // POST: /Account/Register
        [HttpPost]
        [AllowAnonymous]
        [ValidateAntiForgeryToken]
        public ActionResult Register(RegisterViewModel registerModel)
        {
            if (ModelState.IsValid)
            {
                try
                {
                    UserProfileModel model = new UserProfileModel()
                    {
                        FirstName = registerModel.FirstName,
                        LastName = registerModel.LastName,
                        Email = registerModel.Email,
                        Password = registerModel.Password,
                    };

                    long userId = ServiceManager.CreateUserProfile(model);

                    if (userId > 0)
                    {
                        model.Uid = userId;
                        CurrentUser = model;

                        ServiceManager.SendMessage(GenerateWelcomeMessage());

                        return RedirectToAction("Index", "UserProfile");
                    }
                }
                catch (FaultException e)
                {
                    ModelState.AddModelError("", e.Message);
                }
            }

            // If we got this far, something failed, redisplay form
            return View(registerModel);
        }

        [AllowAnonymous]
        public ActionResult LogOff()
        {
            this.CurrentUser = null;
            return RedirectToAction("Index", "Home");
        }

        #region Helpers

        private void AddErrors(IdentityResult result)
        {
            foreach (var error in result.Errors)
            {
                ModelState.AddModelError("", error);
            }
        }

        private ActionResult RedirectToLocal(string returnUrl)
        {
            if (Url.IsLocalUrl(returnUrl))
            {
                return Redirect(returnUrl);
            }

            return RedirectToAction("Index", "Home");
        }

        private MessageModel GenerateWelcomeMessage()
        {
            MessageModel messageModel = new MessageModel()
            {
                Body = "Thank you for registering for the HHS prototype web application.  Please click around and check out the website functionality.",
                DateSent = DateTime.Now,
                IsRead = false,
                IsSent = false,
                RecipientId = CurrentUser.Uid.Value,
                SenderId = -1,
                Subject = "Welcome to the HHS Prototype App",
                UserProfileId = CurrentUser.Uid.Value
            };

            return messageModel;
        }
        #endregion
    }
}