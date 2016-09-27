class MessagesUser < ActiveRecord::Base
  belongs_to :received_message, class_name: 'Message', foreign_key: 'message_id'
  belongs_to :recipient, class_name: 'User', foreign_key: 'user_id'
end
