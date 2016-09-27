using System;
using System.Collections.Generic;
using AgileVendorPool.Models;


namespace AgileVendorPool.BusinessLayer
{
    public class EmailQueries
    {
        public static List<string> GetFosterParentEmailList()
        {
            try
            {
                return DataLayer.EmailQueries.GetFosterParentEmailList();
            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }

        public static List<EmailContent> GetEmail(string email)
        {
            try
            {
                return DataLayer.EmailQueries.GetEmail(email);
            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }


        public static ReturnedData DeleteEmailList(List<int> EmailList)
        {
            ReturnedData returnedData = new ReturnedData();
            returnedData.serverError = new List<ErrorClass>();

            try
            {
                // ---------------------------------------------------------------------------------
                // Validate 
                // ---------------------------------------------------------------------------------
                // HW
                // Code for validation will go here...
        
        
                // ---------------------------------------------------------------------------------
                // OK to go forward 
                // ---------------------------------------------------------------------------------
                DataLayer.EmailQueries.DeleteEmailList(EmailList);

                return returnedData;

            }
            catch (Exception Ex)
            {
                // log Exception
                throw;
            }

        }

        public static ReturnedData SendEmail(EmailContent emailContent)
        {
            ReturnedData returnedData = new ReturnedData();
            returnedData.serverError = new List<ErrorClass>();

            try
            {
                // ---------------------------------------------------------------------------------
                // Validate 
                // ---------------------------------------------------------------------------------
                // HW
                // Code for validation will go here...


                // ---------------------------------------------------------------------------------
                // OK to go forward 
                // ---------------------------------------------------------------------------------
                DataLayer.EmailQueries.SendEmail(emailContent);

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