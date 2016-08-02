class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  protect_from_forgery with: :exception
  force_ssl if: :ssl_configured?

  before_action :set_locale

  private

  def set_locale
    I18n.locale = params[:locale] || I18n.default_locale
  end

  def ssl_configured?
    Rails.env.production?
  end

  def default_url_options(options = {})
    if I18n.locale != I18n.default_locale
      options[:locale] = I18n.locale
    end
    options
  end

  def after_sign_in_path_for(user)
    messages_path
  end
end
