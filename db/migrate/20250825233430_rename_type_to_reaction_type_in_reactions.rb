class RenameTypeToReactionTypeInReactions < ActiveRecord::Migration[8.0]
  def change
    rename_column :reactions, :type, :reaction_type
  end
end
