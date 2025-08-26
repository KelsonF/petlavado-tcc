class Post < ApplicationRecord
  has_one_attached :image
  has_many :comments, dependent: :destroy
  has_many :reactions, dependent: :destroy
  belongs_to :user

  def react!(user, reaction_type)
    nil unless Reaction.reaction_types.key?(reaction_type)

    reaction = reactions.find_or_initialize_by(user: user)
    reaction.update!(reaction_type: reaction_type)
  end
end
