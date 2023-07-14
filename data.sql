INSERT INTO parcelle (id_parcelle, nom) VALUES 
    ('PAR001', 'P1'),
    ('PAR002', 'P2'),
    ('PAR003', 'P3');

INSERT INTO engrai (id_engrai, nom) VALUES 
    ('EGR001', 'EA'),
    ('EGR002', 'EB'),
    ('EGR003', 'EC');


INSERT INTO addition (id_addition, id_engrai, min, max, valeur) VALUES
    ('ADD0001', 'EGR001', 0, 25, 0),
    ('ADD0002', 'EGR001', 26, 50, -5),
    ('ADD0003', 'EGR001', 51, 100,10),
    ('ADD0004', 'EGR002', 0, 25, 10),
    ('ADD0005', 'EGR002', 26, 50, -5),
    ('ADD0006', 'EGR002', 51, 100, -30),
    ('ADD0007', 'EGR003', 0, 25, -10),
    ('ADD0008', 'EGR003', 26, 50, 5),
    ('ADD0009', 'EGR003', 51, 100, 0);

INSERT INTO depense (id_engrai, quantite, prix_unitaire, date) VALUES
    ('EGR001', 100, 500, '2023-06-01 12:00:00'),
    ('EGR002', 100, 500, '2023-06-01 12:00:00'),
    ('EGR003', 100, 500, '2023-06-01 12:00:00'),
    ('EGR001', 100, 550, '2023-06-16 12:00:00'),
    ('EGR002', 100, 550, '2023-06-16 12:00:00'),
    ('EGR003', 100, 550, '2023-06-16 12:00:00'),
    ('EGR001', 100, 1000, '2023-06-30 12:00:00'),
    ('EGR002', 100, 1000, '2023-06-30 12:00:00'),
    ('EGR003', 100, 1000, '2023-06-30 12:00:00');