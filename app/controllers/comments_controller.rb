class CommentsController < ApplicationController
  before_action :set_product

  def create
    @comment = @product.comments.new(comment_params)
    @comment.user = current_user

    if @comment.save
      respond_to do |format|
        format.turbo_stream
        format.html { redirect_to store_product_path(@product.store, @product), notice: "Comentário adicionado!" }
      end
    else
      redirect_to store_product_path(@product.store, @product), alert: "Não foi possível adicionar o comentário."
    end
  end

  private

  def set_product
    @product = Product.find(params[:product_id])
  end

  def comment_params
    params.require(:comment).permit(:content)
  end
end
