CREATE TABLE messages
(
  id                 BIGSERIAL PRIMARY KEY,
  author             BIGSERIAL references users(id),
  recipient          BIGSERIAL references users(id),
  date_created        timestamp default NULL,
  subject            TEXT,
  body               TEXT,
  conversation_id    BIGSERIAL references conversations(id)
);