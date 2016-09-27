namespace :puma do
  desc "Safely restart puma"
  task :safe_restart do
    ruby_string = ENV['GEM_HOME'].gsub(/.*\//,'')
    puts "\n\nSafely restart puma using #{ruby_string}, environment: #{DEPLOY_ENV}\n\n"
    
    on roles(:app) do
      execute("pkill -f #{DEPLOY_APPLICATION}.sock > /dev/null")
      execute("cd #{DEPLOY_TO_DIR}/current && rvm #{ruby_string} do bundle exec puma -b 'unix://#{DEPLOY_TO_DIR}/shared/tmp/sockets/#{DEPLOY_APPLICATION}.sock'  -e #{DEPLOY_ENV}  --control 'unix:///#{DEPLOY_TO_DIR}/shared/tmp/sockets/#{DEPLOY_APPLICATION}_ctl.sock' -d")
    end
  end
end