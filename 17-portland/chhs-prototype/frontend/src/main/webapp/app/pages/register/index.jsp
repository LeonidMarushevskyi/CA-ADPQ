<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAnonymous()">
  <div class="register container">
    <h4 class="register__header form__header">CREATE ACCOUNT</h4>

    <div class="row" ng-if="register.accountError">
      <div class="col-sm-6 col-sm-offset-3">
        <div class="alert alert-danger" role="alert">
          There was an error creating your account. Please try again later.
        </div>
      </div>
    </div>

    <div class="row" ng-if="!register.accountCreated">
      <div class="col-sm-6 col-sm-offset-3">
        <div class="register__summary form__summary">
          <p>Create a user account to find and communicate with foster facilities and case workers in your area.</p>
        </div>

        <form name="registerForm" id="registerForm" ng-submit="register.createAccount(registerForm.$valid)" novalidate>

          <div class="row">
            <div class="col-sm-6 form-group">
              <input type="text"
                      name="firstName"
                      placeholder="First Name"
                      class="form-control"
                      ng-required="true"
                      ng-model="register.data.firstName">
            </div>
            <div class="col-sm-6 form-group">
              <input type="text" name="lastName" placeholder="Last Name" class="form-control" ng-required="true"
                      ng-model="register.data.lastName">
            </div>
          </div>
          <div class="row">
            <div class="col-sm-12 form-group">
              <input name="address" class="form-control" placeholder="Street Address" ng-required="true"
                      ng-model="register.data.address.street">
            </div>
          </div>
          <div class="row">
            <div class="col-sm-6 form-group">
              <input name="city" class="form-control" placeholder="City" ng-required="true" ng-model="register.data.address.city">
            </div>
            <div class="col-sm-3 form-group">
              <select name="state" class="form-control" placeholder="State" ng-required="true" ng-model="register.data.address.state">
                <option value="" selected disabled>State</option>
                <option ng-repeat="state in register.states" value="{{state}}">{{state}}</option>
              </select>
            </div>
            <div class="col-sm-3 form-group">
              <input name="postalCode" class="form-control" placeholder="Zip Code" ng-required="true"
                      ng-model="register.data.address.postalCode">
            </div>
          </div>
          <div class="row">
            <div class="col-sm-6 form-group">
              <input type="email" name="email" placeholder="Email" class="form-control"
                      registration-email-validator
                      ng-model-options="{debounce: 500}"
                      ng-required="true"
                      ng-model="register.data.email">
            </div>
            <div class="col-sm-6 form-group">
              <input type="email" name="emailre" placeholder="Re-enter email" class="form-control" ng-required="true"
                      ng-model="register.data.emailre">
            </div>
          </div>
          <div class="row">
            <div class="col-sm-6 form-group">
              <input type="password" name="password" placeholder="Password" class="form-control" ng-required="true"
                      ng-model="register.data.newPassword">
            </div>
            <div class="col-sm-6 form-group">
              <input type="password" name="passwordre" placeholder="Re-enter password" class="form-control" ng-required="true"
                      ng-model="register.data.newPasswordConfirmation">
            </div>
          </div>

          <p><span class="register__required-asterisk form__required-asterisk">*</span> All fields are required.</p>
          <p>Passwords must match and be at least 10 characters long, contain at least one number, one uppercase A-Z,
            and one lower case a-z.</p>

          <div class="form-group">
            <input id="termsCheckbox" name="terms" type="checkbox" ng-required="true" ng-model="register.data.terms">
            <label for="termsCheckbox">Yes, I have read and accept the <a class="register__terms-link" href=""
                                                                          data-toggle="modal" data-target="#termsModal">
                Terms and Conditions </a></label>
          </div>

          <div class="alert alert-danger" role="alert"
                ng-show="registerForm.firstName.$error.required && register.submitted">
            First name is required.
          </div>
          <div class="alert alert-danger" role="alert"
                ng-show="registerForm.lastName.$error.required && register.submitted">
            Last name is required.
          </div>
          <div class="alert alert-danger" role="alert"
                ng-show="registerForm.address.$error.required && register.submitted">
            Address is required.
          </div>
          <div class="alert alert-danger" role="alert"
                ng-show="registerForm.city.$error.required && register.submitted">
            City is required.
          </div>
          <div class="alert alert-danger" role="alert"
                ng-show="registerForm.state.$error.required && register.submitted">
            State is required.
          </div>
          <div class="alert alert-danger" role="alert"
                ng-show="registerForm.postalCode.$error.required && register.submitted">
            Zip code is required.
          </div>
          <div class="alert alert-danger" role="alert" ng-show="!register.postalCodeValid() && register.submitted">
            Zip code is invalid.
          </div>
          <div class="alert alert-danger" role="alert"
                ng-show="registerForm.email.$error.required && register.submitted">
            Email is required.
          </div>
          <div class="alert alert-danger" role="alert"
                ng-show="registerForm.email.$invalid && registerForm.email.$error.uniqueEmail">
            Email address already in use.
          </div>
          <div class="alert alert-danger" role="alert"
                ng-show="registerForm.email.$invalid && !registerForm.email.$error.required && register.submitted">
            Email is invalid.
          </div>
          <div class="alert alert-danger" role="alert" ng-show="!register.emailsMatch() && register.submitted">
            Emails must match.
          </div>
          <div class="alert alert-danger" role="alert"
                ng-show="registerForm.password.$error.required && register.submitted">
            Password is required.
          </div>
          <div class="alert alert-danger" role="alert" ng-show="!register.passwordValid() && register.submitted">
            Password is invalid.
          </div>
          <div class="alert alert-danger" role="alert" ng-show="!register.passwordsMatch() && register.submitted">
            Passwords must match.
          </div>
          <div class="alert alert-danger" role="alert"
                ng-show="registerForm.terms.$error.required && register.submitted">
            Please accept the Terms and Conditions.
          </div>

          <div class="row">
            <div class="col-sm-6 col-sm-push-6 form-group">
              <button type="submit" name="submit" id="submit" class="register__btn-create form__btn-create form-control btn btn-primary" ng-disabled="registerForm.$invalid">
                CREATE ACCOUNT
              </button>
            </div>
            <div class="col-sm-6 col-sm-pull-6 form-group">
              <button type="button" class="form-control btn btn-default" ng-click="register.cancel()">CANCEL</button>
            </div>
          </div>
        </form>

        <div class="terms-conditions"></div>

      </div>
    </div>
    <div class="row" ng-if="register.accountCreated">
      <div class="col-sm-6 col-sm-offset-3">
        <div class="register__success form__success">
          <h4>Thank you for registering! You can now login using the email and password you provided.</h4>
        </div>
      </div>
    </div>

  </div>
</sec:authorize>
