class Facility < ActiveRecord::Base
  has_many :addresses, as: :owner
  belongs_to :user # facility administrator

  def administrator_name
    "#{self.user.first_name} #{self.user.last_name}" if self.user
  end

  def administrator=(admin)
    self.user = admin
  end
end
