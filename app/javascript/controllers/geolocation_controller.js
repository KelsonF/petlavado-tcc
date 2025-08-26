import { Controller } from "@hotwired/stimulus";

export default class extends Controller {
  connect() {
    const urlParams = new URLSearchParams(window.location.search);

    if (urlParams.has("latitude") && urlParams.has("longitude")) {
      return;
    }

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        urlParams.set("latitude", position.coords.latitude);
        urlParams.set("longitude", position.coords.longitude);
        window.location.search = urlParams.toString();
      });
    }
  }
}
