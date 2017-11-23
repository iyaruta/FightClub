
INSERT INTO hero (id, account_id, name, ability, force, agility,  instinct, stamina, experience, level, health)
VALUES (1, 1, 'Inna', 4, 3, 3, 3, 0, 1)

INSERT INTO hero (id, account_id, name, ability, force, agility,  instinct, stamina, experience, level, health)
VALUES (2, 2, 'Sasha', 4, 3, 3, 3, 0, 1)

INSERT INTO EXPERIENCE_TABLE(experince, ability, level)
VALUES (0, 3, 0)
INSERT INTO EXPERIENCE_TABLE(experince, ability, level)
VALUES (20, 1, 0)
INSERT INTO EXPERIENCE_TABLE(experince, ability, level)
VALUES (40, 1, 0)
INSERT INTO EXPERIENCE_TABLE(experince, ability, level)
VALUES (65, 1, 0)
INSERT INTO EXPERIENCE_TABLE(experince, ability, level)
VALUES (90, 3, 1)
INSERT INTO EXPERIENCE_TABLE(experince, ability, level)
VALUES (120, 1, 0)
INSERT INTO EXPERIENCE_TABLE(experince, ability, level)
VALUES (160, 1, 0)
INSERT INTO EXPERIENCE_TABLE(experince, ability, level)
VALUES (210, 1, 0)

INSERT INTO BATTLE (ID, DATE_TEME, TIMEOUT, DATA)
VALUES (1, now(), 60, 'json')

INSERT INTO HERO_IN_BATTLE (HERO_ID, BATTLE_ID)
VALUES (1, 1)
