using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ServiceModel;
using System.Runtime.Serialization;

namespace Com.Natoma.HhsPrototype.Proc.DataContracts
{
    /// <summary>
    /// Message sent to or from the parent
    /// </summary>
    [DataContract]
    public class Message
    {
        /// <summary>
        /// Date message was sent
        /// </summary>
        [DataMember(IsRequired = true)]
        public DateTime DateSent { get; set; }

        /// <summary>
        /// Body of message
        /// </summary>
        [DataMember(IsRequired = true)]
        public string Body { get; set; }

        /// <summary>
        /// true if Message is sent from the parent. false if sent to the parent
        /// </summary>
        [DataMember]
        public bool IsSent { get; set; }

        /// <summary>
        /// ID of associated user profile
        /// </summary>
        [DataMember]
        public long UserProfileId { get; set; }

        [DataMember]
        public long MessageId { get; set; }

        [DataMember]
        public bool IsRead { get; set; }

        [DataMember]
        public bool IsFlagged { get; set; }

        [DataMember]
        public string Subject { get; set; }

        [DataMember]
        public int MessageType { get; set; } // TODO: Create ENUM for this

        [DataMember]
        public long SenderId { get; set; }

        [DataMember]
        public long RecipientId { get; set; }        


        /// <summary>
        /// Returns copy of Message
        /// </summary>
        /// <returns>Copy of message</returns>
        public Message Clone()
        {
            return (Message)this.MemberwiseClone();
        }
    }
}