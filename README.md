# Catalogo Online 
Um projeto simples e objetivo, onde o administrador pode exibir produtos ou serviços para os usuários que visitarem o catálogo.

## Diagrama de classes

```mermaid
classDiagram
    class Admin {
        int id
        String email
        String password
        String telephone
    }

    class Card {
        int id
        String name
        double price
        String description
        String photo
    }

Admin "1" *-- "n" Card

```
