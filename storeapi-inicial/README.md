## Questões Teóricas

1. Diferença entre @Controller e @RestController?
- @Controller: Usado no Spring MVC tradicional. Retorna o nome de uma página/view (ex: HTML).
- @RestController: Combina @Controller e @ResponseBody. O retorno dos métodos é convertido diretamente no corpo da resposta HTTP (geralmente JSON).

2. Qual a função de @RequestBody?
   Indica que o corpo da requisição HTTP (JSON) enviado pelo cliente deve ser convertido automaticamente num objeto Java pelo Spring.

3. Diferença entre @PathVariable e @RequestParam?
- @PathVariable: Extrai valores diretamente da URL (ex: /api/produtos/5).
- @RequestParam: Extrai parâmetros passados na query string da URL (ex: /api/produtos/busca?nome=note).

4. Por que utilizar ResponseEntity?
   Permite um controlo total sobre a resposta HTTP, permitindo definir códigos de status (200, 201, 204, 404), cabeçalhos e o corpo da resposta.

5. Quando utilizar HTTP 200, 201, 204 e 404?
- 200 OK: Sucesso com retorno de dados (GET, PUT).
- 201 Created: Sucesso na criação de um recurso (POST).
- 204 No Content: Sucesso na execução mas sem conteúdo de retorno (DELETE).
- 404 Not Found: Recurso solicitado não foi encontrado.