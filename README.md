
# Projeto Java com Spring Boot e React

Este projeto é composto por duas partes principais:

1. **Backend**: Desenvolvido em Java utilizando Spring Boot e Spring Security.
2. **Frontend**: Um microfrontend desenvolvido em ReactJS localizado na pasta `react-google-auth`.

## Pré-requisitos

- **Java 17+**
- **Maven 3.6+**
- **Node.js 14+** com **Yarn**
- **Google Client ID**: É necessário para autenticação via Google.

## Configuração do Backend

1. **Variável de Ambiente:**

   Antes de rodar a aplicação, você deve definir a variável de ambiente `GOOGLE_CLIENT_ID` com o valor do seu Google Client ID.

   ```bash
   export GOOGLE_CLIENT_ID="seu-google-client-id"
   ```

2. **Gerar Chaves RSA:**

   O projeto utiliza Spring Security com autenticação JWT. Para isso, você precisa gerar uma chave privada (RSAPrivateKey) e uma chave pública (RSAPublicKey). Salve os arquivos gerados em `src/main/resources/config` com os nomes `private.pem` e `public.pem`, respectivamente.

   Para gerar as chaves, você pode utilizar o comando OpenSSL:

   ```bash
   openssl genpkey -algorithm RSA -out src/main/resources/config/private.pem -pkeyopt rsa_keygen_bits:2048
   openssl rsa -pubout -in src/main/resources/config/private.pem -out src/main/resources/config/public.pem
   ```

3. **Rodar o Projeto Backend:**

   Após configurar a variável de ambiente e gerar as chaves RSA, você pode rodar o projeto utilizando o Maven:

   ```bash
   mvn spring-boot:run
   ```

   O projeto estará disponível em `http://localhost:8080`.

## Configuração do Frontend

1. **Variável de Ambiente:**

   Dentro da pasta `react-google-auth`, crie um arquivo `.env` com a variável de ambiente `REACT_APP_GOOGLE_CLIENT_ID`, utilizando o mesmo Google Client ID configurado no backend:

   ```
   REACT_APP_GOOGLE_CLIENT_ID=seu-google-client-id
   ```

2. **Instalar Dependências:**

   Navegue até a pasta `react-google-auth` e instale as dependências utilizando o Yarn:

   ```bash
   cd react-google-auth
   yarn install
   ```

3. **Rodar o Projeto Frontend:**

   Após instalar as dependências, você pode iniciar o servidor de desenvolvimento do frontend:

   ```bash
   yarn start
   ```

   O frontend estará disponível em `http://localhost:3000`.

## Executando o Projeto

1. **Inicie o backend** utilizando o Maven conforme descrito na seção "Configuração do Backend".
2. **Inicie o frontend** conforme descrito na seção "Configuração do Frontend".
3. Acesse o frontend em `http://localhost:3000` e o backend em `http://localhost:8080`.

## Estrutura do Projeto

- `src/main/java`: Contém o código-fonte do backend.
- `src/main/resources/config`: Contém as chaves RSA para autenticação JWT.
- `react-google-auth`: Contém o código-fonte do frontend.

## Considerações Finais

Certifique-se de ter configurado corretamente as variáveis de ambiente e as chaves RSA antes de rodar o projeto. Em caso de dúvidas, consulte a documentação do Spring Boot e ReactJS.
