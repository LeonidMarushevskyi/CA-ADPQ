using System;
using System.Data;
using System.Data.SqlClient;
using AgileVendorPool.Models;

namespace AgileVendorPool.DataLayer

{
    public class FosterParentQueries
    {

        public static FosterParent GetFosterParent(string email)
        {
            try
            {
                FosterParent parent = new FosterParent();

                using (var connection = new SqlConnection(ConnectionStrings.GetConnectionString(ConnectionStrings.DatabaseKey)))
                {
                    connection.Open();

                    using (var command = new SqlCommand("[dbo].[FosterParentGet]", connection))
                    {
                        command.CommandTimeout = 0;
                        command.CommandType = CommandType.StoredProcedure;
                        command.Parameters.Add(new SqlParameter("@pEmail", email));

                        using (var reader = command.ExecuteReader())
                        {
                            while (reader.Read())
                            {
                                parent.fosterParentId = SQLHelper.ConvertToInt32(reader["FosterParentId"]);
                                parent.email= SQLHelper.ConvertToString(reader["Email"]);
                                parent.lastName= SQLHelper.ConvertToString(reader["LastName"]);
                                parent.firstName = SQLHelper.ConvertToString(reader["FirstName"]);
                                parent.address1 = SQLHelper.ConvertToString(reader["Address1"]);
                                parent.address2 = SQLHelper.ConvertToString(reader["Address2"]);
                                parent.city = SQLHelper.ConvertToString(reader["City"]);
                                parent.state = SQLHelper.ConvertToString(reader["State"]);
                                parent.zipCode = SQLHelper.ConvertToString(reader["ZipCode"]);
                                parent.country = SQLHelper.ConvertToString(reader["Country"]);
                            }
                        }
                    }
                }
                return parent;

            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }
           
        public static FosterParent InsertFosterParent(FosterParent fosterParent)
        {
            try
            {
                using (var connection = new SqlConnection(ConnectionStrings.GetConnectionString(ConnectionStrings.DatabaseKey)))
                {
                    connection.Open();

                    using (var command = new SqlCommand("[dbo].[FosterParentInsert]", connection))
                    {
                        command.CommandTimeout = 0;
                        command.CommandType = CommandType.StoredProcedure;

                        command.Parameters.Add(new SqlParameter("@pEmail", fosterParent.email));
                        command.Parameters.Add(new SqlParameter("@pLastName", fosterParent.lastName));
                        command.Parameters.Add(new SqlParameter("@pFirstName", fosterParent.firstName));
                        command.Parameters.Add(new SqlParameter("@pAddress1", fosterParent.address1));
                        command.Parameters.Add(new SqlParameter("@pAddress2", fosterParent.address2));
                        command.Parameters.Add(new SqlParameter("@pCity", fosterParent.city));
                        command.Parameters.Add(new SqlParameter("@pState", fosterParent.state));
                        command.Parameters.Add(new SqlParameter("@pZipCode", fosterParent.zipCode));
                        // HW
                        // This prtotype doesn't need the Country, so we just write USA
                        fosterParent.country = "USA";
                        command.Parameters.Add(new SqlParameter("@pCountry", fosterParent.country));

                        using (var reader = command.ExecuteReader())
                        {
                            while (reader.Read())
                            {
                                fosterParent.fosterParentId = SQLHelper.ConvertToInt32(reader[0]);

                                break;
                            }
                        }
                    }
                }
                return fosterParent;
            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }

        public static void UpdateFosterParent(FosterParent fosterParent)
        {
            try
            {
                using (var connection = new SqlConnection(ConnectionStrings.GetConnectionString(ConnectionStrings.DatabaseKey)))
                {
                    connection.Open();

                    using (var command = new SqlCommand("[dbo].[FosterParentUpdate]", connection))
                    {
                        command.CommandTimeout = 0;
                        command.CommandType = CommandType.StoredProcedure;
                        command.Parameters.Add(new SqlParameter("@pFosterParentId", fosterParent.fosterParentId));
                        command.Parameters.Add(new SqlParameter("@pEmail", fosterParent.email));
                        command.Parameters.Add(new SqlParameter("@pLastName", fosterParent.lastName));
                        command.Parameters.Add(new SqlParameter("@pFirstName", fosterParent.firstName));
                        command.Parameters.Add(new SqlParameter("@pAddress1", fosterParent.address1));
                        command.Parameters.Add(new SqlParameter("@pAddress2", fosterParent.address2));
                        command.Parameters.Add(new SqlParameter("@pCity", fosterParent.city));
                        command.Parameters.Add(new SqlParameter("@pState", fosterParent.state));
                        command.Parameters.Add(new SqlParameter("@pZipCode", fosterParent.zipCode));
                        fosterParent.country = "USA";
                        command.Parameters.Add(new SqlParameter("@pCountry", fosterParent.country));

                        command.ExecuteNonQuery();
                    }
                }
            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }

        public static void DeleteFosterParent(int fosterParentId)
        {
            try
            {
                using (var connection = new SqlConnection(ConnectionStrings.GetConnectionString(ConnectionStrings.DatabaseKey)))
                {
                    connection.Open();

                    using (var command = new SqlCommand("[dbo].[FosterParentDelete]", connection))
                    {
                        command.CommandTimeout = 0;
                        command.CommandType = CommandType.StoredProcedure;
                        command.Parameters.Add(new SqlParameter("@pFosterParentId", fosterParentId));

                        command.ExecuteNonQuery();
                    }
                }

            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }

    }
}