class Profile < ApplicationRecord
  belongs_to :user
  has_many :posts
  has_one_attached :avatar

  validates :bio, presence: true, length: { maximum: 100 }
  validates :display_name, length: { maximum: 50 }
end
