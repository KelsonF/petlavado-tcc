# frozen_string_literal: true

class ProductCard::Component < ViewComponent::Base
  include ActionView::Helpers::NumberHelper

  attr_reader :product

  def initialize(product:)
    @product = product
  end
end
