class FixProfilesAndPostsRelations < ActiveRecord::Migration[8.0]
  def change
    if column_exists?(:profiles, :posts_id)
      remove_reference :profiles, :posts, index: true, foreign_key: false
    end

    unless column_exists?(:posts, :profile_id)
      add_reference :posts, :profile, null: false, foreign_key: true
    end
  end
end
