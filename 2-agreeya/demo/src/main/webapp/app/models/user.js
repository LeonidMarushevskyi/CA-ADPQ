var spouse = {
     "contactNo": "9876543210",
     "dob": "05/31/2016",
     "firstName": "testf",
     "gender": "M",
     "hobbies": "hhhhhhhhhhhhhhhh",
     "income": "123",
     "lastName": "lname",
     "maritalStatus": "d",
     "occupation": "occu",
     "preference": "ffffffffffffgg",
     "race": "racee",
     "religion": "gg"
};
var user = {
  firstName: "Manish",
  lastName: "Kumar",
  spouseDetails: spouse
};

var res = JSON.stringify(user);
console.log("User registration response: ", res);
