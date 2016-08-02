source 'https://rubygems.org'

ruby '2.3.1'

# Bundle edge Rails instead: gem 'rails', github: 'rails/rails'
gem 'rails', '4.2.6'

gem 'active_model_serializers', '~> 0.10.0'
gem "autoprefixer-rails"
gem 'devise'
gem 'devise-i18n'
gem 'jquery-rails'
gem 'font-awesome-rails'
gem 'geocoder'
gem 'pg', '~> 0.15'
gem 'activerecord-postgis-adapter'
gem 'puma'
gem 'sass-rails', '~> 5.0'
gem 'soda-ruby', require: 'soda' # for accessing CA HHS open data
gem 'uglifier', '>= 1.3.0'

group :development, :test do
  gem 'dotenv-rails'
  gem 'pry-rails'
  gem 'rspec-rails'
  gem 'vcr'
  gem 'webmock'
end

group :development do
  gem 'better_errors'
  gem 'binding_of_caller'
  gem 'guard'
  gem 'guard-livereload'
  gem 'rack-livereload'
  gem 'quiet_assets'
end

group :test do
  gem 'capybara'
  gem 'factory_girl_rails'
  gem 'shoulda-matchers', '~> 3.1'
end

group :production do
  gem 'rails_12factor'
end
