-- directors and actors
INSERT INTO person (id, name, birthdate) values (1, 'Christopher Nolan', '1970-07-30');
INSERT INTO person (id, name, birthdate) values (2, 'Greta Gerwig', '1984-08-04');
INSERT INTO person (id, name, birthdate) values (3, 'Margot Robbie', '1990-07-02');
INSERT INTO person (id, name, birthdate) values (4, 'Ryan Gosling', '1980-11-12');
INSERT INTO person (id, name, birthdate) values (5, 'Cillian Murphy', '1976-05-25');
INSERT INTO person (id, name, birthdate) values (6, 'Emily Blunt', '1983-02-23');
INSERT INTO person (id, name, birthdate) values (7, 'Matt Damon', '1970-10-08');
-- movies
INSERT INTO movie (id, title, year, duration, synopsis, poster_uri, director_id)
    VALUES (1, 'Oppenheimer', 2023, 180,
        'The story of American scientist, J. Robert Oppenheimer, and his role in the development of the atomic bomb.',
        'https://m.media-amazon.com/images/M/MV5BMDBmYTZjNjUtN2M1MS00MTQ2LTk2ODgtNzc2M2QyZGE5NTVjXkEyXkFqcGdeQXVyNzAwMjU2MTY@._V1_FMjpg_UY720_.jpg',
        1);
INSERT INTO movie (id, title, year, duration, synopsis, poster_uri, director_id)
    VALUES (2, 'Barbie', 2023, 114,
        'Barbie suffers a crisis that leads her to question her world and her existence.',
        'https://m.media-amazon.com/images/M/MV5BNjU3N2QxNzYtMjk1NC00MTc4LTk1NTQtMmUxNTljM2I0NDA5XkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_QL75_UX285_CR0,0,285,422_.jpg',
        2);
INSERT INTO movie (id, title, year, duration, director_id)
    VALUES (3, 'Interstellar',	2014,	169, 1);
INSERT INTO movie (id, title, year, duration, director_id)
    VALUES (4, 'The Dark Knight',	2008,	152, 1);
-- play
INSERT INTO play(movie_id, actor_id) VALUES (1, 5);
INSERT INTO play(movie_id, actor_id) VALUES (1, 6);
INSERT INTO play(movie_id, actor_id) VALUES (1, 7);
INSERT INTO play(movie_id, actor_id) VALUES (2, 3);
INSERT INTO play(movie_id, actor_id) VALUES (2, 4);
