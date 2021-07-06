document.addEventListener('DOMContentLoaded', () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            //coordenas de usuario

            console.log("OK geolocalizacion");

            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;

            document.getElementById("latitud").setAttribute("value", latitude);
            document.getElementById("longitud").setAttribute("value", longitude);

        });
    } else {
        console.log("Error geolocalizacion")
    }
})