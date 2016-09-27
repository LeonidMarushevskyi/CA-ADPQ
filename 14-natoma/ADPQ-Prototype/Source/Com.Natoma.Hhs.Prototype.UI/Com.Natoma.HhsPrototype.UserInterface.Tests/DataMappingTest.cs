using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Com.Natoma.HhsPrototype.UserInterface.Models;
using Com.Natoma.HhsPrototype.UserInterface.HhsPrototypeProc;
using Com.Natoma.HhsPrototype.UserInterface;
using AutoMapper;

namespace Com.Natoma.HhsPrototype.UserInterface.Tests
{
    [TestClass]
    public class DataMappingTest
    {
        [TestMethod]
        public void ServiceToModelDataMapping()
        {
            // Arrange
            AutoMapperConfig.RegisterAllMapping();

            // Act
            // Note: No additional actions needed for mapping test

            // Assert
            Mapper.AssertConfigurationIsValid();
        }
    }
}
