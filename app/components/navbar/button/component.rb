class Navbar::Button::Component < ViewComponent::Base
  attr_reader :text, :url, :theme

  COMPONENT_THEMES = %i[ default danger ].freeze

  def initialize(text:, url:, theme:)
    raise ArgumentError, "Invalid theme: #{theme}" unless COMPONENT_THEMES.include?(theme)

    @text = text
    @url = url
    @theme = theme
  end

  def button_classes
    case theme
    when :default
      "btn-secondary rounded-lg hover:bg-neutral-200 hover:text-neutral-800 transition-colors duration-200 px-4 py-2"
    when :danger
      "bg-red-600 hover:bg-red-700 text-white font-medium py-2 px-4 rounded-lg transition-colors duration-200"
    end
  end
end
