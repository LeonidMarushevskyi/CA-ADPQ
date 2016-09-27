using System;
using System.Collections.Generic;
using AgileVendorPool.Models;
using System.Text.RegularExpressions;

namespace AgileVendorPool.BusinessLayer
{
    public class  RegexUtilities
    {
        // HW
        // We used pattern from this link. But there are 
        // other patterns which can be used for email format validation
        // https://msdn.microsoft.com/en-us/library/01escwtf(v=vs.100).aspx
        public static bool IsValidEmail(string strIn)
        {
            
            if (String.IsNullOrEmpty(strIn))
                return false;

            // Return true if strIn is in valid e-mail format.
            return Regex.IsMatch(strIn,
                   @"^(?("")(""[^""]+?""@)|(([0-9a-z]((\.(?!\.))|[-!#\$%&'\*\+/=\?\^`\{\}\|~\w])*)(?<=[0-9a-z])@))" +
                   @"(?(\[)(\[(\d{1,3}\.){3}\d{1,3}\])|(([0-9a-z][-\w]*[0-9a-z]*\.)+[a-z0-9]{2,17}))$",
                   RegexOptions.IgnoreCase);
        }

    }

    public class UtilQueries
    {
        public static ReturnedData Login(string email)
        {
            ReturnedData returnedData = new ReturnedData();
            returnedData.serverError = new List<ErrorClass>();

            try
            {
                // ---------------------------------------------------------------------------------
                // Validate 
                // ---------------------------------------------------------------------------------
                // HW
                // verify email format
                if (!RegexUtilities.IsValidEmail(email))
                {
                    returnedData.serverError.Add(new ErrorClass() { Summary ="Email format is incorrect. Please try again. ", Detail = "" });
                    return returnedData;
                }

                // verify user exist
                if (AgileVendorPool.DataLayer.FosterParentQueries.GetFosterParent(email).email == null)
                {
                    returnedData.serverError.Add(new ErrorClass() { Summary = "User is not registered. Please regsiter first. ", Detail = "" });
                    return returnedData;
                }


                // ---------------------------------------------------------------------------------
                // OK so far
                // ---------------------------------------------------------------------------------


                return returnedData;
            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }

        
        public static ReturnedData Register(string email)
        {
            ReturnedData returnedData = new ReturnedData();
            returnedData.serverError = new List<ErrorClass>();

            try
            {
                // ---------------------------------------------------------------------------------
                // Validate 
                // ---------------------------------------------------------------------------------
                // HW

                // verify email format
                if (!RegexUtilities.IsValidEmail(email))
                {
                    returnedData.serverError.Add(new ErrorClass() { Summary = "Email format is incorrect. Please try again. ", Detail = "" });
                    return returnedData;
                }

                // verify user DOES NOT exist
                if (AgileVendorPool.DataLayer.FosterParentQueries.GetFosterParent(email).email != null)
                {
                    returnedData.serverError.Add(new ErrorClass() { Summary = "User is already registered. Please try to login. ", Detail = "" });
                    return returnedData;
                }


                // ---------------------------------------------------------------------------------
                // OK so far
                // ---------------------------------------------------------------------------------


                return returnedData;
            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }

    }


}