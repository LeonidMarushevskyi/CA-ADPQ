class CreateFacilities < ActiveRecord::Migration
  def change
    create_table :facilities do |t|
      t.string :facility_type
      t.integer :facility_number
      t.string :facility_name
      t.string :licensee
      t.integer :user_id
      t.integer :regional_office
      t.integer :facility_capacity
      t.string :facility_status
      t.timestamp :license_first_date
      t.timestamp :closed_date

      t.timestamps null: false
    end
  end
end
