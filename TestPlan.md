# Plano de teste

## 1. Introdução
Este arquivo mostra o planetamento das atividades de testes das APIs do 
Sicred. Com os testes faremos acompanhamento, revisão, verificação e 
validação do funcionamento das requisições a fim de garantir os retornos
obtidos bem como o body e/ou status code. Caso haja ocorrências (bugs),
ações corretivas e preventivas poderão ser tomadas.

### 1.2. A aplicação
A aplicação se trata de software que é responsável por gerenciar produtos eletrônicos.
Nela é possível fazer buscas de usuário e produtos, além de ter autenticação de usuário.


### 1.3. Escopo
A API passará por testes funcionais. Esses testes visam validar
a qualidade funcional das APIs, tanto para testar a funcionalidade isolada
quanto para validar um "happy path".

## 2. Análise dos requisitos
A lista abaixo identifica aqueles itens - use cases e requisitos funcionais -
que foram identificados como alvos de teste. Essa lista representa o que
será testado.

### 2.1. Teste funcional
* Verifique se a aplicação está funcionando normalmente.
* Verifique se qualquer usuário pode autenticar o login (análise de status code).
* Verifique se com um usuário correto o login é autenticado (análise de status code).
* Verifique se o retorno da API de busca de user está como o esperado.
* Verifique se o retorno da API de busca de products está como o esperado.
* Verifique se o retorno da API de busca de products que requer autenticação está como o esperado.
* Verifique se o retorno da API de busca de products que requer autenticação retorna o status corretamente quando há algum problema na autenticação.
* Verifique se o retorno da API de busca de products que requer autenticação retorna o status corretamente quando o token é inválido.
* Verifique se o retorno da API de busca de product por ID funciona corretamente.
* Verifique se o retorno da API de busca de product por ID retorna o status corretamente quando digita um ID não existente.
* Verifique se a API de adição de um produto funciona como o esperado.

### 2.2. Perfil da perfomance
* Verifique o tempo de resposta dos requests.
* Verifique o tempo de consulta/atualização.

## 3. Estratégia de teste
### 3.1. Tipo de teste
### 3.1.1 Teste funcional
**Objetivo**: Garantir a funcionalidade apropriada do alvo do teste, incluindo processamento,
tempo de resposta, status code e retorno do body (se houver). 

**Técnica**: Executar cada cenário, cada fluxo, usando dados válidos e inválidos,
para verificar o seguinte:
* Resultados esperados com dados válidos.
* Mensagem de feedback apropriada quando dados inválidos são usados.
* Tempo de resposta apropriado para requests.

**Critério de finalização**: 
* Todos os testes planejados foram executados.
* Todos os bugs foram identificados (se houver).

### 3.2 Ferramentas
As seguintes ferramentas serão empregadas para esse projeto:

**Gerenciamento do teste**: GitLab, Word e IntelliJ IDEA.

## 4. Recursos
### 4.1 Equipe
* **Gerente do teste**: Jean Anjos
* **Testador**: Jean Anjos
* **Administrador do teste**: Jean Anjos
* **Desenvolvedor da automação**: Jean Anjos

### 4.2 Sistema
* **Repositório de testes**: 1 notebook de desenvolvimento de teste

### 5. Cronograma
* **Planejar Teste**: 09/02/2024 - 10/02/2024
* **Implementar Teste**: 11/02/2024 - 13/02/2024
* **Executar Teste**: 13/02/2024 - 13/02/2024
* **Avaliar Teste**: 14/02/2024 - 14/02/2024
