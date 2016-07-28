<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<sec:authorize access="isAuthenticated()">
  <header class="clearfix">
    <h3 class="case-worker-messenger__header-text pull-left">My Messages</h3>
    <a href class="case-worker-messenger__compose-link pull-right" ng-click="composeMessage()" data-toggle="modal" data-target="#case-worker-messenger__composer">Create New Message</a>
  </header>

  <div class="case-worker-messenger__messages panel">
    <div class="case-worker-messenger__messages-content panel-body">
      <div class="alert alert-warning alert-dismissable" ng-if="messageRetrieveFailed">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p>Failed to retrieve messages. Please <a href ng-click="loadMessages()">try again</a>.</p>
      </div>
      <div ng-if="messages != null && messages.length == 0">
        <p class="case-worker-messenger__messages-empty text-center">You have no messages.</p>
      </div>
      <div class="case-worker-messenger__messages-list list-group" ng-if="messages.length > 0">
        <a href class="case-worker-messenger__message list-group-item" ng-repeat="message in messages" ng-click="viewMessage(message)">
          <p class="case-worker-messenger__message-sender">{{ message.fromName }}</p>
          <p class="clearfix">
            <span class="case-worker-messenger__message-subject pull-left">{{message.subject}}</span>
            <span class="case-worker-messenger__message-date pull-right">{{ message.createdAt | date : 'M/d/yyyy' }}</span>
          </p>
          <p class="case-worker-messenger__message-contents">{{ message.content | limitTo : 150 }}...</p>
        </a>
      </div>
    </div>
  </div>

  <div class="case-worker-messenger__viewer" ng-if="viewedMessage !== null">
    <div class="case-worker-messenger__viewer-message-info">
      <p class="case-worker-messenger__viewer-sender">From: {{ viewedMessage.fromName }}</p>
      <p class="case-worker-messenger__viewer-recepient">To: {{ viewedMessage.toName }}</p>
      <div class="clearfix">
        <span class="case-worker-messenger__viewer-subject pull-left">{{ viewedMessage.subject }}</span>
        <span class="case-worker-messenger__viewer-date pull-right">{{ viewedMessage.createdAt | date : 'M/d/yyyy' }}</span>
      </div>
    </div>
    <div class="case-worker-messenger__viewer-message-controls">
      <div class="alert alert-warning alert-dismissable" ng-if="messageDeleteFailed">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p>Failed to delete message. Please try again.</p>
      </div>
      <div class="clearfix">
        <a href class="case-worker-messenger__viewer-control pull-right" ng-click="deleteMessage(viewedMessage.id)">Delete</a>
        <a href class="case-worker-messenger__viewer-control case-worker-messenger__viewer-control--primary pull-right" ng-click="replyToMessage(viewedMessage)" data-toggle="modal" data-target="#case-worker-messenger__composer">Reply</a>
      </div>
    </div>
    <div class="case-worker-messenger__viewer-message">{{ viewedMessage.content }}</div>
  </div>

  <div id="case-worker-messenger__composer" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
      <div class="case-worker-messenger__composer-content modal-content">
        <form name="caseWorkerMessageForm" ng-submit="sendMessage(caseWorkerMessageForm.$valid)">
          <div class="case-worker-messenger__composer-header modal-header">
            <button type="button" class="case-worker-messenger__composer-close close" ng-click="cancelCompose()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="case-worker-messenger__composer-title modal-title">Compose Message</h4>
          </div>
          <div class="modal-body">
            <div class="alert alert-warning alert-dismissable" ng-if="messageSendingFailed">
              <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <p>Failed to send message. Please try again.</p>
            </div>
            <div class="case-worker-messenger__composer-info-group form-group">
              <div class="input-group">
                <span class="case-worker-messenger__composer-input-addon input-group-addon">To:</span>
                <input type="text" name="recepient" class="case-worker-messenger__composer-input form-control" disabled ng-model="userMessage.toEmail" ng-required="true">
              </div>
            </div>
            <div class="case-worker-messenger__composer-info-group form-group">
              <div class="input-group">
                <span class="case-worker-messenger__composer-input-addon input-group-addon">Re:</span>
                <input type="text" name="subject" class="case-worker-messenger__composer-input form-control" ng-model="userMessage.subject" ng-required="true"/>
              </div>
            </div>
            <div class="case-worker-messenger__composer-content-group form-group">
              <textarea class="case-worker-messenger__composer-textarea form-control" name="message" rows="15" ng-model="userMessage.content" ng-required="true"></textarea>
            </div>
          </div>
          <div class="modal-footer">
            <button type="submit" class="case-worker-messenger__composer-send btn btn-primary pull-left" ng-disabled="caseWorkerMessageForm.$invalid">Send</button>
            <button type="button" class="case-worker-messenger__composer-cancel btn btn-link pull-left" ng-click="cancelCompose()">Cancel</button>
          </div>
        </form>
      </div>
    </div>
  </div>

</sec:authorize>
