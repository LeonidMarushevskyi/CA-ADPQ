(function() {
	'use strict';
	
	angular.module('cgiAdpq')
		.constant('LOCALES', { 
			'English': {name: 'English', code: 'en-US', language: 'en'},
			'Spanish': {name: 'Español', code: 'es-MX', language: 'es'}
		})
		.constant('AUTH_EVENTS', {
			loginSuccess: 'auth-login-success',
			loginFailed: 'auth-login-failed',
			logoutSuccess: 'auth-logout-success',
			sessionTimeout: 'auth-session-timeout',
			notAuthenticated: 'auth-not-authenticated',
			notAuthorized: 'auth-not-authorized',
			userInfo: 'user-info-returned'
		})
		.constant('STATES', {
		    "AL": "Alabama",
		    "AK": "Alaska",
		    "AS": "American Samoa",
		    "AZ": "Arizona",
		    "AR": "Arkansas",
		    "CA": "California",
		    "CO": "Colorado",
		    "CT": "Connecticut",
		    "DE": "Delaware",
		    "DC": "District Of Columbia",
		    "FM": "Federated States Of Micronesia",
		    "FL": "Florida",
		    "GA": "Georgia",
		    "GU": "Guam",
		    "HI": "Hawaii",
		    "ID": "Idaho",
		    "IL": "Illinois",
		    "IN": "Indiana",
		    "IA": "Iowa",
		    "KS": "Kansas",
		    "KY": "Kentucky",
		    "LA": "Louisiana",
		    "ME": "Maine",
		    "MH": "Marshall Islands",
		    "MD": "Maryland",
		    "MA": "Massachusetts",
		    "MI": "Michigan",
		    "MN": "Minnesota",
		    "MS": "Mississippi",
		    "MO": "Missouri",
		    "MT": "Montana",
		    "NE": "Nebraska",
		    "NV": "Nevada",
		    "NH": "New Hampshire",
		    "NJ": "New Jersey",
		    "NM": "New Mexico",
		    "NY": "New York",
		    "NC": "North Carolina",
		    "ND": "North Dakota",
		    "MP": "Northern Mariana Islands",
		    "OH": "Ohio",
		    "OK": "Oklahoma",
		    "OR": "Oregon",
		    "PW": "Palau",
		    "PA": "Pennsylvania",
		    "PR": "Puerto Rico",
		    "RI": "Rhode Island",
		    "SC": "South Carolina",
		    "SD": "South Dakota",
		    "TN": "Tennessee",
		    "TX": "Texas",
		    "UT": "Utah",
		    "VT": "Vermont",
		    "VI": "Virgin Islands",
		    "VA": "Virginia",
		    "WA": "Washington",
		    "WV": "West Virginia",
		    "WI": "Wisconsin",
		    "WY": "Wyoming"
		})
		.constant('ENDPOINTS', {
			//configure these per your setup
			host: 'http://52.9.243.241',
			profilePort: '8081',
			messagingPort: '8081',
			facilityPort: '8081',
			
			//these stay the same
			get profile() { return this.host + (this.profilePort.length > 0 ? ':' + this.profilePort : '') + '/service/'; },
			get messaging() { return this.host + (this.profilePort.length > 0 ? ':' + this.profilePort : '') + '/messages/'; },
			get facility() { return this.host + (this.profilePort.length > 0 ? ':' + this.profilePort : '') + '/service/getCommunityCareInfo/'; }
		})
		; 
})();