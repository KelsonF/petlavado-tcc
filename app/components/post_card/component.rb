# frozen_string_literal: true

class PostCard::Component < ViewComponent::Base
  include Turbo::FramesHelper

  attr_reader :post

  def initialize(post:)
    @post = post
  end

  def user_signed_in?
    helpers.user_signed_in?
  end
end
