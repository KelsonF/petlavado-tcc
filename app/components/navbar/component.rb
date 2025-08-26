# frozen_string_literal: true

class Navbar::Component < ViewComponent::Base
  def initialize(user:)
    @user = user
  end

  def user_signed_in?
    helpers.user_signed_in?
  end

  def current_user
    helpers.current_user
  end

  def new_store_path
    helpers.new_store_path
  end
end
