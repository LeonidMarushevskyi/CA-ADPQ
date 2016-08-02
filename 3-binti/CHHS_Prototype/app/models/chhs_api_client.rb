class ChhsApiClient < SODA::Client
  DATASET_IDENTIFIER_MAP = {
    adoption_agency: ENV["CHHS_ADOPTION_AGENCIES_DATASET_ID"],
    residential_facility: ENV["CHHS_RESIDENTIAL_FACILITIES_DATASET_ID"]
  }

  def initialize
    super(
      domain: "chhs.data.ca.gov",
      app_token: Rails.application.secrets.chhs_api_token
    )
  end

  def query(facility_type: :adoption_agency, limit: 10_000)
    unless DATASET_IDENTIFIER_MAP.has_key?(facility_type)
      raise ArgumentError.new(
        "Invalid facility_type #{facility_type} " +
        "Valid options are #{DATASET_IDENTIFIER_MAP.keys.join(", ")}"
      )
    end
    dataset_identifier = DATASET_IDENTIFIER_MAP[facility_type]

    get(dataset_identifier, {"$limit" => limit})
  end
end
