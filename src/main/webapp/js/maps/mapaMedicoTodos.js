document.addEventListener('DOMContentLoaded', () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            //coordenas de usuario

            debugger;

            var latitudMedico = position.coords.latitude;
            var longitudMedico = position.coords.longitude;

            var customIcon = new L.Icon({
                iconUrl: '/proyecto_limpio_spring_war_exploded/imagenes/iconos/doctor.png',
                iconSize: [40, 50]
            });

            var markerMedico = new L.marker([latitudMedico, longitudMedico], {icon: customIcon}).bindPopup("Usted está aquí").openPopup();

            //instanciar mapa
            var mymap = L.map('mapaMedico', {
                center: [latitudMedico, longitudMedico],
                zoom: 12
            });

            L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
                maxZoom: 25,
                attribution: 'Datos del mapa de &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a>, ' + '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' + 'Imágenes © <a href="https://www.mapbox.com/">Mapbox</a>',
                id: 'mapbox/streets-v11'
            }).addTo(mymap);

            const array_waypoints = [];
            array_waypoints.push(L.latLng(latitudMedico, longitudMedico));

            var cantidadCitas = document.getElementById("cantidad").innerHTML;

            for (let i = 1; i <= cantidadCitas; i++){
                var lat = "latitud_" + i;
                var lon = "longitud_" + i;
                var latitudObtenida = document.getElementById(lat).innerHTML;
                var longuitudObtenida = document.getElementById(lon).innerHTML;

                array_waypoints.push(L.latLng(latitudObtenida, longuitudObtenida));

            }

            //agregar ruta
            L.Routing.control({
                waypoints: array_waypoints,
                language: 'es',
                createMarker: function (i, waypoint, number) {
                    if (i === 0){
                        return markerMedico;
                    }else {
                        //var indice = i + 1;
                        var lat = "latitud_" + i;
                        var lon = "longitud_" + i;
                        var latitudObtenida = document.getElementById(lat).innerHTML;
                        var longuitudObtenida = document.getElementById(lon).innerHTML;
                        var marker = new L.marker([latitudObtenida, longuitudObtenida]).bindPopup("Cita: " + i);
                        return marker;
                    }
                }
            }).addTo(mymap);

        });
    } else {
        console.log("no funca geolocalizacion")
    }
})