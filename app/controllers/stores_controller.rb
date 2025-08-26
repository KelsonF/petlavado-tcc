class StoresController < ApplicationController
  before_action :set_store, only: [ :show, :edit, :update, :destroy ]
  before_action :authorize_owner!, only: [ :edit, :update, :destroy ]

  def show
    if @store.owner == current_user
      @chats = Chat.where(store: @store).includes(:user).order(updated_at: :desc)
    end
  end

  def new
    @store = current_user.build_store
  end

  def create
    @store = current_user.build_store(store_params)

    if @store.save
      redirect_to new_store_address_path(@store), notice: "Loja criada com sucesso! Bem-vindo ao seu painel administrativo."
    else
      render :new, status: :unprocessable_entity
    end
  end

  def edit
  end

  def update
    if @store.update(store_params)
      redirect_to @store, notice: "Store was successfully updated."
    else
      render :edit, status: :unprocessable_entity
    end
  end

  def destroy
    @store.destroy
    redirect_to root_path, notice: "Store was successfully deleted."
  end

  private

  def set_store
    @store = Store.find(params[:id])
  end

  def authorize_owner!
    unless @store.owner == current_user
      redirect_to root_path, alert: "You are not authorized to perform this action."
    end
  end

  def store_params
    params.expect(store: [ :name, :description ])
  end
end
