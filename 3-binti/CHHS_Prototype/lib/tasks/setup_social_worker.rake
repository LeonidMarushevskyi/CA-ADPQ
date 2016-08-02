desc "Setup initial social worker login"
task :setup_social_worker => :environment do
  user = SocialWorker.find_or_initialize_by(email: "social@binti.com",
                                           phone_number: "(415) 123-4566",
                                           first_name: "Jane",
                                           last_name: "Williams")

  if !user.persisted?
    user.password = ENV["SOCIAL_WORKER_PASSWORD"]
    user.save
  end

end
