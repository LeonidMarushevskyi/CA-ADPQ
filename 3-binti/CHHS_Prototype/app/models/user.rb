class User < ActiveRecord::Base
  devise :database_authenticatable, :recoverable, :registerable,
         :rememberable, :trackable, :validatable

  has_one :conversation
  has_many :messages

  validates :first_name, :last_name, presence: true

  def avatar
    "#{first_name.to_s.first}#{last_name.to_s.first}".upcase
  end

  def full_name
    "#{first_name} #{last_name}"
  end

  def social_worker?
    false
  end

  def create_initial_conversation
    conversation = create_conversation
    SocialWorker.first.conversations << conversation
  end
end
