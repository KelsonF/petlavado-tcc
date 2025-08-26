class AddCoordinatesToAddress < ActiveRecord::Migration[8.0]
  def change
    add_column :addresses, :latitude, :float
    add_column :addresses, :longitude, :float
    add_index :addresses, [ :latitude, :longitude ]
  end
end
