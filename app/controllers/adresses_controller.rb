class AddressesController < ApplicationController
  before_action :set_store, only: [ :new, :create, :edit, :update ]
  before_action :authorize_store_owner!, only: [ :new, :create, :edit, :update ]

  def new
    @address = @store.build_address
  end

  def create
    @address = @store.build_address(address_params)

    if @address.save
      redirect_to @store, notice: "Endereço criado com sucesso!"
    else
      render :new, status: :unprocessable_entity
    end
  end

  def edit
    @address = @store.address
  end

  def update
    @address = @store.address

    if @address.update(address_params)
      redirect_to @store, notice: "Endereço atualizado com sucesso!"
    else
      render :edit, status: :unprocessable_entity
    end
  end

  private

  def set_store
    @store = Store.find(params[:store_id])
  end

  def authorize_store_owner!
    unless @store.owner == current_user
      redirect_to root_path, alert: "Você não tem autorização para esta ação."
    end
  end

  def address_params
    params.require(:address).permit(:zip_code, :street_name, :number, :city, :state)
  end
end
