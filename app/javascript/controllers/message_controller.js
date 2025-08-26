import { Controller } from "@hotwired/stimulus"

export default class extends Controller {
  static targets = [ "form" ]

  connect() {
    this.element.addEventListener("turbo:submit-end", (event) => this.resetForm(event))
  }

  resetForm(event) {
    if (event.target === this.formTarget && event.detail.success) {
      this.formTarget.reset()
    }
  }
}