class AddReactionsToPosts < ActiveRecord::Migration[8.0]
  def change
    remove_foreign_key :reactions, :products
    remove_index :reactions, :product_id

    remove_column :reactions, :product_id, :integer

    add_reference :reactions, :post, null: false, foreign_key: true
  end
end
