# Sistema de Reservas de Salas de Reunião

Este projeto foi desenvolvido para facilitar a reserva de salas de reunião, permitindo que os usuários organizem seus encontros de forma prática e eficiente.

## 1. Como Instalar e Rodar com Docker

Para executar o projeto com Docker, você precisará ter o Docker instalado na sua máquina. Siga os passos abaixo:

### Pré-requisitos

- [Docker](https://www.docker.com/get-started) instalado.

### Passos

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seuusuario/sistema-reservas-salas.git
   cd sistema-reservas-salas
   ```

2. **Construa a imagem Docker**:
   ```bash
   docker-compose build
   ```

3. **Inicie o contêiner**:
   ```bash
   docker-compose up
   ```

4. O sistema estará disponível em `http://localhost:8080`.

## 2. Endpoints

Aqui estão os principais endpoints que você pode utilizar:

### Reservas

- **Criar Reserva**
  - **Método:** `POST`
  - **Endpoint:** `/reservas`
  - **Request Body:**
    ```json
    {
      "salaId": "id_da_sala",
      "data": "2024-10-30",
      "horaInicio": "14:00",
      "horaFim": "16:00",
      "usuarioId": "id_do_usuario"
    }
    ```
  - **Resposta:** Retorna a reserva criada.

- **Cancelar Reserva**
  - **Método:** `DELETE`
  - **Endpoint:** `/reservas/{id}`
  - **Descrição:** Cancela a reserva com o ID especificado.

- **Buscar Reservas**
  - **Método:** `GET`
  - **Endpoint:** `/reservas`
  - **Descrição:** Retorna uma lista de todas as reservas.
  - **Parâmetros (opcionais):**
    - `data`: Filtra reservas pela data.

## 3. Componentes do Sistema

- **Modelos (`model`):** Aqui estão as entidades do sistema, como `Reserva` e `Sala`.
- **Repositórios (`repository`):** Interfaces que lidam com a persistência de dados.
- **Serviços (`service`):** Contêm a lógica de negócio, como criação e cancelamento de reservas.
- **Controladores (`controller`):** Manipulam as requisições HTTP e conectam a API com a lógica do serviço.
- **Exceções (`exception`):** Definem erros personalizados para o sistema.

## 4. Rodando os Testes

Para garantir que tudo está funcionando como deveria, você pode rodar os testes. Aqui estão os passos:

1. Navegue até o diretório do projeto.
2. Execute o seguinte comando:
   ```bash
   ./mvnw test
   ```
3. Verifique os resultados dos testes que aparecerão no console.
