class Api::FacilitiesController < ApplicationController
  def index
    # validate zipcode first
    if params[:zipcode] && !ZIPCODES_INDEX[params["zipcode"]]
      render json: {error: "Please enter a valid California zipcode"}
      return
    end

    scope = Facility.active.precise_location

    case params[:facility_type]
    when "adoption_agency"
      scope = scope.adoption_agency
    when "residential_facility"
      scope = scope.residential_facility
    end

    if params[:zipcode] && params[:within]
      scope = scope.within_miles_of_zipcode(params[:within], params[:zipcode])
    end

    if params[:zipcode]
      scope = scope.nearest_to_zipcode(params[:zipcode])
    end

    if params[:limit]
      scope = scope.limit(params[:limit])
    end

    render json: scope, zipcode: params[:zipcode]
  end
end
