namespace :db do

  desc 'Call db:drop db:create db:migrate db:seed for different Rails environment'
  task start_over: :environment do
    puts("RAILS_ENV=#{Rails.env} rake db:drop db:create db:migrate db:seed")
    raise 'Go away! This is production environment.' if Rails.env == 'production'

    ActiveRecord::Base.establish_connection(Rails.env.to_sym)
    ['db:drop', 'db:create', 'db:migrate', 'db:seed'].each do |task_name|
      Rake::Task[task_name].invoke
    end
  end 

end