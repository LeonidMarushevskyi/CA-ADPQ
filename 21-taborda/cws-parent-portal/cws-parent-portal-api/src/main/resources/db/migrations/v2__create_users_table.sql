CREATE TABLE users
(
  id                 BIGSERIAL PRIMARY KEY,
  first_name         TEXT,
  last_name          TEXT,
  in_care_of         TEXT,
  address1           TEXT,
  address2           TEXT,
  state_abbreviation CHARACTER(2),
  city               TEXT,
  zip_code           CHARACTER(5),
  image_url          TEXT,
  email              TEXT,
  password_hash      CHARACTER(60)
);