class Role < ActiveRecord::Base
  ROLES = {
    parent:         :PARENT,
    child:          :CHILD,
    case_worker:    :CASE_WORKER,
    facility_admin: :FACILITY_ADMIN,
    system_admin:   :SYSTEM_ADMIN
  }

  has_many :roles_users
  has_many :users, through: :roles_users
end
