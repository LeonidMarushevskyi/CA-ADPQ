class FacilitiesController < ApplicationController
  before_action :authenticate_user!, except: [:index]

  def index
    @facilities = Facility.includes(:addresses).all
  end
end
