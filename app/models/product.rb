class Product < ApplicationRecord
  belongs_to :store
  has_one_attached :image
  has_many :comments, dependent: :destroy
  has_many :reactions, dependent: :destroy

  validates :name, presence: true
  validates :price, numericality: { greater_than_or_equal_to: 0 }

  private

  def self.search(query)
    where("name LIKE ? OR description LIKE ?", "%#{query}%", "%#{query}%")
  end
end
