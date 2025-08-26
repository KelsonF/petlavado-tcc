class Store < ApplicationRecord
  belongs_to :owner, class_name: "User"
  has_many :products, dependent: :destroy
  has_many :chats, dependent: :destroy
  has_many :messages, through: :chats

  validates :name, presence: true
  validates :description, presence: true
  validates :contact_info, presence: true
end
