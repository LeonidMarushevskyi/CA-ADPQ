class SocialWorker < User
  has_and_belongs_to_many :conversations, join_table: "social_worker_conversations"

  def social_worker?
    true
  end
end
