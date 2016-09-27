class MessagesController < ApplicationController
  before_action :authenticate_user!

  # GET /messages
  def index
    @sent_messages = current_user.sent_messages
    @received_messages = current_user.received_messages
  end

  # GET /messages/new
  def new
    @message = Message.new
    @message.sender = current_user
    @message.recipients = []
  end

  # POST /messages
  def create
    message_params = params[:message]
    #{}"message"=>{"recipients"=>"child_1@hanoian.com", "subject"=>"Test subject", "content"=>"Test content"}}
    rcps = message_params[:recipients]
    recipients = User.where('email in (?)', rcps.gsub(' ','').split(',')).to_a
    render text: 'No recipients', status: :not_found if recipients.empty?

    message = Message.create(sender: current_user, recipients: recipients, subject: message_params[:subject], content: message_params[:content])  
    @recipient_string =  message.recipients_array.join('<br/>').html_safe
  end

  # GET /messages/:id
  def show
    @disabled = 'disabled'
    @message = Message.includes(:sender, :recipients).find(params[:id])
  end

  # GET /messages/:id/edit
  def edit
  end

  # PATCH/PUT /messages/:id
  def update
  end

  # DELETE messages/:id
  def destroy
  end
end
