CREATE TABLE clients (
       id UUID PRIMARY KEY,
       utilisateurid UUID NOT NULL,
       nomClient VARCHAR(50),
       addreseClient VARCHAR(50),
       codePostalClient INTEGER,
       villeClient VARCHAR(50),
       siretClient VARCHAR(50),
       telephoneClient INTEGER,

    CONSTRAINT fk_user
       FOREIGN KEY(utilisateurid)
       REFERENCES users(id)
       ON DELETE CASCADE
);