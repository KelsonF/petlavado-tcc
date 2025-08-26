class Profile < ApplicationRecord
  belongs_to :user
  has_many :posts

  validates :bio, presence: true, length: { maximum: 100 }
end
