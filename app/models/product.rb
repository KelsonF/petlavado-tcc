class Product < ApplicationRecord
  belongs_to :store

  has_one_attached :image

  validates :name, presence: true
  validates :price, numericality: { greater_than_or_equal_to: 0 }

  private

  scope :search, ->(query) { where("name LIKE ? OR description LIKE ?", "%#{query}%", "%#{query}%") }
end
