INSERT INTO Language (Id) 
VALUES (1), (2);

INSERT INTO LanguageLabel (Id, Content, LanguageId) 
VALUES (1, 'Français', 1), (2, 'English', 2);

INSERT INTO Product (Id, Price, Photo) 
VALUES 
(1, 5.30, 'cd_efico'),
(2,  4.25, 'cd_milano'),
(3, 3.99, 'colomgrains'),
(4,  6.22, 'costagrain'),
(5,  7.99, 'decafaine_d'),
(6, 8.50, 'melange_delahaut'),
(7, 5.36, 'pads_colombie'),
(8, 5.87, 'pads_costarica'),
(9, 5.12, 'pads_deca'),
(10, 6.28, 'pads_italien'),
(11, 7.88, 'pads_italien'),
(12, 25.99, 'pg_belge'),
(13, 31.85, 'pg_classique'),
(14, 50.99, 'pg_delice'),
(15, 4.99, 'pur_arabica_eth');

INSERT INTO ProductLabel (Id, Content, ProductId, LanguageId) 
VALUES 
(1, 'Café en grains efico', 1, 1),
(2, 'Café en grains milano', 2, 1),
(3, 'Café en grains colom', 3, 1),
(4, 'Café en grains costa', 4, 1),
(5, 'Décafainé en capsules', 5, 1),
(6, 'Mélange Delahaut en capsules', 6, 1),
(7, 'Pads colombie', 7, 1),
(8, 'Pads costarica', 8, 1),
(9, 'Pads déca', 9, 1),
(10, 'Pads italien', 10, 1),
(11, 'Pads moca', 11, 1),
(12, 'Panier garnis belge', 12, 1),
(13, 'Panier garnis classique', 13, 1),
(14, 'Panier garnis délice', 14, 1),
(15, 'Pur arabica en capsules', 15, 1),
(16, 'Efico coffee beans', 1, 2),
(17, 'Milano coffee beans', 2, 2),
(18, 'Colom coffee beans', 3, 2),
(19, 'Costa coffee beans', 4, 2),
(20, 'Decaffeinated capsule', 5, 2),
(21, 'Mix Delahaut capsule', 6, 2),
(22, 'Padscolombie', 7, 2),
(23, 'Pads costarica', 8, 2),
(24, 'Pads deca', 9, 2),
(25, 'Pads italien', 10, 2),
(26, 'Pads moca', 11, 2),
(27, 'Gift basket belge', 12, 2),
(28, 'Gift basket classique', 13, 2),
(29, 'Gift basket delice', 14, 2),
(30, 'Pure arabica capsule', 15, 2);

INSERT INTO Description (Id, Content, ProductId, LanguageId) 
VALUES 
(1, 'Café en grains efico', 1, 1),
(2, 'Café en grains milano', 2, 1),
(3, 'Café en grains colom', 3, 1),
(4, 'Café en grains costa', 4, 1),
(5, 'Décafainé en capsules', 5, 1),
(6, 'Mélange Delahaut en capsules', 6, 1),
(7, 'Pads colombie', 7, 1),
(8, 'Pads costarica', 8, 1),
(9, 'Pads déca', 9, 1),
(10, 'Pads italien', 10, 1),
(11, 'Pads moca', 11, 1),
(12, 'Panier garnis belge', 12, 1),
(13, 'Panier garnis classique', 13, 1),
(14, 'Panier garnis délice', 14, 1),
(15, 'Pur arabica en capsules', 15, 1),
(16, 'Efico coffee beans', 1, 2),
(17, 'Milano coffee beans', 2, 2),
(18, 'Colom coffee beans', 3, 2),
(19, 'Costa coffee beans', 4, 2),
(20, 'Decaffeinated capsule', 5, 2),
(21, 'Mix Delahaut capsule', 6, 2),
(22, 'Padscolombie', 7, 2),
(23, 'Pads costarica', 8, 2),
(24, 'Pads deca', 9, 2),
(25, 'Pads italien', 10, 2),
(26, 'Pads moca', 11, 2),
(27, 'Gift basket belge', 12, 2),
(28, 'Gift basket classique', 13, 2),
(29, 'Gift basket delice', 14, 2),
(30, 'Pure arabica capsule', 15, 2);