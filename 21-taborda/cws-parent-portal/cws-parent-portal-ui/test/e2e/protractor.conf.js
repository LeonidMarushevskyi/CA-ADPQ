exports.config = {

    specs: [
        'createProfile/createProfile.spec.js',
        'login/login.spec.js',
        'editProfile/editProfile.spec.js',
        'fosterAgency/fosterAgency.spec.js',
        'editProfile/editProfile.spec.js'
    ],

    baseUrl: 'http://localhost:3000',
    params: {
        email: 'joey.doe@example.com',
        password: 'xyz'
    }
};