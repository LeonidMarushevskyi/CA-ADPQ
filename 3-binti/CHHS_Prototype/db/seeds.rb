unless SocialWorker.find_by(email: "social.worker@binti.com")
  SocialWorker.create(
    first_name: "Social",
    last_name: "Worker",
    email: "social.worker@binti.com",
    password: "Blueberry23"
  )
end
