class AddSocialWorkerToUsers < ActiveRecord::Migration
  def change
    add_column :users, :social_worker, :boolean, default: false
  end
end
