const baseURL = 'http://localhost:8081';

export default function(endpoint){
    return fetch(`${baseURL}${endpoint}`)
        .then(response => response.json())
        .catch(error => {
            console.error('Erro ao acessar o endpoint /jogos/frases', error);
        });
}