class HomeController < ApplicationController
  def index
    if signed_in?
      redirect_to dashboard_user_path
    end
  end

  def map
  end
end
