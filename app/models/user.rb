class User < ApplicationRecord
  include Role

  has_one :store, foreign_key: "owner_id", class_name: "Store"
  has_one :profile, dependent: :destroy
  has_many :chats, dependent: :destroy
  has_many :messages, through: :chats
  has_many :comments, dependent: :destroy
  has_many :reactions, dependent: :destroy
  has_many :posts, through: :profile

  validates :user_type, presence: true

  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable, :trackable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :validatable
end
