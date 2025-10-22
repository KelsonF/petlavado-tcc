class StorePolicy < ApplicationPolicy
  def manage_products?
    user.present? && record.owner_id == user.id
  end
end
