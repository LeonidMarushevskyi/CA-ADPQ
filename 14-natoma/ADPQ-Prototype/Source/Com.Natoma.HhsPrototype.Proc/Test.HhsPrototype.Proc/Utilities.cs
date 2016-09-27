using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Configuration;

namespace Test.HhsPrototype.Proc
{
    public class Utilities
    {
        public static string BaseDirectory()
        {
            string baseDirectory = ConfigurationManager.AppSettings.Get("saveDirectory");

            if (string.IsNullOrEmpty(baseDirectory) || !Directory.Exists(baseDirectory))
            {
                baseDirectory = Directory.GetCurrentDirectory();
            }

            return baseDirectory;
        }

    }
}
