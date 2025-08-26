class ApplicationController < ActionController::Base
  include Pundit::Authorization

  before_action :authenticate_user!
  allow_browser versions: :modern

  rescue_from Pundit::NotAuthorizedError do
    redirect_to posts_path, alert: "You are not authorized to perform this action."
  end
end
