class CreateMessagesUsers < ActiveRecord::Migration
  def change
    create_table :messages_users do |t|
      t.references :message
      t.references :user
      t.boolean :read_status, default: false

      t.timestamps null: false
    end
  end
end
