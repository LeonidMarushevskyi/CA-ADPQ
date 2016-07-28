<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
  <div class="manage-account container">
    <h4 class="manage-account__header form__header">MANAGE ACCOUNT</h4>

    <div class="row" ng-if="update.error">
      <div class="col-sm-6 col-sm-offset-3">
        <div class="alert alert-danger" role="alert">
          There was an error updating your account. Please try again later.
        </div>
      </div>
    </div>

    <div class="row" ng-if="update.updated">
      <div class="col-sm-6 col-sm-offset-3">
        <div class="manage-account__success form__success">
          <h4>Account information successfully updated.</h4>
          <div class="row">
            <div class="col-xs-4 col-xs-offset-4">
              <button ng-click="update.updated = false" class="form-control btn btn-default manage-acount__success_btn-ok">Ok</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row" ng-if="!update.updated">
      <div class="col-sm-6 col-sm-offset-3">
        <div class="manage-account__summary form__summary">
          <p>Current account information.</p>
        </div>

        <form name="updateAccountForm" id="updateAccountForm" class="form__error_display" ng-submit="update.update()" novalidate>
          <div class="row">
            <div class="col-sm-6 form-group">
              <input type="text"
                      name="email"
                      placeholder="Email"
                      class="form-control"
                      disabled="true"
                      ng-model="update.account.email">
            </div>
          </div>

          <div class="row">
            <div class="col-sm-6 form-group">
              <input type="text"
                      name="firstName"
                      placeholder="First Name"
                      class="form-control"
                      ng-required="true"
                      ng-model="update.account.firstName">
            </div>
            <div class="col-sm-6 form-group">
              <input type="text" name="lastName" placeholder="Last Name" class="form-control" ng-required="true"
                      ng-model="update.account.lastName">
            </div>
          </div>
          <div class="row">
            <div class="col-sm-12 form-group">
              <input name="address" class="form-control" placeholder="Street Address" ng-required="true"
                      ng-model="update.account.address.street">
            </div>
          </div>
          <div class="row">
            <div class="col-sm-6 form-group">
              <input name="city" class="form-control" placeholder="City" ng-required="true" ng-model="update.account.address.city">
            </div>
            <div class="col-sm-3 form-group">
              <select name="state" class="form-control" placeholder="State" ng-required="true" ng-model="update.account.address.state">
                <option value="" selected disabled>State</option>
                <option ng-repeat="state in update.states" value="{{state}}">{{state}}</option>
              </select>
            </div>
            <div class="col-sm-3 form-group">
              <input name="postalCode" class="form-control" placeholder="Zip Code" ng-required="true"
                      ng-model="update.account.address.postalCode">
            </div>
          </div>
          <p><span class="form__required-asterisk">*</span> All fields are required.</p>
          <div class="row">
            <div class="col-sm-6 col-sm-push-6 form-group">
              <button type="submit" name="submit" id="submit" class="manage-account__btn-create form__btn-create form-control btn btn-primary" ng-disabled="updateAccountForm.$invalid">
                UPDATE ACCOUNT
              </button>
            </div>
            <div class="col-sm-6 col-sm-pull-6 form-group">
              <button type="button" class="form-control btn btn-default form__btn-cancel" ng-click="update.cancel()">CANCEL</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</sec:authorize>
