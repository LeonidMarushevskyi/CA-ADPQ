module MessageHelper
  LIST_TYPES_MAP = {
    sent_messages: 'to',
    received_messages: 'from'
  }
  # list_type can be :sent_messages or :received_messages 
  def first_column_header(list_type)
    LIST_TYPES_MAP[list_type]
  end

  def msg_to(message)
    result = message.recipients.map(&:name).join(', ')
    string_30(result)
  end

  def msg_from(message)
    result = message.sender.name
    string_30(result)
  end

  def string_30(str)
    (str.size > 30)? str[0..27] + '...' : str
  end
end
