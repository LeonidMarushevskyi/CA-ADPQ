using System;
using System.Globalization;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using Com.Natoma.HhsPrototype.UserInterface.Helpers;
using Com.Natoma.HhsPrototype.UserInterface.Models;

namespace Com.Natoma.HhsPrototype.UserInterface.Controllers
{
    public class HhsPrototypeController : Controller
    {
        private IServiceHelper _serviceHelper;
        private const string currentUserKey = "current user";

        public HhsPrototypeController()
        {
            //TODO: Remove default constructor once dependency injection framework implemented
            _serviceHelper = new ServiceHelper();
        }

        public HhsPrototypeController(IServiceHelper serviceHelper)
        {
            _serviceHelper = serviceHelper;
        }

        protected IServiceHelper ServiceManager
        {
            get
            {
                return _serviceHelper;
            }

        }

        public UserProfileModel CurrentUser
        {
            get
            {
                object user = Session[currentUserKey];

                if (user != null)
                {
                    return (UserProfileModel)user;
                }
                else
                {
                    return null;
                }
            }
            set
            {
                Session[currentUserKey] = value;
            }
        }

        protected bool IsUserLoggedIn
        {
            get
            {
                return this.CurrentUser != null;
            }
        }
    }
}