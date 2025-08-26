require "test_helper"

class PostsControllerTest < ActionDispatch::IntegrationTest
  include Devise::Test::IntegrationHelpers

  setup do
    @user = User.first || User.create!(email: "test@example.com", password: "password123")
    sign_in @user
    @post = @user.posts.create!(description: "Test post")
  end

  test "should get index" do
    get posts_url
    assert_response :success
  end

  test "should create post" do
    assert_difference("Post.count") do
      post posts_url, params: { post: { description: "Another post" } }
    end
    assert_redirected_to post_url(Post.last)
  end

  test "should show post" do
    get post_url(@post)
    assert_response :success
  end

  test "should update post" do
    patch post_url(@post), params: { post: { description: "Updated" } }
    assert_redirected_to post_url(@post)
    @post.reload
    assert_equal "Updated", @post.description
  end

  test "should destroy post" do
    assert_difference("Post.count", -1) do
      delete post_url(@post)
    end
    assert_redirected_to posts_url
  end
end
