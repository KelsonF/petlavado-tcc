class MessagesController < ApplicationController
  before_action :set_chat, only: [ :create ]

  def create
    @message = @chat.messages.create(message_params)
    @message.user = current_user

    if @message.save
      @message.broadcast_append_to(
        [ @chat, "messages" ],
        target: "messages",
        partial: "messages/message",
        locals: { user: current_user, message: @message }
      )
    end

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
