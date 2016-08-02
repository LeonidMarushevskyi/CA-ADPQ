class IndexFacilitiesOnLocationIsApproximate < ActiveRecord::Migration
  def change
    add_index :facilities, :location_is_approximate
  end
end
