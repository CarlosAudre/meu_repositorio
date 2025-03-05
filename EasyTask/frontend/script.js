document.addEventListener("DOMContentLoaded", async () => {
    const tarefas = await carregarTarefas(); //Pega as tarefas no banco de dados
    tarefas.forEach(tarefa => {
        criarTarefaNoDOM(tarefa.id, tarefa.anotacao, tarefa.dia, tarefa.grinfar); 
    });
});

function criarTarefaNoDOM(id, anotacao, dia, grinfar) {
    const container = document.querySelector(`.tarefa[data-dia="${dia}"]`); //Container do dia da semana
    const tamanhoOriginal = container.offsetHeight; //Tamano original do container

    let novaTarefa = document.createElement('div');
    novaTarefa.classList.add('tarefa_adicionada');

    let novoTextarea = document.createElement('textarea');
    novoTextarea.classList.add('tarefa_adicionada_texto');
    novoTextarea.setAttribute("placeholder", "Nova tarefa...");
    novoTextarea.value = anotacao;
    novoTextarea.setAttribute("data-id", id);
    novoTextarea.dataset.dia = dia;

    
    const defaultBG = "rgba(0, 0, 0, 0)"; 

    if (grinfar) {
        novoTextarea.dataset.grinfar = "true";
        
        novoTextarea.dataset.corOriginal = defaultBG;
        novoTextarea.style.backgroundColor = "rgb(4, 189, 4)";
    } else {
        novoTextarea.dataset.grinfar = "false";
        novoTextarea.dataset.corOriginal = defaultBG;
    }
    
   
    let botoesContainer = document.createElement("div");
    botoesContainer.classList.add("itens");

    let salvar = document.createElement("div");
    salvar.classList.add("salvar");
    salvar.innerHTML = `<img src="img/salvar.png" title="Salvar"  alt="">`;

    let grifar = document.createElement("div");
    grifar.classList.add("circulo_grinfar");
    grifar.setAttribute("title", "Marcar como concluido");

    let deletar = document.createElement("div");
    deletar.classList.add("deletar");
    deletar.innerHTML = `<img src="img/deletar.png" title="Deletar" alt="">`;

    salvar.addEventListener("click", () => salvarTarefa(novoTextarea));
    novoTextarea.addEventListener("blur", () => atualizarTarefa(novoTextarea));
    deletar.addEventListener("click", async () => {
        await deletarTarefa(novoTextarea, novaTarefa); //parâmetros representam respectivamente a tarefa do banco e a do dom(visual)
        setTimeout(() => atualizarAlturaContainer(container, tamanhoOriginal), 50);
    });
    
    
    grifar.addEventListener("click", async function () {
       
        let estadoAtual = novoTextarea.dataset.grinfar === "true";
        let novoEstado = !estadoAtual;
         
        novoTextarea.dataset.grinfar = novoEstado.toString();
        
       
        if (novoEstado) {
            novoTextarea.style.backgroundColor = "rgb(4, 189, 4)";
        } else {
            novoTextarea.style.backgroundColor = novoTextarea.dataset.corOriginal;
        }
        
        console.log("Novo estado (grinfar):", novoEstado);
        
        
        await atualizarTarefaGrifada(novoTextarea, novoEstado);
    });

    botoesContainer.appendChild(salvar); //appendChild coloca um elemento filho dentro de um elemento pai
    botoesContainer.appendChild(grifar);
    botoesContainer.appendChild(deletar);
    novaTarefa.appendChild(novoTextarea);
    novaTarefa.appendChild(botoesContainer);
    container.appendChild(novaTarefa);

    ajustarAlturaTextarea(novoTextarea);
    atualizarAlturaContainer(container, tamanhoOriginal);

    console.log(` Tarefa carregada com ID: ${id} no dia ${dia} (grinfar: ${grinfar})`);
}

//---------------------------------------------------------------------------------------------

document.querySelectorAll('.tarefa').forEach(container => {
    let tamanhoOriginal = container.offsetHeight;
    let dia = container.getAttribute('data-dia');

    container.querySelector('.tarefa_botoes').addEventListener('click', function () {
        let novaTarefa = document.createElement('div');
        novaTarefa.classList.add('tarefa_adicionada');

        let tarefaTempId = 'tarefa_' + crypto.randomUUID();

        let novoTextarea = document.createElement('textarea');
        novoTextarea.classList.add('tarefa_adicionada_texto');
        novoTextarea.setAttribute("placeholder", "Nova tarefa...");
        novoTextarea.setAttribute("id", tarefaTempId);
        novoTextarea.dataset.dia = dia;

        let botoesContainer = document.createElement("div");
        botoesContainer.classList.add("itens");

        let salvar = document.createElement("div");
        salvar.classList.add("salvar");
        salvar.innerHTML = `<img src="img/salvar.png" title="Salvar" alt="">`;

        let grifar = document.createElement("div");
        grifar.classList.add("circulo_grinfar");
        grifar.setAttribute("title", "Marcar como concluido");

        let deletar = document.createElement("div");
        deletar.classList.add("deletar");
        deletar.innerHTML = `<img src="img/deletar.png" title="Deletar" alt="">`;

        salvar.addEventListener("click", () => salvarTarefa(novoTextarea));
        novoTextarea.addEventListener("blur", () => atualizarTarefa(novoTextarea));
        deletar.addEventListener("click", async () => {
            await deletarTarefa(novoTextarea, novaTarefa);
            setTimeout(() => atualizarAlturaContainer(container, tamanhoOriginal), 50);
        });
        
        grifar.addEventListener("mousedown", function(e) {
           
            e.preventDefault();
        });
        
        grifar.addEventListener("click", async function () {
            
            if (!novoTextarea.dataset.corOriginal) {
                novoTextarea.dataset.corOriginal = window.getComputedStyle(novoTextarea).backgroundColor;
            }
            
            
            let estadoAtual = novoTextarea.dataset.grinfar === "true";
            let novoEstado = !estadoAtual;
            
            
            novoTextarea.dataset.grinfar = novoEstado.toString();
            
            
            if (novoEstado) {
                novoTextarea.style.backgroundColor = "rgb(4, 189, 4)";
            } else {
                novoTextarea.style.backgroundColor = novoTextarea.dataset.corOriginal || "";
            }
            
           
            await atualizarTarefaGrifada(novoTextarea, novoEstado);
        });
        
        

        botoesContainer.appendChild(salvar);
        botoesContainer.appendChild(grifar);
        botoesContainer.appendChild(deletar);
        novaTarefa.appendChild(novoTextarea);
        novaTarefa.appendChild(botoesContainer);
        container.appendChild(novaTarefa);

        novoTextarea.addEventListener('input', () => atualizarAlturaContainer(container, tamanhoOriginal));
        atualizarAlturaContainer(container, tamanhoOriginal);

        console.log(` Nova tarefa criada com ID temporário: ${tarefaTempId}`);
    });
});

function ajustarAlturaTextarea(textarea) {
    textarea.style.height = "auto";  
    textarea.style.height = textarea.scrollHeight + "px"; 
}

function atualizarAlturaContainer(container) {
    if (!container) return;
    container.style.height = "auto";
    container.style.height = container.scrollHeight + "px"; 
}


document.addEventListener("input", (event) => {
    if (event.target.classList.contains("tarefa_adicionada_texto")) {
        ajustarAlturaTextarea(event.target);
        atualizarAlturaContainer(event.target.closest(".tarefa"));
    }
});

document.addEventListener("focus", (event) => {
    if (event.target.classList.contains("tarefa_adicionada_texto")) {
        ajustarAlturaTextarea(event.target);
        atualizarAlturaContainer(event.target.closest(".tarefa"));
    }
}, true);