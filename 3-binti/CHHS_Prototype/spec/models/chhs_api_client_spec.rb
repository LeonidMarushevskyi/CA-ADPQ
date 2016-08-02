require "rails_helper"

RSpec.describe ChhsApiClient do
  it "fetches a single adoption agency" do
    results = VCR.use_cassette("chhs_api") do
      subject.query(limit: 1)
    end
    expect(results.length).to eq(1)

    expect(results.first["location"]).to be_present
    expect(results.first["facility_name"]).to be_present
    expect(results.first["facility_type"]).to eq("ADOPTION AGENCY")
  end
end
