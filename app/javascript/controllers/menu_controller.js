import { Controller } from "@hotwired/stimulus"

export default class extends Controller {
  static targets = ["dropdown"]

  toggle() {
    this.dropdownTarget.classList.toggle("hidden")
  }

  hide(event) {
    if (!this.element.contains(event.target)) {
      this.dropdownTarget.classList.add("hidden")
    }
  }

  connect() {
    document.addEventListener("click", this.hide.bind(this))
  }

  disconnect() {
    document.removeEventListener("click", this.hide.bind(this))
  }
}
