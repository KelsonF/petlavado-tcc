class Message < ApplicationRecord
  belongs_to :user
  belongs_to :chat
  after_create :broadcast_message

  def broadcast_message
    broadcast_append_to(
      [ chat, "messages" ],
      target: "messages",
      partial: "messages/message",
      locals: { message: self }
    )
  end
end
