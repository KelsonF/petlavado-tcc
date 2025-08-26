class RemovesContactInfoFromStores < ActiveRecord::Migration[8.0]
  def change
    remove_column :stores, :contact_info, :string
  end
end
