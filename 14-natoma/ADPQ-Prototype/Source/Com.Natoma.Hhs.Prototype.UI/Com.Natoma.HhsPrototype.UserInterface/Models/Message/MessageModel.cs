using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Com.Natoma.HhsPrototype.UserInterface.Models
{
    public class MessageModel
    {
        /// <summary>
        /// DateTime the message was created
        /// </summary>
        [DisplayFormat(DataFormatString = "MM/dd/yyy hh:mm:ss tt")]
        public DateTime DateSent { get; set; }

        /// <summary>
        /// Body of the message
        /// </summary>
        public string Body { get; set; }

        /// <summary>
        /// Is message "Sent" or "Received"
        /// </summary>
        public bool IsSent { get; set; }

        /// <summary>
        /// Unique identifying value of User creating message
        /// </summary>
        public long UserProfileId { get; set; }

        /// <summary>
        /// Unique identifying value of message
        /// </summary>
        public long MessageId { get; set; }

        /// <summary>
        /// Subject of the message
        /// </summary>
        public string Subject { get; set; }
        
        /// <summary>
        /// Unique identifying value of Sender of message
        /// </summary>
        public long SenderId { get; set; }

        /// <summary>
        /// Unique identifying value of Recipient of message
        /// </summary>
        [Display(Name = "Recipient")]
        public long RecipientId { get; set; }

        // Extended Properties
        // TODO: Get "Name" from "UserProfile" data
        public string RecipientName { get; set; }
        public string SenderName { get; set; }

        // Not currently implemented

        /// <summary>
        /// Is message checked for page function (delete, mark as read)
        /// </summary>
        public bool IsChecked { get; set; }

        /// <summary>
        /// Type of message
        /// </summary>
        public int MessageType { get; set; } // TODO: Create ENUM for this

        /// <summary>
        /// Has the message been read by the user
        /// </summary>
        public bool IsRead { get; set; }

        /// <summary>
        /// Has the message been flagged by the user
        /// </summary>
        public bool IsFlagged { get; set; }
    }
}
