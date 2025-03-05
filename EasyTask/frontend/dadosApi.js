const API_URL = "http://localhost:8081/tarefa";

async function salvarTarefa(textarea) {
    const texto = textarea.value.trim(); //O trim remove os espaços antes e depois
    const dia = textarea.dataset.dia;
    if (!texto) return; // Evita que o usuário salve uma tarefa vazia
    
    if (textarea.dataset.id) { //Se a tarefa já tiver um id, ele chama o atualizar tarefa.
        await atualizarTarefa(textarea);
        return;
    }
    
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ anotacao: texto, dia: dia }) //Transforma em json antes de enviar
        });

        if (!response.ok) throw new Error(`Erro ao salvar tarefa: ${response.statusText}`);

        const data = await response.json();
        textarea.setAttribute("data-id", data.id); //É setado o atributo data.id(dado pela API) em data-id na tarfa
    } catch (error) {
        console.error("Erro ao salvar tarefa:", error);
    }
}


async function atualizarTarefa(textarea) {
    const id = textarea.dataset.id; //pega o id
    const texto = textarea.value.trim(); //pega o texto
    const dia = textarea.dataset.dia //pega o dia
    const grinfar = textarea.dataset.grinfar === "true";  

    try {
        const response = await fetch(API_URL, {  
            method: "PUT", //Jà é entendido que a tarefa será atualizada
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ id: id, anotacao: texto, dia: dia, grinfar: grinfar })
        });

        if (!response.ok) throw new Error(`Erro ao atualizar tarefa: ${response.statusText}`);
    } catch (error) {
        console.error("Erro ao atualizar tarefa:", error);
    }
}

async function deletarTarefa(textarea, tarefaContainer)  { //parâmetros representam respectivamente a tarefa do banco e a do dom(visual)
    const id = textarea.dataset.id;
    
    tarefaContainer.remove(); // Remove a tarefa do DOM
    
    try {
        const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" /*Entende que a tarefa será deletada do banco*/ });
        if (!response.ok) throw new Error(response.statusText);
    } catch (error) {
        console.error("Erro ao deletar tarefa:", error);
    }
}

async function carregarTarefas() {
    try {
        const response = await fetch(API_URL, { method: "GET" }); //Pega a tarefa
        if (!response.ok) throw new Error("Erro ao carregar tarefas");
        return await response.json();
    } catch (error) {
        console.error("Erro ao carregar tarefas:", error);
        return [];
    }
}

async function atualizarTarefaGrifada(textarea, novoEstado) { //O parâmetro novoEstado representa o valo de grinfado, que vem quando essa função é chamada
    const id = textarea.dataset.id;
    const texto = textarea.value.trim();
    const dia = textarea.dataset.dia || textarea.closest('.tarefa').getAttribute('data-dia');
    if (!id || !texto) return;
    try {
        const response = await fetch(API_URL, { 
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ id: id, anotacao: texto, dia: dia, grinfar: novoEstado })
        });
        if (!response.ok) throw new Error(response.statusText);
    } catch (error) {
        console.error("Erro ao atualizar estado grifado:", error);
    }
}