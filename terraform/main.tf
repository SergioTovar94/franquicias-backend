terraform {
  required_providers {
    neon = {
      source  = "kislerdm/neon"
      version = "~> 0.8"
    }
  }
}

provider "neon" {
  api_key = var.neon_api_key
}

variable "neon_api_key" {
  type      = string
  sensitive = true
}

data "neon_project" "mi_proyecto" {
  id = "billowing-poetry-30590174"
}

resource "neon_database" "franquicias_db" {
  project_id = data.neon_project.mi_proyecto.id
  branch_id  = data.neon_project.mi_proyecto.default_branch_id
  name       = "neondb"
  owner_name = "neondb_owner"

  lifecycle {
    ignore_changes = all
  }
}

output "connection_string" {
  value = nonsensitive("postgresql://${data.neon_project.mi_proyecto.database_host}:5432/neondb?sslmode=require")
}
