class User < ApplicationRecord
  has_one :store, foreign_key: "owner_id", class_name: "Store"
  has_many :addresses, dependent: :destroy
  has_many :chats, dependent: :destroy
  has_many :messages, through: :chats
  has_many :comments, dependent: :destroy
  has_many :reactions, dependent: :destroy

  enum(:user_type, { customer: 0, store_owner: 1 })
  validates :user_type, presence: true

  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable, :trackable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :validatable
end
