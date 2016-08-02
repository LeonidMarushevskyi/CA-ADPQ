FactoryGirl.define do
  factory :facility do
    factory :adoption_agency do
      facility_type :adoption_agency
    end
    factory :residential_facility do
      facility_type :residential_facility
    end
    location_is_approximate false
  end
end
