require "rails_helper"

RSpec.describe "Facilities queries" do
  context "Valid queries" do
    context "By facility type, without zipcode or distance" do
      let!(:licensed_adoption_agency) {
        create(:adoption_agency, status: "LICENSED")
      }
      let!(:closed_adoption_agency) {
        create(:adoption_agency, status: "CLOSED")
      }
      let!(:licensed_residential_facility) {
        create(:residential_facility, status: "LICENSED")
      }
      let!(:closed_residential_facility) {
        create(:residential_facility, status: "CLOSED")
      }

      context "asking for adoption agencies" do
        it "does not return any CLOSED agencies or any residential facilities" do
          get "/api/facilities", facility_type: :adoption_agency

          expect(response).to be_success
          all_parsed = JSON.parse(response.body)
          expect(all_parsed.count).to eq(1)
          facility = all_parsed.first

          expect(facility["status"]).to eq("LICENSED")
          expect(facility["facility_type"]).to eq("adoption_agency")
        end
      end

      context "asking for residential facilities" do
        it "does not return any CLOSED facilities or adoption agencies" do
          get "/api/facilities", facility_type: :residential_facility

          expect(response).to be_success
          all_parsed = JSON.parse(response.body)
          expect(all_parsed.count).to eq(1)
          facility = all_parsed.first

          expect(facility["status"]).to eq("LICENSED")
          expect(facility["facility_type"]).to eq("residential_facility")
        end
      end
    end

    context "Adoption agencies Within 50 miles of 94589 (Vallejo)" do
      let!(:marysville) {
        create(:adoption_agency,
               location: "POINT(-121.587785 39.142332)",
               address: "623 C STREET",
               city: "MARYSVILLE",
               phone_number: "(530) 741-2800",
               name: "ENVIRONMENTAL ALTERNATIVES-MARYSVILLE",
               administrator: "KING, MELODY",
               status: "LICENSED",
               facility_number: 587000187,
               zipcode: "95901",
               county: nil
              )
      }
      let!(:oakland) {
        create(:adoption_agency,
               location: "POINT(-122.275554 37.797441)",
               address: "401 BROADWAY",
               city: "OAKLAND",
               phone_number: "(510) 268-2422",
               name: "ALAMEDA COUNTY SOCIAL SERVICES AGENCY",
               administrator: "LEE, SALLY",
               status: "LICENSED",
               facility_number: 15200804,
               zipcode: "94604",
               county: nil
              )
      }

      it "returns the correct results" do
        get "/api/facilities", zipcode: 94589, within: 50,
          facility_type: :adoption_agency

        expect(response).to be_success
        all_parsed = JSON.parse(response.body)
        expect(all_parsed.count).to eq(1)
        facility = all_parsed.first

        expect(facility["city"]).to eq("OAKLAND")
        expect(facility["phone_number"]).to eq("(510) 268-2422")
        expect(facility["address"]).to eq("401 BROADWAY")
        expect(facility["name"]).to eq("ALAMEDA COUNTY SOCIAL SERVICES AGENCY")
        expect(facility["lat"]).to eq(37.797441)
        expect(facility["lon"]).to eq(-122.275554)
        expect(facility["distance_in_miles"]).to eq(25)
        expect(facility["status"]).to eq("LICENSED")
      end
    end
  end
end
