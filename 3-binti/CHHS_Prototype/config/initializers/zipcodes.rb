require "csv"

# simple solution for a prototype, this object takes < 200KB
# for a production app, might store zipcode boundaries in a PostGIS instead
ZIPCODES_INDEX = {}

CSV.foreach("#{Rails.root}/config/data/ca_zipcodes.csv", headers: true) do |row|
  ZIPCODES_INDEX[row["zipcode"]] = {
    lat: row["lat"],
    lon: row["lon"],
    city: row["city"]
  }
end

