# MS Order e History

<h2> Informações:</h2>

Projeto de microserviço de requisição de pedidos, as extensões e tecnologias utilizadas foram:
<ul>
  <li>Spring Boot</li>
  <li>Model Mapper</li>
  <li>ViaCEP</li>
  <li>Lombok</li>
  <li>RabbitMQ</li>
  <li>MySQL</li>
  <li>Flyway</li>
  <li>MongoDB</li>
  <li>Swagger</li>
</ul>

<h2>Order:</h2>

<h3>Order</h3>

<h4>Parametros</h4>

<ul>
  <li>O CPF é composto por 11 numeros</li>
  <li>Item é uma lista, e cada um deve possuir:</li>
  <li>Uma string com o nome</li>
  <li>Data de expiração, no formato ano-mes-dia</li>
  <li>Preço no formato 0.0</li>
  <li>Uma string com a descrição</li>
  <li>Address é composto de:</li>
  <li>O numero do endereço</li>
  <li>O CEP, composto por 8 numeros</li>
</ul>

<h4>Formato da Requisição em Json: </h4>

    {
    "cpf": "00000000000",
    "item": [
        {
        "name": "String",
        "expirationDate": "AAAA-MM-DD",
        "price": 000.00,
        "description": "String"
        },
        {
        "name": "String",
        "expirationDate": "AAAA-MM-DD",
        "price": 000.00,
        "description": "String"
        }
        ],
    "address": {
        "numero": 000,
        "cep": "00000000"
        }
    }

<h4>Formato da Resposta em Json:</h4>

    {
    "id": 0,
    "cpf": "00000000000",
    "total": 000.00,
    "item": [
        {
        "id": 0,
        "name": "String",
        "creationDate": "AAAA-MM-DD",
        "expirationDate": "AAAA-MM-DD",
        "price": 000.00,
        "description": "String"
        },
        {
        "id": 0,
        "name": "String",
        "creationDate": "AAAA-MM-DD",
        "expirationDate": "AAAA-MM-DD",
        "price": 000.00,
        "description": "String"
        }
    ],
    "address": {
        "id": 0,
        "logradouro": "String",
        "numero": 00,
        "bairro": "String",
        "localidade": "String",
        "uf": "UF",
        "cep": "00000-000"
        }
    }

<h4>Formato de envio para o MS History</h4>

    {
    "id": 0
    "total": 000.00
    }

<h3>Endpoints:</h3>

<h4>POST</h4>
"Através desse endpoint você poderá cadastrar novas Orders. Após a criação retornará um 201 e enviará via RabbitMQ para o MS History uma requisição. Se algum campo obrigatório for violado, retornará 400 com uma mensagem informando o campo e o erro, se o parametro estiver errado, retornará um 500."

<h4>GET</h4>
"Através desse endpoint você poderá listar todos os Orders. Existem filtros para o retorno da requisição, utilizando os Params. Ex: Campo 'sort' para asc ou desc (valor Total). Ex: Listar por CPF."

<h4>GET/{id}</h4>

"Através desse endpoint você poderá consultar Orders por id. Se não existir, retornará 404."

<h4>PUT/{id}</h4>

"Através desse endpoint você poderá atualizar uma Order. Se algum campo obrigatório for violado, retornará 400 com uma mensagem informando o campo e o erro, se o ID informado não existir, retornará 404."

<h4>DELETE/{id}</h4>

"Através desse endpoint você poderá excluir uma Company. Se o ID informado não existir, retornará 404."

<h3>Item</h3>

<h3>Endpoint:</h3>

<h4>PATCH/{id}/{name}</h4>

"Através desse endpoint você poderá atualizar o nome do item pelo seu id. Se não existir, retornará 404."

<h2>History:</h2>

<h3>History</h3>

<h4>Parametros</h4>

<ul>
  <li>O ID da Order é recebido via RabbitMQ</li>
  <li>O valor total é recebido via RabbitMQ</li>
  <li>A data de criação da History é criada no momento da recepção da mensagem</li>
</ul>

<h4>Formato de recepção de MS Order</h4>

    {
    "id": 63d3e5890f63d6a2e34e751c
    "orderId": 0
    "total": 000.00
    "historyDate": AAAA-MM-DD
    }

<h3>Endpoint:</h3>

<h4>GET</h4>

"Através desse endpoint você poderá listar todos os Histories. Existem filtros para o retorno da requisição, utilizando os Params. Ex: Listar por historyDate."
