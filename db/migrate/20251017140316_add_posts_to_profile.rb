class AddPostsToProfile < ActiveRecord::Migration[8.0]
  def change
    add_reference :profiles, :posts, null: false, foreign_key: true
  end
end
