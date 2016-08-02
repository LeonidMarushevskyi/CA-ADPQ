desc "Try our best to geocode facilities to lat/lon"
task :geocode_facilities => :environment do
  def update_facility(facility, place, query, location_is_approximate)
    puts "\n\n\n"
    puts facility.id
    puts query
    puts place.formatted_address

    facility.update_attributes(
      location: "POINT(#{place.longitude} #{place.latitude})",
      location_is_approximate: location_is_approximate
    )
  end

  Facility.where(location: nil).find_each do |facility|
    query = [
      facility.address,
      facility.city,
      facility.county,
      "California",
      facility.zipcode
    ].compact.join(", ")

    results = Geocoder.search(query)
    first = results.first

    if first
      # trust google ;)
      # in all cases that I checked, Google's was a legit match
      update_facility(facility, first, query, false)
    else
      # Just go for a county match
      query = "#{facility.county} County, California"
      results = Geocoder.search(query)
      first = results.first

      if first
        update_facility(facility, first, query, true)
      end
    end
    sleep 1 # be kind to Google
  end
end
