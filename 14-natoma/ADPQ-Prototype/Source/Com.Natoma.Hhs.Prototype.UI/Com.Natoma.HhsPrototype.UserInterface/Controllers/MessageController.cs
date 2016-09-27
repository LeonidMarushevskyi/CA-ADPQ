using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

using Com.Natoma.HhsPrototype.UserInterface.Helpers;
using Com.Natoma.HhsPrototype.UserInterface.HhsPrototypeProc;
using Com.Natoma.HhsPrototype.UserInterface.Models;

namespace Com.Natoma.HhsPrototype.UserInterface.Controllers
{
    public class MessageController : HhsPrototypeController
    {
        #region Private Members
        // TODO: Populate list from available "CaseWorkers" in system
        private List<SelectListItem> selectListItemList = new List<SelectListItem>()
        {
            new SelectListItem() { Text = "(Select Case Worker)", Value = "" },
            new SelectListItem() { Text = "Case Worker Anne", Value = "-1" },
            new SelectListItem() { Text = "Case Worker Benny", Value = "-2" },
            new SelectListItem() { Text = "Case Worker Clarie", Value = "-3" },
            new SelectListItem() { Text = "Case Worker Devon", Value = "-4" }
        };
        #endregion
        
        // GET: Message
        public ActionResult Index()
        {
            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;

            if (!this.IsUserLoggedIn)
            {
                return RedirectToAction("Index", "Home");
            }

            List<MessageModel> messages = null;

            try
            {
                string userFirstAndLastName = CurrentUser.FirstName + " " + CurrentUser.LastName;

                messages = ServiceManager.GetMessagesForUser(CurrentUser.Uid.Value).OrderByDescending(o => o.DateSent).ToList();

                foreach (var message in messages)
                {
                    message.RecipientName = this.GetNameByUserId(message.RecipientId, userFirstAndLastName);
                    message.SenderName = this.GetNameByUserId(message.SenderId, userFirstAndLastName);
                }
            }
            catch (Exception e)
            {
                ViewBag.Exception = e;
            }

            return View(messages);
        }

        // GET: Message/Create
        public ActionResult Create()
        {
            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;

            if (!this.IsUserLoggedIn)
            {
                return RedirectToAction("Index", "Home");
            }

            ViewBag.ContactList = new SelectList(selectListItemList, "Value", "Text");

            return View();
        }

        // POST: Message/Create
        [HttpPost]
        public ActionResult Create(CreateMessageModel createMessageModel)
        {
            try
            {
                ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;

                if (!this.IsUserLoggedIn)
                {
                    return RedirectToAction("Index", "Home");
                }

                MessageModel model = new MessageModel()
                {
                    IsSent = true,
                    UserProfileId = CurrentUser.Uid.Value,
                    DateSent = DateTime.Now,
                    SenderId = CurrentUser.Uid.Value,
                    RecipientId = createMessageModel.RecipientId,
                    Subject = createMessageModel.Subject,
                    Body = createMessageModel.Body
                };

                try
                {
                    ServiceManager.SendMessage(model);
                }
                catch (Exception e)
                {
                    ViewBag.Exception = e;
                    ModelState.AddModelError("", "Error sending message");
                }

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Message/Read
        public ActionResult Read(long id)
        {
            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;

            if (!this.IsUserLoggedIn)
            {
                return RedirectToAction("Index", "Home");
            }

            // Get message by MessageId
            MessageModel message = null;

            try
            {
                string userFirstAndLastName = CurrentUser.FirstName + " " + CurrentUser.LastName;
                message = ServiceManager.GetMessagesForUser(CurrentUser.Uid.Value).FirstOrDefault(f => f.MessageId == id);
                message.RecipientName = this.GetNameByUserId(message.RecipientId, userFirstAndLastName);
                message.SenderName = this.GetNameByUserId(message.SenderId, userFirstAndLastName);
            }
            catch (Exception e)
            {
                ViewBag.Exception = e;
            }

            return View(message);
        }

        // TODO: Implement method
        public ActionResult DeleteMessages(object data)
        {
            List<long> messageIds = new List<long>();

            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;

            if (!this.IsUserLoggedIn)
            {
                return RedirectToAction("Index", "Home");
            }

            ServiceManager.DeleteUserMessages(CurrentUser.Uid.Value, messageIds);

            return RedirectToAction("Index");
        }

        public ActionResult DeleteMessage(long messageId)
        {
            ViewBag.IsUserLoggedIn = this.IsUserLoggedIn;

            if (!this.IsUserLoggedIn)
            {
                return RedirectToAction("Index", "Home");
            }

            ServiceManager.DeleteUserMessages(CurrentUser.Uid.Value, new List<long>() { messageId });

            return RedirectToAction("Index");
        }

        public ActionResult CreateSampleInboxMessage()
        {
            if (this.CurrentUser != null && this.CurrentUser.Uid.HasValue)
            {
                MessageModel message = this.GenerateGenericMessage(this.CurrentUser.Uid.Value);
                ServiceManager.SendMessage(message);
            }

            return RedirectToAction("Index", "Message");
        }

        #region Helpers
        /// <summary>
        /// Method to get the "Sender/Recipient" name for a sample "Case Worker".
        /// Placeholder until "Case Worker" users are implemented in the system.
        /// </summary>
        /// <param name="userId"></param>
        /// <param name="userFirstAndLastName"></param>
        /// <returns></returns>
        // TODO: Move to "Helper" class
        private string GetNameByUserId(long userId, string userFirstAndLastName)
        {
            if (userId < 0)
            {
                switch (userId)
                {
                    case -1:
                        return "Case Worker Anne";
                    case -2:
                        return "Case Worker Benny";
                    case -3:
                        return "Case Worker Clarie";
                    case -4:
                        return "Case Worker Devon";
                    default:
                        return "Unknown User";
                }
            }
            else
            {
                return userFirstAndLastName;
            }
        }

        /// <summary>
        /// Method to generate a generic "Inbox" message for testing and evaluation purposes.
        /// </summary>
        /// <param name="userId"></param>
        /// <returns></returns>
        // TODO: Move method to "Helper" class
        private MessageModel GenerateGenericMessage(long userProfileId)
        {
            Random rando = new Random();

            MessageModel model = new MessageModel()
            {
                DateSent = DateTime.Now,
                IsSent = false,
                RecipientId = userProfileId,
                SenderId = rando.Next(-4, -1),
                Subject = "Sample Message " + rando.Next(100, 999999),
                UserProfileId = userProfileId,
                Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris id iaculis risus. Aliquam eget varius diam, a iaculis lectus. Ut a viverra tortor. Maecenas mattis tristique est sed molestie. Curabitur faucibus nisi ut gravida auctor. Quisque dignissim elit sed nibh ultricies maximus. Etiam turpis sem, euismod non libero semper, consequat dapibus quam. Vivamus tempor sit amet massa sed tristique. Curabitur molestie augue a neque tempor ullamcorper. Etiam volutpat, neque eget dapibus ultrices, massa lacus placerat metus, auctor hendrerit dolor ipsum quis ligula.<br /><br />Curabitur eget risus ac libero sollicitudin malesuada.Sed non ultricies metus.Nam pharetra ac eros elementum facilisis.Praesent a sapien bibendum,posuere elit imperdiet, gravida turpis.Curabitur et suscipit justo.Praesent a quam accumsan, placerat enim nec, rutrum magna.Nam sed velit laoreet, blandit justo non, lacinia libero.Curabitur semper magna at nisi cursus dictum.Phasellus sodales tempor sem ac malesuada.<br /><br />Nam venenatis enim nisl, a ullamcorper lacus tincidunt vel.Ut at turpis justo.Fusce rhoncus ante sed diam rutrum faucibus.Nunc mollis porta turpis, et pharetra tellus ultricies vel.Phasellus vel mollis elit.Proin ac rhoncus sapien.Nunc at ultrices lorem, at interdum massa.Maecenas vehicula mauris sodales nibh ullamcorper iaculis.Nam ut dictum mi, id cursus dui.Donec nec elit suscipit, porta ligula quis, fermentum enim.Nunc ut vulputate turpis.Vivamus at ante laoreet massa lacinia pulvinar.<br /><br />Aliquam condimentum purus ex, at pellentesque dui congue vitae.In tempus orci in turpis euismod imperdiet et quis magna."
            };

            return model;
        }
        #endregion
    }
}