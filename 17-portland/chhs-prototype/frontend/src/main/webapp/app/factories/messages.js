'use strict';

angular.module('chhs').factory('messagesFactory', function ($http, $log, $q) {

  function createMessage(){
    return {
      toEmail: null,
      inReplyToId: null,
      subject: null,
      content: null,
    };
  }

  function getMessages() {
    return $http.get('/api/messages').then(function (response) {
      return response.data;
    });
  }

  function sendMessage(userMessage) {
    return $http.post('/api/messages', userMessage);
  }

  function deleteMessage(messageId) {
    $log.debug('Marking message ' + messageId + ' as deleted.');
    return $http.delete('/api/messages/' + messageId);
  }

  return {
    createMessage: createMessage,
    getMessages: getMessages,
    sendMessage: sendMessage,
    deleteMessage: deleteMessage
  };
});
