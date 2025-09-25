class Address < ApplicationRecord
  belongs_to :user

  validates :zip_code, presence: true
  validates :street_name, presence: true
  validates :number, presence: true
  validates :city, presence: true

  geocoded_by :full_address
  after_validation :geocode, if: :will_save_change_to_street_name?

  def full_address
    [ street_name, number, city, state, zip_code ].compact.join(", ")
  end
end
