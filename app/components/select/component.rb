class Select::Component < ViewComponent::Base
  def initialize(form:, name:, label:, options:, placeholder: nil, required: false, disabled: false)
    @form = form
    @name = name
    @label = label
    @options = options
    @placeholder = placeholder
    @required = required
    @error = @form.object.errors[@name].first
    @disabled = disabled
  end

  def call
    content_tag :div, class: "grid w-full max-w-sm items-center gap-1.5" do
      concat render_label
      concat render_select
      concat render_error if @error
    end
  end

  private

  def render_label
    @form.label(@name, @label, class: label_css_classes)
  end

  def render_select
    @form.select(
      @name,
      options_for_select(@options, @form.object.send(@name)),
      { prompt: @placeholder },
      {
        class: select_css_classes,
        required: @required,
        disabled: @disabled
      }
    )
  end

  def render_error
    content_tag :p, @error, class: "text-sm text-danger-300"
  end

  def options_for_select(options, selected_value = nil)
    options.map do |text, value|
      [ text, value ]
    end
  end

  def select_css_classes
    base_classes = [
      "flex h-10 w-full items-center rounded-md",
      "bg-surface-elevated border border-border",
      "px-3 py-2 text-sm text-fg placeholder:text-fg-soft",
      "focus:outline-none focus:ring-1 focus:ring-ring",
      "disabled:cursor-not-allowed disabled:opacity-50",
      "[&>span]:line-clamp-1"
    ]

    error_classes = @error ? [ "border-danger-400 focus:ring-danger-300" ] : []

    (base_classes + error_classes).join(" ")
  end

  def label_css_classes
    "text-sm font-medium leading-none text-fg"
  end
end
