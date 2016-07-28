CREATE TABLE conversations
(
  id                 BIGSERIAL PRIMARY KEY,
  dateCreated        timestamp default NULL,
  dateUpdated        timestamp default NULL,
  read               boolean default false
);