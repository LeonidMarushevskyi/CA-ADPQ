class ConversationMailer < ApplicationMailer
  def new_message_email(user)
    @user = user
    mail(
      to: user.email,
      subject: "You have a new message on the CHHS Parent Gateway"
    )
  end
end
