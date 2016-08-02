class AddTypesToUsers < ActiveRecord::Migration
  def change
    add_column :users, :type, :string
    remove_column :users, :social_worker, :boolean, default: false
  end
end
