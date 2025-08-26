class AddUserTypeToUser < ActiveRecord::Migration[8.0]
  def change
    add_column :users, :user_type, :integer
  end
end
