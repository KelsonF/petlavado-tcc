class RemoveProductAssociationFromComments < ActiveRecord::Migration[8.0]
  def change
    remove_reference :comments, :product, index: true, foreign_key: true
  end
end
