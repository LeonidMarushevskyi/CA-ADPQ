class CreateAddresses < ActiveRecord::Migration
  def change
    create_table :addresses do |t|
      t.string :street
      t.string :city
      t.string :state
      t.string :zip
      t.string :county_name
      t.string :telephone_number
      t.references :owner, polymorphic: true, index: true

      t.timestamps null: false
    end
  end
end
