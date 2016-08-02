class UsersController < ApplicationController
  before_action :authenticate_user!

  def update
    if current_user.update(user_params)
      flash[:notice] = t("user.profile.saved")
      redirect_to messages_path
    else
      flash[:error] = current_user.errors.full_messages.join(", ")
      render :edit
    end
  end

  def edit
    current_user.state ||= "CA"
  end

  private

  def user_params
    attributes = [
      :address,
      :city,
      :first_name,
      :last_name,
      :phone_number,
      :state,
      :zipcode
    ]
    attributes << :email unless current_user.social_worker?
    params.require(:user).permit(attributes)
  end
end
