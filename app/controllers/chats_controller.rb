class ChatsController < ApplicationController
  before_action :set_chat, only: [ :show ]
  before_action :authorize_chat_access, only: [ :show ]

  def show
  end

  def create
    @chat = Chat.find_by(user: current_user, store_id: chat_params[:store_id])
    @current_user = current_user

    unless @chat
      @chat = Chat.new(chat_params)
      @chat.user = @current_user
      @chat.save
    end

    redirect_to @chat, notice: "Chat iniciado com sucesso."
  end

  private

  def set_chat
    @chat = Chat.find(params[:id])
  end

  def authorize_chat_access
    unless @chat.user == current_user || @chat.store.owner == current_user
      redirect_to root_path, alert: "Acesso não autorizado."
    end
  end

  def chat_params
    params.require(:chat).permit(:message, :store_id)
  end
end
