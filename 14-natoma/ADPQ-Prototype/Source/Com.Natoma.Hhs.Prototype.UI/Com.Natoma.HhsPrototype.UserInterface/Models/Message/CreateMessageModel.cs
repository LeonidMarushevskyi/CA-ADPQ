using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Com.Natoma.HhsPrototype.UserInterface.Models
{
    public class CreateMessageModel
    {
        /// <summary>
        /// Body of the message being created
        /// </summary>
        [Required]
        [Display(Name = "Message")]
        public string Body { get; set; }
        
        /// <summary>
        /// Subject of the message being created
        /// </summary>
        [Required]
        [Display(Name = "Subject")]
        public string Subject { get; set; }
        
        /// <summary>
        /// Unique identifying value of Recipient of message
        /// </summary>
        [Required]
        [Display(Name = "Recipient")]
        public long RecipientId { get; set; }
    }
}
