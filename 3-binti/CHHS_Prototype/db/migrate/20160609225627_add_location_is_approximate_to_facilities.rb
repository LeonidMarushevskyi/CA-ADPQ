class AddLocationIsApproximateToFacilities < ActiveRecord::Migration
  def change
    add_column :facilities, :location_is_approximate, :boolean
  end
end
