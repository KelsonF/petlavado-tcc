module MessagesHelper
  def message_classes(message, current_user)
    message.user == current_user ? "mb-2 flex justify-end" : "mb-2 flex justify-start"
  end

  def message_bubble_classes(message, current_user)
    message.user == current_user ? "px-4 py-2 rounded-lg bg-primary-600 text-white" : "px-4 py-2 rounded-lg bg-surface-elevated text-fg"
  end
end
