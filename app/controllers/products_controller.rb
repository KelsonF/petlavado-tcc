class ProductsController < ApplicationController
  before_action :set_store, except: [ :index ]
  before_action :set_product, only: [ :show, :edit, :update, :destroy ]
  before_action :authorize_store_owner!, only: [ :new, :create, :edit, :update, :destroy ]

  def index
    @products = Product.includes(:store)
    @products = filter_products(@products)

    if params[:latitude].present? && params[:longitude].present?
      user_location = [ params[:latitude].to_f, params[:longitude].to_f ]
      @products = @products.sort_by { |product| distance_to_store(product.store, user_location) }
    end

    @products = sort_products(@products)
  end

  def show; end

  def new
    @product = @store.products.new
  end

  def create
    @product = @store.products.new(product_params)

    if @product.save
      redirect_to [ @store, @product ], notice: "Produto criado com sucesso."
    else
      render :new
    end
  end

  def edit; end

  def update
    if @product.update(product_params)
      redirect_to [ @store, @product ], notice: "Produto atualizado com sucesso."
    else
      render :edit
    end
  end

  def destroy
    @product.destroy
    redirect_to store_products_path(@store), notice: "Produto removido com sucesso."
  end

  private

  def set_store
    @store = Store.find(params[:store_id])
  end

  def set_product
    @product = @store.products.find(params[:id])
  end

  def filter_products(products)
    return products unless params[:search].present?

    search_term = "%#{params[:search]}%"
    products.where("name LIKE :search OR description LIKE :search", search: search_term)
  end

  def sort_products(products)
    return products unless params[:sort].present?

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

  def authorize_store_owner!
    authorize @store, :manage_products?
  end

  def product_params
    params.require(:product).permit(:name, :description, :price, :image)
  end

  def distance_to_store(store, user_location)
    store_location = [ store.address.latitude, store.address.longitude ]
    Geocoder::Calculations.distance_between(user_location, store_location)
  end
end
