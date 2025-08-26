class Address < ApplicationRecord
  belongs_to :user

  validates :zip_code, presence: true, length: { is: 5 }
  validates :street_name, presence: true
  validates :number, presence: true
end
