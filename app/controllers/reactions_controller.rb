class ReactionsController < ApplicationController
  before_action :set_post

  def create
    @post.react!(current_user, params[:reaction_type])

    respond_to do |format|
      format.turbo_stream
      format.html { redirect_to store_post_path(@post.store, @post) }
    end
  rescue ActiveRecord::RecordInvalid
    redirect_to store_post_path(@post.store, @post), alert: "Não foi possível registrar a reação."
  end

  private

  def set_post
    @post ||= Post.find(params[:post_id])
  end
end
