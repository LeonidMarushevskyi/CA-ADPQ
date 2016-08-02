VCR.configure do |c|
  c.cassette_library_dir = "#{Rails.root}/spec/vcr_cassettes"
  c.hook_into :webmock
  c.filter_sensitive_data("<CHHS_API_TOKEN>") do
    # not necessarily a secret but just setting good precedent
    Rails.application.secrets.chhs_api_token
  end
end
