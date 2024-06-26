# CivAnsible
This project is a hybrid gradle/ansible project that is designed to provision and deploy services to a server.

Vendored plugins are located in `src/` (gradle), since they are used to build the end plugin directory (`/build/`)
Configs are located in `files/`, since they are purely copied by ansible

## Prerequisites
1. Create a user on the server named `actions` with sudo privileges
2. Create Environments in Github settings with the following environment secrets:
    - SSH_KNOWN_HOSTS
    - SSH_PRIVATE_KEY
    - SUDO_PASSWORD
3. Create a repo scoped secret with the contents of your secrets.yml called SECRETS_YML
4. Create an ansible inventory named after the environment

## Usage
1. Build dependencies with `gradle :ansible:build`
2. Use an ansible playbook with `ansible-playbook -i inventories/<inventory> playbooks/<playbook>`

## TODOs
- Private Config
- Mount backups location and configure params, setup

## Provisioned Layout
```
/
└── opt/
    ├── backup-and-restart.sh
    └── stacks/
        └── <stack>/
            ├── <stack>.yml
            └── ...<service-data>  
```

## Deployable Services

```mermaid
graph TD;

subgraph Exposed Ports;
  port_http((80));
  port_https((443));
  port_mc((25565));
  port_vote((8192));
end;

subgraph Minecraft;
  mc_waterfall[Waterfall];
  mc_paper[Paper];
  mc_kira[Kira];
  mc_mariadb[(MariaDB)];
  mc_postgres[(Postgres)];
  mc_rabbitmq[(RabbitMQ)];
  
  port_mc-->mc_waterfall;
  port_vote-->mc_paper;
    
  mc_waterfall-->mc_paper;
  mc_waterfall-->mc_postgres;
  mc_paper-->mc_postgres;
  mc_paper-->mc_mariadb;
  mc_paper-->mc_rabbitmq;
  mc_kira-->mc_postgres;
  mc_kira-->mc_rabbitmq;
end;

subgraph Auth;
  auth_keycloak[Keycloak];
  auth_postgres[(Postgres)];
  
  auth_keycloak-->auth_postgres;
end;

subgraph Monitoring;
  mon_grafana[Grafana];
  mon_loki[Loki];
  
  mon_grafana-->mon_loki;
  mon_grafana-->mc_postgres;
  mon_grafana-->mc_mariadb;
  mon_grafana-.->auth_keycloak;
end;

subgraph Portainer;
  por_portainer[Portainer];
  por_agent[Portainer Agent];
  
  por_portainer-->por_agent;
  por_portainer-.->auth_keycloak;
end;

subgraph Traefik;
  tfk_traefik[Traefik];
  
  port_http-->tfk_traefik;
  port_https-->tfk_traefik;
  
  tfk_traefik-->mon_grafana;
  tfk_traefik-->por_portainer;
end;
```