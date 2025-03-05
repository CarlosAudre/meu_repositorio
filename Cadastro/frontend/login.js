document.getElementById("login-form").addEventListener("submit", async function(event) {
    event.preventDefault();
    const nome = document.getElementById("usuario-login").value;
    const senha = document.getElementById("senha-login").value;

    try {
        const resposta = await fetch("http://localhost:8081/api/login", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({nome, senha}),
        });

        
        if (resposta.ok) {
            const data = await resposta.json();
            alert("Login bem-sucedido!");
            window.location.href = "home.html";
        } else {
            const data = await resposta.json(); 
            document.getElementById("login-mensagem").textContent = data.message || "Erro ao fazer login";
        }
    } catch (error) {
        document.getElementById("login-mensagem").textContent = "Erro ao conectar ao servidor";
        console.error("Erro no login:", error);
    }
});
