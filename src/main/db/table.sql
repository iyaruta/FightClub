CREATE TABLE PLAYER
(
  ID SERIAL NOT NULL,
  NAME VARCHAR (50),
  AGILITY BIGINT NOT NULL,
  SKILL BIGINT NOT NULL,
  INSTINCT BIGINT NOT NULL,
  STAMINA BIGINT NOT NULL,
  LEVEL BIGINT NOT NULL,
  experience bigint not NULL,
  HEALTH BIGINT NOT NULL,
  CONSTRAINT PLAYER_PK PRIMARY KEY (NAME)
);
