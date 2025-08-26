class RemovePostAssociationFromUser < ActiveRecord::Migration[8.0]
  def change
    remove_reference :posts, :user, index: true, foreign_key: true
  end
end
