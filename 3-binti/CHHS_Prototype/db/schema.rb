# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20160610023802) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"
  enable_extension "postgis"

  create_table "conversations", force: :cascade do |t|
    t.integer  "user_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "facilities", force: :cascade do |t|
    t.json      "raw_location_data"
    t.integer   "facility_type"
    t.string    "address"
    t.string    "city"
    t.string    "phone_number"
    t.string    "name"
    t.string    "administrator"
    t.string    "status"
    t.integer   "facility_number"
    t.string    "zipcode"
    t.string    "county"
    t.geography "location",                limit: {:srid=>4326, :type=>"point", :geographic=>true}
    t.boolean   "location_is_approximate"
  end

  add_index "facilities", ["facility_number"], name: "index_facilities_on_facility_number", using: :btree
  add_index "facilities", ["facility_type"], name: "index_facilities_on_facility_type", using: :btree
  add_index "facilities", ["location"], name: "index_facilities_on_location", using: :gist
  add_index "facilities", ["location_is_approximate"], name: "index_facilities_on_location_is_approximate", using: :btree
  add_index "facilities", ["status"], name: "index_facilities_on_status", using: :btree

  create_table "messages", force: :cascade do |t|
    t.text     "body"
    t.integer  "user_id"
    t.integer  "conversation_id"
    t.datetime "created_at",      null: false
    t.datetime "updated_at",      null: false
  end

  create_table "social_worker_conversations", force: :cascade do |t|
    t.integer "social_worker_id"
    t.integer "conversation_id"
  end

  create_table "users", force: :cascade do |t|
    t.string   "first_name"
    t.string   "last_name"
    t.integer  "birth_month"
    t.integer  "birth_day"
    t.integer  "birth_year"
    t.string   "address"
    t.string   "city"
    t.string   "state"
    t.string   "zipcode"
    t.string   "email",                  default: "", null: false
    t.string   "encrypted_password",     default: "", null: false
    t.string   "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.inet     "current_sign_in_ip"
    t.inet     "last_sign_in_ip"
    t.datetime "created_at",                          null: false
    t.datetime "updated_at",                          null: false
    t.string   "type"
    t.string   "phone_number"
  end

  add_index "users", ["email"], name: "index_users_on_email", unique: true, using: :btree
  add_index "users", ["reset_password_token"], name: "index_users_on_reset_password_token", unique: true, using: :btree

end
