module ChatHelper
  def chat_other_party_name(chat, current_user)
    current_user == chat.user ? chat.store.name : chat.user.email
  end
end
