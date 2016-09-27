require 'rails_helper'

describe UsersController do
  before :each do
    @user = User.where("email like 'parent_%'").first

    @request.env["devise.mapping"] = Devise.mappings[:admin]
    sign_in @user
  end

  context 'show' do
    it 'renders user information' do
      get :show, { id: @user.id }
      expect(response).to have_http_status(:ok)
      expect(response).to render_template('show')
    end
  end
end