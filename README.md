# Monster Catalog System

Sistema de microsserviços para catalogação e rastreamento de monstros do universo de *Lord of the Mysteries*, desenvolvido como projeto acadêmico no Instituto Infnet.

## Visão Geral

O sistema é composto por quatro microsserviços independentes que se comunicam via Eureka (discovery), API Gateway e Apache Kafka (mensageria assíncrona).

| Serviço | Porta | Responsabilidade |
|---|---|---|
| `discovery-service` | 8761 | Eureka Server — registro e descoberta de serviços |
| `gateway-api` | 8083 | Roteamento de requisições externas |
| `monster-service` | 8081 | Categorias, monstros e atualização de raridade |
| `region-service` | 8082 | Regiões e registro de avistamentos |

## Tecnologias

- Java 25 + Spring Boot 4.1.0
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway (WebMVC)
- Apache Kafka (KRaft)
- H2 (banco em memória)
- Prometheus + Grafana (métricas)
- Logstash + SLF4J (logs)
- Docker Compose

## Como Executar

### 1. Subir a infraestrutura Docker

```bash
cd monster-service/docker
docker-compose up -d
```

Serviços disponíveis após subir:
- Kafka UI: http://localhost:8086
- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000 (admin/admin)

### 2. Subir os microsserviços (nessa ordem)

```
1. discovery-service
2. monster-service
3. region-service
4. gateway-api
```

### 3. Verificar

- Eureka Dashboard: http://localhost:8761
- H2 Console monster-service: http://localhost:8081/h2-console (JDBC URL: `jdbc:h2:mem:monsterdb`)
- H2 Console region-service: http://localhost:8082/h2-console (JDBC URL: `jdbc:h2:mem:regiondb`)

## Endpoints

Todos os endpoints são acessíveis via gateway na porta `8083`.

### monster-service

```
GET    /api/monsters
GET    /api/monsters/{id}
POST   /api/monsters
PUT    /api/monsters/{id}
GET    /api/monsters/category/{categoryId}
GET    /api/monsters/status/{status}

GET    /api/categories
GET    /api/categories/{id}
POST   /api/categories
```

### region-service

```
GET    /api/regions
GET    /api/regions/{id}
POST   /api/regions
PUT    /api/regions/{id}

POST   /api/sightings
GET    /api/sightings/region/{regionId}
GET    /api/sightings/monster/{monsterId}
```

## Fluxo de Atualização de Raridade

```
POST /api/sightings (region-service)
  → salva avistamento
  → publica monsterId no tópico Kafka: sighting-register
    → monster-service consome o evento
    → incrementa sightingCount
    → recalcula status:
        sightingCount == 0       → EXTINCT
        sightingCount entre 1-5  → RARE
        sightingCount > 5        → COMMON
```

## Autores

- Leticia Saraiva da Silva — monster-service
- Sarah Figueiredo — region-service
