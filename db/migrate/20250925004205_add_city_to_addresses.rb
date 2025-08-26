class AddCityToAddresses < ActiveRecord::Migration[8.0]
  def change
    add_column :addresses, :city, :string, null: false
  end
end
