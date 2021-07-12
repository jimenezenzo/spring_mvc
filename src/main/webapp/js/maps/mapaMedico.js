document.addEventListener('DOMContentLoaded', () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            //coordenas de usuario

            //var latitudObtenida = document.getElementById("latitud").innerHTML;
            //var longuitudObtenida = document.getElementById("longitud").innerHTML;

            var latitudMedico = position.coords.latitude;
            var longitudMedico = position.coords.longitude;

            var listaDePosiciones = document.getElementsByClassName("listaCoordenadas");

            var mymap = L.map('mapaMedico', {
                center: [listaDePosiciones[0].innerHTML , listaDePosiciones[1].innerHTML],
                zoom: 12
            });

            for(i=0;i<listaDePosiciones.length;i++){
                var j = i;

                latitudObtenida = listaDePosiciones[j].innerHTML;
                j++
                longuitudObtenida =listaDePosiciones[j].innerHTML;

                L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
                    maxZoom: 25,
                    attribution: 'Datos del mapa de &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a>, ' + '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' + 'Imágenes © <a href="https://www.mapbox.com/">Mapbox</a>',
                    id: 'mapbox/streets-v11'
                }).addTo(mymap);
                L.Routing.control({
                    waypoints: [
                        L.latLng(latitudObtenida, longuitudObtenida), //coordenadas del paciente
                        L.latLng(latitudMedico, longitudMedico) //coordenadas del consultorio
                    ],
                    language: 'es'
                }).addTo(mymap);
                j++
            }
        });
    } else {
        console.log("no funca geolocalizacion")
    }
})