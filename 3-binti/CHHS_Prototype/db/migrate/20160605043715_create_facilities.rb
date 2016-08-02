class CreateFacilities < ActiveRecord::Migration
  def change
    create_table :facilities do |t|
      t.json :raw_location_data
      t.integer :facility_type
      t.string :address
      t.string :city
      t.string :phone_number
      t.string :name
      t.string :administrator
      t.string :status
      t.integer :facility_number
      t.string :zipcode
      t.string :county
      t.st_point :location, geographic: true
    end

    add_index :facilities, :facility_number
    add_index :facilities, :facility_type
    add_index :facilities, :location, using: :gist
  end
end
