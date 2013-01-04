databaseChangeLog = {

	changeSet(author: "sveri (generated)", id: "1357256466845-1") {
		createTable(tableName: "album") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-2") {
		createTable(tableName: "photo") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "album_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-3") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-4") {
		createTable(tableName: "role_permissions") {
			column(name: "role_id", type: "BIGINT")

			column(name: "permissions_string", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-5") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "password_hash", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_salt", type: "TINYBLOB") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-6") {
		createTable(tableName: "user_permissions") {
			column(name: "user_id", type: "BIGINT")

			column(name: "permissions_string", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-7") {
		createTable(tableName: "user_roles") {
			column(name: "user_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-8") {
		addPrimaryKey(columnNames: "user_id, role_id", tableName: "user_roles")
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-9") {
		addForeignKeyConstraint(baseColumnNames: "album_id", baseTableName: "photo", baseTableSchemaName: "pix_grails", constraintName: "FK65B3E324377A84", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "album", referencedTableSchemaName: "pix_grails", referencesUniqueColumn: "false")
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-10") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "role_permissions", baseTableSchemaName: "pix_grails", constraintName: "FKEAD9D23BEAEA34E", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencedTableSchemaName: "pix_grails", referencesUniqueColumn: "false")
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-11") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_permissions", baseTableSchemaName: "pix_grails", constraintName: "FKE693E610B3D9672E", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "pix_grails", referencesUniqueColumn: "false")
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-12") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_roles", baseTableSchemaName: "pix_grails", constraintName: "FK73429949EAEA34E", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role", referencedTableSchemaName: "pix_grails", referencesUniqueColumn: "false")
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-13") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_roles", baseTableSchemaName: "pix_grails", constraintName: "FK73429949B3D9672E", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user", referencedTableSchemaName: "pix_grails", referencesUniqueColumn: "false")
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-14") {
		createIndex(indexName: "name", tableName: "album", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-15") {
		createIndex(indexName: "album_id", tableName: "photo", unique: "true") {
			column(name: "album_id")

			column(name: "name")
		}
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-16") {
		createIndex(indexName: "name", tableName: "role", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "sveri (generated)", id: "1357256466845-17") {
		createIndex(indexName: "username", tableName: "user", unique: "true") {
			column(name: "username")
		}
	}
}
