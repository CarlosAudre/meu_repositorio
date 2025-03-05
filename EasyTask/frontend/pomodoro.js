const html = document.querySelector("html")
const focoBt = document.getElementById("foco_bt")
const curtoBt = document.getElementById("curto_bt")
const longoBt = document.getElementById("longo_bt")
const titulo = document.querySelector(".principal_titulo")
const banner = document.querySelector(".banner")
const botoes = document.querySelectorAll(".botao")
const comecarPausarBt = document.querySelector(".botao_comecar")
const temporizador = document.querySelector(".cronometro")


let tempoDecorridoEmSegundos = 1500 
let intervaloId = null


function getTempoInicial(){
    const contexto = html.getAttribute('data-contexto')
    switch(contexto){
        case 'foco': return 1500
        case 'curto': return 300
        case 'longo': return 900
    }
}


focoBt.addEventListener("click", function(){
    if(intervaloId) zerar()  //Se já houver um intervalo de contagem, ele zera esse intervalo  
    alterarContexto('foco')
    focoBt.classList.add('botao_selecionado') 
    
})

curtoBt.addEventListener("click", function(){
    if(intervaloId) zerar()
    alterarContexto('curto')
    curtoBt.classList.add('botao_selecionado')
})

longoBt.addEventListener("click", function(){
    if(intervaloId) zerar()   
    alterarContexto('longo')
    longoBt.classList.add('botao_selecionado') 
    
})

function alterarContexto(contexto){
    botoes.forEach(botao => {
        botao.classList.remove('botao_selecionado')
    });

    banner.setAttribute('src', `img/${contexto}.png`)
    switch(contexto){
        case "foco":
            titulo.innerHTML = `Organize suas ideias e <br> foque no que realmente importa.  
            <strong class="negrito"><br>Transforme cada momento em conquista!</strong>`;
            tempoDecorridoEmSegundos = 1500
            break;
        
        case "curto":
            titulo.innerHTML = `Hora de uma pausa rápida! 
            <strong class="negrito"><br>Respire fundo e recarregue suas energias.</strong>`;
            tempoDecorridoEmSegundos = 300
            break;

        case "longo":
            titulo.innerHTML = `Momento de um grande descanso! 
            <strong class="negrito"><br> Descanse bem e revitalize suas forças.</strong>`;
            tempoDecorridoEmSegundos = 900
            break;
    }
    mostrarTemporizador()
}

function contagemRegresiva(){
    if(tempoDecorridoEmSegundos <= 0){
        zerar()
        
        tempoDecorridoEmSegundos = getTempoInicial()
        mostrarTemporizador()
        return
    }
    tempoDecorridoEmSegundos--
    mostrarTemporizador()
}

comecarPausarBt.addEventListener("click", iniciarOuPausar)

function iniciarOuPausar(){
    if(intervaloId){ // Se o cronômetro está rodando, pausa sem resetar o tempo
        zerar()
        return
    }
    intervaloId = setInterval(contagemRegresiva, 1000) // Executa a cada 1 segundo | 1000ms = 1seg
    comecarPausarBt.textContent = "Pausar"
}

function zerar(){
    clearInterval(intervaloId)
    intervaloId = null
    comecarPausarBt.textContent = "Começar"
}

function mostrarTemporizador(){
    const tempo = new Date(tempoDecorridoEmSegundos * 1000)
    const tempoFormatado = tempo.toLocaleString('pt-BR', { minute: '2-digit', second: '2-digit' })
    temporizador.innerHTML = tempoFormatado
}

mostrarTemporizador()
