using System;
using System.Collections.Generic;
using AgileVendorPool.Models;

namespace AgileVendorPool.BusinessLayer
{
    public class FosterParentQueries
    {
        // HW
        // In this layer:
        // - Business rules will be enforced
        // - Data will also get validated


        public static FosterParent GetFosterParent(string email)
        {
            try
            {
                return DataLayer.FosterParentQueries.GetFosterParent(email);
            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }

        public static ReturnedData UpdateFosterParent(FosterParent parent)
        {
            ReturnedData returnedData = new ReturnedData();
            returnedData.serverError = new List<ErrorClass>();

            try
            {
                // HW
                // ---------------------------------------------------------------------------------
                // Validate 
                // ---------------------------------------------------------------------------------
                //GeneralValidation.ValidateParent(parent, returnedData.serverError);
                if (returnedData.serverError.Count > 0)
                {
                    return returnedData;
                }


                // ---------------------------------------------------------------------------------
                // OK to go forward 
                // ---------------------------------------------------------------------------------
                DataLayer.FosterParentQueries.UpdateFosterParent(parent);

                return returnedData;
            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }

        public static ReturnedData InsertFosterParent(FosterParent parent)
        {
            ReturnedData returnedData = new ReturnedData();
            returnedData.serverError = new List<ErrorClass>();

            try
            {
                // HW
                // ---------------------------------------------------------------------------------
                // Validate 
                // ---------------------------------------------------------------------------------
                //GeneralValidation.ValidateParent(parent, returnedData.serverError);
                if (returnedData.serverError.Count > 0)
                {
                    return returnedData;
                }


                // ---------------------------------------------------------------------------------
                // OK to go forward 
                // ---------------------------------------------------------------------------------
                returnedData.serverData= DataLayer.FosterParentQueries.InsertFosterParent(parent);

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