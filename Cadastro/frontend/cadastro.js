document.getElementById("registrar-form").addEventListener("submit", async function(event) {
    event.preventDefault();

    const nome = document.getElementById("usuario-cadastro").value;
    const senha = document.getElementById("senha-cadastro").value;

    if (nome.length < 5 || nome.includes(" ")) {
        document.getElementById("cadastro-mensagem").textContent = "O nome deve ter pelo menos 5 caracteres e não pode conter espaços.";
        return;
    }

    if (senha.length < 8 || senha.includes(" ")) {
        document.getElementById("cadastro-mensagem").textContent = "A senha deve ter pelo menos 8 caracteres e não pode conter espaços."
        return;
    }

    try {
        const resposta = await fetch("http://localhost:8081/api/registrar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({nome, senha}), 
        });

        
        const data = await resposta.json();

        if (resposta.ok) {
            alert(data.message); 
            window.location.href = "login.html";
        } else {
            alert("Erro no cadastro: " + (data.message || "Tente novamente"));
        }
        

    } catch (error) {
        document.getElementById("cadastro-mensagem").textContent = "Erro ao conectar ao servidor";
        console.error("Erro no cadastro:", error);
    }
});
