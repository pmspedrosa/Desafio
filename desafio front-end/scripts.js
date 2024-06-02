document.addEventListener("DOMContentLoaded", async function() {
    const formCandidato = document.getElementById("form-candidato");
    const tabelaCandidatos = document.getElementById("tabela-candidatos").getElementsByTagName("tbody")[0];
    const popupEditar = document.getElementById("popup-editar");
    const formEditarCandidato = document.getElementById("form-editar-candidato");
    let editingCandidateId = null;

    formCandidato.addEventListener("submit", function(event) {
        event.preventDefault();
        adicionarCandidato();
    });


    async function carregarCandidatos() {
        try {
            const response = await fetch("http://localhost:8080/candidatos");
            const candidatos = await response.json();
            tabelaCandidatos.innerHTML = "";
            candidatos.forEach(candidato => {
                const row = tabelaCandidatos.insertRow();
                row.insertCell(0).innerText = candidato.id;
                row.insertCell(1).innerText = candidato.nome;
                row.insertCell(2).innerText = candidato.contacto;
                row.insertCell(3).innerText = candidato.idade;
                row.insertCell(4).innerText = candidato.morada;
                row.insertCell(5).innerText = candidato.profissao.descricao;
                const actionsCell = row.insertCell(6);
                const editButton = document.createElement("button");
                editButton.innerText = "Editar";
                editButton.onclick = () => editarCandidato(candidato);
                actionsCell.appendChild(editButton);
                const deleteButton = document.createElement("button");
                deleteButton.innerText = "Eliminar";
                deleteButton.onclick = () => eliminarCandidato(candidato.id);
                actionsCell.appendChild(deleteButton);
            });
        } catch (error) {
            showPopupMessage("Erro ao carregar candidatos: " + error.message);
            console.error("Erro ao carregar candidatos", error);
        }
    }

    async function adicionarCandidato() {
        // Obter a profissão selecionada
        const profissaoSelect = document.getElementById("profissao");
        const profissaoId = profissaoSelect.value;
        const profissaoDescricao = profissaoSelect.options[profissaoSelect.selectedIndex].text;
    
        // Cria um objeto candidato com os inputs do formulário
        const candidato = {
            nome: document.getElementById("nome").value,
            contacto: document.getElementById("contacto").value,
            idade: document.getElementById("idade").value,
            morada: document.getElementById("morada").value,
            profissao: {
                id: profissaoId,
                descricao: profissaoDescricao
            }
        };
        console.log(candidato);
        // Envia o objeto candidato para o servidor num pedido POST
        try {
            const response = await fetch("http://localhost:8080/candidatos", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify([candidato])  // Enviar como array
            });
    
            // Se o pedido foi bem sucedido, recarrega a lista de candidatos e limpa o formulário
            if (response.ok) {
                carregarCandidatos();  // Recarrega a lista de candidatos
                document.getElementById("form-candidato").reset();  // Reset ao form
                showPopupMessage("Candidato adicionado com sucesso!", "success");
            } else {
                const errorText = await response.text();
                showPopupMessage("Erro ao adicionar candidatos: " + errorText);
                console.error("Erro ao adicionar candidato", response);
            }
        } catch (error) {
            showPopupMessage("Erro ao adicionar candidatos: " + error.message);
            console.error("Erro ao adicionar candidato", error);
        }
    }
    
    async function carregarProfissoes(selectId) {
        try {
            const response = await fetch("http://localhost:8080/profissoes");
            const profissoes = await response.json();
            const profissaoSelect = document.getElementById(selectId || "profissao");
            profissoes.forEach(profissao => {
                const option = document.createElement("option");
                option.value = profissao.id;
                option.textContent = profissao.descricao;
                profissaoSelect.appendChild(option);
            });
        } catch (error) {
            showPopupMessage("Erro ao carregar profissões: " + error.message);
            console.error("Erro ao carregar profissões", error);
        }
    }

    function fecharPopup() {
        popupEditar.style.display = "none";
    }

    //evento click do botão fechar
    document.getElementById("fechar").addEventListener("click", function() {
        fecharPopup();
    });

    formEditarCandidato.addEventListener("submit", function(event) {
        event.preventDefault();
        salvarEdicaoCandidato();
    });

    async function editarCandidato(candidato) {
        editingCandidateId = candidato.id;
        document.getElementById("editar-nome").value = candidato.nome;
        document.getElementById("editar-contacto").value = candidato.contacto;
        document.getElementById("editar-idade").value = candidato.idade;
        document.getElementById("editar-morada").value = candidato.morada;
        // Preencher o dropdown de profissões
        await carregarProfissoes("editar-profissao");

        // Definir a profissão atual do candidato como selecionada no dropdown
        const profissaoSelect = document.getElementById("editar-profissao");
        profissaoSelect.value = candidato.profissao.id;
        // Trigger change event para atualizar o dropdown
        const changeEvent = new Event("change");
        profissaoSelect.dispatchEvent(changeEvent);
        
        popupEditar.style.display = "block";
    }

    async function salvarEdicaoCandidato() { 
        const candidato = {
            nome: document.getElementById("editar-nome").value,
            contacto: document.getElementById("editar-contacto").value,
            idade: document.getElementById("editar-idade").value,
            morada: document.getElementById("editar-morada").value,
            profissao: {
                id: document.getElementById("editar-profissao").value,
                descricao: document.getElementById("editar-profissao").options[document.getElementById("editar-profissao").selectedIndex].text
            }
        };
    
        console.log(candidato);
        try {
            const response = await fetch(`http://localhost:8080/candidatos/${editingCandidateId}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(candidato)
            });
    
            if (response.ok) { 
                carregarCandidatos();
                fecharPopup();
                showPopupMessage("Candidato editado com sucesso!", "success");
            } else {
                const errorText = await response.text();
                showPopupMessage("Erro ao editar candidato: " + errorText);
                console.error("Erro ao editar candidato", response.statusText);
            }
        } catch (error) {
            showPopupMessage("Erro ao editar candidato: " + error.message);
            console.error("Erro", error);
        }
    }
    

    async function eliminarCandidato(id) {
        try {
            await fetch(`http://localhost:8080/candidatos/${id}`, { method: "DELETE" });
            carregarCandidatos();
            showPopupMessage("Candidato eliminado com sucesso!", "success");
        } catch (error) {
            showPopupMessage("Erro ao eliminar candidato: " + error.message);
        }
    }

    function showPopupMessage(message, type = "error") {
        const popup = document.getElementById("popup-message");
        popup.innerText = message;
        
        if (type === "success") {
            popup.style.backgroundColor = "#4CAF50"; // Background verde para sucesso
        }
        else {
            popup.style.backgroundColor = "#f44336"; // Background vermelho para erro
        }
    
        popup.style.display = "block";
    
        setTimeout(() => {
            popup.style.display = "none";
        }, 2500); // Mostrar o popup durante 2.5 segundos
    }
    

    await carregarProfissoes(); // Carregar profissões quando a página é carregada
    await carregarCandidatos(); // Carregar candidatos quando a página é carregada
});
