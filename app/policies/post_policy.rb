class PostPolicy < ApplicationPolicy
  def edit?
    owner?
  end

  def update?
    owner?
  end

  def destroy?
    owner?
  end

  private

  def owner?
    record.profile.user == user
  end
end
