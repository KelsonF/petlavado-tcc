class PostsController < ApplicationController
  before_action :authenticate_user!, except: %i[index show]
  before_action :set_post, only: %i[show edit update destroy]
  before_action :authorize_post, only: %i[edit update destroy]

  def index
    @posts = Post.includes(profile: :user).order(created_at: :desc)
  end

  def show; end

  def new
    @post = current_user.profile.posts.new
  end

  def create
    @post = current_user.profile.posts.new(post_params)

    if @post.save
      redirect_to @post, notice: "Post created successfully."
    else
      render :new, status: :unprocessable_entity
    end
  end

  def edit; end

  def update
    if @post.update(post_params)
      redirect_to @post, notice: "Post updated successfully."
    else
      render :edit, status: :unprocessable_entity
    end
  end

  def destroy
    @post.destroy
    redirect_to posts_path, notice: "Post deleted successfully."
  end

  private

  def set_post
    @post = Post.find(params[:id])
  end

  def authorize_post
    authorize @post
  end

  def post_params
    params.require(:post).permit(:description, :image)
  end
end
