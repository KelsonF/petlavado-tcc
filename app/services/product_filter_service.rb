class ProductFilterService
  def initialize(scope, params)
    @scope = scope
    @params = params
  end

  def call
    products = filter_by_search(@scope)
    products = sort_by_location(products) if location_params_present?
    sort_products(products)
  end

  private

  attr_reader :scope, :params

  def filter_by_search(products)
    return products unless params[:search].present?

    search_term = "%#{params[:search]}%"
    products.where("name LIKE :search OR description LIKE :search", search: search_term)
  end

  def location_params_present?
    params[:latitude].present? && params[:longitude].present?
  end

  def sort_by_location(products)
    user_location = [ params[:latitude].to_f, params[:longitude].to_f ]
    # Note: This returns an Array, not an ActiveRecord::Relation
    products.sort_by { |product| distance_to_store(product.store, user_location) }
  end

  def distance_to_store(store, user_location)
    store_location = [ store.address.latitude, store.address.longitude ]
    Geocoder::Calculations.distance_between(user_location, store_location)
  end

  def sort_products(products)
    # If products is already an Array (from sort_by_location), we use Ruby's sort_by
    # If it's a Relation, we use ActiveRecord's order
    
    return products unless params[:sort].present?

    if products.is_a?(Array)
      sort_array(products)
    else
      sort_relation(products)
    end
  end

  def sort_relation(products)
    case params[:sort]
    when "price_asc"
      products.order(:price)
    when "price_desc"
      products.order(price: :desc)
    when "name"
      products.order(:name)
    else
      products.order(created_at: :desc)
    end
  end

  def sort_array(products)
    case params[:sort]
    when "price_asc"
      products.sort_by(&:price)
    when "price_desc"
      products.sort_by(&:price).reverse
    when "name"
      products.sort_by(&:name)
    else
      products.sort_by(&:created_at).reverse
    end
  end
end
