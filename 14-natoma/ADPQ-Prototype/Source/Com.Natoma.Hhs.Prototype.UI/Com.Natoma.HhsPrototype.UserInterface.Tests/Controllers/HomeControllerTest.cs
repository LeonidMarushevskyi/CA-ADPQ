using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web.Mvc;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Com.Natoma.HhsPrototype.UserInterface;
using Com.Natoma.HhsPrototype.UserInterface.Controllers;
using Moq;
using Com.Natoma.HhsPrototype.UserInterface.Helpers;
using System.Web.SessionState;
using MvcFakes;

namespace Com.Natoma.HhsPrototype.UserInterface.Tests.Controllers
{
    [TestClass]
    public class HomeControllerTest
    {
        [TestMethod]
        public void Index()
        {
            // Arrange
            var mockSvcHelper = new Mock<IServiceHelper>();
            HomeController controller = new HomeController(mockSvcHelper.Object);
            var sessionItems = new SessionStateItemCollection();

            controller.ControllerContext = new FakeControllerContext(controller, sessionItems);

            // Act
            ViewResult result = controller.Index() as ViewResult;

            // Assert
            Assert.IsNotNull(result);
        }

        [TestMethod]
        public void About()
        {
            // Arrange
            var mockSvcHelper = new Mock<IServiceHelper>();
            HomeController controller = new HomeController(mockSvcHelper.Object);
            var sessionItems = new SessionStateItemCollection();

            controller.ControllerContext = new FakeControllerContext(controller, sessionItems);

            // Act
            ViewResult result = controller.About() as ViewResult;

            // Assert
            Assert.AreEqual("HHS Prototype", result.ViewBag.Message);
        }
    }
}
