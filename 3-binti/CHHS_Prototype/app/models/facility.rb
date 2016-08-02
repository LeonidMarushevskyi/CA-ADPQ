class Facility < ActiveRecord::Base
  enum facility_type: { adoption_agency: 0, residential_facility: 1}

  scope :active, -> { where.not(status: "CLOSED") }

  scope :precise_location, -> { where(location_is_approximate: false) }

  scope :nearest_to_zipcode, ->(zipcode) {
    lat, lon = ZIPCODES_INDEX[zipcode.to_s].slice(:lat, :lon).values
    order(
      "facilities.location::geometry <-> ST_SetSRID(ST_MakePoint(#{lon}, #{lat}), 4326)::geometry"
    )
  }

  scope :within_miles_of_zipcode, ->(num_miles, zipcode) {
    lat, lon = ZIPCODES_INDEX[zipcode.to_s].slice(:lat, :lon).values
    where(
      "ST_DWithin(Geography(facilities.location)," +
      "Geography(ST_MakePoint(#{lon}, #{lat})), #{num_miles.to_i * 1609.34})"
    )
  }

  def self.dump_to_yaml(filename)
    File.open(filename, "w") do |file|
      find_each do |facility|
        file.write(facility.as_yaml)
      end
    end
  end

  def self.import_yaml_dump(filename)
    File.open(filename) do |file|
      YAML.load_documents(file) do |record|
        create!(record)
      end
    end
  end

  def as_yaml
    attributes.except("id", "location").tap { |hash|
      # the location fields don't have sensible to_json methods so we need to
      # deal with them here (we'll just use the WKT text strings)
      hash["location"] = location.as_text if location
    }.to_yaml
  end
end
