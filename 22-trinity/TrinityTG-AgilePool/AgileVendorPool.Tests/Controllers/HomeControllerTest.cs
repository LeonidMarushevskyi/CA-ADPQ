using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web.Mvc;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using AgileVendorPool;
using AgileVendorPool.Controllers;

namespace AgileVendorPool.Tests.Controllers
{
    [TestClass]
    public class HomeControllerTest
    {
        [TestMethod]
        public void Index()
        {
            // Arrange
            HomeController controller = new HomeController();

            // Act
            ViewResult result = controller.Index() as ViewResult;

            // Assert
            Assert.IsNotNull(result);
        }

        [TestMethod]
        public void Profile()
        {
            // Arrange
            HomeController controller = new HomeController();

            string email = "Abc@Yahoo.com";

            // Act
            ViewResult result = controller.FosterParentProfile(email) as ViewResult;

            // Assert
            Assert.AreEqual("Your application description page.", result.ViewBag.Message);
        }

        [TestMethod]
        public void EmailFosterParent()
        {
            // Arrange
            HomeController controller = new HomeController();

            // Act
            ViewResult result = controller.Email("FosterParent") as ViewResult;

            // Assert
            Assert.IsNotNull(result);
        }

        [TestMethod]
        public void EmailCaseWorker()
        {
            // Arrange
            HomeController controller = new HomeController();

            // Act
            ViewResult result = controller.Email("CaseWorker") as ViewResult;

            // Assert
            Assert.IsNotNull(result);
        }

        [TestMethod]
        public void EmailCaseWFacilitySearch()
        {
            // Arrange
            HomeController controller = new HomeController();

            // Act
            ViewResult result = controller.FacilitySearch() as ViewResult;

            // Assert
            Assert.IsNotNull(result);
        }



    }
}
