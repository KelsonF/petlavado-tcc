class Input::Component < ViewComponent::Base
  def initialize(form: nil, label:, name:, type: "text", value: nil, placeholder: nil, required: false, disabled: false)
    @form = form
    @label = label
    @name = name
    @type = type
    @value = value
    @placeholder = placeholder
    @required = required
    @error = @form&.object&.errors&.[](@name)&.first
    @disabled = disabled
  end

  def call
    content_tag :div, class: "grid w-full max-w-sm items-center gap-1.5" do
      concat render_label
      concat render_input
      concat render_error if @error
    end
  end

  private

  def render_label
    if @form
      @form.label(@name, @label, class: label_css_classes)
    else
      label_tag(@name, @label, class: label_css_classes)
    end
  end

  def render_input
    if @form
      @form.text_field(
        @name,
        type: @type,
        placeholder: @placeholder,
        required: @required,
        disabled: @disabled,
        class: input_css_classes
      )
    else
      text_field_tag(
        @name,
        @value,
        type: @type,
        placeholder: @placeholder,
        required: @required,
        disabled: @disabled,
        class: input_css_classes
      )
    end
  end

  def render_error
    content_tag :p, @error, class: "text-sm text-destructive"
  end

  def input_css_classes
    base_classes = [
      "flex h-10 w-full items-center rounded-md",
      "bg-surface-elevated border border-border",
      "px-3 py-2 text-sm text-fg placeholder:text-fg-soft",
      "focus:outline-none focus:ring-1 focus:ring-ring",
      "disabled:cursor-not-allowed disabled:opacity-50",
      "[&>span]:line-clamp-1"
    ]

    error_classes = @error ? [ "border-destructive" ] : []

    (base_classes + error_classes).join(" ")
  end

  def label_css_classes
    "text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
  end
end
