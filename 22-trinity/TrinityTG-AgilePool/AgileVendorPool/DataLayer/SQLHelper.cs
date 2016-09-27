using System;

namespace AgileVendorPool.DataLayer
{
    public class ConnectionStrings
    {
        public const string DatabaseKey = "AgileVendorPool";

        public static string GetConnectionString(string key)
        {
            try
            {
                return System.Configuration.ConfigurationManager.ConnectionStrings[key].ConnectionString;
            }
            catch
            {
                return "";
            }
        }
    }

    public static class SQLHelper
    {
  
        public static Int16 ConvertToInt16(object str)
        {
            short result = 0;
            bool flag = Int16.TryParse(str.ToString(), out result);
            return result;
        }

        public static Int32 ConvertToInt32(object str)
        {
            int result = 0;
            bool flag = Int32.TryParse(str.ToString(), out result);
            return result;
        }

        public static Int64 ConvertToInt64(object str)
        {
            long result = 0;
            bool flag = Int64.TryParse(str.ToString(), out result);
            return result;
        }

        public static string ConvertToString(object str)
        {
            if (str != null && str != DBNull.Value)
                return str.ToString().Trim();
            else
                return string.Empty;
        }

        public static decimal ConvertToDecimal(object str)
        {
            decimal result = 0;
            bool flag = Decimal.TryParse(str.ToString(), out result);
            return result;
        }

        public static double ConvertToDouble(object str)
        {
            double result = 0.0;
            bool flag = double.TryParse(str.ToString(), out result);
            return result;
        }

        public static DateTime ConvertToDateTime(object str)
        {
            DateTime result = DateTime.MinValue;
            bool flag;
            if (str != null)
                flag = DateTime.TryParse(str.ToString(), out result);
            return result;
        }

        public static bool ConvertToBoolean(object obj)
        {
            if (obj != null && obj != DBNull.Value)
                return Convert.ToBoolean(obj);

            return false;
        }

        public static DateTime? ConvertToNullableDateTime(object str)
        {
            if (str != null && !string.IsNullOrEmpty(str.ToString()))
                return ConvertToDateTime(str);
            return null;
        }

        public static bool? ConvertToNullableBoolean(object str)
        {
            if (str != null && !string.IsNullOrEmpty(str.ToString()))
                return ConvertToBoolean(str);
            return null;
        }

        public static decimal? ConvertToNullableDecimal(object obj)
        {
            if (obj != null && !string.IsNullOrEmpty(obj.ToString()))
                return ConvertToDecimal(obj);
            return null;
        }

        public static short? ConvertToNullableShort(object obj)
        {
            if (obj != null && !string.IsNullOrEmpty(obj.ToString()))
                return ConvertToInt16(obj);
            return null;
        }

        public static int? ConvertToNullableInt(object obj)
        {
            if (obj != null && !string.IsNullOrEmpty(obj.ToString()))
                return ConvertToInt32(obj);
            return null;
        }

        public static long? ConvertToNullableLong(object obj)
        {
            if (obj != null && !string.IsNullOrEmpty(obj.ToString()))
                return ConvertToInt64(obj);
            return null;
        }

        public static byte? ConvertToNullableByte(object obj)
        {
            if (obj != null && !string.IsNullOrEmpty(obj.ToString()))
                return Convert.ToByte(obj);
            return null;
        }

        public static DateTime? ConvertToNullableMaxDateTime(object str)
        {
            if (str != null && !string.IsNullOrEmpty(str.ToString()))
                return ConvertToDateTime(str);
            return DateTime.MaxValue;
        }
    }
}