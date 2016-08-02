class IndexFacilitiesOnStatus < ActiveRecord::Migration
  def change
    add_index :facilities, :status
  end
end
