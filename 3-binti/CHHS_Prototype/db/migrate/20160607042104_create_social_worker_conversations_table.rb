class CreateSocialWorkerConversationsTable < ActiveRecord::Migration
  def change
    create_table :social_worker_conversations do |t|
      t.references :social_worker
      t.references :conversation
    end
  end
end
