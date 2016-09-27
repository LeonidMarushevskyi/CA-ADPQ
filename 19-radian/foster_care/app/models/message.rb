class Message < ActiveRecord::Base
  belongs_to :sender, class_name: 'User', foreign_key: 'sender_id'

  has_many :messages_users, dependent: :destroy
  has_many :recipients, through: :messages_users

  def recipients_array
    recipients.map { |x| "#{x.name} (#{x.email})" }
  end

  def sent_date
    created_at.strftime('%b-%d-%Y %I:%M:%S')
  end
end
