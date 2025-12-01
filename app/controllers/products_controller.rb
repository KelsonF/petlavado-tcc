class ProductsController < ApplicationController
  before_action :set_store, except: [ :index ]
  before_action :set_product, only: [ :show, :edit, :update, :destroy ]
  before_action :authorize_store_owner!, only: [ :new, :create, :edit, :update, :destroy ]

  def index
    @products = Product.includes(:store)
    @products = ProductFilterService.new(@products, params).call
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

  def authorize_store_owner!
    authorize @store, :manage_products?
  end

  def product_params
    params.require(:product).permit(:name, :description, :price, :image)
  end
end
