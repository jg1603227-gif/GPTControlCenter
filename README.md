# GPT Control Center

Aplicativo Android inicial para centralizar permissões e futuras solicitações de automação do GPT.

## Estado atual

- Interface inicial em Jetpack Compose.
- Categorias de permissão demonstrativas.
- Workflow do GitHub Actions para gerar APK de depuração.

**Importante:** nesta versão, os interruptores são apenas estado visual em memória. Ainda não há conexão com o GPT nem execução de tarefas. As integrações serão implementadas em etapas, com autorização explícita e validação de cada comando.

## Compilar

O GitHub Actions compila automaticamente ao enviar alterações para `main`. Também é possível iniciar manualmente em Actions > Build Android APK > Run workflow.

O APK ficará disponível em Artifacts após a conclusão do workflow.
