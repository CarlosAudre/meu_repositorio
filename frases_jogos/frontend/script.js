import getDados from "./getDados.js";

const btnSortear = document.querySelector('.botao');
const tituloElemento = document.getElementById('titulo');
const personagemElemento = document.getElementById('personagem');
const fraseElemento = document.getElementById('frase');
const imagemElemento = document.getElementById('imagem-frase');  

function carregarInfoJogos() {
    
    getDados(`/jogos/frases`) 
        .then(data => {
            console.log(data);
            tituloElemento.innerHTML = `Jogo: <strong>${data.titulo}</strong>`;
            personagemElemento.innerHTML = `Citado por: <strong>${data.personagem}</strong>`      
            imagemElemento.src = data.post;
            fraseElemento.innerHTML = `"${data.frase}"`;

        })
        .catch(error => {
            console.error('Erro ao obter informações do jogo:', error);
        });
}

window.onload = function() {
    carregarInfoJogos();  
}; 
btnSortear.addEventListener('click', carregarInfoJogos);  