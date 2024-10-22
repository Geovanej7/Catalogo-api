# Catalogo Online 
Um projeto simples e objetivo, onde o administrador pode exibir produtos ou serviços para os usuários que visitarem o catálogo.

## Diagrama de classes

```mermaid
classDiagram
    class Admin {
        Long id
        String email
        String password
        String phone
    }

    class Card {
        Long id
        String title
        double price
        String description
        String image
    }

Admin "1" *-- "n" Card

```
