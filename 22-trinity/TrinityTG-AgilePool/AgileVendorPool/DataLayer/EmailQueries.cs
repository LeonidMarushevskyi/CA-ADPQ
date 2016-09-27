using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using AgileVendorPool.Models;

namespace AgileVendorPool.DataLayer
{
    public class EmailQueries
    {
        public static List<string> GetFosterParentEmailList()
        {
            try
            {
                List<string> emailList = new List<string>();

                using (var connection = new SqlConnection(ConnectionStrings.GetConnectionString(ConnectionStrings.DatabaseKey)))
                {
                    connection.Open();

                    using (var command = new SqlCommand("[dbo].[FosterParentGet]", connection))
                    {
                        command.CommandTimeout = 0;
                        command.CommandType = CommandType.StoredProcedure;
                        command.Parameters.Add(new SqlParameter("@pEmail", ""));

                        using (var reader = command.ExecuteReader())
                        {
                            while (reader.Read())
                            {
                                string email = SQLHelper.ConvertToString(reader["Email"]);
                                emailList.Add(email);
                            }
                        }
                    }
                }
                return emailList;

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
                List<EmailContent> emailList = new List<EmailContent>();

                using (var connection = new SqlConnection(ConnectionStrings.GetConnectionString(ConnectionStrings.DatabaseKey)))
                {
                    connection.Open();

                    using (var command = new SqlCommand("[dbo].[EmailGet]", connection))
                    {
                        command.CommandTimeout = 0;
                        command.CommandType = CommandType.StoredProcedure;
                        command.Parameters.Add(new SqlParameter("@pEmail", email));

                        using (var reader = command.ExecuteReader())
                        {
                            
                            while (reader.Read())
                            {
                                EmailContent emailContent = new EmailContent();

                                emailContent.emailId = SQLHelper.ConvertToInt32(reader["EmailId"]);
                                emailContent.emailFrom = SQLHelper.ConvertToString(reader["EmailFrom"]);
                                emailContent.emailTo = SQLHelper.ConvertToString(reader["EmailTo"]);
                                emailContent.emailSubject = SQLHelper.ConvertToString(reader["EmailSubject"]);
                                emailContent.emailBody = SQLHelper.ConvertToString(reader["EmailBody"]);
                                emailContent.emailDate = SQLHelper.ConvertToDateTime(reader["EmailDate"]);

                                // HW
                                // Change the DATE format like how MS Outlook displays it in the Inbox.
                                // This can also be done in JavaScript.
                                DateTime d1 = new DateTime();
                                //d1 = TimeZoneInfo.ConvertTimeBySystemTimeZoneId(emailContent.emailDate, TimeZoneInfo.Local.Id, "Pacific Standard Time");
                                d1 = emailContent.emailDate.AddHours(-7); // change to PST
                                emailContent.dynamicEmailDate = d1.DayOfWeek + " " + d1.ToShortDateString() + " " + d1.ToShortTimeString();

                                emailList.Add(emailContent);
                            }
                        }
                    }
                }
                return emailList;

            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }

        public static void SendEmail(EmailContent emailContent)
        {
            try
            {
                using (var connection = new SqlConnection(ConnectionStrings.GetConnectionString(ConnectionStrings.DatabaseKey)))
                {
                    connection.Open();

                    using (var command = new SqlCommand("[dbo].[EmailInsert]", connection))
                    {
                        command.CommandTimeout = 0;
                        command.CommandType = CommandType.StoredProcedure;

                        // @pEmailFrom, @pEmailTo, @pEmailSubject, @EmailBody, GETDATE())
                        command.Parameters.Add(new SqlParameter("@pEmailFrom", emailContent.emailFrom));
                        command.Parameters.Add(new SqlParameter("@pEmailTo", emailContent.emailTo));
                        command.Parameters.Add(new SqlParameter("@pEmailSubject", emailContent.emailSubject));
                        command.Parameters.Add(new SqlParameter("@EmailBody", emailContent.emailBody));

                        using (var reader = command.ExecuteReader())
                        {
                            while (reader.Read())
                            {
                                emailContent.emailId= SQLHelper.ConvertToInt32(reader[0]);

                                // we only want the first record for this prototype
                                break;
                            }
                        }
                    }
                }
                return ;
            }
            catch (Exception Ex)
            {
                // log Exception 
                throw;
            }
        }

 
        public static void DeleteEmailList(List<int> EmailList)
        {
            try
            {
                using (var connection = new SqlConnection(ConnectionStrings.GetConnectionString(ConnectionStrings.DatabaseKey)))
                {
                    connection.Open();

                    foreach (var email in EmailList)
                    {
                        using (var command = new SqlCommand("[dbo].[EmailDelete]", connection))
                        {
                            command.CommandTimeout = 0;
                            command.CommandType = CommandType.StoredProcedure;
                            command.Parameters.Add(new SqlParameter("@pEmailId", email));

                            command.ExecuteNonQuery();
                        }
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