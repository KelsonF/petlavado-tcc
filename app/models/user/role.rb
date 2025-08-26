module User::Role
  extend ActiveSupport::Concern

  included do
    enum(:user_type, { customer: 0, store_owner: 1 })

    def self.select_user_types
      {
        customer: "Tutor",
        store_owner: "Lojista"
      }.map { |key, value| [ value, key ] }
    end
  end
end
