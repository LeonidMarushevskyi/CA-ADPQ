class CreateMessages < ActiveRecord::Migration
  def change
    create_table :messages do |t|
      t.string :subject
      t.text :content
      t.references :sender

      t.timestamps null: false
    end
  end
end
