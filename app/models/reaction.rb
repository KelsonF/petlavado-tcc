class Reaction < ApplicationRecord
  belongs_to :user
  belongs_to :post

  enum :reaction_type, [ :like, :dislike ]
  validates :reaction_type, presence: true
  validates :user_id, uniqueness: { scope: :post_id }
end
