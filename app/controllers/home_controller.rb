class HomeController < ApplicationController
  skip_before_action :authenticate_user!, only: [ :index ]

  def index
    @featured_products = Product.includes(:store).limit(8)
    @featured_stores = Store.includes(:owner).limit(6)
    @total_products = Product.count
    @total_stores = Store.count
  end
end
