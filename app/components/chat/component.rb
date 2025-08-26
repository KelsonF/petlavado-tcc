class Chat::Component < ViewComponent::Base
  include Turbo::FramesHelper

  def initialize(chat:)
    @chat = chat
  end
end
