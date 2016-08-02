desc "Load facilities dump from config/data/facilities.yml"
task :load_facilities_dump => :environment do
  Facility.import_yaml_dump("#{Rails.root}/config/data/facilities.yml")
end
