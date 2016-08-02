class FacilitySerializer < ActiveModel::Serializer
  def attributes(*args)
    super.tap do |attrs|
      if distance_in_miles
        attrs[:distance_in_miles] = distance_in_miles
      end
    end
  end

  attributes :address, :city, :facility_type, :name, :phone_number, :status

  attribute :lon do
    object.location&.lon
  end

  attribute :lat do
    object.location&.lat
  end

  private

  def distance_in_miles
    if query_zipcode_info && object.location
      (object.location.distance(query_location) / 1609.34).round(1)
    end
  end

  def query_location
    RGeo::Geographic.spherical_factory.point(
      query_zipcode_info[:lon], query_zipcode_info[:lat]
    )
  end

  def query_zipcode_info
    @query_zipcode_info ||= ZIPCODES_INDEX[@instance_options[:zipcode].to_s]
  end
end
