class Reaction < ApplicationRecord
  belongs_to :user
  belongs_to :product

  enum :reaction_type, [ :like, :dislike ]
  validates :reaction_type, presence: true
end
