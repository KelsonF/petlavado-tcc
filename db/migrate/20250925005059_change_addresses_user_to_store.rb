class ChangeAddressesUserToStore < ActiveRecord::Migration[8.0]
  def change
    # Remove the foreign key constraint first
    remove_foreign_key :addresses, :users
    remove_index :addresses, :user_id

    # Remove the user_id column
    remove_column :addresses, :user_id, :integer

    # Add store_id column
    add_reference :addresses, :store, null: false, foreign_key: true
  end
end
