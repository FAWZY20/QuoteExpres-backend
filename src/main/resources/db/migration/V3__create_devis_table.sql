CREATE TABLE devis (
    id UUID PRIMARY KEY,
    utilisateurid UUID NOT NULL,
    clientId UUID NOT NULL,
    titredevis VARCHAR(255),
    status VARCHAR(255),
    numerofacture INTEGER,
    dateDevis DATE,
    listProduct JSONB,
    info TEXT,
    totalHt NUMERIC(15,2),
    totalTva NUMERIC(15,2),
    totalTtc NUMERIC(15,2),

  CONSTRAINT fk_user
        FOREIGN KEY(utilisateurid)
        REFERENCES users(id)
        ON DELETE CASCADE
);