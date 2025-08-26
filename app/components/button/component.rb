class Button::Component < ViewComponent::Base
  def initialize(label:, url:, method: :get, **options)
    @label = label
    @url = url
    @method = method
    @options = options
  end

  private

  def button_classes
    "w-full bg-cyan-600 text-white font-bold py-2 px-4 rounded hover:bg-navy-700 transition cursor-pointer"
  end
end
