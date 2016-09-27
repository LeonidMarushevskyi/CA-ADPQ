class User < ActiveRecord::Base
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable

  has_many :addresses, as: :owner

  has_many :roles_users
  has_many :roles, through: :roles_users

  belongs_to :facility # only for facility_admin, case_worker and children

  has_many :sent_messages, class_name: 'Message', foreign_key: 'sender_id'

  has_many :messages_users
  has_many :received_messages, through: :messages_users

  def name
    "#{first_name} #{last_name}"
  end
end
