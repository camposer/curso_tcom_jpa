-- Esto es un comentario
/* Esto también es un comentario */
CREATE TABLE persona(
	id INTEGER NOT NULL 
		GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	nombre VARCHAR(20) NOT NULL,
	apellido VARCHAR(20) NOT NULL,
	fechaNacimiento DATE,
	PRIMARY KEY (id)
);

CREATE TABLE ordenador(
	id INTEGER NOT NULL 
		GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	persona_id INTEGER NOT NULL,
	nombre VARCHAR(50),
	serial VARCHAR(50),
	PRIMARY KEY (id),
	CONSTRAINT fk_ordenador_persona FOREIGN KEY (persona_id)
		REFERENCES persona(id)
);
