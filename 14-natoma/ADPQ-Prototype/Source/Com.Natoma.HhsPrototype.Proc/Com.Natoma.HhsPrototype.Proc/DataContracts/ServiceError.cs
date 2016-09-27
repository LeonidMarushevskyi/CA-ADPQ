using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace Com.Natoma.HhsPrototype.Proc.DataContracts
{
    [DataContract]
    public class ServiceError
    {
        [DataMember]
        public string ErrorMessage { get; set; }

        [DataMember]
        public string ErrorDetails { get; set; }
    }
}