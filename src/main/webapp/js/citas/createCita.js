document.addEventListener('DOMContentLoaded', () => {
    var selectEspecialidad = document.querySelector('#selectEspecialidad')
    var selectMedico = document.querySelector('#selectMedico')
    var fecha = document.querySelector('#fecha')
    var contenedorRadio = document.querySelector('#contenedorRadio')
    var alertHorario = document.querySelector('#alertHorario')
    alertHorario.style.display = "none"

    selectEspecialidad.addEventListener('change', () => {
        axios.get('/proyecto_limpio_spring_war_exploded/api/medicos/' + selectEspecialidad.value)
            .then(response => {
                while (selectMedico.firstChild){
                    selectMedico.removeChild(selectMedico.firstChild)
                }

                response.data.forEach(medico => {
                    var option = document.createElement('option');
                    option.text = medico.persona.apellido
                    option.value = medico.id
                    selectMedico.appendChild(option)
                })
            })
            .catch(error => {
                console.log(error)
            })
    })

    fecha.addEventListener('change', () => {
        if (selectMedico.value !== '' && fecha.value !== ''){
            alertHorario.style.display = "none"

            axios.get('/proyecto_limpio_spring_war_exploded/api/horarios/'+selectMedico.value+'/'+fecha.value)
                .then(response => {

                    while (contenedorRadio.firstChild){
                        contenedorRadio.removeChild(contenedorRadio.firstChild)
                    }

                    if (response.data.length > 0){
                        response.data.forEach(horario => {
                            var input = document.createElement("input")
                            input.type = "radio"
                            input.name = "hora"
                            input.value = horario
                            contenedorRadio.appendChild(input)

                            var label = document.createElement("label")
                            label.textContent = horario
                            contenedorRadio.appendChild(label)

                            var br = document.createElement("br")
                            contenedorRadio.appendChild(br)
                        })
                    }
                   else {
                        alertHorario.removeChild(alertHorario.firstChild)
                        alertHorario.style.display = "block"
                        var alert = document.createElement("p")
                        alert.textContent = "No hay horarios disponibles para esta fecha"
                        alertHorario.appendChild(alert)
                    }
                })
                .catch(error => {
                    console.log(error.response)
                })
        }
    })
})