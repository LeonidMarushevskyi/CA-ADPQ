(function () {
    'use strict';
    var module = angular.module("chhsDemo");

    function userHomeRootController($cookieStore) {
        var model = this;

        //model.GoToUserAccountDetails = function () {
        //    model.$router.navigate(['UserHomeRoot', 'UserAccountDetails']);
        //}
        model.tabA = "active";
        model.breadCrumText = "My Page";
        if($cookieStore.get("selectedTab")){
             model.tabA = "";
            model.tabB = "";
            model.tabC = "";
            model.tabD = "";
            model.tabE = "";
            var tabNo = parseInt($cookieStore.get("selectedTab"));
            switch (tabNo) {
                case 1:
                    model.tabA = "active";
                    model.breadCrumText = "My Page";
                    break;
                case 2:
                    model.tabB = "active";
                    model.breadCrumText = "Manage Profile";
                    break;
                case 3:
                    model.tabC = "active";
                    model.breadCrumText = "Search";
                    break;
                case 4:
                    model.tabD = "active";
                    model.breadCrumText = "Search";
                    break;
                case 5:
                    model.tabE = "active";
                    model.breadCrumText = "Inbox";
                    break;
                default:
                    model.tabA = "active";
                    model.breadCrumText = "My Page";
                    break;
            }
        }


        model.setActive = function (tabNo) {
            if($cookieStore.get("selectedTab")){
                $cookieStore.remove("selectedTab");
            }
            $cookieStore.put("selectedTab", tabNo);

            model.tabA = "";
            model.tabB = "";
            model.tabC = "";
            model.tabD = "";
            model.tabE = "";

            switch (tabNo) {
                case 1:
                    model.tabA = "active";
                    model.breadCrumText = "My Page";
                    break;
                case 2:
                    model.tabB = "active";
                    model.breadCrumText = "Manage Profile";
                    break;
                case 3:
                    model.tabC = "active";
                    model.breadCrumText = "Search";
                    break;
                case 4:
                    model.tabD = "active";
                    model.breadCrumText = "Search";
                    break;
                case 5:
                    model.tabE = "active";
                    model.breadCrumText = "Inbox";
                    break;
                default:
                    model.tabA = "active";
                    model.breadCrumText = "My Page";
                    break;
            }



        }

    }

    module.component("userHomeRoot", {
        templateUrl: './app/templates/userHomeRoot.html',
        $routeConfig: [
            { path: "/userStatus", component: "userStatus", name: "UserStatus", useAsDefault: true },
            { path: "/userAccountDetails", component: "userAccountDetails", name: "UserAccountDetails" },
            { path: "/userPersonalInformation", component: "userPersonalInformation", name: "UserPersonalInformation" },
            { path: "/userPreferences", component: "userPreferences", name: "UserPreferences" },
            { path: "/userLicense", component: "userLicense", name: "UserLicense" },
            {path: "/childFacilities", component: "childResidentialFacilities", name: "SearchChildResidentialFacilities"},
            {path: "/fosterCareAgencies", component: "fosterCareAgencies", name: "SearchFosterCareAgencies"},
            {path: "/mails", component: "userInbox", name: "UserInbox"},
        ],
        controllerAs: "model",
        controller: ['$cookieStore', userHomeRootController]
    });
}());
