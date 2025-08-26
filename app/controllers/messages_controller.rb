class MessagesController < ApplicationController
  before_action :set_chat, only: [ :create ]

  def create
    @message = @chat.messages.create(message_params)
    @message.user = current_user
    @message.save

    render json: {}, status: :no_content
  end

  private
  def set_chat
    @chat ||= Chat.find(params[:chat_id])
  end

  def message_params
    params.require(:message).permit(:content)
  end
end
