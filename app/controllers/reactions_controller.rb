class ReactionsController < ApplicationController
  before_action :set_product

  def create
    @reaction = @product.reactions.find_or_initialize_by(user: current_user)
    @reaction.reaction_type = params[:reaction_type]

    respond_to do |format|
      if @reaction.save
        format.turbo_stream
        format.html { redirect_to store_product_path(@product.store, @product) }
      else
        format.turbo_stream
        format.html { redirect_to store_product_path(@product.store, @product), alert: "Não foi possível registrar a reação." }
      end
    end
  end

  private

  def set_product
    @product ||= Product.find(params[:product_id])
  end
end
