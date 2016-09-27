using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AgileVendorPool.Models
{
    // --------------------------------------------------------------------------------
    // This class is not used on the server. 
    // The call to the external API is made from the client JavaScript code
    // This is just for reference.
    // --------------------------------------------------------------------------------
    public class Facility
    {
        public string county_name { get; set; }
        public string facility_address { get; set; }
        public string facility_administrator { get; set; }
        public string facility_capacity { get; set; }
        public string facility_city { get; set; }
        public string facility_name { get; set; }
        public string facility_number { get; set; }
        public string facility_state { get; set; }
        public string facility_status { get; set; }
        public string facility_telephone_number { get; set; }
        public string facility_type { get; set; }
        public string facility_zip { get; set; }
        public string license_first_date { get; set; }
        public string licensee { get; set; }
        public Location location { get; set; }
        public string location_address { get; set; }
        public string location_state { get; set; }
        public string location_zip { get; set; }
        public string regional_office { get; set; }
    }

    public class Location
    {
        public string type { get; set; }
        public List<int> coordinates { get; set; }
        
    }
}